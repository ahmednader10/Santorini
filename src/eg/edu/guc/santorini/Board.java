package eg.edu.guc.santorini;

import java.util.ArrayList;

import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;


import eg.edu.guc.santorini.tiles.Piece;

import eg.edu.guc.santorini.utilities.Location;


public class Board implements BoardInterface  {
	
	private Player player1;
	private Player player2;
	private Location [][] boardLocations = new Location [SIDE][SIDE];
	
	
	public Board() {
		
	}
	
	public Board(Player p1, Player p2) {
		player1 = p1;
		player2 = p2;
		initializing();
		boardLocations[0][0].setPiece(true);
		boardLocations[4][1].setPiece(true);
		boardLocations[0][3].setPiece(true);
		boardLocations[4][4].setPiece(true);
		
		player1.getT1().setLocation(new Location(0, 0));
		player1.getT2().setLocation(new Location(4, 1));
		player2.getT1().setLocation(new Location(0, 3));
		player2.getT2().setLocation(new Location(4, 4));
		
		player1.getT1().setType(player1.getTileType());
		player1.getT2().setType(player1.getTileType());
		player2.getT1().setType(player2.getTileType());
		player2.getT2().setType(player2.getTileType());
		
		player1.setTurn(true);
		player2.setTurn(false);
	}
	
	public boolean canMove(Piece piece, Location location) {
		boolean contains = false;
		
		ArrayList<Location> places = piece.possibleMoves();
	
		for (int i = 0; i < places.size(); i++) {
			if (places.get(i).equals(location)) {
				 contains = true;
				 break;
			}
		}
		int xLoc = location.getxCoordinate();
		int yLoc = location.getyCoordinate();
		int xPiece = piece.getLocation().getxCoordinate();
		int yPiece = piece.getLocation().getyCoordinate();
		int pieceLevel = boardLocations[yPiece][xPiece].getLevel();
		int levelDiff = boardLocations[yLoc][xLoc].getLevel() - pieceLevel;
		if (contains && levelDiff < 2 && levelDiff > -3 
				&& !boardLocations[yLoc][xLoc].isPiece()) { 
			return true;
		}
		return false;
	}
	
	public boolean canPlace(Piece piece, Location location) {
		boolean contains = false;
		ArrayList<Location> places = piece.possiblePlacements();
		for (int i = 0; i < places.size(); i++) {
			if (places.get(i).equals(location)) {
				 contains = true;
				 break;
			}
		}
		int y1 = location.getyCoordinate();
		int x1 = location.getxCoordinate();
		if (contains && !boardLocations[y1][x1].isPiece()) {
			int xLoc = location.getxCoordinate();
			int yLoc = location.getyCoordinate();
			int locLevel = boardLocations[yLoc][xLoc].getLevel();
			return locLevel <= 3;
		}
		return false;
	}
	
	public void move(Piece piece, Location newLocation) throws InvalidMoveException {
		if (!canMove(piece, newLocation)) {
			throw new InvalidMoveException("Invalid.");
		}
		int xLoc = newLocation.getxCoordinate();
		int yLoc = newLocation.getyCoordinate();
		if ((player1.getT1().equals(piece) || player1.getT2().equals(piece))
				&& player1.isTurn()
				&& !isGameOver()) {
			Location temp = piece.getLocation();
			boardLocations[temp.getyCoordinate()][temp.getxCoordinate()].setPiece(false);
			boardLocations[yLoc][xLoc].setPiece(true);
			piece.setLocation(newLocation);
			player1.setTurn(false);
			player2.setTurn(true);
			piece.setJustMoved(true);
		}
		else {
			if ((player2.getT1().equals(piece) || player2.getT2().equals(piece))
					&& player2.isTurn()
					&& !isGameOver()) {
				Location temp = piece.getLocation();
				boardLocations[temp.getyCoordinate()][temp.getxCoordinate()].setPiece(false);
				boardLocations[yLoc][xLoc].setPiece(true);
				piece.setLocation(newLocation);
				player1.setTurn(true);
				player2.setTurn(false);
				piece.setJustMoved(true);
			}
			else {
				throw new InvalidMoveException("Invalid move.");
			}
		}
	}
	
	public void place(Piece piece, Location newLocation) throws InvalidPlacementException {
		if (!canPlace(piece, newLocation)) {
			throw new InvalidPlacementException("Invalid plcement.");
		}
		if (piece.isJustMoved() && !isGameOver()) {
			Location temp = newLocation;
			int x = boardLocations[temp.getyCoordinate()][temp.getxCoordinate()].getLevel();
			x += 1;
			boardLocations[temp.getyCoordinate()][temp.getxCoordinate()].setLevel(x);
			piece.setJustMoved(false);
		}
		else {
			throw new InvalidPlacementException("Invalid placement.");
		}
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	public Player getTurn() {
		
		if (getPlayer1().isTurn() && !player2.getT1().isJustMoved() 
				&& !player2.getT2().isJustMoved()) {
			return getPlayer1();
		}
			else {
				if (!player1.getT1().isJustMoved() && !player1.getT2().isJustMoved()) {
					return player2;
				}
				
		else {			
			return getPlayer1();
		}
		
			}
		
	}

	public void setBoardLocations(Location [][] boardLocations) {
		this.boardLocations = boardLocations;
	}

	public Location [][] getBoardLocations() {
		return boardLocations;
	}
	
	public boolean hasNoMoves(Player player) {
		ArrayList<Location> locations1 = player.getT1().possibleMoves();
		for (int i = 0; i < locations1.size(); i++) {
			if (canMove(player.getT1(), locations1.get(i))) {
				return false;
			}
		}
		ArrayList<Location> locations2 = player.getT2().possibleMoves();
		for (int j = 0; j < locations2.size(); j++) {
			if (canMove(player.getT2(), locations2.get(j))) {
				return false;
			}
		}
		
		return true;
	}
	
	public String [][] display() {
		String [][] r = new String[SIDE][SIDE];
		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				r[i][j] = boardLocations[i][j].getLevel() + "";
				if (player1.getT1().getLocation().equals(boardLocations[i][j]) 
						|| player1.getT2().getLocation().equals(boardLocations[i][j])) {
					int type = player1.getTileType();
					if (type == 1) {
						r[i][j] += "C1";
					}
					if (type == 2) {
						r[i][j] += "P1";
					}
				}
				if (player2.getT1().getLocation().equals(boardLocations[i][j]) 
						|| player2.getT2().getLocation().equals(boardLocations[i][j])) {
					int type = player2.getTileType();
					if (type == 1) {
						r[i][j] += "C2";
					}
					if (type == 2) {
						r[i][j] += "P2";
					}
				}
			}
		}
		return r;
	}

	public void initializing() {
		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				boardLocations[i][j] = new Location(i, j);
			}
		}
	}
	
	public boolean isGameOver() {
		if ((hasNoMoves(player1) && player1.isTurn()) || (hasNoMoves(player2) && player2.isTurn()) 
				|| isWinner(player1) 
				|| isWinner(player2)) {
				
			return true;
		}
		return false;
	}
	
	public Player getWinner() {
		if (isWinner(player1)) {
			return player1;
		}
		else {
			if (isWinner(player2)) {
				return player2;
			}
			else {
				return null;
			}
		}
	}
	
	public boolean isWinner(Player player) {
		int x1 = player.getT1().getLocation().getxCoordinate();
		int y1 = player.getT1().getLocation().getyCoordinate();
		int x2 = player.getT2().getLocation().getxCoordinate();
		int y2 = player.getT2().getLocation().getyCoordinate();
		if (boardLocations[y1][x1].getLevel() == 3 
				|| boardLocations[y2][x2].getLevel() == 3) {
 				
			
			return true;
		}
		else {
			if (player == player1 && hasNoMoves(player2) && player2.isTurn()) {
				return true;
			}
			else {
				if (player == player2 && hasNoMoves(player1) && player1.isTurn()) {
					return true;
				}
				
			}
		}
		return false;
	}
}

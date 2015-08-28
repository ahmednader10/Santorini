package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public abstract class Piece implements PieceInterface {
	
	private Location location;
	private int type;
	private boolean justMoved = false;
	
	public Piece() {
		
	}
	
	public Piece(Location l, int t) {
		location = l;
		type = t;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}
	
	/*public ArrayList<Location> possibleMoves() {
		ArrayList<Location> a1 = new ArrayList<Location>();
		int pieceLocationY = (this.getLocation()).getyCoordinate();
		int pieceLocationX = (this.getLocation()).getxCoordinate();
		if (this.type == 1) {
			if (pieceLocationY - 1 >= 0) {
				a1.add(new Location(pieceLocationY - 1, pieceLocationX));
			}
			if (pieceLocationX - 1 >= 0) {
				a1.add(new Location(pieceLocationY, pieceLocationX - 1));
			}
			if (pieceLocationY + 1 <= 4) {
				a1.add(new Location(pieceLocationY + 1, pieceLocationX));
			}
			if (pieceLocationX + 1 <= 4) {
				a1.add(new Location(pieceLocationY, pieceLocationX + 1));
			}
		}
		else {
			if (this.type == 2) {
				if (pieceLocationY - 1 >= 0 && pieceLocationX - 1 >= 0) {
					a1.add(new Location(pieceLocationY - 1, pieceLocationX - 1));
				}
				if (pieceLocationY - 1 >= 0 && pieceLocationX + 1 <= 4) {
					a1.add(new Location(pieceLocationY - 1, pieceLocationX + 1));
				}
				if (pieceLocationY + 1 <= 4 && pieceLocationX - 1 >= 0) {
					a1.add(new Location(pieceLocationY + 1, pieceLocationX - 1));
				}
				if (pieceLocationY + 1 <= 4 && pieceLocationX + 1 <= 4) {
					a1.add(new Location(pieceLocationY + 1, pieceLocationX + 1));
				}
			}
		}
		return a1;
	}
	*/
	public ArrayList<Location> possiblePlacements() {
		ArrayList<Location> a1 = new ArrayList<Location>();
		int pieceLocationY = (this.getLocation()).getyCoordinate();
		int pieceLocationX = (this.getLocation()).getxCoordinate();
		if (pieceLocationY - 1 >= 0) {
			a1.add(new Location(pieceLocationY - 1, pieceLocationX));
			if (pieceLocationX - 1 >= 0) {
				a1.add(new Location(pieceLocationY - 1, pieceLocationX - 1));
			}
			if (pieceLocationX + 1 <= 4) {
				a1.add(new Location(pieceLocationY - 1, pieceLocationX + 1));
			}
		}
		if (pieceLocationX - 1 >= 0) {
			a1.add(new Location(pieceLocationY, pieceLocationX - 1));
		}
		if (pieceLocationX + 1 <= 4) {
			a1.add(new Location(pieceLocationY, pieceLocationX + 1));
		}
		if (pieceLocationY + 1 <= 4) {
			a1.add(new Location(pieceLocationY + 1, pieceLocationX));
			if (pieceLocationX - 1 >= 0) {
				a1.add(new Location(pieceLocationY + 1, pieceLocationX - 1));
			}
			if (pieceLocationX + 1 <= 4) {
				a1.add(new Location(pieceLocationY + 1, pieceLocationX + 1));
			}
		}
		return a1;
	}

	public void setJustMoved(boolean justMoved) {
		this.justMoved = justMoved;
	}

	public boolean isJustMoved() {
		return justMoved;
	}
	
	public boolean equals(Piece p) {
		return this.getLocation().equals(p.getLocation()) && this.type == p.getType();
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
}

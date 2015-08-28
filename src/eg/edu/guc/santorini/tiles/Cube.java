package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Cube extends Piece {
	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> a1 = new ArrayList<Location>();
		int pieceLocationY = (this.getLocation()).getyCoordinate();
		int pieceLocationX = (this.getLocation()).getxCoordinate();
		
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
			return a1;
		}
	
}


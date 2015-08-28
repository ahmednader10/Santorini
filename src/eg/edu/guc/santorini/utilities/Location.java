package eg.edu.guc.santorini.utilities;

public class Location {
	
	private int yCoordinate;
	private int xCoordinate;
	private int level = 0;
	private boolean piece = false;
	
	
	public Location() {
		
	}
	
	public Location(int y, int x) {
		setyCoordinate(y);
		setxCoordinate(x);
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setPiece(boolean piece) {
		this.piece = piece;
	}

	public boolean isPiece() {
		return piece;
	}
	
	public boolean equals(Location l) {
		return this.getxCoordinate() == l.getxCoordinate() 
		&& this.getyCoordinate() == l.getyCoordinate(); 
	}

	
	
}

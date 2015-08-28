package eg.edu.guc.santorini.players;



import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;

import eg.edu.guc.santorini.tiles.Pyramid;


public class Player {
	
	private String name;
	private int tileType;
	private Piece t1;
	private Piece t2;
	private boolean turn;
	
	public Player() {
		
	}
	
	public Player(String n, int t) {
		name = n;
		tileType = t;
		if (tileType == 1) {
			t1 = new Cube();
			t2 = new Cube();
		} else {
			t1 = new Pyramid();
			t2 = new Pyramid();
		}
	}

	public boolean equals(Player x) {
		return this.name == x.getName() && this.tileType == x.getTileType();
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setTileType(int tileType) {
		this.tileType = tileType;
	}

	public int getTileType() {
		return tileType;
	}

	public void setT1(Piece piece1) {
		this.t1 = piece1;
	}

	public Piece getT1() {
		return t1;
	}

	public void setT2(Piece piece2) {
		this.t2 = piece2;
	}

	public Piece getT2() {
		return t2;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public boolean isTurn() {
		return turn;
	}

	
	
}

package eg.edu.guc.santorini.gui;
import javax.swing.Icon;
import javax.swing.JLabel;




@SuppressWarnings("serial")
public class Tile extends JLabel {
	
	private int x1;
	private int y1;
	private int layer;
	private boolean highlighted;
	private boolean piece;
	


	public Tile(Icon i, int x1, int y1) {
		super();
		layer = 0;
		setIcon(i);
		this.x1 = x1;
		this.y1 = y1;
	}

	

	public int getX1() {
		return x1;
	}



	public void setX1(int x1) {
		this.x1 = x1;
	}



	public int getY1() {
		return y1;
	}



	public void setY1(int y1) {
		this.y1 = y1;
	}



	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}



	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}



	public boolean isHighlighted() {
		return highlighted;
	}



	public void setPiece(boolean piece) {
		this.piece = piece;
	}



	public boolean isPiece() {
		return piece;
	}
}

package eg.edu.guc.santorini.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;



@SuppressWarnings("serial")
public class Window extends JFrame implements MouseListener, ActionListener {
	
	
	private Board board;
	private JButton start;
	private JPanel controlPanel, boardPanel;
	private Tile [][] theBoard;
	private JLabel label1, label2, label3, label4;
	private JTextField txt1, txt2, txt3, txt4;
	private String name1, type1, name2, type2;
	private Tile tempTile;
	private Piece tempPiece;
	
	
	
	
	public Window() {
		super("Santorini");
		setBounds(100, 0, 1000, 760);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theBoard = new Tile[5][5];
		boardPanel = new JPanel(null);
		getContentPane().add(boardPanel, BorderLayout.CENTER);
		boardPanel.setBackground(Color.cyan);
		controlPanel = new JPanel(new FlowLayout());
		getContentPane().add(controlPanel, BorderLayout.EAST);
		controlPanel.setSize(400, 1000);
		start = new JButton();
		start.setBounds(100, 200, 300, 100);
		controlPanel.add(start);
		start.setActionCommand("start");
		start.setText("start");
     	start.addMouseListener(this);
		start.addActionListener(this);
		initialBoardState();
		board = new Board();
	}
	
	public void createBoardPanel() {
		boardPanel.setLayout(new GridLayout(5, 5));
		boardPanel.setBackground(Color.WHITE);
		ImageIcon icon = new ImageIcon("New folder/0.png");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				theBoard[i][j] = new Tile(icon, i, j);
				theBoard[i][j].addMouseListener(this);
				boardPanel.add(theBoard[i][j]);
			}
		}
	}
	
	public void initialBoardState() {
		label1 = new JLabel("P1 name");
		label1.setLocation(90, 60);
		label1.setSize(150, 60);
		boardPanel.add(label1);
		txt1 = new JTextField();
		txt1.setLocation(90, 110);
		txt1.setSize(120, 30);
		boardPanel.add(txt1);
		
		label2 = new JLabel("1 for cubes/ 2 for pyramids");
		label2.setLocation(90, 150);
		label2.setSize(350, 60);
		boardPanel.add(label2);
		txt2 = new JTextField();
		txt2.setLocation(90, 200);
		txt2.setSize(50, 30);
		boardPanel.add(txt2);
		
		label3 = new JLabel("P2 name");
		label3.setLocation(90, 270);
		label3.setSize(150, 60);
		boardPanel.add(label3);
		txt3 = new JTextField();
		txt3.setLocation(90, 320);
		txt3.setSize(120, 30);
		boardPanel.add(txt3);
		
		label4 = new JLabel("1 for cubes/ 2 for pyramids");
		label4.setLocation(90, 370);
		label4.setSize(350, 60);
		boardPanel.add(label4);
		txt4 = new JTextField();
		txt4.setLocation(90, 420);
		txt4.setSize(50, 30);
		boardPanel.add(txt4);
		
		
	}
	
	public void newBoardPanel() {
		
		name1 = txt1.getText();
	    label1.setVisible(false);
		txt1.setVisible(false);
		type1 = txt2.getText();
		label3.setVisible(false);
		txt3.setVisible(false);
		name2 = txt3.getText();
	    label2.setVisible(false);
		txt2.setVisible(false);
	    txt4.setVisible(false);
	    label4.setVisible(false);
	    type2 = txt4.getText();
		boardPanel.removeAll();
		createBoardPanel();
		controlPanel.removeAll();
		positionOfPieces();
	}
	
	public void positionOfPieces() {
		int e1 = 0, e2 = 0;
		if (type1.length() != 0 && type2.length() != 0 
				&& (type1.equals("1") || type1.equals("2")) 
				&& (type2.equals("1") || type2.equals("2")) 
				&& (name1.length() != 0 && name2.length() != 0))
		{   
			e1 = Integer.parseInt(type1 + "");
			e2 = Integer.parseInt(type2 + "");
			}
		else
		{ 
			this.setVisible(false);
			JOptionPane.showMessageDialog(null, 
					"Please enter full data/valid numbers for both players! ");
			Window x = new Window();
			x.setVisible(true);
			x.txt1.setText(name1);
			x.txt2.setText(type1);
			x.txt3.setText(name2);
			x.txt4.setText(type2); }
		Player p1 = new Player(name1, e1);
		Player p2 = new Player(name2, e2);
		board = new Board(p1, p2);
		ImageIcon i1 = new ImageIcon("New folder/0C1.png");
		ImageIcon i2 = new ImageIcon("New folder/0P1.png");
		ImageIcon i3 = new ImageIcon("New folder/0C2.png");
		ImageIcon i4 = new ImageIcon("New folder/0P2.png");
		if (e1 == 1) {
			theBoard[0][0].setIcon(i1);
		    theBoard[4][1].setIcon(i1);
			i1 = new ImageIcon("New folder/0C1.png");
		}
		else {
			if (e1 == 2) {
				theBoard[0][0].setIcon(i2);
				theBoard[4][1].setIcon(i2);
				i2 = new ImageIcon("New folder/0P1.png");
			} }
		if (e2 == 1) {
			theBoard[0][3].setIcon(i3);
			theBoard[4][4].setIcon(i3);
			i3 = new ImageIcon("New folder/0C2.png");
		}
		else {
			if (e2 == 2) {
				theBoard[0][3].setIcon(i4);
				theBoard[4][4].setIcon(i4);
				i4 = new ImageIcon("New folder/0P2.png");
			} }	}
	
	public static void main(String[]args) {
		Window w1 = new Window();
		w1.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			tempTile = (Tile) e.getSource();
			int yTile = tempTile.getY1(); int xTile = tempTile.getX1();
			Location tile = new Location(xTile, yTile);
			if (e.getSource() == tempTile && containsPiece(tempTile)) {
			 yTile = tempTile.getY1(); xTile = tempTile.getX1();
		     tile = new Location(xTile, yTile);
		     Location[][] x = board.getBoardLocations();
		     int xPiece1 = board.getPlayer1().getT1().getLocation().getxCoordinate();
		     int yPiece1 = board.getPlayer1().getT1().getLocation().getyCoordinate();
		     if (tile.equals(x[yPiece1][xPiece1])) {
		    	 tempPiece = board.getPlayer1().getT1();  }
		     else {
			     int xPiece2 = board.getPlayer1().getT2().getLocation().getxCoordinate();
			     int yPiece2 = board.getPlayer1().getT2().getLocation().getyCoordinate();
		    	 if (tile.equals(x[yPiece2][xPiece2])) {
		    		 tempPiece = board.getPlayer1().getT2(); }
		    	 else {
				     int xPiece3 = board.getPlayer2().getT1().getLocation().getxCoordinate();
				     int yPiece3 = board.getPlayer2().getT1().getLocation().getyCoordinate();
		    		 if (tile.equals(x[yPiece3][xPiece3])) {
		    			 tempPiece = board.getPlayer2().getT1(); }
		    		 else {
		    		     int xPiece4 = board.getPlayer2().getT2().getLocation().getxCoordinate();
		    		     int yPiece4 = board.getPlayer2().getT2().getLocation().getyCoordinate();
		    		     if (tile.equals(x[yPiece4][xPiece4])) {
		    		    	 tempPiece = board.getPlayer2().getT2();  } } } } }
         if (tempPiece != null && e.getSource() == tempTile && containsPiece(tempTile)
				&& !tempPiece.isJustMoved()) {
			highlight(tempTile); } else {
			if (tempPiece != null && e.getSource() == tempTile && containsPiece(tempTile) 
					&& tempPiece.isJustMoved() 
					&& (board.getTurn().getT1() == tempPiece 
							|| board.getTurn().getT2() == tempPiece)) {
				highlightPlaces(theBoard[tempPiece.getLocation().getyCoordinate()]
				                         [tempPiece.getLocation().getxCoordinate()]);
			} else {
			if (tempPiece != null && e.getSource() == tempTile && !containsPiece(tempTile) 
					&& !tempPiece.isJustMoved() && (board.getTurn().getT1() == tempPiece 
					|| board.getTurn().getT2() == tempPiece)) {
				    movePiece(tempTile);
					highlightPlaces(tempTile); } 
			if (tempPiece != null && e.getSource() == tempTile 
					&& tempTile.isHighlighted() && tempPiece.isJustMoved() 
					&& (board.getTurn().getT1() == tempPiece 
					|| board.getTurn().getT2() == tempPiece)) {
				placeTile(tempTile); } } } }
		 		
	
	public void highlight(Tile tempTile) {
		int x = tempTile.getX1();
		int y = tempTile.getY1();
		String [][] display = board.display();
		ArrayList<Location> o1 = board.getPlayer1().getT1().possibleMoves();
		ArrayList<Location> o2 = board.getPlayer2().getT1().possibleMoves();
		ArrayList<Location> o3 = board.getPlayer1().getT2().possibleMoves();
		ArrayList<Location> o4 = board.getPlayer2().getT2().possibleMoves();
		if (board.getPlayer1().getT1().getLocation().equals(new Location(x, y))) {
			clearHighLighted();
			for (int i = 0; i < o1.size(); i++) {
				int x1 = o1.get(i).getxCoordinate();
				int y1 = o1.get(i).getyCoordinate();
				if (board.canMove(board.getPlayer1().getT1(), new Location(y1, x1))) {
					theBoard[y1][x1].
					setIcon(new ImageIcon("New folder/" + display[y1][x1] + "H" + ".png"));
					theBoard[y1][x1].setHighlighted(true);
					tempPiece = board.getPlayer1().getT1(); } } }
		else {  if (board.getPlayer2().getT1().getLocation().equals(new Location(x, y))) {
				clearHighLighted();
				for (int i = 0; i < o2.size(); i++) {
					int x1 = o2.get(i).getxCoordinate();
					int y1 = o2.get(i).getyCoordinate();
					if (board.canMove(board.getPlayer2().getT1(), new Location(y1, x1))) {
						theBoard[y1][x1].
						setIcon(new ImageIcon("New folder/" + display[y1][x1] + "H" + ".png"));
						theBoard[y1][x1].setHighlighted(true);
						tempPiece = board.getPlayer2().getT1(); 	} }	}
			else { if (board.getPlayer1().getT2().getLocation().equals(new Location(x, y))) {
					clearHighLighted();
					for (int i = 0; i < o3.size(); i++) {
						int x1 = o3.get(i).getxCoordinate();
						int y1 = o3.get(i).getyCoordinate();
						if (board.canMove(board.getPlayer1().getT2(), new Location(y1, x1))) {
							theBoard[y1][x1].
							setIcon(new ImageIcon("New folder/" + display[y1][x1] + "H" + ".png"));
							theBoard[y1][x1].setHighlighted(true);
							tempPiece = board.getPlayer1().getT2();
						} } }
				else {  if (board.getPlayer2().getT2().getLocation().equals(new Location(x, y))) {
						clearHighLighted();
						for (int i = 0; i < o4.size(); i++) {
							int x1 = o4.get(i).getxCoordinate();
							int y1 = o4.get(i).getyCoordinate();
							if (board.canMove(board.getPlayer2().getT2(), new Location(y1, x1))) {
							theBoard[y1][x1].
							setIcon(new ImageIcon("New folder/" + display[y1][x1] + "H" + ".png"));
							theBoard[y1][x1].setHighlighted(true);
							tempPiece = board.getPlayer2().getT2();
							} } } }	} } }
	
	public void highlightPlaces(Tile tempTile) {
		    if (containsPiece(tempTile) && tempPiece.isJustMoved()) {
	        int x = tempPiece.getLocation().getxCoordinate();
			int y = tempPiece.getLocation().getyCoordinate();
			String [][] display = board.display();
			ArrayList<Location> o1 = board.getPlayer1().getT1().possiblePlacements();
			ArrayList<Location> o2 = board.getPlayer2().getT1().possiblePlacements();
			ArrayList<Location> o3 = board.getPlayer1().getT2().possiblePlacements();
			ArrayList<Location> o4 = board.getPlayer2().getT2().possiblePlacements();
			if (board.getPlayer1().getT1().getLocation().equals(new Location(y, x))) {
				clearHighLighted();
	             for (int i = 0; i < o1.size(); i++) {
					int x1 = o1.get(i).getxCoordinate();
					int y1 = o1.get(i).getyCoordinate();
					if (board.canPlace(board.getPlayer1().getT1(), new Location(y1, x1))) {
						theBoard[y1][x1].
						setIcon(new ImageIcon("New folder/" + display[y1][x1] + "P" + ".png"));
						theBoard[y1][x1].setHighlighted(true);
					} } }
			else {
				if (board.getPlayer2().getT1().getLocation().equals(new Location(y, x))) {
					clearHighLighted();
					for (int i = 0; i < o2.size(); i++) {
						int x1 = o2.get(i).getxCoordinate();
						int y1 = o2.get(i).getyCoordinate();
						if (board.canPlace(board.getPlayer2().getT1(), new Location(y1, x1))) {
							theBoard[y1][x1].
							setIcon(new ImageIcon("New folder/" + display[y1][x1] + "P" + ".png"));
							theBoard[y1][x1].setHighlighted(true);
						} }	}
				else { if (board.getPlayer1().getT2().getLocation().equals(new Location(y, x))) {
					clearHighLighted();
					for (int i = 0; i < o3.size(); i++) {
						int x1 = o3.get(i).getxCoordinate();
						int y1 = o3.get(i).getyCoordinate();
						if (board.canPlace(board.getPlayer1().getT2(), new Location(y1, x1))) {
							theBoard[y1][x1].
							setIcon(new ImageIcon("New folder/" + display[y1][x1] + "P" + ".png"));
							theBoard[y1][x1].setHighlighted(true); } } }
				else {
					if (board.getPlayer2().getT2().getLocation().equals(new Location(y, x))) {
						clearHighLighted();
						for (int i = 0; i < o4.size(); i++) {
							int x1 = o4.get(i).getxCoordinate();
							int y1 = o4.get(i).getyCoordinate();
							if (board.canPlace(board.getPlayer2().getT2(), new Location(y1, x1))) {
							theBoard[y1][x1].
							setIcon(new ImageIcon("New folder/" + display[y1][x1] + "P" + ".png"));
							theBoard[y1][x1].setHighlighted(true);
							} } } } } } } }
	
	public void clearHighLighted() {
		
		String [][] display = board.display();
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (theBoard[i][j].isHighlighted()) {
					theBoard[i][j].setIcon(new ImageIcon("New folder/" + display[i][j] + ".png"));
				}
			}
		}
	}
	public boolean containsPiece(Tile tempTile) {
		int x = tempTile.getX1();
		int y = tempTile.getY1();
		Location [][] s = board.getBoardLocations();
		if (s[x][y].isPiece()) {
			return true;
		}
		return false;
	}
	
	public void movePiece(Tile tempTile) {
		int xPiece = tempPiece.getLocation().getxCoordinate();
		int yPiece = tempPiece.getLocation().getyCoordinate();
		if (tempTile.isHighlighted()) {
			try {
				board.move(tempPiece, new Location(tempTile.getX1(), tempTile.getY1()));
			}
			catch (Exception e) {
				return;
			}
	
	int xTile = tempTile.getX1();
	int yTile = tempTile.getY1();
	String[][] display = board.display();
	theBoard[yPiece][xPiece].
	setIcon(new ImageIcon("New folder/" + display[yPiece][xPiece] + ".png"));
	clearHighLighted();
	theBoard[tempTile.getX1()][tempTile.getY1()].
	setIcon(new ImageIcon("New folder/" + display[xTile][yTile] + ".png"));
	if (board.isWinner(board.getPlayer1()) || board.isWinner(board.getPlayer2())) {
		JOptionPane.showMessageDialog(null, board.getWinner().getName() + " won!");
		JOptionPane.showMessageDialog(null, "Restart game ?");
	    this.setVisible(false);
		Window x = new Window();
		x.setVisible(true);
	}
	tempPiece.setJustMoved(true);
	theBoard[yPiece][xPiece].setPiece(false);
	theBoard[tempTile.getX1()][tempTile.getY1()].setPiece(true);
	}
	}
	
	
	public void placeTile(Tile tempTile) {
		
				if (tempTile.isHighlighted()) {
				try {
					board.place(tempPiece, new Location(tempTile.getX1(), tempTile.getY1()));
				}
				catch (Exception e) {
					return;
				}
		clearHighLighted();
		int level = theBoard[tempTile.getX1()][tempTile.getY1()].getLayer();
		theBoard[tempTile.getX1()][tempTile.getY1()].setLayer(++level);
		theBoard[tempTile.getX1()]
		         [tempTile.getY1()].setIcon(new ImageIcon("New folder/" + level + ".png"));
		if (board.isWinner(board.getPlayer1()) || board.isWinner(board.getPlayer2())) {
			JOptionPane.showMessageDialog(null, board.getWinner().getName() + " won!");
			JOptionPane.showMessageDialog(null, "Restart game ?");
		    this.setVisible(false);
			Window x = new Window();
			x.setVisible(true);
		}
		
		tempPiece.setJustMoved(false);
		
		}
		}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() != start) {
			tempTile = (Tile) e.getSource();
			tempTile.setBorder(BorderFactory.createLoweredBevelBorder());
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (tempTile != null) {
			tempTile.setBorder(BorderFactory.createEmptyBorder());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (e.getActionCommand().equals("start")) {
			newBoardPanel();
		}
	}
	
}

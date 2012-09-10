/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jchess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import data.Board;
import data.Location;
import data.Piece;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ChessBoard extends javax.swing.JPanel {

	private int yLoc;
	private int xLoc;
	public static int scale = 40; // 28;
	public static int border = 0;

	private static boolean orientation = true;
	private Board board;
	private MoveController controller;
	private Piece selected;

	private boolean reversed = false;

	public ChessBoard(MoveList list) {
		setBounds(0, 0, 10 * scale, 10 * scale);
		setSize(10 * scale, 10 * scale);
		setVisible(true);
		setPreferredSize(new Dimension(10 * scale, 10 * scale));
		board = new Board();
		ChessBoardListener listener = new ChessBoardListener(this);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		controller = new MoveController(board, list);
	}

	/**
	 * 
	 */
	public void clearSelected() {
		// TODO Auto-generated method stub
		selected = null;
	}

	/**
	 * @param i
	 * @param j
	 */
	public Piece getPiece(int i, int j) {
		// TODO Auto-generated method stub
		int xPos = (i - 20) / scale;
		int yPos = (j - 20) / scale;
		if (xPos < 0 || xPos > 7 || yPos < 0 || yPos > 7) {
			return null;
		}
		return board.getPiece(xPos, yPos);
	}

	/**
	 * @param i
	 * @param j
	 */
	public void movePiece(int i, int j) {
		if (selected == null) {
			return;
		}
		// TODO Auto-generated method stub
		int xPos = (i - 20) / scale;
		int yPos = (j - 20) / scale;
		if (xPos < 0 || xPos > 7 || yPos < 0 || yPos > 7) {
			return;
		}
		Location loc = new Location(xPos, yPos);

		System.out.println("Trying to move " + selected + " to " + loc);
		controller.move(selected, loc);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {

					g.setColor(Color.ORANGE);
				} else {
					g.setColor(Color.RED);
				}
				g.fillRect(20 + i * scale, 20 + j * scale, scale - border,
						scale - border);
			}
		}
		int row = 8;
		char col = 'a';
		g.setColor(Color.WHITE);
		g.drawString("^v", 8, 16);
		for (int i = 1; i < 9; i++) {
			g.drawString(String.valueOf(row), 8, 10 + i * scale);
			g.drawString(String.valueOf(row--), (9 * scale) - 4, 10 + i * scale);
			g.drawString(String.valueOf(col), i * scale, 16);
			g.drawString(String.valueOf(col++), i * scale, 4 + (9 * scale));
		}

		Iterator pieces = board.getPieces().iterator();

		while (pieces.hasNext()) {
			Piece piece = (Piece) pieces.next();
			if (piece != selected) {
				piece.getImage().paintIcon(this, g,
						piece.getX() * scale + scale / 2,
						piece.getY() * scale + scale / 2);
			}
		}

		// paint selected last
		if (selected != null) {
			selected.getImage().paintIcon(this, g, xLoc, yLoc);
		}

		// repaint();

	}

	/**
	 * 
	 */
	public void reset() {
		// TODO Auto-generated method stub
		controller.reset();
		repaint();
	}

	public void reverseBoard() {
		reversed = !reversed;
	}

	/**
	 * @param i
	 * @param j
	 */
	public void setMouseLocation(MouseEvent e) {
		// TODO Auto-generated method stub
		if (selected == null) {
			return;
		}

		xLoc = e.getX();
		yLoc = e.getY();
		repaint();
	}

	/**
	 * @param piece
	 */
	public void setSelected(Piece piece) {
		if (piece == null) {
			selected = null;
			return;
		}
		if (piece.isWhite() != controller.isWhite()) {
			selected = null;
		} else {
			selected = piece;
		}

	}

}

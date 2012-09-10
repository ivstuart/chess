/*
 * Created on 06-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jchess;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import data.Piece;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ChessBoardListener extends MouseInputAdapter {

	private ChessBoard board; // Reference to chess board

	/**
	 * 
	 */
	public ChessBoardListener(ChessBoard chessBoard) {
		super();
		board = chessBoard;
		// TODO Auto-generated constructor stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		board.setMouseLocation(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		board.setMouseLocation(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// Have you clicked on one of you own pieces?
		Piece piece = board.getPiece(e.getX(), e.getY());

		board.setSelected(piece);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		board.movePiece(e.getX(), e.getY());
		board.clearSelected();
		board.repaint();
	}

}

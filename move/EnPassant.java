/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package move;

import data.Board;
import data.Location;
import data.Piece;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EnPassant extends Move {

	/**
	 * 
	 */
	public EnPassant(int horizontalChange, int verticalChange) {
		super(horizontalChange, verticalChange);

	}

	/**
	 * @return
	 */
	@Override
	public int getDx() {
		// TODO Auto-generated method stub
		return dx;
	}

	/**
	 * @return
	 */
	@Override
	public int getDy() {
		// TODO Auto-generated method stub
		return dy;
	}

	/**
	 * @param from
	 * @param to
	 */
	@Override
	public boolean isValid(Location from, Location to, Board board) {
		// TODO Auto-generated method stub
		int xMov = to.getXPos() - from.getXPos();
		int yMov = to.getYPos() - from.getYPos();

		System.out.println("xMov = " + xMov);
		System.out.println("yMov = " + yMov);

		Piece toCapture = board.getPiece(to.getXPos(), to.getYPos() - dy);

		System.out.println("To capture " + toCapture);

		if (toCapture != Move.getEnPassantPawn()) {
			System.out.println("Not enpassant pawn!");
			return false;
		}

		if (xMov != dx || yMov != dy) {
			System.out.println("Move does not match!");
			return false;
		}
		// exact match of move
		System.out.println("EnPassant Extact match!");

		return true;

	}

}

/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package move;

import data.Board;
import data.Location;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MoveNoCapture extends Move {

	protected int dx;
	protected int dy;

	/**
	 * 
	 */
	public MoveNoCapture(int horizontalChange, int verticalChange) {
		super(horizontalChange, verticalChange);
		dx = horizontalChange;
		dy = verticalChange;
		// TODO Auto-generated constructor stub
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

		System.out.println("Trace dx = " + dx);
		System.out.println("Trace dy = " + dy);

		if (xMov != dx || yMov != dy) {
			return false;
		}

		// Pawns can not capture when moving forwards

		if (board.getPiece(from.getXPos() + dx, from.getYPos() + dy) != null) {
			System.out.println("Piece there!");
			return false;
		}

		// exact match of move
		System.out.println("Move matches");
		return true;

	}

}

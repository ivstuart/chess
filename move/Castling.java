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
public class Castling extends Move {

	protected int numberOfSteps;

	public Castling(int horizontalChange, int verticalChange, int steps) {
		super(horizontalChange, verticalChange);

		numberOfSteps = steps;

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

		if (Math.abs(xMov) != 2 || yMov != 0) {
			System.out.println("King must move 2!");
			return false;
		}

		int xLoc = from.getXPos();
		int yLoc = from.getYPos();

		System.out.println("X location = " + xLoc);
		System.out.println("dX         = " + dx);

		// Check 3 left are empty or 2 right are empty

		int step = 1;
		for (; step < numberOfSteps; step++) {
			if (board.isPiece(xLoc + (dx * step), yLoc)) {
				System.out.println("Piece at" + (xLoc + (dx * step)));
				return false;
			}
		}
		step++;

		System.out.println("Rook at" + (xLoc + (dx * step)));

		Piece rook = board.getPiece(xLoc + (dx * step), yLoc);

		Location rookLoc = new Location(xLoc + dx, yLoc);
		board.setPiece(rook, rookLoc);

		// Need to move king 2 square right. Rook left 3 squares.

		return true;

	}

}

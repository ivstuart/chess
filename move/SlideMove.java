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
public class SlideMove extends Move {

	/**
	 * 
	 */
	public SlideMove(int horizontalChange, int verticalChange) {
		super(horizontalChange, verticalChange);

		// TODO Auto-generated constructor stub
	}

	/**
	 * @param from
	 * @param to
	 */
	@Override
	public boolean isValid(Location from, Location to, Board board) {
		System.out.println("Slide move called");
		// TODO Auto-generated method stub
		int xMov = to.getXPos() - from.getXPos();
		int yMov = to.getYPos() - from.getYPos();

		int dx2 = 0;
		int dy2 = 0;

		boolean slideThroughOtherPiece = false;

		for (int steps = 0; steps < 7; steps++) {

			dx2 += dx;
			dy2 += dy;

			System.out.println("SL dx2 = " + dx2);
			System.out.println("SL dx2 = " + dy2);

			// You can not slide over pieces
			if (slideThroughOtherPiece == true) {
				System.out.println("You can not slide through pieces");
				return false;
			}

			if (dx2 == xMov && dy2 == yMov) {
				return true;
			}

			int newX = from.getXPos() + dx2;
			int newY = from.getYPos() + dy2;

			if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
				return false;
			}

			if (board.getPiece(newX, newY) != null) {
				slideThroughOtherPiece = true;
			}
		}

		// No way to slide to move
		return false;

	}

}

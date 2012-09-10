/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package move;

import data.Board;
import data.Location;
import data.Pawn;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Move {

	protected static Pawn enPassantPawn;

	/**
	 * @return
	 */
	public static Pawn getEnPassantPawn() {
		return enPassantPawn;
	}

	/**
	 * @param pawn
	 */
	public static void setEnPassantPawn(Pawn pawn) {
		enPassantPawn = pawn;
	}

	protected int dx;

	protected int dy;

	public Move(int horizontalChange, int verticalChange) {
		super();
		dx = horizontalChange;
		dy = verticalChange;
	}

	/**
	 * @return
	 */
	public int getDx() {
		// TODO Auto-generated method stub
		return dx;
	}

	/**
	 * @return
	 */
	public int getDy() {
		// TODO Auto-generated method stub
		return dy;
	}

	/**
	 * @param from
	 * @param to
	 */
	public boolean isValid(Location from, Location to, Board board) {
		// TODO Auto-generated method stub
		int xMov = to.getXPos() - from.getXPos();
		int yMov = to.getYPos() - from.getYPos();

		System.out.println("xMov = " + xMov);
		System.out.println("yMov = " + yMov);

		if (xMov != dx || yMov != dy) {
			System.out.println("Move does not match!");
			System.out.println("xMov = " + xMov);
			System.out.println("dx = " + dx);
			System.out.println("yMov = " + yMov);
			System.out.println("dy = " + dy);
			return false;
		}
		// exact match of move
		System.out.println("Extact match!");
		return true;

	}

}

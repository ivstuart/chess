/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package data;

import move.Move;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Location {

	private int xPos;
	private int yPos;

	/**
	 * 
	 */
	public Location(int horizontal, int vertical) {
		super();
		xPos = horizontal;
		yPos = vertical;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param move
	 */
	public Location add(Move move) {
		// TODO Auto-generated method stub
		return new Location(xPos + move.getDx(), yPos + move.getDy());

	}

	public boolean equals(Location loc) {
		return (loc.getXPos() == xPos && loc.getYPos() == yPos);
	}

	/**
	 * @return
	 */
	public int getXPos() {
		return xPos;
	}

	/**
	 * @return
	 */
	public int getYPos() {
		return yPos;
	}

	@Override
	public String toString() {
		return (char) ('a' + xPos) + "" + (8 - yPos);
		// return "("+xPos+","+yPos+")";
	}

}

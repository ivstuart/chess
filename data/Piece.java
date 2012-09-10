/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package data;

import java.util.LinkedList;

import javax.swing.ImageIcon;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class Piece {

	public static Piece createPiece(Pawn pawn, String name) {
		Piece newPiece = null;
		try {
			newPiece = (Piece) Class.forName("data." + name).newInstance();
			newPiece.initialise(pawn.getLoc(), pawn.isWhite());

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newPiece;

	}

	protected ImageIcon image;
	protected Location loc;
	protected boolean hasMoved = false;
	protected LinkedList moves;

	protected boolean isWhite = true;

	public Piece() {
	}

	/**
	 * 
	 */
	public Piece(Location position, boolean whiteFlag) {
		super();
		loc = position;
		isWhite = whiteFlag;
		moves = new LinkedList();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param g
	 */
	public ImageIcon getImage() {

		return image;

	}

	/**
	 * @return
	 */
	public Location getLoc() {
		// TODO Auto-generated method stub
		return loc;
	}

	/**
	 * @return
	 */
	public LinkedList getMoves() {
		return moves;
	}

	public int getX() {
		return loc.getXPos();
	}

	public int getY() {
		return loc.getYPos();
	}

	public boolean hasMoved() {
		return hasMoved;
	}

	/**
	 * @param location
	 * @param b
	 */
	public abstract void initialise(Location location, boolean isWhite);

	public boolean isPawn() {
		return false;
	}

	/**
	 * @return
	 */
	public boolean isWhite() {
		return isWhite;
	}

	/**
	 * 
	 */
	public void moved() {
		// TODO Auto-generated method stub
		hasMoved = true;

	}

	/**
	 * @param to
	 */
	public void setLocation(Location to) {
		// TODO Auto-generated method stub
		loc = to;
	}

}

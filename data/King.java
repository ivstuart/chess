/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package data;

import java.net.URL;

import javax.swing.ImageIcon;

import move.Castling;
import move.Move;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class King extends Piece {

	private Castling leftSide;
	private Castling rightSide;

	/**
	 * 
	 */
	public King(Location loc, boolean flag) {
		super(loc, flag);

		// Load Graphics

		String filename = "/images/BK.gif";
		if (isWhite) {
			filename = "/images/WK.gif";
		}
		URL url = King.class.getResource(filename);

		image = url != null ? new ImageIcon(url) : new ImageIcon("null");
		if (image == null) {
			System.out.println("Could not load image from jar file: "
					+ filename + "\n");
			System.exit(-1);
		}

		// King can move 1 square in 8 directions
		moves.add(new Move(0, 1));
		moves.add(new Move(0, -1));
		moves.add(new Move(1, 0));
		moves.add(new Move(-1, 0));
		moves.add(new Move(1, 1));
		moves.add(new Move(1, -1));
		moves.add(new Move(-1, 1));
		moves.add(new Move(-1, -1));

		// Castling moves
		if (isWhite) {
			leftSide = new Castling(-1, 0, 3);
			rightSide = new Castling(1, 0, 2);
		} else {
			leftSide = new Castling(1, 0, 2);
			rightSide = new Castling(-1, 0, 3);
		}

		moves.add(leftSide);
		moves.add(rightSide);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data.Piece#initialise(data.Location, boolean)
	 */
	@Override
	public void initialise(Location location, boolean isWhiteFlag) {
		// TODO Auto-generated method stub
		loc = location;
		isWhite = isWhiteFlag;
	}

	@Override
	public void moved() {
		moves.remove(leftSide);
		moves.remove(rightSide);
	}

	public void remove(boolean isLeftSide) {
		if (isLeftSide) {
			moves.remove(leftSide);
		} else {
			moves.remove(rightSide);
		}
	}

}

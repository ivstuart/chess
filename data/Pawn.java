/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package data;

import java.net.URL;

import javax.swing.ImageIcon;

import move.EnPassant;
import move.Move;
import move.MoveMustCapture;
import move.MoveNoCapture;
import move.SlideMoveNoCapture;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Pawn extends Piece {

	private Move startMove;

	/**
	 * 
	 */
	public Pawn(Location loc, boolean flag) {
		super(loc, flag);
		int direction = 0;
		if (isWhite) {
			direction = -1;
		} else {
			direction = 1;
		}

		String filename = "/images/BP.gif";
		if (isWhite) {
			filename = "/images/WP.gif";
		}
		URL url = Pawn.class.getResource(filename);

		image = url != null ? new ImageIcon(url) : new ImageIcon("null");
		if (image == null) {
			System.out.println("Could not load image from jar file: "
					+ filename + "\n");
			System.exit(-1);
		}

		// Pawn can move 1 forwards and 2 on first move
		moves.add(new MoveNoCapture(0, direction));

		// No jumping over pieces.
		startMove = new SlideMoveNoCapture(0, direction);

		moves.add(startMove);

		// Capture diagonally
		moves.add(new MoveMustCapture(1, direction));
		moves.add(new MoveMustCapture(-1, direction));

		// EnPassant
		moves.add(new EnPassant(1, direction));
		moves.add(new EnPassant(-1, direction));

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
	public boolean isPawn() {
		return true;
	}

	@Override
	public void moved() {
		moves.remove(startMove);
	}

}

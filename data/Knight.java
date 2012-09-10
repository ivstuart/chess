/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package data;

import java.net.URL;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import move.Move;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Knight extends Piece {

	public static ImageIcon getImage(boolean isWhite) {
		String filename = "/images/BN.gif";
		if (isWhite) {
			filename = "/images/WN.gif";
		}
		URL url = Knight.class.getResource(filename);

		ImageIcon icon = url != null ? new ImageIcon(url) : new ImageIcon(
				"null");
		if (icon == null) {
			System.out.println("Could not load image from jar file: "
					+ filename + "\n");
			System.exit(-1);
		}
		return icon;
	}

	public Knight() {
	}

	/**
	 * 
	 */
	public Knight(Location loc, boolean flag) {
		initialise(loc, flag);

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
		image = getImage(isWhite);

		moves = new LinkedList();

		// Knight can move 3 squares in 8 fixed places
		moves.add(new Move(1, 2));
		moves.add(new Move(1, -2));
		moves.add(new Move(-1, 2));
		moves.add(new Move(-1, -2));
		moves.add(new Move(2, 1));
		moves.add(new Move(2, -1));
		moves.add(new Move(-2, 1));
		moves.add(new Move(-2, -1));

	}

}

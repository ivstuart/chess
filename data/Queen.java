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

import move.SlideMove;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Queen extends Piece {

	public static ImageIcon getImage(boolean isWhite) {
		String filename = "/images/BQ.gif";
		if (isWhite) {
			filename = "/images/WQ.gif";
		}
		URL url = Queen.class.getResource(filename);

		ImageIcon icon = url != null ? new ImageIcon(url) : new ImageIcon(
				"null");
		if (icon == null) {
			System.out.println("Could not load image from jar file: "
					+ filename + "\n");
			System.exit(-1);
		}
		return icon;
	}

	public Queen() {
	}

	/**
	 * 
	 */
	public Queen(Location loc, boolean flag) {
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

		// Queen can move any number of squares in 8 directions
		moves.add(new SlideMove(0, 1));
		moves.add(new SlideMove(0, -1));
		moves.add(new SlideMove(1, 0));
		moves.add(new SlideMove(-1, 0));
		moves.add(new SlideMove(1, 1));
		moves.add(new SlideMove(1, -1));
		moves.add(new SlideMove(-1, 1));
		moves.add(new SlideMove(-1, -1));
	}
}

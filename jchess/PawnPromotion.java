/*
 * Created on 07-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jchess;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import data.Bishop;
import data.Knight;
import data.Queen;
import data.Rook;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class PawnPromotion {

	JFrame frame;

	/**
	 * 
	 */
	public PawnPromotion(boolean isWhite, MoveController controller) {
		super();
		frame = new JFrame("Select Replacement Piece");

		Container pane = frame.getContentPane();

		JButton rook = new JButton(Rook.getImage(isWhite));
		JButton knight = new JButton(Knight.getImage(isWhite));
		JButton bishop = new JButton(Bishop.getImage(isWhite));
		JButton queen = new JButton(Queen.getImage(isWhite));

		rook.setName("Rook");
		knight.setName("Knight");
		bishop.setName("Bishop");
		queen.setName("Queen");

		pane.setLayout(new FlowLayout());

		pane.add(rook);
		pane.add(knight);
		pane.add(bishop);
		pane.add(queen);

		PromotionListener listener = new PromotionListener(controller);

		rook.addMouseListener(listener);
		knight.addMouseListener(listener);
		bishop.addMouseListener(listener);
		queen.addMouseListener(listener);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();

		frame.setVisible(true);
		// TODO Auto-generated constructor stub
	}

	public void close() {
		frame.dispose();
	}

}

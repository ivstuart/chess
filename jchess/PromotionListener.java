/*
 * Created on 07-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jchess;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class PromotionListener extends MouseAdapter {

	private MoveController controller;

	public PromotionListener(MoveController mc) {
		super();
		controller = mc;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		String name = ((JButton) e.getSource()).getName();

		controller.promoteTo(name);
	}

}

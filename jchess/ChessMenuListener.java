/*
 * Created on 09-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jchess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ChessMenuListener implements ActionListener {

	private ChessBoard chessBoard;

	/**
	 * @param board
	 */
	public ChessMenuListener(ChessBoard board) {
		super();
		chessBoard = board;
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if (command.equalsIgnoreCase("new")) {
			chessBoard.reset();
		}
	}

}

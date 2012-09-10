/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jchess;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class GameGUI {

	JFrame frame;
	Container pane;

	ChessBoard board;

	public GameGUI() {

		// 1. Optional: Specify who draws the window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);

		// 2. Create the frame.
		frame = new JFrame("IvanChess v0.1");

		pane = frame.getContentPane();

		// 3. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.init();

		menu();

		// 5. Size the frame.
		frame.pack();

		// 6. Show it.
		frame.setVisible(true);

	}

	private void init() {

		MoveList list = new MoveList();
		board = new ChessBoard(list);

		pane.add(board, BorderLayout.WEST);
		pane.add(new JLabel("MoveList"));
		pane.add(list.getContainer(), BorderLayout.EAST);

	}

	private void menu() {
		JMenuBar menuBar = new JMenuBar();
		ChessMenuListener listener = new ChessMenuListener(board);

		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);

		JMenuItem open = new JMenuItem("Open");
		open.setMnemonic(KeyEvent.VK_O);

		JMenuItem save = new JMenuItem("Save");
		save.setMnemonic(KeyEvent.VK_S);

		JMenuItem newGame = new JMenuItem("New");
		newGame.setMnemonic(KeyEvent.VK_N);
		newGame.addActionListener(listener);

		JMenuItem quit = new JMenuItem("Quit");
		quit.setMnemonic(KeyEvent.VK_Q);

		menu.add(newGame);
		menu.add(open);
		menu.add(save);
		menu.addSeparator();
		menu.add(quit);

		menuBar.add(menu);

		frame.setJMenuBar(menuBar);

		// Menu section 2

		JMenu menu2 = new JMenu("Options");
		menu2.setMnemonic(KeyEvent.VK_O);

		JMenuItem rollback = new JMenuItem("Play From View");
		JMenuItem edit = new JMenuItem("Edit View");

		JCheckBoxMenuItem bAI = new JCheckBoxMenuItem("Black AI");
		JCheckBoxMenuItem wAI = new JCheckBoxMenuItem("White AI");

		JCheckBoxMenuItem show = new JCheckBoxMenuItem("Show Moves");
		JCheckBoxMenuItem timer = new JCheckBoxMenuItem("Timer");

		menu2.add(rollback);
		menu2.add(edit);
		menu2.add(bAI);
		menu2.add(wAI);
		menu2.add(show);
		menu2.add(timer);

		menuBar.add(menu2);
	}

}

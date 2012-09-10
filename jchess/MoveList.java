/*
 * Created on 06-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jchess;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MoveList {

	private DefaultListModel model;
	private JScrollPane scrollPane;

	/**
	 * model.removeElementAt model.addElement
	 */
	public MoveList() {
		super();
		model = new DefaultListModel();

		JList list = new JList(model);

		scrollPane = new JScrollPane(list);

		scrollPane.setPreferredSize(new Dimension(100, 400));

		// scrollPane.setBorder(null);

	}

	public void add(Object obj) {
		model.addElement(obj);
	}

	/**
	 * 
	 */
	public void clear() {
		// TODO Auto-generated method stub
		model.clear();
	}

	public JScrollPane getContainer() {
		return scrollPane;
	}

	public void remove(int index) {
		model.remove(index);
	}

}

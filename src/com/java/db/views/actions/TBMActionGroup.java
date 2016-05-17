/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     TBMActionGroup.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-28   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.views.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;

/**
 *  ���ڹ���������actiongroup 
 */
public class TBMActionGroup extends AbstractActionGroup
{

	public TBMActionGroup(TreeViewer tv)
	{
		super(tv);
	}

	@Override
	public void fillContextMenu(IMenuManager menu)
	{
		MenuManager mm = (MenuManager) menu;
		mm.add(new CreateTable());
		super.fillContextMenu(menu);
	}
	
	/**
	 * ˢ�±�s
	 * @see com.java.db.views.actions.AbstractActionGroup#refresh()
	 */
	@Override
	public void refresh()
	{
	}
	
	/**
	 * �½���
	 */
	class CreateTable extends Action
	{
		public CreateTable()
		{
			setText("�½���");
		}
		
		@Override
		public void run()
		{
			treeViewer.refresh();
		}
	}
}

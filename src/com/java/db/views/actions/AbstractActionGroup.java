/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     AbstractActionGroup.java
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
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionGroup;

import com.java.db.DBActivator;

/**
 * ���view�д����ĳ���ActionGroup
 */
public abstract class AbstractActionGroup extends ActionGroup
{
	protected TreeViewer treeViewer;
	protected Shell activeShell;
	protected Object selectElement;
	
	public AbstractActionGroup(TreeViewer tv)
	{
		treeViewer = tv;
		activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		selectElement = ((IStructuredSelection)treeViewer.getSelection()).getFirstElement();
	}

	/**
	 * �Ѿ���������Ӳ˵���treeViewer��
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillContextMenu(IMenuManager menu)
	{
		menu.add(new Separator());		// �ָ�
		menu.add(new Refresh());		// ���ˢ��
		super.fillContextMenu(menu);
	}
	
	/**
	 * ˢ������
	 */
	class Refresh extends Action
	{
		public Refresh()
		{
			setText("ˢ��");
			
			// ����ͼƬ
			ImageDescriptor imageDescriptor = DBActivator.getImageDescriptor("icons/refresh.gif");
			setImageDescriptor(imageDescriptor);
			setHoverImageDescriptor(imageDescriptor);
		}
		
		@Override
		public void run()
		{
			refresh();
		}
	}
	
	/**
	 * ����ˢ��action
	 */
	public abstract void refresh();
}

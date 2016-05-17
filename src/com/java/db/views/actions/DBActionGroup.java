/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     DBActionGroup.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-27   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.views.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.MessageBox;

import com.java.db.actions.OpenSQLQueryEditor;
import com.java.db.conn.ConnControl;
import com.java.db.model.DBModel;

/**
 * �������ݿ��actiongroup
 */
public class DBActionGroup extends AbstractActionGroup
{
	private DBModel selection;
	public DBActionGroup(TreeViewer tv)
	{
		super(tv);
		selection = (DBModel) ((IStructuredSelection)treeViewer.getSelection()).getFirstElement();
	}
	
	/**
	 * ��������Ĳ˵�
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager menu)
	{
		MenuManager mm = (MenuManager) menu;
		mm.add(new AddDB(selection.getParent()));			// �½����ݿ�
		mm.add(new DelDB());								// ɾ�����ݿ�
		mm.add(new Separator());
		mm.add(new PropertyDB());							// ��������
		mm.add(new OpenSQLQueryEditor(
				selection.getParent().getConnControl()));	// �򿪱༭����
		super.fillContextMenu(menu);
	}
	
	/**
	 * ɾ�����ݿ�
	 */
	class DelDB extends Action
	{
		public DelDB()
		{
			setText("ɾ�����ݿ�");
		}
		
		@Override
		public void run()
		{
			ConnControl connControl = selection.getParent().getConnControl();
			if(MessageDialog.openConfirm(activeShell, "ȷ��ɾ��", "��ȷ���Ƿ�ɾ�������ݿ⣿"))
			{
				if(connControl.execute("DROP DATABASE " +  selection.getDb().getDBName()))
				{
					selection.getParent().remove(selection);	// ɾ�����ݿ�
					treeViewer.refresh(); 					// ˢ��treeviewer
				}
				else
				{
					MessageBox mb = new MessageBox(activeShell);
					mb.setText("��ʾ");
					mb.setMessage("ɾ��ʧ��!");
					mb.open();
				}
			}
		}
	}
	
	/**
	 * ���ݿ�����
	 */
	class PropertyDB extends Action
	{
		public PropertyDB()
		{
			setText("���ݿ�����");
		}
		
		@Override
		public void run()
		{
			MessageBox mb = new MessageBox(activeShell);
			mb.setText("��ʾ");
			mb.setMessage("���ݿ�����!");
			mb.open();
			return;
		}
	}
	
	/**
	 * ˢ�����ݿ��ڵ���Ϣ
	 */
	@Override
	public void refresh()
	{
		selection.getParent().getConnControl().refreshDB(selection);
		treeViewer.refresh();
	}
}

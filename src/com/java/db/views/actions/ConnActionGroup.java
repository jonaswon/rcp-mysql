/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     ConnActionGroup.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-26   Slive      V001.00		�����ļ�
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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.java.db.actions.OpenSQLQueryEditor;
import com.java.db.editors.SQLEditor;
import com.java.db.editors.SQLInput;
import com.java.db.model.ConnectModel;
import com.java.db.model.ConnectModelManager;
import com.java.db.model.util.ConnUtil;
import com.java.db.util.DBSystemConstant;

/**
 * �������ӵ�ActionGroup��selection ���� @ConnectModel��
 */
public class ConnActionGroup extends AbstractActionGroup
{
	private ConnectModel selection;

	public ConnActionGroup(TreeViewer tv)
	{
		super(tv);
		selection = (ConnectModel) ((IStructuredSelection)treeViewer.getSelection()).getFirstElement();
	}
	
	
	/**
	 * ��������Ĳ˵�
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillContextMenu(IMenuManager menu)
	{
		MenuManager mm = (MenuManager) menu;
		mm.add(new OpenConn(treeViewer));			// ������
		mm.add(new CloseConn());		// �ر�����
		mm.add(new Separator());
		mm.add(new DelConn());			// ɾ������
		mm.add(new Separator());
		mm.add(new AddDB(selection));	// �½����ݿ�
		mm.add(new Separator());
		mm.add(new PropertyConn());		// ��������
		mm.add(new OpenSQLQueryEditor(
				selection.getConnControl())); // �򿪱༭����
		super.fillContextMenu(menu);
	}
	
	/**
	 * �ر�����
	 */
	class CloseConn extends Action
	{
		public CloseConn()
		{
			setText("�ر�����");
			if(selection.getConnControl().isConnected())
			{
				setEnabled(true);	// �Ѿ����Ϻ󣬲ſ��Թر�
			}
			else
			{
				setEnabled(false);
			}
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public void run()
		{
			selection.getConnControl().closeConn();
			treeViewer.refresh();
			
			// �ر���صĲ�ѯ����
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IEditorPart[] parts = activePage.getEditors();
			for (int i = 0; i < parts.length; i++)
			{
				IEditorPart editorPart = parts[i];
				
				// �����ظ���
				if(editorPart instanceof SQLEditor)
				{
					SQLInput editorInput = (SQLInput) editorPart.getEditorInput();
					if(editorInput.getConnControl().equals(selection.getConnControl()))	// ͬһ�����ӣ�����Ҫ��
					{
						// �ر�
						activePage.closeEditor(editorPart, false);
						return ;
					}
				}
			}
		}
	}
	
	/**
	 * ɾ������
	 */
	class DelConn extends Action
	{
		public DelConn()
		{
			setText("ɾ������");
		}
		
		@Override
		public void run()
		{
			if(MessageDialog.openConfirm(activeShell, "ȷ��ɾ��", "��ȷ���Ƿ�ɾ�������ӣ�"))
			{
				// �ȹر�
				selection.getConnControl().closeConn();
				// ����漰���ļ��洢������ɾ���ļ�
				ConnectModelManager.getInstance().remove(selection);
				ConnUtil.removeConn(selection, DBSystemConstant.CONN_PATH);
				if(ConnectModelManager.getInstance().getChildren() == null)	// ��ֹ��ʾΪ���������
				{
					treeViewer.setInput(null);
				}
				treeViewer.refresh();
			}
		}
	}
	
	/**
	 * ��������
	 */
	class PropertyConn extends Action
	{
		public PropertyConn()
		{
			setText("��������");
		}
		
		@Override
		public void run()
		{
			MessageBox mb = new MessageBox(activeShell);
			mb.setText("��ʾ");
			mb.setMessage("��������");
			mb.open();
			return;
		}
	}

	@Override
	public void refresh()
	{
		selection.getConnControl().refreshConn();
		treeViewer.refresh();
	}
}

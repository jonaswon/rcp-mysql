package com.java.db.views.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.java.db.model.ConnectModel;

/**
 * �����ݿ�����
 */
public class OpenConn extends Action
{
	private TreeViewer treeViewer;
	private ConnectModel selection;
	private Shell activeShell;

	public OpenConn(TreeViewer tv)
	{
		treeViewer = tv;
		selection = (ConnectModel) ((IStructuredSelection)treeViewer.getSelection()).getFirstElement();
		activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		
		setText("������");
		if(selection.getConnControl().isConnected())
		{
			setEnabled(false);	// ���Ϻ󣬽���
		}
		else
		{
			setEnabled(true);
		}
	}
	
	@Override
	public void run()
	{
		if(selection.getConnControl().openConn())
		{
			// ˢ��
			treeViewer.refresh();
			treeViewer.expandToLevel(2);	// չ���������˵�
			setEnabled(false);
		}
		else
		{
			MessageBox mb = new MessageBox(activeShell);
			mb.setText("��ʾ");
			mb.setMessage("����ʧ��!");
			mb.open();
			setEnabled(true);
		}
	}
}
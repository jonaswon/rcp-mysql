package com.java.db.views.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;

import com.java.db.model.ConnectModel;

/**
 * ������ݿ�
 */
public class AddDB extends Action
{
	ConnectModel selection;
	public AddDB(ConnectModel selection)
	{
		setText("������ݿ�");
		this.selection = selection;
		if(!selection.getConnControl().isConnected())
		{
			setEnabled(false);
		}
	}
	
	@Override
	public void run()
	{
		AddDBDialog addDBDialog = new AddDBDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().
				getShell(),SWT.CLOSE,selection);
		addDBDialog.open();
	}
}
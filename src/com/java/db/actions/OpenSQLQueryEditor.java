/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     OpenConnectDialog.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-26   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.java.db.conn.ConnControl;
import com.java.db.editors.SQLEditor;
import com.java.db.editors.SQLInput;

/**
 * �򿪶����Ĳ�ѯ�༭��
 */
public class OpenSQLQueryEditor extends Action
{
	public ConnControl connControl;
	public OpenSQLQueryEditor(ConnControl connControl)
	{
		setText("��ѯ����");
		setToolTipText("�򿪲�ѯ����");
		this.connControl = connControl;
		if(!connControl.isConnected())
		{
			setEnabled(false);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run()
	{
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable(){

			@Override
			public void run()
			{
				IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IEditorPart[] parts = activePage.getEditors();
				for (int i = 0; i < parts.length; i++)
				{
					IEditorPart editorPart = parts[i];
					
					// �����ظ���
					if(editorPart instanceof SQLEditor)
					{
						SQLInput editorInput = (SQLInput) editorPart.getEditorInput();
						if(editorInput.getConnControl().equals(connControl))	// ͬһ�����ӣ�����Ҫ��
						{
							// ǰ����ʾ
							activePage.bringToTop(editorPart);
							return ;
						}
					}
				}
				
				// ��editor
				SQLInput slqInput = new SQLInput();
				slqInput.setConnControl(connControl);
				try
				{
					activePage.openEditor(slqInput, SQLEditor.ID);
				}
				catch (PartInitException e)
				{
					e.printStackTrace();
				}
				
			}});
	}
}

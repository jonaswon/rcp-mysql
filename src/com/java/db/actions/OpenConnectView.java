/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     OpenConnectView.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-25   Slive    V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.PlatformUI;

import com.java.db.DBActivator;
import com.java.db.views.ConnectManagerView;


public class OpenConnectView extends Action
{
	
	public OpenConnectView()
	{
		super();
		setText("������ͼ");
		setToolTipText("��������ͼ");
		
		// ����ͼƬ
		ImageDescriptor imageDescriptor = DBActivator.getImageDescriptor("icons/connectmanger.gif");
		setImageDescriptor(imageDescriptor);
		setHoverImageDescriptor(imageDescriptor);
		
	}
	
	@Override
	public void run()
	{
		try
		{
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage().showView(ConnectManagerView.ID);
		}
		catch (Exception e)
		{
		}
		super.run();
	}
}

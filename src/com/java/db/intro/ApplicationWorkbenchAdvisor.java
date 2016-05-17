
package com.java.db.intro;

import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor
{

	private static final String PERSPECTIVE_ID = "com.java.db.perspective";

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer)
	{
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	public void initialize(IWorkbenchConfigurer configurer)
	{
		super.initialize(configurer);
		configurer.setSaveAndRestore(true);
		
		// ��ȡƽ̨UI�Ĵ洢���ԣ����ı䡰IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS��ֵ
		PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, false);
		
		// ����͸��ͼ�����
        PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR
        										, true);
	}

	public String getInitialWindowPerspectiveId()
	{
		return PERSPECTIVE_ID;
	}
}

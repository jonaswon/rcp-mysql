
package com.java.db.intro;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.java.db.actions.OpenConnectView;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor
{

	private IWorkbenchAction introAction;
	private OpenConnectView openConnectView;
	
	// window actions
	private IContributionItem wOpenPerspectives;	// ��͸��ͼ
	private IContributionItem wShowViews;			// ��view�б�
	private IWorkbenchAction wPreferences;			// ����ѡ��

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer)
	{
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window)
	{
		introAction = ActionFactory.INTRO.create(window);
		register(introAction);
		
		openConnectView = new OpenConnectView();
		
		// ��ʼ��&Window�˵��е�actions
		wOpenPerspectives = ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(window);
		wShowViews = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
		wPreferences = ActionFactory.PREFERENCES.create(window);
		wPreferences.setText("��ѡ��");
		register(wPreferences);
	}

	protected void fillMenuBar(IMenuManager menuBar)
	{
		// �ļ��˵�
		MenuManager fileMenu = new MenuManager("&�ļ�",IWorkbenchActionConstants.M_FILE);
		menuBar.add(fileMenu);
		
		// ���߲˵�
		MenuManager toolMenu = new MenuManager("&����");
		menuBar.add(toolMenu);
		toolMenu.add(openConnectView);
		
		// windowMenu
		MenuManager windowMenu = new MenuManager("&����",IWorkbenchActionConstants.M_WINDOW);
		menuBar.add(windowMenu);
		MenuManager perspectiveMenu = new MenuManager("�� &͸��ͼ");
		windowMenu.add(perspectiveMenu);
		perspectiveMenu.add(wOpenPerspectives);
		MenuManager showViewMenu = new MenuManager("��ʾ &View");
		windowMenu.add(showViewMenu);
		showViewMenu.add(wShowViews);
		windowMenu.add(wPreferences);
		
		// �����˵�
		MenuManager helpMenu = new MenuManager("&����");
		menuBar.add(helpMenu);

		// Help
		helpMenu.add(introAction);
	}

}

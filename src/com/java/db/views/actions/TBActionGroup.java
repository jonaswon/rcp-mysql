/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     TBActionGroup.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-28   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.views.actions;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import com.java.db.conn.ConnControl;
import com.java.db.model.TableModel;
import com.java.db.model.dbbeans.TBDatas;
import com.java.db.views.QueryResultView;

/**
 * ���ڵ�������actiongroup
 */
public class TBActionGroup extends AbstractActionGroup
{
	private TableModel selection;
	private ConnControl conn;
	public TBActionGroup(TreeViewer tv)
	{
		super(tv);
		selection = (TableModel) ((IStructuredSelection)treeViewer.getSelection()).getFirstElement();
		conn = selection.getParent().getParent().getParent().getConnControl();
	}

	@Override
	public void fillContextMenu(IMenuManager menu)
	{
		MenuManager mm = (MenuManager) menu;
		mm.add(new OpenTable());
		mm.add(new DelTable());
		super.fillContextMenu(menu);
	}
	
	/**
	 * �򿪱�
	 */
	class OpenTable extends Action
	{
		public OpenTable()
		{
			setText("�򿪱�");
			setToolTipText("���ز�ѯ���");
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void run()
		{
			String sql = "use " + selection.getParent().getParent().getName() + "\r\n";
			conn.execute(sql);	// ��ѡ�����ݿ⣬ִ�в�ѯ
			sql = "select * from " + selection.getName();
			ResultSet rs = conn.executeQuery(sql);	// ���ز�ѯ���
			TBDatas datas = selection.getDatas();
			datas.clear();
			if(rs == null)
			{
				MessageBox mb = new MessageBox(activeShell);
				mb.setText("��ʾ");
				mb.setMessage("��ѯ����!");
				mb.open();
				return;
			}
			else
			{
				
				ResultSetMetaData rsData;
				try
				{
					
					rsData = rs.getMetaData();
					int count = rsData.getColumnCount();
					String[] colNames = new String[count];
					ArrayList<String>[] als = new ArrayList[count];
					for (int i = 1; i <= count; i++)
					{
						colNames[i - 1] = rsData.getColumnName(i);	// ��ȡ����
						als[i - 1] = new ArrayList<String>();
						datas.add(colNames[i - 1], als[i - 1]);
					}
					
					
					// ��ȡ����
					while(rs.next())
					{
						for (int i = 1; i <= count; i++)
						{
							String getStr = rs.getString(i);
							if(getStr != null)
							{
								als[i - 1].add(getStr);
							}
							else
							{
								als[i - 1].add("NULL");
							}
						}
						try
						{
							datas.setCount(als[0].size());
						}
						catch (Exception e)
						{
							// TODO: handle exception
						}
					}
					
					
					// ����view
					IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					IViewPart vp = activePage.findView(QueryResultView.ID);
					if(vp == null)
					{
						try
						{
							vp = activePage.showView(QueryResultView.ID);
						}
						catch (PartInitException e)
						{
							e.printStackTrace();
						}
					}
					((QueryResultView)vp).updateDatas(datas,selection.getName());
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ɾ����
	 */
	class DelTable extends Action
	{
		public DelTable()
		{
			setText("ɾ����");
		}
		
		@Override
		public void run()
		{
			if(MessageDialog.openConfirm(activeShell, "ȷ��ɾ��", "��ȷ���Ƿ�ɾ���ñ�"))
			{
				String sql = "use " + selection.getParent().getParent().getName() + "\r\n";
				conn.execute(sql);	// ��ѡ�����ݿ⣬ִ�в�ѯ
				if(!conn.execute("drop table " + selection.getName()))
				{
					MessageBox mb = new MessageBox(activeShell);
					mb.setText("��ʾ");
					mb.setMessage("ɾ��������!");
					mb.open();
					return;
				}
				else
				{
					selection.getParent().remove(selection);
				}
				treeViewer.refresh();
			}
		}
	}

	@Override
	public void refresh()
	{
		// �����Ѿ���������
		conn.refreshTB(selection.getParent());
		treeViewer.refresh();
	}
}

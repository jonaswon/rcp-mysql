/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     Conn.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-27   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.conn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.db.model.ConnectModel;
import com.java.db.model.DBModel;
import com.java.db.model.FunModel;
import com.java.db.model.FunModelManager;
import com.java.db.model.PreModel;
import com.java.db.model.PreModelManager;
import com.java.db.model.TBModelManager;
import com.java.db.model.TableModel;
import com.java.db.model.ViewModel;
import com.java.db.model.ViewModelManager;
import com.java.db.model.connectbeans.GeneralInforBean;

/**
 * �������ݣ��������ݿ��
 */
public class ConnControl implements Serializable
{
	/**
	 * Ĭ��ID
	 */
	private static final long serialVersionUID = -1978392618854565537L;
	/**
	 * ���ݿ�����ģ�ͣ����ԡ�������
	 */
	private ConnectModel connModel;
	private boolean isConnected; 	// ����״̬
	private Connection conn;
	private Statement statememt;			// ���ݼ���

	public ConnControl(ConnectModel conn)
	{
		this.connModel = conn;
	}
	
	/**
	 * ��ȡ���ݿ�����ģ��
	 */
	public ConnectModel getConnModel()
	{
		return connModel;
	}
	
	/**
	 * ��ȡ����
	 */
	public Connection getConn()
	{
		return conn;
	}
	
	/**
	 * ����״̬����Ϊ��������ʱ�����ⲻ�������޸���������
	 * @return
	 */
	public boolean isConnected()
	{
		return isConnected;
	}
	
	/**
	 * ��ȡStatement
	 * @return
	 */
	public Statement getStatememt()
	{
		return statememt;
	}
	
	/**
	 * ��������
	 * @return
	 */
	public boolean openConn()
	{
		if(isConnected)
		{
			return false;	// �Ѿ�����������Ҫ��������
		}
		else
		{
			//�ȹر�
			closeConn();
			try
			{
				Class.forName("com.mysql.jdbc.Driver");	// 1. ע������
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
			// Mysql ������
			GeneralInforBean generalInfor = connModel.getConnect().getGeneralInfor();
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://" 
						+ generalInfor.getHostName() + ":" + generalInfor.getPort(),
						generalInfor.getUserName(),generalInfor.getPwd());
				statememt = conn.createStatement();
				refreshConn();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				closeConn();	// �رգ���ֹй¶
				return false;
			}	
			isConnected = true;
			return true;
		}
	}
	
	/**
	 * ˢ������
	 */
	public void refreshConn()
	{
		String[][] strings = getShowInfor("show databases", null);
		if(strings != null)
		{
			connModel.removeAll();
			for (int i = 0; i < strings.length; i++)
			{
				DBModel dbModel = new DBModel(strings[i][0]);
				connModel.add(dbModel);
				refreshDB(dbModel);
			}
		}
	}
	/**
	 * ˢ�����ݿ�
	 */
	public void refreshDB(DBModel dbModel)
	{
		refreshTB(dbModel.getTbManger());	// ˢ�±��
		refreshVB(dbModel.getViewManger());	// ˢ����ͼ�ȵȣ�
		refreshPre(dbModel.getPreModelManager());	// ˢ�´洢����
		refreshFun(dbModel.getFunModelManager());	// ˢ�º���
	}
	
	/**
	 * ˢ�±��
	 */
	public void refreshTB(TBModelManager tbManager)
	{
		String[][] strings = getShowInfor("show full tables from " 
				+ tbManager.getParent().getName(), tbManager.getParent());
		if(strings != null)
		{
			tbManager.removeAll();
			for (int j = 0; j < strings.length; j++)
			{
				// ����view
				if(!strings[j][1].equalsIgnoreCase("view"))
				{
					tbManager.add(new TableModel(strings[j][0]));
				}
			}
		}
	}
	
	/**
	 * ˢ����ͼ
	 */
	public void refreshVB(ViewModelManager vbManager)
	{
		String[][] strings = getShowInfor("show full tables from " + 
				vbManager.getParent().getName(), vbManager.getParent());
		if(strings != null)
		{
			vbManager.removeAll();
			for (int j = 0; j < strings.length; j++)
			{
				// ����table
				if(strings[j][1].equalsIgnoreCase("view"))
				{
					vbManager.add(new ViewModel(strings[j][0]));
				}
			}
		}
	}
	
	/**
	 * ˢ�´洢����
	 */
	public void refreshPre(PreModelManager preManager)
	{
		String[][] strings = getShowInfor("show procedure status", preManager.getParent());
		if(strings != null)
		{
			preManager.removeAll();
			for (int j = 0; j < strings.length; j++)
			{
				// �������ݿ⣬ֻ�������������ݿ�ʱ�������
				if(preManager.getParent().getName().equals(strings[j][0]))
				{
					String name = strings[j][1];
					preManager.add(new PreModel(name));
				}
			}
		}
	}
	
	/**
	 * ˢ�º���
	 */
	public void refreshFun(FunModelManager funManager)
	{
		String[][] strings = getShowInfor("show function status", funManager.getParent());
		if(strings != null)
		{
			funManager.removeAll();
			for (int j = 0; j < strings.length; j++)
			{
				// �������ݿ⣬ֻ�������������ݿ�ʱ�������
				if(funManager.getParent().getName().equals(strings[j][0]))
				{
					String name = strings[j][1];
					funManager.add(new FunModel(name));
				}
			}
		}
	}
	
	/**
	 * ִ��sql���
	 * @param sql
	 * @return
	 */
	public boolean execute(String sql)
	{
		if(statememt == null)
		{
			return false;
		}
		else
		{
			try
			{
				// �����һ�����Ϊ ResultSet �����򷵻� true�������Ϊ���¼������߲������κν�����򷵻� false 
				statememt.execute(sql);
				return true;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
		}
	}
	
	/**
	 * ���ز�ѯ��������ã�
	 * @param sql
	 * @return Ϊnullʱ�����ѯʧ��
	 */
	public ResultSet executeQuery(String sql)
	{
		if(statememt == null)
		{
			return null;
		}
		else
		{
			try
			{
				return statememt.executeQuery(sql);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return null;
		}
	}
	
	/**
	 * ������䣬��insert update del��
	 * @param sql
	 * @return 
	 */
	public boolean executeUpdate(String sql) 
	{
		if(statememt == null)
		{
			return false;
		}
		else
		{
			try
			{
				// INSERT��UPDATE �� DELETE �����м��������� 0����ʾ�������κ����ݵ� SQL ���
				return (statememt.executeUpdate(sql) >= 0) ?true:false;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
		}
	}

	/**
	 * ����show �����ѯ��Ϣ
	 * @param dbModel ���ݿ�ģ�ͣ�δ��ʱ�����л����ݿ�
	 * @return
	 */
	private String[][] getShowInfor(String string, DBModel dbModel)
	{
		if(statememt == null)
		{
			return null;
		}
		else
		{
			try
			{
				ArrayList<String[]> al = new ArrayList<String[]>();
				if(dbModel != null)
				{
					execute("use " + dbModel.getName());
				}
				ResultSet rs = statememt.executeQuery(string);
				while(rs.next())
				{
					try
					{
						int columnCount = rs.getMetaData().getColumnCount();
						String[] strings = new String[columnCount];
						for (int i = 0; i < strings.length; i++)
						{
							strings[i] = rs.getString(i + 1);
						}
						al.add(strings);
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
				if(al.size() <= 0)
				{
					return null;
				}
				String[][] dataBases = new String[al.size()][2];
				return al.toArray(dataBases);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * �ر�����
	 */
	public void closeConn()
	{
		try
		{
			connModel.removeAll();
			if(statememt != null)
			{
				statememt.close();
			}
		}
		catch (SQLException e)
		{
		}
		try
		{
			if(conn != null)
			{
				conn.close();
			}
		}
		catch (SQLException e)
		{
		}
		isConnected = false;
		statememt = null;
		conn = null;
	}
	
	/**
	 * ��������
	 * @param connectModel
	 * @return
	 */
	public static boolean TestConn(ConnectModel connectModel)
	{
		System.out.println(connectModel);
		if(connectModel == null)
		{
			return false;
		}
		// 1. ע���������Ƿ�ÿ�ζ���Ҫע�᣿
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		try
		{
			GeneralInforBean generalInfor = connectModel.getConnect().getGeneralInfor();
			Connection conn = DriverManager.getConnection("jdbc:mysql://" 
					+ generalInfor.getHostName() + ":" + generalInfor.getPort(),
					generalInfor.getUserName(),generalInfor.getPwd());
			if(!conn.isClosed())
			{
				conn.close();	// �ر�
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}	
		return true;
	}
}

/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     ConnectModel.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-26  Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.java.db.conn.ConnControl;
import com.java.db.model.connectbeans.ConnectBean;

/**
 * ����ģ�ͣ����ڹ������ӣ��������ݿ����Ϣ
 *
 */
public class ConnectModel implements IList<DBModel>,IBase<ConnectModelManager>
{
	private List<DBModel> dblist;
	private ConnectBean connect;
	private ConnControl connControl;
	private ConnectModelManager parent;
	
	public ConnectModel()
	{
		connect = new ConnectBean();
		dblist = new LinkedList<DBModel>();
		connControl = new ConnControl(this);
	}
	
	/**
	 * ��ȡ���ӿ���
	 * @return
	 */
	public ConnControl getConnControl()
	{
		return connControl;
	}
	
	/**
	 * ��ȡ��������
	 * @return
	 */
	public ConnectBean getConnect()
	{
		return connect;
	}
	
	public void setConnect(ConnectBean connect)
	{
		this.connect = connect;
	}
	public boolean add(DBModel dbModel)
	{
		if(dbModel == null)
		{
			return false;
		}
		if(!isExisted(dbModel))
		{
			dbModel.setParent(this);	// ��Ӹ���
			return dblist.add(dbModel);	// ��ͬ���Ƶ�Ӧ���ų�
		}
		else
		{
			return false;
		}
	}
	
	public ConnectModelManager getParent()
	{
		return parent;
	}
	
	public void setParent(ConnectModelManager parent)
	{
		this.parent = parent;
	}
	
	/**
	 * ֻҪ��ͬ���ľ�Ĭ��Ϊͬ��
	 * @param dbModel
	 * @return
	 */
	public boolean isExisted(DBModel dbModel)
	{
		Iterator<DBModel> it = dblist.iterator();
		while(it.hasNext())
		{
			DBModel next = it.next();
			String name = next.getDb().getDBName();
			String name2 = dbModel.getDb().getDBName().trim();
			if(name.isEmpty())
			{
				return true;
			}
		}
		return false;
	}
	
	public void remove(DBModel dbModel)
	{
		dblist.remove(dbModel);
	}
	
	public void removeAll()
	{
		dblist.clear();
	}
	
	public DBModel get(int index)
	{
		return dblist.get(index);
	}
	
	/**
	 * ������У����������Ƿ��ؿ�
	 */
	public DBModel[] getChildren()
	{
		int size = dblist.size();
		if(size <= 0)
		{
			return null;
		}
		DBModel[] dbModels = new DBModel[size];
		return dblist.toArray(dbModels);
	}
	
	@Override
	public String toString()
	{
		if(connect != null)
		{
			return connect.getGeneralInfor().getConnName();
		}
		return super.toString();
	}

	@Override
	public String getName()
	{
		return connect.getGeneralInfor().getConnName();
	}
}

/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     DBModel.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-26   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model;

import com.java.db.model.dbbeans.DBBean;


/**
 * ���ݿ�ģ�ͣ����������ݿ�bean֮�ϣ��ṩ�������ݿⷽ��<br>
 * ����
 * @see ConnectModel
 *
 */
public class DBModel implements IBase<ConnectModel>
{
	private DBBean db;
	private ConnectModel parent;
	private TBModelManager tbManger;		// ��Ӧ������ģ�ͣ��̶���
	private ViewModelManager viewManger; 	// ��Ӧ��ͼģ�ͣ��̶���
	private PreModelManager preModelManager;// �洢����
	private FunModelManager funModelManager;// ����
	
	public DBModel(String dbName)
	{
		db = new DBBean(dbName.trim());
		tbManger = new TBModelManager();
		tbManger.setParent(this);	// ���ø��ڵ�
		viewManger = new ViewModelManager();
		viewManger.setParent(this);
		preModelManager = new PreModelManager();
		preModelManager.setParent(this);
		funModelManager = new FunModelManager();
		funModelManager.setParent(this);
	}
	
	public DBBean getDb()
	{
		return db;
	}
	
	public TBModelManager getTbManger()
	{
		return tbManger;
	}
	
	public ViewModelManager getViewManger()
	{
		return viewManger;
	}
	
	public PreModelManager getPreModelManager()
	{
		return preModelManager;
	}
	
	public FunModelManager getFunModelManager()
	{
		return funModelManager;
	}
	
	public ConnectModel getParent()
	{
		return parent;
	}
	
	public void setParent(ConnectModel parent)
	{
		this.parent = parent;
	}
	
	/**
	 * �����ӽڵ�
	 * @return
	 */
	public Object[] getChildren()
	{
		// ��������ڵ�
		return new Object[]{tbManger,viewManger,preModelManager,funModelManager};
	}
	
	@Override
	public String getName()
	{
		return db.getDBName();
	}
}

/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     TBModel.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-28   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model;

import java.util.Iterator;


/**
 * ��ģ�ͣ����Դ�Ŷ����ṹ
 */
public class TBModelManager extends AbstractDBModelManager<TableModel>
{
	public TBModelManager()
	{
		super();
	}

	@Override
	public String getName()
	{
		return "��";		// ���ʻ�ʱ����Ҫ���޸�
	}
	
	@Override
	public boolean add(TableModel tbModel)
	{
		if(tbModel == null)
		{
			return false;
		}
		if(!isExisted(tbModel))
		{
			tbModel.setParent(this);	// ��Ӹ���
			return list.add(tbModel);	// ��ͬ���Ƶ�Ӧ���ų�
		}
		else
		{
			return false;
		}
	}
	@Override
	public TableModel[] getChildren()
	{
		int size = list.size();
		if(size <= 0)
		{
			return null;
		}
		TableModel[] tableBeans = new TableModel[size];
		return list.toArray(tableBeans);
	}
	@Override
	public boolean isExisted(TableModel t)
	{
		Iterator<TableModel> it = list.iterator();
		while(it.hasNext())
		{
			TableModel next = it.next();
			String name = next.getName();
			String name2 = t.getName();
			if(name == name2 || name.equals(name2))
			{
				return true;
			}
		}
		return false;
	}
	
}

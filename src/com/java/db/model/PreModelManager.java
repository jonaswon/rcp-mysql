/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     FunAndPreModelManager.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-11-1   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model;

import java.util.Iterator;

/**
 * ����ģ�͵Ĺ���
 */
public class PreModelManager extends AbstractDBModelManager<PreModel>
{

	@Override
	public boolean add(PreModel PreModel)
	{
		if(PreModel == null)
		{
			return false;
		}
		if(!isExisted(PreModel))
		{
			PreModel.setParent(this);	// ��Ӹ���
			return list.add(PreModel);	// ��ͬ���Ƶ�Ӧ���ų�
		}
		else
		{
			return false;
		}
	}
	@Override
	public PreModel[] getChildren()
	{
		int size = list.size();
		if(size <= 0)
		{
			return null;
		}
		PreModel[] tableBeans = new PreModel[size];
		return list.toArray(tableBeans);
	}
	@Override
	public boolean isExisted(PreModel t)
	{
		Iterator<PreModel> it = list.iterator();
		while(it.hasNext())
		{
			PreModel next = it.next();
			String name = next.getName();
			String name2 = t.getName();
			if(name == name2 || name.equals(name2))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String getName()
	{
		return "�洢����";
	}
}

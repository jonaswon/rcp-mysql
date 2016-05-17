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
public class FunModelManager extends AbstractDBModelManager<FunModel>
{

	@Override
	public boolean add(FunModel funModel)
	{
		if(funModel == null)
		{
			return false;
		}
		if(!isExisted(funModel))
		{
			funModel.setParent(this);	// ��Ӹ���
			return list.add(funModel);	// ��ͬ���Ƶ�Ӧ���ų�
		}
		else
		{
			return false;
		}
	}
	@Override
	public FunModel[] getChildren()
	{
		int size = list.size();
		if(size <= 0)
		{
			return null;
		}
		FunModel[] tableBeans = new FunModel[size];
		return list.toArray(tableBeans);
	}
	@Override
	public boolean isExisted(FunModel t)
	{
		Iterator<FunModel> it = list.iterator();
		while(it.hasNext())
		{
			FunModel next = it.next();
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
		return "����";
	}
}

/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     ViewModel.java
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
 * view ģ�ͣ��������ݿ����е�view
 */
public class ViewModelManager  extends AbstractDBModelManager<ViewModel>
{
	public ViewModelManager()
	{
		super();
	}
	
	@Override
	public String getName()
	{
		return "��ͼ";		// ���ʻ�ʱ����Ҫ���޸�
	}
	
	@Override
	public boolean add(ViewModel tbModel)
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
	public ViewModel[] getChildren()
	{
		int size = list.size();
		if(size <= 0)
		{
			return null;
		}
		ViewModel[] tableBeans = new ViewModel[size];
		return list.toArray(tableBeans);
	}
	@Override
	public boolean isExisted(ViewModel t)
	{
		Iterator<ViewModel> it = list.iterator();
		while(it.hasNext())
		{
			ViewModel next = it.next();
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

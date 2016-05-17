/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     DBFiled.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-31   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model.dbbeans;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ��Ų�ѯ�������ͼ��Ӧ������
 */
public class TBDatas
{
	private Map<String, ArrayList<String>> result;
	private int pageSize;
	private int count;
	private int nowPage;
	
	/**
	 * @description
	 * @param count ��ѯ�����С�����Ӧ�ÿ����ڴ���0
	 */
	public TBDatas()
	{
		result = new LinkedHashMap<String, ArrayList<String>>();
		pageSize = -1;
		nowPage = 1;
	}
	
	/**
	 * ����ÿһҳpage�Ĵ�С�����ܴ�������Ŀ
	 * @param pageSize
	 */
	public boolean setPageSize(int pageSize)
	{
		if(pageSize == getPageSize())
		{
			return false;
		}
		else if(pageSize >= getCount() || pageSize <= 0)
		{
			this.pageSize = getCount();
			return true;
		}
		else
		{
			this.pageSize = pageSize;
			return true;
		}
		
	}
	
	/**
	 * ��ȡÿһҳpage�Ĵ�С
	 * @return
	 */
	public int getPageSize()
	{
		return pageSize;
	}
	
	/**
	 * ��ȡ��ҳ��
	 * @return
	 */
	public int getPageCount()
	{
		if(pageSize == -1)
		{
			return 1;
		}
		else
		{
			return getCount()/pageSize + ((getCount()%pageSize) > 0 ? 1 : 0);
		}
	}
	
	public void add(String cloName,ArrayList<String> str)
	{
		result.put(cloName, str);
	}
	
	public void clear()
	{
		result.clear();
	}
	
	public String[] getColNames()
	{
		int size = result.keySet().size();
		if(size <= 0)
		{
			return null;
		}
		String[] str = new String[size];
		return result.keySet().toArray(str);
	}
	
	private String getColName(int index)
	{
		String[] str = getColNames();
		if(str == null)
		{
			return null;
		}
		else
		{
			try
			{
				return str[index];
			}
			catch (Exception e)
			{
				return null;
			}
		}
	}
	
	/**
	 * ��ȡÿһ���ķ���ֵ
	 * @param index	������
	 * @return
	 */
	private String[] getResultCol(int index)
	{
		String colName = getColName(index);
		if(colName == null)
		{
			return null;
		}
		ArrayList<String> arrayList = result.get(colName);
		if(arrayList.size() <= 0)
		{
			return null;
		}
		String[] str = new String[arrayList.size()];
		return arrayList.toArray(str);
	}
	
	/**
	 * ��ȡÿһ���ķ���ֵ
	 * @param index	������
	 * @return
	 */
	private String[] getResultCol(int index,int page)
	{
		String colName = getColName(index);
		if(colName == null)
		{
			return null;
		}
		List<String> arrayList = null;
		ArrayList<String> al = result.get(colName);
		if(pageSize <= 0)
		{
			arrayList = al;
		}
		else
		{
			int from = (page - 1)* pageSize;
			int to = (page* pageSize);
			if(al.size() < to)		// �ж��Ƿ����
			{
				to = al.size();
			}
			arrayList = al.subList(from, to);
		}
		if(arrayList.size() <= 0)
		{
			return null;
		}
		String[] str = new String[arrayList.size()];
		return arrayList.toArray(str);
	}
	
	/**
	 * ��ȡ���еĽ��
	 * @return
	 */
	public String[][] getResults()
	{
		int size = result.size();
		if(size <= 0)
		{
			return null;
		}
		String[][] str = new String[getResultCol(0).length][size];
		for (int i = 0; i < size; i++)
		{
			String[] resultCol = getResultCol(i);
			for (int j = 0; j < resultCol.length; j++)
			{
				str[j][i] = resultCol[j];
			}
		}
		return str;
	}

	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	/**
	 * ��ȡ��Ŀ
	 * @return
	 */
	public int getCount()
	{
		return count;
	}
	/**
	 * ����ҳ����ȡ���
	 * @param page ���page <= 0 �����ص�һҳ
	 * @return
	 */
	public String[][] getResults(int page)
	{
		nowPage = 1;
		if(page >= 1 && page <= getPageCount())
		{
			nowPage = page;
		}
		else if(page > getPageCount())
		{
			nowPage = getPageCount();
		}
		
		int size = result.size();
		if(size <= 0)
		{
			return null;
		}
		int length = getResultCol(0,nowPage).length;	// ���һҳ�ĳ���
		String[][] str = new String[length][size];
		for (int i = 0; i < size; i++)
		{
			String[] resultCol = getResultCol(i,nowPage);
			for (int j = 0; j < resultCol.length; j++)
			{
				str[j][i] = resultCol[j];
			}
		}
		return str;
	}
	
	/**
	 * ��ȡ��ǰҳ
	 * @return
	 */
	public int getNowPage()
	{
		return nowPage;
	}
}

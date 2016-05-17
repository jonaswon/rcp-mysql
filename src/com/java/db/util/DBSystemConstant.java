/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     DBSystemConstant.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-31   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.util;

import com.java.db.DBActivator;
import com.java.db.preferences.PreferenceConstants;


public class DBSystemConstant
{
	public static String CONN_PATH = null;
	static
	{
		CONN_PATH = DBActivator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_PATH);
	}
}

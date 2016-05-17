/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     ConnectBean.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-26   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model.connectbeans;

import java.io.Serializable;

/**
 * ��������
********************
*SSL Information
********************
ʹ�� SSL: False
ʹ����֤: False
�ͻ�����Կ: 
�ͻ���֤��: 
CA ֤��: 

********************
*SSH Information
********************
ʹ�� SSH ͨ��: False
�������� IP ��ַ: 
�˿�: 22
�û���: 
��֤����: ����
��������: False

********************
*HTTP Information
********************
ʹ�� HTTP ͨ��: False
ͨ����ַ: 
ʹ��������֤: False
�û���: 
��������: False
ʹ��֤����֤: False
�ͻ�����Կ: 
�ͻ���֤��: 
CA ֤��: 
Use Proxy: False
��������� ����: 
��������� �˿�: 0
��������� �û���: 
��������� ��������: False

********************
*Other Information
********************
�������汾: 5.5.13
ͨѶЭ��: 10
ѶϢ: 10.14.147.106 via TCP/IP
 */
public class ConnectBean implements Serializable
{
	/**
	 * general VersionID
	 */
	private static final long serialVersionUID = 8261628204429244953L;
	/*********************
	 * General Information
	 ********************/
	private GeneralInforBean generalInfor;

	/*********************
	 * Advanced Information
	 *********************
	*/
	private AdvancedInforBean advancedInfor;
	
	/**
	 * @description ���캯������ʼ����������
	 */
	public ConnectBean()
	{
		// ��ʼ��
		generalInfor = new GeneralInforBean();
		advancedInfor = new AdvancedInforBean();
	}

	
	public GeneralInforBean getGeneralInfor()
	{
		return generalInfor;
	}

	
	public AdvancedInforBean getAdvancedInfor()
	{
		return advancedInfor;
	}
}

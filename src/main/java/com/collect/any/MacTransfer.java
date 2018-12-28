/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：MacTransfer.java
 *  版本变更记录（可选）：修改日期2017年11月28日  下午5:08:39，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.any;

import java.text.MessageFormat;

/** 
 * @Description:
 * <p>创建日期：2017年11月28日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class MacTransfer {
    private static final String MAC_SEPARATOR = "\\.";
    private static final String MAC_HEX_SEPARATOR = ":";
    private static final String MAC_PATTERN = "{0}.{1}.{2}.{3}.{4}.{5}";
    private static final String MAC_HEX_PATTERN = "{0}:{1}:{2}:{3}:{4}:{5}";
	public static String changeMacIntegerToHex(final String mac) {
        if (mac == null) {
            return null;
        }
        String hexStr = null;
        try {
            String[] arr = mac.split(MAC_SEPARATOR);
            String[] hexArr = new String[6];
            int i = 0;
            for (String s : arr) {
                int t_s = Integer.parseInt(s);
                String _hex = Integer.toHexString(t_s);
                hexArr[i++] = _hex.length() == 1 ? "0" + _hex : _hex;
            }
            hexStr = MessageFormat.format(MAC_HEX_PATTERN, (Object[]) hexArr);
        } catch (NumberFormatException e) {
        	e.printStackTrace();
        }
        return hexStr;
    }
	
    public static void main(String[] args) {
		System.out.println(changeMacIntegerToHex("132.61.198.55.45.16"));
	}
}

package com.tec.mac;

import java.text.MessageFormat;

public class MacUtil {
	private static final String MAC_SEPARATOR = "\\.";
	private static final String MAC_PATTERN = "{0}.{1}.{2}.{3}.{4}.{5}";
	private static final String MAC_HEX_PATTERN = "{0}:{1}:{2}:{3}:{4}:{5}";
	private static final String MAC_HEX_SEPARATOR = ":";
	
	/**
	 * @Description: Mac转Index 
	 * 创建人：SUNLEI, 2018年9月19日 下午4:55:59
	 * 修改人：SUNLEI, 2018年9月19日 下午4:55:59
	 * @param macHex
	 * @return 
	 * @return String  
	 * @throws
	 */
	public static String changeMacHexToInteger(final String macHex) {
        if (macHex == null) {
            return null;
        }
        String intStr = null;
        try {
            String[] arr = macHex.split(MAC_HEX_SEPARATOR);
            if (arr.length == 6) {
                String[] intArr = new String[arr.length];
                int i = 0;
                for (String hex : arr) {
                    intArr[i++] = String.valueOf(Integer.parseInt(hex, 16));
                }
                intStr = MessageFormat.format(MAC_PATTERN, (Object[]) intArr);
            } else {
                intStr = macHex;
            }
        } catch (NumberFormatException e) {
            System.out.println("changeMacHexToInteger Mac : " + macHex + " Error : " + e.getMessage());
        }
        return intStr;
    }
	
	/**
	 * @Description:index转换mac
	 * 创建人：SUNLEI, 2018年9月19日 下午5:01:02
	 * 修改人：SUNLEI, 2018年9月19日 下午5:01:02
	 * @param mac
	 * @return 
	 * @return String  
	 * @throws
	 */
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
            System.out.println("MacIntegerToHex Mac : " + mac + " Error : " + e.getMessage());
            hexStr = "";
        }
        return hexStr;
    }
	
	public static void main(String[] args) {
		System.out.println(changeMacHexToInteger("58:69:6c:ac:e2:93"));
		System.out.println(changeMacIntegerToHex("88.105.108.172.226.147"));
	}
}

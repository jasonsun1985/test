package com.collect.data;

import com.collect.data.DataUnitFormatter.DataUnit;

public final class DataUnitMapping {
    /**
     * <code>S_MB2</code> - Mb.
     */
    private static final String S_MB2 = "Mb";
    /**
     * <code>S_KB2</code> - Kb.
     */
    private static final String S_KB2 = "Kb";
    /**
     * <code>S_PBPS</code> - Pbps.
     */
    private static final String S_PBPS = "Pbps";
    /**
     * <code>S_TBPS</code> - Tbps.
     */
    private static final String S_TBPS = "Tbps";
    /**
     * <code>S_GBPS</code> - Gbps.
     */
    private static final String S_GBPS = "Gbps";
    /**
     * <code>S_MBPS</code> - Mbps.
     */
    private static final String S_MBPS = "Mbps";
    /**
     * <code>S_KBPS2</code> - Kbps.
     */
    private static final String S_KBPS2 = "Kbps";
    /**
     * <code>S_PB</code> - Pb.
     */
    private static final String S_PB = "Pb";
    /**
     * <code>S_TB</code> - Tb.
     */
    private static final String S_TB = "Tb";
    /**
     * <code>S_GB</code> - Gb.
     */
    private static final String S_GB = "Gb";
    /**
     * <code>S_MB</code> - mb.
     */
    private static final String S_MB = "mb";
    /**
     * <code>S_KB</code> - kb.
     */
    private static final String S_KB = "kb";
    /**
     * <code>S_B</code> - b.
     */
    private static final String S_B = "b";
    /**
     * <code>S_KBPS</code> - kbps.
     */
    private static final String S_KBPS = "kbps";
    /**
     * <code>S_BPS</code> - kbps.
     */
    private static final String S_BPS = "bps";

    /**
     * Constructors.
     */
    private DataUnitMapping() {
    }

    /**
     * 转换速率数据单位
     * 
     * @param unit 待转换单位
     * @return DataUnit
     */
    public static DataUnit translateFlowUnit(final String unit) {
        if (S_BPS.equalsIgnoreCase(unit))
            return DataUnit.bit;
        if (S_KBPS.equalsIgnoreCase(unit))
            return DataUnit.Kb;
        else return DataUnit.Kb;
    }

    /**
     * 转化卷单位
     * @param unit 传入单位
     * @return 传出单位
     */
    public static DataUnit translateVolumnUnit(final String unit) {
        DataUnit t_unit=null;
        if (S_B.equalsIgnoreCase(unit)){
            t_unit =  DataUnit.bit;
        }else if (S_KB.equalsIgnoreCase(unit)){
            t_unit =  DataUnit.Kb;
        }else if (S_MB.equalsIgnoreCase(unit)){
            t_unit =  DataUnit.Mb;
        }else if (S_GB.equalsIgnoreCase(unit)){
            t_unit =  DataUnit.Gb;
        }else if (S_TB.equalsIgnoreCase(unit)){
            t_unit =  DataUnit.Tb;
        }else if (S_PB.equalsIgnoreCase(unit)){
            t_unit =  DataUnit.Pb;
        } else t_unit =  DataUnit.Kb;
        return t_unit;
    }

    /**
     * 转化速率单位
     * @param unit 传入单位
     * @return 传出单位
     */
    public static String parseFlowUnit(final DataUnit unit) {
        String t_value = "";
        switch (unit) {
            case bit:
                t_value =  S_BPS;
                break;
            case Kb:
                t_value =  S_KBPS2;
                break;
            case Mb:
                t_value =  S_MBPS;
                break;
            case Gb:
                t_value =  S_GBPS;
                break;
            case Tb:
                t_value =  S_TBPS;
                break;
            case Pb:
                t_value =  S_PBPS;
                break;
            default:
                return S_KBPS2;
        }
        return t_value;
    }

    /**
     * 转化存储单位
     * @param unit 传入数据单位
     * @return String
     */
    public static String parseVolumnUnit(final DataUnit unit) {
        String t_value = "";
        switch (unit) {
            case bit:
                t_value = S_B;
                break;
            case Kb:
                t_value = S_KB2;
                break;
            case Mb:
                t_value = S_MB2;
                break;
            case Gb:
                t_value = S_GB;
                break;
            case Tb:
                t_value = S_TB;
                break;
            case Pb:
                t_value = S_PB;
                break;
            default:
                t_value = S_KB2;
        }
        return t_value;
    }
}

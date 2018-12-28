package com.collect.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数据单位格式工具类 <br>
 * 提供 bit, Kb, Mb, Gb, Tb, Pb, Byte, KB, MB, GB, TB, PB 之间数据转换<br>
 */
public class DataUnitFormatter {

	/**
	 * <code>S_DEFAULT_DISPlAY_SCALE</code> - 默认显示精度
	 */
	public static final int S_DEFAULT_DISPlAY_SCALE = 2;

	/**
	 * <code>S_DEFAULT_COUNT_SCALE</code> - 默认计算精度
	 */
	public static final int S_DEFAULT_COUNT_SCALE = 8;

	/**
	 * 格式化为可读的数据（例如：1.23Kb，25MB，34.2GB）<br>
	 * 默认：2位小数<br>
	 * 
	 * @param data 被格式化数据
	 * @param from 数据原单位
	 * @return 格式化的字符串
	 */
	public static String format(double data, DataUnit from) {
		return format(data, from, S_DEFAULT_DISPlAY_SCALE);
	}

	/**
	 * 格式化为可读的数据（例如：1.23Kb，25MB，34.2GB）- 指定小数位<br>
	 * 例如：<code>1024MB, 4位小数 -> 1.0000GB</code><br>
	 * 
	 * @param data 被格式化数据
	 * @param from 数据原单位
	 * @param scale 指定小数位
	 * @return 格式化的字符串
	 */
	public static String format(double data, DataUnit from, int scale) {
		BigDecimal t_baseValue = toBaseUnitValue(data, from);
		OctetUnit t_to = getHumanOctetUnit(t_baseValue);

		return formatFromBase(t_baseValue, from, DataUnit.valueOf(from, t_to), scale);
	}

	/**
	 * 格式化为可读的数据（例如：1.23Kb，25MB，34.2GB）- 指定格式化目标单位<br>
	 * 例如： <code>1024MB, 目标：KB -> 1048576.00KB</code><br>
	 * <code>1Byte, 目标：bit -> 8.00</code><br>
	 * <code>1024KB, 目标：bit -> 8388608.00</code><br>
	 * 
	 * @param data 被格式化数据
	 * @param from 数据原单位
	 * @param to 格式化目标单位
	 * @return 格式化的字符串
	 */
	public static String format(double data, DataUnit from, DataUnit to) {
		return format(data, from, to, S_DEFAULT_DISPlAY_SCALE);
	}

	/**
	 * 格式化为可读的数据（例如：1.23Kb，25MB，34.2GB）- 指定格式化目标单位，小数位<br>
	 * 
	 * @param data 被格式化数据
	 * @param from 数据原单位
	 * @param to 格式化目标单位
	 * @param scale 指定小数位
	 * @return 格式化的字符串
	 */
	public static String format(double data, DataUnit from, DataUnit to, int scale) {
		BigDecimal t_baseValue = toBaseUnitValue(data, from);

		return formatFromBase(t_baseValue, from, to, scale);
	}

	/**
	 * 格式化为可读的数据（例如：1.23Kb，25MB，34.2GB）- 指定格式化目标单位<br>
	 * 仅返回数值，不带单位
	 * 
	 * @param data 被格式化数据
	 * @param from 数据原单位
	 * @param to 格式化目标单位
	 * @return 格式化后的数值
	 */
	public static double formate2Double(double data, DataUnit from, DataUnit to) {
		return formate2Double(data, from, to, S_DEFAULT_DISPlAY_SCALE);
	}

	/**
	 * 格式化为可读的数据（不带单位）- 指定格式化目标单位，小数位<br>
	 * 
	 * @param data 被格式化数据
	 * @param from 数据原单位
	 * @param to 格式化目标单位
	 * @return 格式化后的数值
	 */
	public static double formate2Double(double data, DataUnit from, DataUnit to, int scale) {
		BigDecimal t_baseValue = toBaseUnitValue(data, from);

		return formatFromBase2BigDecimal(t_baseValue, from, to, scale).doubleValue();
	}

	private static String formatFromBase(BigDecimal baseValue, DataUnit from, DataUnit to, int scale) {
		OctetUnit t_to = to.toOctetUnit();

		return formatFromBase2BigDecimal(baseValue, from, to, scale).toPlainString()
				+ DataUnit.valueOf(from, t_to).toString();
	}

	private static BigDecimal formatFromBase2BigDecimal(BigDecimal baseValue, DataUnit from, DataUnit to, int scale) {
		OctetUnit t_to = to.toOctetUnit();
		BigDecimal t_temp = null;
		if (t_to == null) {
			t_temp = baseValue;
		} else {
			t_temp = t_to.convert(baseValue);
		}

		// Bit与Byte换算
		BigDecimal t_dst = null;
		if (from.toBitByte().equals(to.toBitByte())) {
			t_dst = t_temp;
		} else {
			if (to.isByte()) {
				t_dst = t_temp.divide(new BigDecimal(8), S_DEFAULT_COUNT_SCALE, RoundingMode.HALF_UP);
			} else {
				t_dst = t_temp.multiply(new BigDecimal(8));
			}
		}

		return t_dst.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 取得可读的数据单位（例如：Kb，MB，bit）<br>
	 * 
	 * 以原单位为准：<br>
	 * 原单位为Byte，返回Byte，KB，MB，GB等<br>
	 * 原单位为bit，返回bit, Kb, Mb等<br>
	 * 
	 * @param data 被格式化数据
	 * @param from 数据原单位
	 * @return 可读的数据单位
	 */
	public static DataUnit getHumDataUnit(double data, DataUnit from) {
		BigDecimal t_baseValue = toBaseUnitValue(data, from);
		OctetUnit t_to = getHumanOctetUnit(t_baseValue);
		if(t_to == null){
		    return from;
		}
		return DataUnit.valueOf(from, t_to);
	}

	private static OctetUnit getHumanOctetUnit(BigDecimal baseValue) {
		long octets = baseValue.longValue();
		if (octets < OctetUnit.K.toLong()) {
			return null;
		} else if (octets >= OctetUnit.K.toLong() && octets < OctetUnit.M.toLong()) {
			return OctetUnit.K;
		} else if (octets >= OctetUnit.M.toLong() && octets < OctetUnit.G.toLong()) {
			return OctetUnit.M;
		} else if (octets >= OctetUnit.G.toLong() && octets < OctetUnit.T.toLong()) {
			return OctetUnit.G;
		} else if (octets >= OctetUnit.T.toLong() && octets < OctetUnit.P.toLong()) {
			return OctetUnit.T;
		} else if (octets >= OctetUnit.P.toLong()) {
			return OctetUnit.P;
		} else {
			return null;
		}
	}

	private static BigDecimal toBaseUnitValue(double data, DataUnit from) {
		BigDecimal t_srcData = new BigDecimal(Double.toString(data));
		return toBaseUnitValue(t_srcData, from);
	}

	private static BigDecimal toBaseUnitValue(BigDecimal data, DataUnit from) {
		OctetUnit t_octetUnit = from.toOctetUnit();
		if (t_octetUnit == null) {
			return data;
		}

		return t_octetUnit.toBaseUnit(data);
	}

	/**
	 * 数据单位: 包括bit, Kb, Mb, Gb, Tb, Pb, Byte, KB, MB, GB, TB, PB<br>
	 */
	public static enum DataUnit {
		bit, Kb, Mb, Gb, Tb, Pb, Byte, KB, MB, GB, TB, PB;

		public static DataUnit valueOf(DataUnit unit, OctetUnit octetUnit) {
			if (unit.isByte()) {
				if (octetUnit == null) {
					return Byte;
				}
				switch (octetUnit) {
				case K:
					return KB;
				case M:
					return MB;
				case G:
					return GB;
				case T:
					return TB;
				case P:
					return PB;
				default:
					return Byte;
				}
			} else {
				if (octetUnit == null) {
					return bit;
				}
				switch (octetUnit) {
				case K:
					return Kb;
				case M:
					return Mb;
				case G:
					return Gb;
				case T:
					return Tb;
				case P:
					return Pb;
				default:
					return bit;
				}
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Kb:
			case Mb:
			case Gb:
			case Tb:
			case Pb:
			case KB:
			case MB:
			case GB:
			case TB:
			case PB:
				return super.toString();
			case bit:
			case Byte:
				return "";
			default:
				return "";
			}
		}

		/**
		 * 是否为Byte类单位（例如：Byte，KB，MB，GB等）
		 * @return 是否为Byte类单位
		 */
		public boolean isByte() {
			return this.toBitByte().isByte();
		}

		private BitByte toBitByte() {
			switch (this) {
			case bit:
			case Kb:
			case Mb:
			case Gb:
			case Tb:
			case Pb:
				return BitByte.bit;
			case Byte:
			case KB:
			case MB:
			case GB:
			case TB:
			case PB:
				return BitByte.Byte;
			default:
				return BitByte.Byte;
			}
		}

		private OctetUnit toOctetUnit() {
			switch (this) {
			case bit:
			case Byte:
				return null;
			case Kb:
			case KB:
				return OctetUnit.K;
			case Mb:
			case MB:
				return OctetUnit.M;
			case Gb:
			case GB:
				return OctetUnit.G;
			case Tb:
			case TB:
				return OctetUnit.T;
			case Pb:
			case PB:
				return OctetUnit.P;
			default:
				return null;
			}
		}
	}

	private static enum BitByte {
		bit, Byte;

		private boolean isByte() {
			return this.equals(BitByte.Byte);
		}
	}

	// 字节单位
	protected static enum OctetUnit {
		K, M, G, T, P;

		public long toLong() {
			switch (this) {
			case K:
				return 1024L; // (1L << 10);
			case M:
				return 1048576L; // (1L << 20);
			case G:
				return 1073741824L; // (1L << 30);
			case T:
				return 1099511627776L; // (1L << 40);
			case P:
				return 1125899906842624L; // (1L << 50);
			}
			return -1;
		}

		protected BigDecimal toBigDecimal() {
			return new BigDecimal(this.toLong());
		}

		protected BigDecimal convert(BigDecimal octets) {
			return convert(octets, this, S_DEFAULT_COUNT_SCALE);
		}

		protected static BigDecimal convert(BigDecimal octets, OctetUnit unit, int scale) {
			return octets.divide(unit.toBigDecimal(), scale, RoundingMode.HALF_UP);
		}

		protected BigDecimal toBaseUnit(BigDecimal octets) {
			return octets.multiply(this.toBigDecimal());
		}

	}
	
	public static void main(String[] args) {
//	    System.out.println(format(123123.321, DataUnit.Kb,DataUnit.Gb));
	    DataUnit d = getHumDataUnit(Float.MAX_VALUE, DataUnit.Kb);
	    System.out.println(d);
	    System.out.println(formate2Double(24690.321, DataUnit.Kb,d));
//	    System.out.println(d.name());
//	    System.out.println(DataUnitMapping.translateFlowUnit("kbps"));
	    System.out.println(Float.POSITIVE_INFINITY);
	}
}

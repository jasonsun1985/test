package com.tec.test;

public interface IWireless<PO extends Wireless> {
	public String getPOName(PO po);
	public <T> T getT();
}

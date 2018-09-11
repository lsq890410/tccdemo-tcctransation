package org.tccdemo.hmily.inventorysend.service;

import com.hmily.tcc.annotation.Tcc;

public interface IInventorySendService {
	@Tcc
	public void send(String inventoryid)throws Exception;
	@Tcc
	public void confirm(String inventoryid) throws Exception;
	@Tcc
	public void cancel(String inventoryid)throws Exception;
}

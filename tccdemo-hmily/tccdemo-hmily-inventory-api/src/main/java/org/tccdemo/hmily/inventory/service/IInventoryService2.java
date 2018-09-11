package org.tccdemo.hmily.inventory.service;

import com.hmily.tcc.annotation.Tcc;

public interface IInventoryService2 {
	@Tcc
	public void delivery(String inventoryid)throws Exception;
	@Tcc
	public void confirm(String inventoryid) throws Exception;
	@Tcc
	public void cancel(String inventoryid)throws Exception;
}

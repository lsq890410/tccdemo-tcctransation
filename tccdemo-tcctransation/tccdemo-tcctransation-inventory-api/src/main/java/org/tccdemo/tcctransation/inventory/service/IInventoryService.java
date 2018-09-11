package org.tccdemo.tcctransation.inventory.service;

import org.mengyun.tcctransaction.api.Compensable;

public interface IInventoryService {
	@Compensable
	public void delivery(String inventoryid)throws Exception;
	public void confirm(String inventoryid) throws Exception;
	public void cancel(String inventoryid)throws Exception;
}

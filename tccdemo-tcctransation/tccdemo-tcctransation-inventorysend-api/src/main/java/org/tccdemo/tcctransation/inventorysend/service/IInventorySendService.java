package org.tccdemo.tcctransation.inventorysend.service;

import org.mengyun.tcctransaction.api.Compensable;

public interface IInventorySendService {
	@Compensable
	public void inventorySend(String inventoryid)throws Exception;
	public void confirm(String inventoryid) throws Exception;
	public void cancel(String inventoryid)throws Exception;
}

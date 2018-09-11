package org.tccdemo.tcctransation.inventory.service2.impl;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tccdemo.tcctransation.inventory.service.IInventoryService2;
import org.tccdemo.tcctransation.inventory.service.impl.InventoryTccService;
import org.tccdemo.tcctransation.inventorysend.service.IInventorySendService;

@Service("inventoryService2")
public class InventoryServiceImpl2 implements IInventoryService2{
	@Autowired
	private InventoryTccService inverntoryService;
	@Autowired
	private IInventorySendService inverntorySendService;
	@Override
	@Compensable(confirmMethod = "confirm", cancelMethod = "cancel")
	public void delivery(String inventoryid) throws Exception {
		inverntoryService.delivery(inventoryid);
		//发货
		inverntorySendService.inventorySend(inventoryid);
	}

	@Override
	public void confirm(String inventoryid) throws Exception {
		inverntoryService.confirm(inventoryid);
	}

	@Override
	public void cancel(String inventoryid) throws Exception {
		inverntoryService.cancel(inventoryid);
	}
}

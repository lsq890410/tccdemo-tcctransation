package org.tccdemo.tcctransation.inventory.service.impl;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tccdemo.tcctransation.inventory.service.IInventoryService;

@Service("inventoryService")
public class InventoryServiceImpl implements IInventoryService{
	@Autowired
	private InventoryTccService2 inverntoryService;
	@Override
	@Compensable(confirmMethod = "confirm", cancelMethod = "cancel")
	public void delivery(String inventoryid) throws Exception {
		inverntoryService.delivery(inventoryid);
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

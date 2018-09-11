package org.tccdemo.hmily.inventory.service2.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tccdemo.hmily.inventory.service.IInventoryService2;
import org.tccdemo.hmily.inventory.service.impl.InventoryTccService;
import org.tccdemo.hmily.inventorysend.service.IInventorySendService;

import com.hmily.tcc.annotation.Tcc;

@Service("inventoryService2")
public class InventoryServiceImpl2 implements IInventoryService2{
	@Autowired
	private InventoryTccService inverntoryService;
	@Autowired
	private IInventorySendService inventorySendService;
	@Override
	@Tcc(confirmMethod = "confirm", cancelMethod = "cancel")
	public void delivery(String inventoryid) throws Exception {
		inverntoryService.delivery(inventoryid);
		//发货
		inventorySendService.send(inventoryid);
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

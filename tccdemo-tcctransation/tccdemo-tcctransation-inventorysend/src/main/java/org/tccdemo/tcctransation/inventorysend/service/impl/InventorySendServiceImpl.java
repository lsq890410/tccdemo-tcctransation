package org.tccdemo.tcctransation.inventorysend.service.impl;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tccdemo.tcctransation.inventorysend.service.IInventorySendService;

@Service("inventorySendService")
public class InventorySendServiceImpl implements IInventorySendService{
	@Autowired
	private InventorySendTccService inverntoryService;
	@Override
	@Compensable(confirmMethod = "confirm", cancelMethod = "cancel")
	public void inventorySend(String inventoryid) throws Exception {
		inverntoryService.send(inventoryid);
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

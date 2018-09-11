package org.tccdemo.hmily.inventorysend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tccdemo.hmily.inventorysend.service.IInventorySendService;

import com.hmily.tcc.annotation.Tcc;

@Service("inventorySendService")
public class InventorySendServiceImpl implements IInventorySendService{
	@Autowired
	private InventorySendTccService inverntoryService;
	@Override
	@Tcc(confirmMethod = "confirm", cancelMethod = "cancel")
	public void send(String inventoryid) throws Exception {
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

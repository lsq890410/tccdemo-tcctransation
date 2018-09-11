package org.tccdemo.tcctransation.pay.service.impl;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tccdemo.tcctransation.inventory.service.IInventoryService;
import org.tccdemo.tcctransation.pay.service.IPayService;

@Service("payService")
public class PayServiceImpl implements IPayService{
	@Autowired
	private PayTccService2 payService;
	@Autowired
	private IInventoryService inventoryService;
	@Compensable(confirmMethod = "confirm", cancelMethod = "cancel")
	public void pay(String accountid, Integer money) throws Exception {
		payService.pay(accountid, money);
	}
	
	public void confirm(String accountid, Integer money) throws Exception {
		payService.confirm(accountid, money);
	}
	
	public void cancel(String accountid, Integer money) throws Exception {
		payService.cancel(accountid, money);
	}
}

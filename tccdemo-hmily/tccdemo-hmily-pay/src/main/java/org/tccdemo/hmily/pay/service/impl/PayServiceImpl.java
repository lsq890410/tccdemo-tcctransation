package org.tccdemo.hmily.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tccdemo.hmily.inventory.service.IInventoryService;
import org.tccdemo.hmily.pay.service.IPayService;

import com.hmily.tcc.annotation.Tcc;

@Service("payService")
public class PayServiceImpl implements IPayService{
	@Autowired
	private PayTccService2 payService;
	
	@Tcc(confirmMethod = "confirm", cancelMethod = "cancel")
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

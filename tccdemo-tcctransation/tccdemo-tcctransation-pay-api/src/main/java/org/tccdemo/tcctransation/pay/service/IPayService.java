package org.tccdemo.tcctransation.pay.service;

import org.mengyun.tcctransaction.api.Compensable;

public interface IPayService {
	@Compensable
	public void pay(String accountid,Integer money)throws Exception;
	public void confirm(String accountid, Integer money) throws Exception;
	public void cancel(String accountid, Integer money)throws Exception;
}

package org.tccdemo.hmily.pay.service;

import com.hmily.tcc.annotation.Tcc;

public interface IPayService {
	@Tcc
	public void pay(String accountid,Integer money)throws Exception;
	@Tcc
	public void confirm(String accountid, Integer money) throws Exception;
	@Tcc
	public void cancel(String accountid, Integer money)throws Exception;
}

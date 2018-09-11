package org.tccdemo.easytransation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tccdemo.easytransation.pay.api.PayRequest;
import org.tccdemo.easytransation.pub.OrderVO;

import com.yiqiniu.easytrans.protocol.tcc.TccMethod;

@Component
public class PayServiceImpl implements TccMethod<PayRequest, OrderVO>{

	public static final String METHOD_NAME="pay";
	
	@Autowired
	private PayTccService2 payService;

	public OrderVO doTry(PayRequest orderVO) {
		try {
			payService.pay(orderVO.getOrderVo().getAccountid(), orderVO.getOrderVo().getMoney());
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
		return null;
	}
	@Override
	public void doConfirm(PayRequest orderVO) {
		try {
			payService.confirm(orderVO.getOrderVo().getAccountid(), orderVO.getOrderVo().getMoney());
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
	}


	@Override
	public void doCancel(PayRequest orderVO) {
		try {
			payService.cancel(orderVO.getOrderVo().getAccountid(), orderVO.getOrderVo().getMoney());
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
	}
	
	@Override
	public int getIdempotentType() {
		return IDENPOTENT_TYPE_BUSINESS;
	}

	
}

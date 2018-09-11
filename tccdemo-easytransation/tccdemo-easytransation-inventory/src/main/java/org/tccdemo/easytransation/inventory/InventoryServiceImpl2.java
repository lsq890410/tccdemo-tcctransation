package org.tccdemo.easytransation.inventory;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tccdemo.easytransation.inventory.api.InventoryRequest2;
import org.tccdemo.easytransation.order.request.InventorySendRequest;
import org.tccdemo.easytransation.pub.OrderVO;

import com.yiqiniu.easytrans.core.EasyTransFacade;
import com.yiqiniu.easytrans.protocol.tcc.TccMethod;

@Service("inventoryService2")
public class InventoryServiceImpl2 implements TccMethod<InventoryRequest2, OrderVO>{
	@Resource
	private EasyTransFacade transaction;
	@Autowired
	private InventoryTccService inventoryService;
	@Override
	public OrderVO doTry(InventoryRequest2 orderVO) {
		try {
			inventoryService.delivery(orderVO.getOrderVo().getInventoryid());
			InventorySendRequest sendRequest = new InventorySendRequest();
			sendRequest.setOrderVo(orderVO.getOrderVo());
			transaction.execute(sendRequest).get();
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
		return null;
	}
	@Override
	public void doConfirm(InventoryRequest2 orderVO) {
		try {
			inventoryService.confirm(orderVO.getOrderVo().getInventoryid());
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
	}


	@Override
	public void doCancel(InventoryRequest2 orderVO) {
		try {
			inventoryService.cancel(orderVO.getOrderVo().getInventoryid());
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
	}
	
	@Override
	public int getIdempotentType() {
		return IDENPOTENT_TYPE_BUSINESS;
	}
}

package org.tccdemo.easytransation.inventorysend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tccdemo.easytransation.order.request.InventorySendRequest;
import org.tccdemo.easytransation.pub.OrderVO;

import com.yiqiniu.easytrans.protocol.tcc.TccMethod;

@Service("inventorySendService")
public class InventorySendServiceImpl implements TccMethod<InventorySendRequest, OrderVO>{
	@Autowired
	private InventorySendTccService inventoryService;
	@Override
	public OrderVO doTry(InventorySendRequest orderVO) {
		try {
			inventoryService.send(orderVO.getOrderVo().getInventoryid());
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
		return null;
	}
	@Override
	public void doConfirm(InventorySendRequest orderVO) {
		try {
			inventoryService.confirm(orderVO.getOrderVo().getInventoryid());
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
	}


	@Override
	public void doCancel(InventorySendRequest orderVO) {
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

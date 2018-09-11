package org.tccdemo.easytransation.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tccdemo.easytransation.inventory.api.InventoryRequest;
import org.tccdemo.easytransation.pub.OrderVO;

import com.yiqiniu.easytrans.protocol.tcc.TccMethod;

@Service("inventoryService")
public class InventoryServiceImpl implements TccMethod<InventoryRequest, OrderVO>{
	@Autowired
	private InventoryTccService2 inventoryService;
	@Override
	public OrderVO doTry(InventoryRequest orderVO) {
		try {
			inventoryService.delivery(orderVO.getOrderVo().getInventoryid());
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
		return null;
	}
	@Override
	public void doConfirm(InventoryRequest orderVO) {
		try {
			inventoryService.confirm(orderVO.getOrderVo().getInventoryid());
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
	}


	@Override
	public void doCancel(InventoryRequest orderVO) {
		try {
			inventoryService.cancel(orderVO.getOrderVo().getInventoryid());
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
	}
	
	@Override
	public int getIdempotentType() {
		return IDENPOTENT_TYPE_FRAMEWORK;
	}
}

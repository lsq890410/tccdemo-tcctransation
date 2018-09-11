package org.tccdemo.easytransation.inventory.api;

import org.tccdemo.easytransation.pub.OrderVO;

import com.yiqiniu.easytrans.protocol.BusinessIdentifer;
import com.yiqiniu.easytrans.protocol.tcc.TccMethodRequest;
@BusinessIdentifer(appId="inventory-service",busCode="inventory",rpcTimeOut=2000)
public class InventoryRequest implements TccMethodRequest<OrderVO> {
	private OrderVO orderVo;

	public OrderVO getOrderVo() {
		return orderVo;
	}

	public void setOrderVo(OrderVO orderVo) {
		this.orderVo = orderVo;
	}
	
}

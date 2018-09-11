package org.tccdemo.easytransation.inventorysend.api;

import org.tccdemo.easytransation.pub.OrderVO;

import com.yiqiniu.easytrans.protocol.BusinessIdentifer;
import com.yiqiniu.easytrans.protocol.tcc.TccMethodRequest;
@BusinessIdentifer(appId="inventorysend-service",busCode="pay",rpcTimeOut=2000)
public class InventorySendRequest implements TccMethodRequest<OrderVO> {
	private OrderVO orderVo;

	public OrderVO getOrderVo() {
		return orderVo;
	}

	public void setOrderVo(OrderVO orderVo) {
		this.orderVo = orderVo;
	}
	
}

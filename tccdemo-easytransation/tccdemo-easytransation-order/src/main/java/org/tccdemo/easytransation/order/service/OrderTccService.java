package org.tccdemo.easytransation.order.service;

import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tccdemo.easytransation.inventory.api.InventoryRequest;
import org.tccdemo.easytransation.inventory.api.InventoryRequest2;
import org.tccdemo.easytransation.pay.api.PayRequest;
import org.tccdemo.easytransation.pub.OrderVO;

import com.yiqiniu.easytrans.core.EasyTransFacade;

@Component
public class OrderTccService {
	public static final String BUSINESS_CODE = "order-service";
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Resource
	private EasyTransFacade transaction;
	@Transactional
	public String addOrder(OrderVO orderVO) throws Exception {
		System.out.println("try");
		//更新状态为初始化状态
		String sql  = "update easytransation_order set status = 0 where orderno = '"+ orderVO.getOrderno()+"'";
		jdbcTemplate.execute(sql);
		long time = System.currentTimeMillis();
		transaction.startEasyTrans(BUSINESS_CODE,time);
		InventoryRequest  request = new InventoryRequest();
		request.setOrderVo(orderVO);
		Future<OrderVO> deductFuture = transaction.execute(request);
		PayRequest  request2 = new PayRequest();
//		deductFuture.get();
		request2.setOrderVo(orderVO);
		Future<OrderVO> deductFuture2 = transaction.execute(request2);
//		deductFuture2.get();
		confirm(orderVO);
//		payService.pay(orderVO.getAccountid(), orderVO.getMoney());
//		inventoryService.delivery(orderVO.getInventoryid());
		return "success";
	}
	
	@Transactional
	public String addOrder2(OrderVO orderVO) throws Exception {
		System.out.println("try");
		//更新状态为初始化状态
		String sql  = "update easytransation_order set status = 0 where orderno = '"+ orderVO.getOrderno()+"'";
		jdbcTemplate.execute(sql);
		long time = System.currentTimeMillis();
		transaction.startEasyTrans(BUSINESS_CODE, System.currentTimeMillis());
		orderVO.setAccountid(time+"");
		orderVO.setInventoryid(time+"");
		InventoryRequest2  request = new InventoryRequest2();
		request.setOrderVo(orderVO);
		Future<OrderVO> deductFuture = transaction.execute(request);
		deductFuture.get();
//		PayRequest  request2 = new PayRequest();
//		request2.setOrderVo(orderVO);
//		Future<OrderVO> deductFuture2 = transaction.execute(request2);
		confirm(orderVO);
//		payService.pay(orderVO.getAccountid(), orderVO.getMoney());
//		inventoryService.delivery(orderVO.getInventoryid());
		return "success";
	}
	
	public void confirm(OrderVO orderVO) throws Exception {
		System.out.println("confirm");
		//更新状态为确认状态
		String sql  = "update tcctransation_order set status = 1 where orderno = '"+ orderVO.getOrderno()+"'";
		jdbcTemplate.update(sql);
	}
//	
//	public void cancel(OrderVO orderVO) throws Exception {
//		System.out.println("cancel");
//		//更新状态为删除状态
//		String sql  = "update tcctransation_order set status = 2 where orderno = '"+ orderVO.getOrderno()+"'";
//		jdbcTemplate.update(sql);
//	}
}

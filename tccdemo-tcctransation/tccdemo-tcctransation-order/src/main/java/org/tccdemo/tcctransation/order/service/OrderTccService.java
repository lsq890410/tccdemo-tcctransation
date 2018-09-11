package org.tccdemo.tcctransation.order.service;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.tccdemo.tcctransation.inventory.service.IInventoryService;
import org.tccdemo.tcctransation.inventory.service.IInventoryService2;
import org.tccdemo.tcctransation.order.vo.OrderVO;
import org.tccdemo.tcctransation.pay.service.IPayService;

@Component
public class OrderTccService {
	@Autowired
	@Qualifier("busiTemplate")
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private IPayService payService;
	@Autowired
	private IInventoryService inventoryService;
	@Autowired
	private IInventoryService2 inventoryService2;
	@Compensable(confirmMethod = "confirm", cancelMethod = "cancel")
	public String addOrder(OrderVO orderVO) throws Exception {
		System.out.println("try");
		//更新状态为初始化状态
		String sql  = "update tcctransation_order set status = 0 where orderno = '"+ orderVO.getOrderno()+"'";
		jdbcTemplate.execute(sql);
		payService.pay(orderVO.getAccountid(), orderVO.getMoney());
		inventoryService.delivery(orderVO.getInventoryid());
		return "success";
	}
	
	@Compensable(confirmMethod = "confirm", cancelMethod = "cancel")
	public String addOrder2(OrderVO orderVO) throws Exception {
		System.out.println("try");
		//更新状态为初始化状态
		String sql  = "update tcctransation_order set status = 0 where orderno = '"+ orderVO.getOrderno()+"'";
		jdbcTemplate.execute(sql);
		inventoryService2.delivery(orderVO.getInventoryid());
//		int i=1/0;
		return "success";
	}
	
	public void confirm(OrderVO orderVO) throws Exception {
		System.out.println("confirm");
		//更新状态为确认状态
		String sql  = "update tcctransation_order set status = 1 where orderno = '"+ orderVO.getOrderno()+"'";
		jdbcTemplate.update(sql);
	}
	
	public void cancel(OrderVO orderVO) throws Exception {
		System.out.println("cancel");
		//更新状态为删除状态
		String sql  = "update tcctransation_order set status = 2 where orderno = '"+ orderVO.getOrderno()+"'";
		jdbcTemplate.update(sql);
	}
}

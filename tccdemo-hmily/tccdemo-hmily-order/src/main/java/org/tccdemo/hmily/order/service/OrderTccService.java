package org.tccdemo.hmily.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.tccdemo.hmily.inventory.service.IInventoryService;
import org.tccdemo.hmily.inventory.service.IInventoryService2;
import org.tccdemo.hmily.order.vo.OrderVO;
import org.tccdemo.hmily.pay.service.IPayService;

import com.hmily.tcc.annotation.Tcc;

@Component
public class OrderTccService {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private IPayService payService;
	@Autowired
	private IInventoryService inventoryService;
	@Autowired
	private IInventoryService2 inventoryService2;
	@Tcc(confirmMethod = "confirm", cancelMethod = "cancel")
	public String addOrder(OrderVO orderVO) throws Exception {
		//更新状态为初始化状态
		String sql  = "update hmily_order set status = 0 where orderno = '"+ orderVO.getOrderno()+"'";
		jdbcTemplate.execute(sql);
		payService.pay(orderVO.getAccountid(), orderVO.getMoney());
		inventoryService.delivery(orderVO.getInventoryid());
		return "success";
	}
	@Tcc(confirmMethod = "confirm", cancelMethod = "cancel")
	public String addOrder2(OrderVO orderVO) throws Exception {
		//更新状态为初始化状态
		String sql  = "update hmily_order set status = 0 where orderno = '"+ orderVO.getOrderno()+"'";
		jdbcTemplate.execute(sql);
		inventoryService2.delivery(orderVO.getInventoryid());
		return "success";
	}
	
	public void confirm(OrderVO orderVO) throws Exception {
		//更新状态为确认状态
		String sql  = "update hmily_order set status = 1 where orderno = '"+ orderVO.getOrderno()+"'";
		jdbcTemplate.update(sql);
//		new BaseDAO().executeUpdate(sql);
	}
	
	public void cancel(OrderVO orderVO) throws Exception {
		//更新状态为删除状态
		String sql  = "update hmily_order set status = 2 where orderno = '"+ orderVO.getOrderno()+"'";
		jdbcTemplate.update(sql);
//		new BaseDAO().executeUpdate(sql);
	}
}

package org.tccdemo.easytransation.order.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.tccdemo.easytransation.pub.OrderVO;

@Component
public class OrderService {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private OrderTccService orderTccService;
	public void orderPay(String userid,String inventoryid,String accountid,Integer money) throws Exception {
		String id = UUID.randomUUID().toString();
		OrderVO orderVO =  getOrderVO(id, userid, inventoryid, accountid, Integer.valueOf(money));
		orderVO.setAccountid(id);
		orderVO.setInventoryid(id);
		String sql = "insert into easytransation_order(userid,orderno,inventoryid,accountid,momey)values(?,?,?,?,?);";
		jdbcTemplate.update(sql,orderVO.getUserid(),orderVO.getOrderno(),orderVO.getInventoryid(),orderVO.getAccountid(),orderVO.getMoney());
		orderTccService.addOrder(orderVO);
	}
	
	public void orderPay2(String userid,String inventoryid,String accountid,Integer money) throws Exception {
		OrderVO orderVO =  getOrderVO(UUID.randomUUID().toString(), userid, inventoryid, accountid, Integer.valueOf(money));
		String sql = "insert into easytransation_order(userid,orderno,inventoryid,accountid,momey)values(?,?,?,?,?);";
		jdbcTemplate.update(sql,orderVO.getUserid(),orderVO.getOrderno(),orderVO.getInventoryid(),orderVO.getAccountid(),orderVO.getMoney());
		orderTccService.addOrder2(orderVO);
	}
	
	private OrderVO getOrderVO(String orderno,String userId,String inventoryid,String accountid,Integer money) {
		return new OrderVO(orderno, userId, inventoryid, accountid, money);
	}
}

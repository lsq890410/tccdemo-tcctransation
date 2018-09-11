package org.tccdemo.tcctransation.order.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.tccdemo.tcctransation.order.vo.OrderVO;

@Component
public class OrderService {
	@Autowired
	@Qualifier("busiTemplate")
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private OrderTccService orderTccService;
	public void orderPay(String userid,String inventoryid,String accountid,Integer money) throws Exception {
		OrderVO orderVO =  getOrderVO(UUID.randomUUID().toString(), userid, inventoryid, accountid, Integer.valueOf(money));
		String id = UUID.randomUUID().toString();
		orderVO.setAccountid(id);
		orderVO.setInventoryid(id);
		String sql = "insert into tcctransation_order(userid,orderno,inventoryid,accountid,momey)values(?,?,?,?,?);";
		jdbcTemplate.update(sql,orderVO.getUserid(),orderVO.getOrderno(),orderVO.getInventoryid(),orderVO.getAccountid(),orderVO.getMoney());
		orderTccService.addOrder(orderVO);
	}
	
	public void orderPay2(String userid,String inventoryid,String accountid,Integer money) throws Exception {
		OrderVO orderVO =  getOrderVO(UUID.randomUUID().toString(), userid, inventoryid, accountid, Integer.valueOf(money));
		String sql = "insert into tcctransation_order(userid,orderno,inventoryid,accountid,momey)values(?,?,?,?,?);";
		jdbcTemplate.update(sql,orderVO.getUserid(),orderVO.getOrderno(),orderVO.getInventoryid(),orderVO.getAccountid(),orderVO.getMoney());
		orderTccService.addOrder2(orderVO);
	}
	
	private OrderVO getOrderVO(String orderno,String userId,String inventoryid,String accountid,Integer money) {
		return new OrderVO(orderno, userId, inventoryid, accountid, money);
	}
}

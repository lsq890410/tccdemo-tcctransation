package org.tccdemo.tcctransation.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tccdemo.tcctransation.order.service.OrderService;



@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@PostMapping(value = "/orderPay")
	public String orderPay(@RequestParam(value = "userid") String userid,@RequestParam(value = "inventoryid") String inventoryid
			,@RequestParam(value = "accountid") String accountid,@RequestParam(value = "money") Integer money) throws Exception {
		orderService.orderPay(userid, inventoryid, accountid, money);
		return "success";
	}
	
	@PostMapping(value = "/orderPay2")
	public String orderPay2(@RequestParam(value = "userid") String userid,@RequestParam(value = "inventoryid") String inventoryid
			,@RequestParam(value = "accountid") String accountid,@RequestParam(value = "money") Integer money) throws Exception {
		orderService.orderPay2(userid, inventoryid, accountid, money);
		return "success";
	}
}

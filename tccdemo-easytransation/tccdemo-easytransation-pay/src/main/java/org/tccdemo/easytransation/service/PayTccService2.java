package org.tccdemo.easytransation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class PayTccService2 {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	public void pay(String accountid, Integer money) throws Exception {
		System.out.println("try");
		String sql = "insert into easytransation_account (freeze,accountid,money) values(? ,?,?)";
		jdbcTemplate.update(sql, money,accountid,0);
	}
	
	public void confirm(String accountid, Integer money) throws Exception {
		System.out.println("confirm");
		String sql = "update easytransation_account set  freeze = freeze -"+money+",money = money - "+money+"  where accountid = ?";
		jdbcTemplate.update(sql, accountid);
	}
	
	public void cancel(String accountid, Integer money) throws Exception {
		System.out.println("cancel");
		String sql = "update easytransation_account set  freeze = freeze - "+money+" where accountid = ?";
		jdbcTemplate.update(sql, accountid);
	}
}

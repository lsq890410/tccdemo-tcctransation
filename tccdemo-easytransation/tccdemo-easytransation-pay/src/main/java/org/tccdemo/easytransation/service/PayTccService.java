package org.tccdemo.easytransation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class PayTccService {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	public void pay(String accountid, Integer money) throws Exception {
		System.out.println("try");
		String sql = "update easytransation_account set money = 0, freeze = freeze + ? where accountid = ?";
		jdbcTemplate.update(sql, money,accountid);
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

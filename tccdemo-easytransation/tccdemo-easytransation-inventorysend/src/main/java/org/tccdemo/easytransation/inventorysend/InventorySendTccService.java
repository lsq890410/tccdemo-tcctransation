package org.tccdemo.easytransation.inventorysend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventorySendTccService {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	public void send(String inventoryid) throws Exception {
		System.out.println("try");
		String sql = "update tcctransation_inventorysend set status = 0 where inventoryid = ?";
		jdbcTemplate.update(sql, inventoryid);
	}

	public void confirm(String inventoryid) throws Exception {
		System.out.println("confirm");
		String sql = "update tcctransation_inventorysend set status = 1,number = number+1 where inventoryid = ?";
		jdbcTemplate.update(sql, inventoryid);
	}

	public void cancel(String inventoryid) throws Exception {
		System.out.println("cancel");
		String sql = "update tcctransation_inventorysend set status = 2 where inventoryid = ?";
		jdbcTemplate.update(sql, inventoryid);
	}
}

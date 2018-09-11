package org.tccdemo.tcctransation.inventory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryTccService {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	public void delivery(String inventoryid) throws Exception {
		System.out.println("try");
		String sql = "update tcctransation_inventory set freeze = freeze + 1 where inventoryid = ?";
		jdbcTemplate.update(sql, inventoryid);
	}

	public void confirm(String inventoryid) throws Exception {
		System.out.println("confirm");
		String sql = "update tcctransation_inventory set  freeze = freeze - 1,nnumber = nnumber-1  where inventoryid = ?";
		jdbcTemplate.update(sql, inventoryid);
	}

	public void cancel(String inventoryid) throws Exception {
		System.out.println("cancel");
		String sql = "update tcctransation_inventory set  freeze = freeze - 1 where inventoryid = ?";
		jdbcTemplate.update(sql, inventoryid);
	}
}

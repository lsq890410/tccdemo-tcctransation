package org.tccdemo.easytransation.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryTccService2 {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	public void delivery(String inventoryid) throws Exception {
		System.out.println("try");
		String sql = "insert into easytransation_inventory (inventoryid,freeze,nnumber) values (?,?,?)";
		jdbcTemplate.update(sql, inventoryid,1,1);
	}

	public void confirm(String inventoryid) throws Exception {
		System.out.println("confirm");
		String sql = "update easytransation_inventory set  freeze = freeze - 1,nnumber = nnumber-1  where inventoryid = ?";
		jdbcTemplate.update(sql, inventoryid);
	}

	public void cancel(String inventoryid) throws Exception {
		System.out.println("cancel");
		String sql = "update easytransation_inventory set  freeze = freeze - 1 where inventoryid = ?";
		jdbcTemplate.update(sql, inventoryid);
	}
}

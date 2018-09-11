package org.tccdemo.hmily.order.vo;

import java.io.Serializable;

public class OrderVO implements Serializable{
	public OrderVO() {
		
	}
	public OrderVO(String orderno,String userid,String inventoryid, String accountid,Integer momey) {
		this.orderno = orderno;
		this.userid = userid;
		this.inventoryid = inventoryid;
		this.accountid = accountid;
		this.money = momey;
	}
	private String orderno;
	private String userid ;
	private String inventoryid;
	private String accountid;
	private Integer money;
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getInventoryid() {
		return inventoryid;
	}
	public void setInventoryid(String inventoryid) {
		this.inventoryid = inventoryid;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

}

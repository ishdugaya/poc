package com.mocipoc.model;
import java.io.Serializable;

public class OrderModel implements Serializable,Comparable<OrderModel>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String orderType;
	private String orderTimeStamp;
	
	
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderTimeStamp() {
		return orderTimeStamp;
	}
	public void setOrderTimeStamp(String orderTimeStamp) {
		this.orderTimeStamp = orderTimeStamp;
	}
	
	@Override
	public String toString() {
		return "OrderModel [orderType=" + orderType + ", orderTimeStamp=" + orderTimeStamp + "]";
	}
	

	public OrderModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderModel(String orderType, String orderTimeStamp) {
		super();
		this.orderType = orderType;
		this.orderTimeStamp = orderTimeStamp;
	}
	
	@Override
	public int compareTo(OrderModel o) {
		if(this.getOrderTimeStamp().equals(o.orderTimeStamp)){
			return o.orderType.compareTo(this.orderType);
		}
		return 0;
	}
	
	
	
	
	
}

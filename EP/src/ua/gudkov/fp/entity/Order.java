package ua.gudkov.fp.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Order entity.
 * 
 * @author A.Gudkov
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private Date orderDate;
	private long userId;
	private long tourId;
	private double totalCost;
	private OrderStatus orderStatus;

	public Order(long id, Date orderDate, long userId, long tourId, double totalCost, OrderStatus orderStatus) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.userId = userId;
		this.tourId = tourId;
		this.totalCost = totalCost;
		this.orderStatus = orderStatus;
	}

	public Order() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTourId() {
		return tourId;
	}

	public void setTourId(long tourId) {
		this.tourId = tourId;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", userId=" + userId + ", tourId=" + tourId
				+ ", totalCost=" + totalCost + ", orderStatus=" + orderStatus + "]";
	}

}

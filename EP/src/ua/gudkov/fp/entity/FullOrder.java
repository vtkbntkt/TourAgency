package ua.gudkov.fp.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Represents order entity combined with order status entity, and user entity.
 * 
 * @author A.Gudkov
 *
 */
public class FullOrder implements Serializable {
	private static final long serialVersionUID = 293459353258797547L;
	private long id;
	private Date orderDate;
	private long userId;
	private long tourId;
	private double totalCost;
	private OrderStatus orderStatus;
	private String firstName;
	private String lastName;

	public FullOrder(long id, Date orderDate, long userId, long tourId, double totalCost, OrderStatus orderStatus,
			String firstName, String lastName) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.userId = userId;
		this.tourId = tourId;
		this.totalCost = totalCost;
		this.orderStatus = orderStatus;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public FullOrder() {
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "FullOrder [id=" + id + ", orderDate=" + orderDate + ", userId=" + userId + ", tourId=" + tourId
				+ ", totalCost=" + totalCost + ", orderStatus=" + orderStatus + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

}

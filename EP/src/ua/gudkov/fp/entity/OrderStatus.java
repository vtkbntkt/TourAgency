package ua.gudkov.fp.entity;

/**
 * Order status entity.
 * 
 * @author A.Gudkov
 *
 */
public enum OrderStatus {
	REGISTERED("registered"), PAID("paid"), CANCELED("canceled");

	private String value;

	OrderStatus(String value) {
		this.value = value;
	}

	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}

	public String getName() {
		return name().toLowerCase();
	}

	public int getId() {
		return ordinal();
	}

	/**
	 * Returns order status value as String. Returns null if the name is not
	 * equals any order status values.
	 * 
	 * @param name
	 *            order status
	 * @return order status value, or null if the name is not equals any order
	 *         status values.
	 */
	public static String getValue(String name) {
		for (OrderStatus status : OrderStatus.values()) {
			if (status.getName().equals(name))
				return status.value;
		}
		return null;
	}

}

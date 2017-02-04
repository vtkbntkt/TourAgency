package ua.gudkov.fp.entity;

/**
 * Currency bank code entity.
 * 
 * @author A.Gudkov
 *
 */
public enum BankCode {

	USD("usd"), EUR("eur"), UAH("uah");

	private String value;

	BankCode(String value) {
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

	/**
	 * Returns currency bank code value as String. Returns null if the name is
	 * not equals any bank code values.
	 * 
	 * @param name
	 *            currency bank code
	 * @return bank code value, or null if the name is not equals any bank code
	 *         values.
	 */
	public static String getValue(String name) {
		for (BankCode bc : BankCode.values()) {
			if (bc.getName().equals(name))
				return bc.value;
		}
		return null;
	}

}

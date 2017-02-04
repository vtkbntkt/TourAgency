package ua.gudkov.fp.entity;

/**
 * Tour sorting entity.
 * 
 * @author A.Gudkov
 *
 */
public enum Sorting {

	COST("price"), RATING("raiting"), DATE("dept_date");

	private String value;

	Sorting(String value) {
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
	 * Returns sorting value as String. Returns null if the name is not equals
	 * any sorting values.
	 * 
	 * @param name
	 *            sorting
	 * @return sorting value, or null if the name is not equals any sorting
	 *         values.
	 */
	public static String getValue(String sorting) {
		for (Sorting s : Sorting.values()) {
			if (s.getName().equals(sorting))
				return s.value;
		}
		return null;
	}

}

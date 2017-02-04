package ua.gudkov.fp.entity;

/**
 * Tour type entity.
 * 
 * @author A.Gudkov
 *
 */
public enum Type {
	TRIP, SHOPPING, REST;

	public static Type getType(Tour tour) {
		int typeId = tour.getTypeId();
		return Type.values()[typeId];
	}

	public String getName() {
		return name().toLowerCase();
	}

	public int getId() {
		return ordinal();
	}

	/**
	 * Returns tour type index. Returns -1 if the type is not equals any tour
	 * type values.
	 * 
	 * @param type
	 *            type
	 * @return type index or -1 if the type is not equal any type values
	 */
	public static int getId(String type) {
		for (Type t : Type.values()) {
			if (t.getName().equals(type))
				return t.ordinal();
		}
		return -1;
	}
}

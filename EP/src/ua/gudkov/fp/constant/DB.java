package ua.gudkov.fp.constant;

/**
 * Constants related to application data base.
 * 
 * @author A.Gudkov
 *
 */
public class DB {

	/**
	 * Hotels table with column names.
	 */
	public static final class Hotels {
		public static final String HOTEL_TABLE_NAME = "hotels";
		public static final String ID = HOTEL_TABLE_NAME.concat(".id");
		public static final String NAME = HOTEL_TABLE_NAME.concat(".name");
		public static final String RATING = HOTEL_TABLE_NAME.concat(".raiting");
		public static final String COUNTRY = HOTEL_TABLE_NAME.concat(".country");
		public static final String CITY = HOTEL_TABLE_NAME.concat(".city");
		public static final String DESCRIPTION = HOTEL_TABLE_NAME.concat(".description");

		private Hotels() {
		}

	}

	/**
	 * Tour table with column names.
	 */
	public static final class Tour {
		public static final String TOUR_TABLE_NAME = "tours";
		public static final String ID = TOUR_TABLE_NAME.concat(".id");
		public static final String TYPE_ID = TOUR_TABLE_NAME.concat(".type_id");
		public static final String PRICE = TOUR_TABLE_NAME.concat(".price");
		public static final String PERSON_COUNT = TOUR_TABLE_NAME.concat(".person_count");
		public static final String HOTEL_ID = TOUR_TABLE_NAME.concat(".hotel_id");
		public static final String IS_HOT = TOUR_TABLE_NAME.concat(".ishot");
		public static final String DEPT_DATE = TOUR_TABLE_NAME.concat(".dept_date");
		public static final String NIGHT_COUNT = TOUR_TABLE_NAME.concat(".night_count");

		private Tour() {
		}
	}

}

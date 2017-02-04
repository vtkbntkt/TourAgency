package ua.gudkov.fp.util;

import org.apache.commons.lang3.StringUtils;

import ua.gudkov.fp.constant.DB;
import ua.gudkov.fp.entity.TourFilter;

/**
 * Sql statement builder implementation. Provides sql statement creating
 * according to filter form objects.
 * 
 * @author A.Gudkov
 *
 */
public class SqlBuilder {

	private SqlBuilder() {

	}

	/**
	 * Returns sql statement to select data according to tour filter form
	 * parameters.
	 * 
	 * @param filter
	 *            tour filter
	 * @return statement
	 */
	public static String selectQuery(TourFilter filter) {
		SelectBuilder query = new SelectBuilder();
		addColumns(query);
		addJoins(query);
		addConditions(query, filter);
		addSorting(query, filter);
		addLimit(query, filter);
		return query.toString();
	}

	/**
	 * Returns sql statement to found tour by its id.
	 * 
	 * @param id
	 *            tour id
	 * @return statement
	 */
	public static String selectQuery(long id) {
		SelectBuilder query = new SelectBuilder();
		addColumns(query);
		addJoins(query);
		addCondition(query, id);
		return query.toString();
	}

	/**
	 * Returns sql statement to receive number of results according to given
	 * tour filter form parameters.
	 * 
	 * @param filter
	 *            tour filter
	 * @return statement
	 */
	public static String countQuery(TourFilter filter) {
		SelectBuilder query = new SelectBuilder();
		addCount(query);
		addJoins(query);
		addConditions(query, filter);
		return query.toString();
	}

	/**
	 * Adds in select builder conditions.
	 * 
	 * @param sb
	 *            select builder
	 * @param id
	 *            tour id
	 */
	private static void addCondition(SelectBuilder sb, long id) {
		sb.where(SelectBuilder.Eq(DB.Tour.ID, String.valueOf(id)));
	}

	/**
	 * Adds in select builder count functions
	 * 
	 * @param sb
	 *            select builder
	 */
	private static void addCount(SelectBuilder sb) {
		sb.column(SelectBuilder.CountFunc("total"));
	}

	/**
	 * Adds in select builder columns
	 * 
	 * @param sb
	 *            select builder
	 */
	private static void addColumns(SelectBuilder sb) {
		sb.column(DB.Tour.ID).column(DB.Tour.PRICE).column(DB.Tour.PERSON_COUNT).column(DB.Tour.IS_HOT)
				.column(DB.Tour.DEPT_DATE).column(DB.Tour.NIGHT_COUNT).column(DB.Tour.TYPE_ID).column(DB.Hotels.ID)
				.column(DB.Hotels.NAME).column(DB.Hotels.RATING).column(DB.Hotels.COUNTRY).column(DB.Hotels.CITY)
				.column(DB.Hotels.DESCRIPTION);

	}

	/**
	 * Adds in select builder 'join' attributes
	 * 
	 * @param sb
	 *            select builder
	 */
	private static void addJoins(SelectBuilder sb) {
		sb.from(DB.Tour.TOUR_TABLE_NAME)
				.join(SelectBuilder.On(DB.Hotels.HOTEL_TABLE_NAME, DB.Tour.HOTEL_ID, DB.Hotels.ID));
	}

	/**
	 * Adds in select builder condition operators with arguments according to
	 * given tour filter parameters.
	 * 
	 * @param sb
	 *            select builder
	 * @param filter
	 *            tour filter
	 */
	private static void addConditions(SelectBuilder sb, TourFilter filter) {
		String country = filter.getCountry();
		int type = filter.getType();
		int costFrom = filter.getCostFrom();
		int costTo = filter.getCostTo();
		int personNum = filter.getPersonNum();
		int rating = filter.getRating();
		int nightNum = filter.getNightNum();
		String deptDate = filter.getDeptDate();

		if (!StringUtils.isEmpty(country)) {
			sb.where(SelectBuilder.Eq(DB.Hotels.COUNTRY, country));
		}

		if (type >= 0) {
			sb.where(SelectBuilder.Eq(DB.Tour.TYPE_ID, String.valueOf(type)));
		}

		if (costFrom > 0 && costTo > 0) {
			sb.where(SelectBuilder.Between(DB.Tour.PRICE, String.valueOf(costFrom), String.valueOf(costTo)));
		} else if (costFrom > 0) {
			sb.where(SelectBuilder.Greater(DB.Tour.PRICE, String.valueOf(costFrom)));

		} else if (costTo > 0) {
			sb.where(SelectBuilder.Less(DB.Tour.PRICE, String.valueOf(costTo)));
		}

		if (personNum > 0) {
			sb.where(SelectBuilder.Eq(DB.Tour.PERSON_COUNT, String.valueOf(personNum)));
		}

		if (rating > 0) {
			sb.where(SelectBuilder.Eq(DB.Hotels.RATING, String.valueOf(rating)));
		}

		if (nightNum > 0) {
			sb.where(SelectBuilder.Eq(DB.Tour.NIGHT_COUNT, String.valueOf(nightNum)));
		}

		if (!StringUtils.isEmpty(deptDate)) {
			sb.where(SelectBuilder.Eq(DB.Tour.DEPT_DATE, String.valueOf(deptDate)));

		} else {
			sb.where(SelectBuilder.Greater(DB.Tour.DEPT_DATE, "CURDATE()"));
		}
	}

	/**
	 * Adds in the select builder 'sorting' attributes
	 * 
	 * @param sb
	 *            select builder
	 * @param filter
	 *            tour filter
	 */
	private static void addSorting(SelectBuilder sb, TourFilter filter) {
		String sortBy = filter.getSortBy();
		sb.orderBy(DB.Tour.IS_HOT, false);
		if (sortBy != null) {
			sb.orderBy(sortBy);
		}

	}

	/**
	 * Adds in the select builder 'limit' attributes
	 * 
	 * @param sb
	 *            select builder
	 * @param filter
	 *            tour filter
	 */
	private static void addLimit(SelectBuilder sb, TourFilter filter) {
		int itemsPerPage = filter.getItemsPerPage();
		int currentPage = filter.getCurrentPage();
		int fromPos = (currentPage - 1) * itemsPerPage;
		sb.limit(fromPos + "," + itemsPerPage);
	}
}
package ua.gudkov.fp.converter;

import org.apache.commons.lang3.StringUtils;

import ua.gudkov.fp.bean.TourFilterBean;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.Sorting;
import ua.gudkov.fp.entity.TourFilter;
import ua.gudkov.fp.entity.Type;

/**
 * Tour filter form bean to tour filter entity converter. The class contains
 * method to convert displayed tour cost to real according to current currency
 * rate. Also the convert method provides data validation.
 * 
 * @author A.Gudkov
 *
 */
public class TourFilterFormBeanToTourFilter implements Converter<TourFilterBean, TourFilter> {

	@Override
	public TourFilter convert(TourFilterBean tfb) {
		TourFilter tourFilter = new TourFilter();

		String country = tfb.getCountry();
		String type = tfb.getType();
		String costFrom = tfb.getCostFrom();
		String costTo = tfb.getCostTo();
		String personNum = tfb.getPersonNum();
		String rating = tfb.getRating();
		String nightNum = tfb.getNightNum();
		String deptDate = tfb.getDeptDate();
		String sortBy = tfb.getSortBy();
		String itemsPerPage = tfb.getItemsPerPage();
		String currentPage = tfb.getCurrentPage();
		String rate = tfb.getRate();

		if (!StringUtils.isEmpty(country)) {
			tourFilter.setCountry(country);
		}

		if (!StringUtils.isEmpty(type)) {
			tourFilter.setType(Type.getId(type));
		} else {
			tourFilter.setType(-1);

		}

		if (StringUtils.isNumeric(costFrom)) {
			tourFilter.setCostFrom(convertCost(rate, Integer.parseInt(costFrom)));
		}

		if (StringUtils.isNumeric(costTo)) {
			tourFilter.setCostTo(convertCost(rate, Integer.parseInt(costTo)));
		}

		if (StringUtils.isNumeric(personNum)) {
			tourFilter.setPersonNum(Integer.parseInt(personNum));
		}
		if (StringUtils.isNumeric(rating)) {
			tourFilter.setRating(Integer.parseInt(rating));
		}
		if (StringUtils.isNumeric(nightNum)) {
			tourFilter.setNightNum(Integer.parseInt(nightNum));
		}

		if (!StringUtils.isEmpty(deptDate)) {
			tourFilter.setDeptDate(deptDate);
		}

		if (!StringUtils.isEmpty(sortBy)) {
			String sorting = Sorting.getValue(sortBy);
			if (sorting != null) {
				tourFilter.setSortBy(sorting);
			}
		}

		if (StringUtils.isNumeric(itemsPerPage)) {
			tourFilter.setItemsPerPage(Integer.parseInt(itemsPerPage));
		}

		if (StringUtils.isNumeric(currentPage)) {
			tourFilter.setCurrentPage(Integer.parseInt(currentPage));
		}

		return tourFilter;
	}

	/**
	 * Returns cost converted according to current currency rate.
	 * 
	 * @param rate
	 *            current currency rate
	 * @param cost
	 *            cost inputed by user using convenient currency
	 * @return cost
	 */
	private int convertCost(String rate, int cost) {
		int convertedValue = cost;
		if (rate != null) {
			convertedValue = new Double(cost / Double.valueOf(rate)).intValue();
		}
		return convertedValue;
	}

}

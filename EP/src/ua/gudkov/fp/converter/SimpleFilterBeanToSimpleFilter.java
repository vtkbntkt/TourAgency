package ua.gudkov.fp.converter;

import org.apache.commons.lang3.StringUtils;

import ua.gudkov.fp.bean.SimpleFilterFormBean;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.SimpleFilter;

/**
 * Simple filter form bean to simple filter entity converter. The converter
 * perform some calculation to determine needed values for Simple filter fields.
 * 
 * @author A.Gudkov
 *
 */
public class SimpleFilterBeanToSimpleFilter implements Converter<SimpleFilterFormBean, SimpleFilter> {

	@Override
	public SimpleFilter convert(SimpleFilterFormBean simpleFilterForm) {
		SimpleFilter simpleFilter = new SimpleFilter();
		int itemsPerPage = StringUtils.isNumeric(simpleFilterForm.getItemsPerPage())
				? Integer.valueOf(simpleFilterForm.getItemsPerPage()) : 10;
		long currentPage = StringUtils.isNumeric(simpleFilterForm.getCurrentPage())
				? Long.valueOf(simpleFilterForm.getCurrentPage()) : 1;
		simpleFilter.setRowNum(itemsPerPage);
		simpleFilter.setStartRow((currentPage - 1) * itemsPerPage);
		return simpleFilter;
	}

}

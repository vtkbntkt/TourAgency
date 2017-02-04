package ua.gudkov.fp.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.gudkov.fp.bean.TourFilterBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.extractor.api.Extractor;

/**
 * Tour filter form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class TourFilterFormExtractor implements Extractor<TourFilterBean> {

	@Override
	public TourFilterBean extract(HttpServletRequest request) throws IOException, ServletException {
		TourFilterBean tfb = new TourFilterBean();
		tfb.setCostFrom(request.getParameter(Const.TourFilterForm.COST_FROM));
		tfb.setCostTo(request.getParameter(Const.TourFilterForm.COST_TO));
		tfb.setCountry(request.getParameter(Const.TourFilterForm.COUNTRY));
		tfb.setDeptDate(request.getParameter(Const.TourFilterForm.DEPT_DATE));
		tfb.setNightNum(request.getParameter(Const.TourFilterForm.NIGHT_NUM));
		tfb.setPersonNum(request.getParameter(Const.TourFilterForm.PERSON_NUM));
		tfb.setRating(request.getParameter(Const.TourFilterForm.RATING));
		tfb.setSortBy(request.getParameter(Const.TourFilterForm.SORTING));
		tfb.setType(request.getParameter(Const.TourFilterForm.TYPE));
		tfb.setCurrentPage(request.getParameter(Const.TourFilterForm.CURRENT_PAGE));
		tfb.setItemsPerPage(request.getParameter(Const.TourFilterForm.ITEMS_PER_PAGE));
		return tfb;
	}

}

package ua.gudkov.fp.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.gudkov.fp.bean.TourFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.extractor.api.Extractor;

/**
 * Tour adding form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class TourFormExtractor implements Extractor<TourFormBean> {

	@Override
	public TourFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		TourFormBean tfb = new TourFormBean();
		tfb.setPart(request.getPart(Const.TourForm.FILE));
		tfb.setCost(request.getParameter(Const.TourForm.COST));
		tfb.setDate(request.getParameter(Const.TourForm.DATE));
		tfb.setHotel(request.getParameter(Const.TourForm.HOTEL));
		tfb.setNightNum(request.getParameter(Const.TourForm.NIGHTS));
		tfb.setPersonNum(request.getParameter(Const.TourForm.PERSONS));
		tfb.setType(request.getParameter(Const.TourForm.TYPE));
		return tfb;
	}

}

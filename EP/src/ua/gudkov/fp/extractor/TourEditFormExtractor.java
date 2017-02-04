package ua.gudkov.fp.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.gudkov.fp.bean.TourEditFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.extractor.api.Extractor;

/**
 * Tour edit form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class TourEditFormExtractor implements Extractor<TourEditFormBean> {

	@Override
	public TourEditFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		TourEditFormBean tefb = new TourEditFormBean();
		tefb.setPart(request.getPart(Const.TourEditForm.FILE));
		tefb.setCost(request.getParameter(Const.TourEditForm.COST));
		tefb.setDate(request.getParameter(Const.TourEditForm.DATE));
		tefb.setHotel(request.getParameter(Const.TourEditForm.HOTEL));
		tefb.setNightNum(request.getParameter(Const.TourEditForm.NIGHTS));
		tefb.setPersonNum(request.getParameter(Const.TourEditForm.PERSONS));
		tefb.setType(request.getParameter(Const.TourEditForm.TYPE));
		tefb.setId(request.getParameter(Const.TourEditForm.ID));
		return tefb;
	}

}

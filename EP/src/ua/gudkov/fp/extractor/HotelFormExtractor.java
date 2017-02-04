package ua.gudkov.fp.extractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import ua.gudkov.fp.bean.HotelFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.extractor.api.Extractor;

/**
 * Hotel form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class HotelFormExtractor implements Extractor<HotelFormBean> {

	@Override
	public HotelFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		HotelFormBean hfb = new HotelFormBean();
		hfb.setCity(request.getParameter(Const.HotelForm.SITY));
		hfb.setCountry(request.getParameter(Const.HotelForm.COUNTRY));
		hfb.setDescription(request.getParameter(Const.HotelForm.DESCRIPTION));
		hfb.setName(request.getParameter(Const.HotelForm.NAME));
		hfb.setRating(request.getParameter(Const.HotelForm.RAITING));

		List<Part> parts = new ArrayList<Part>();
		for (Part part : request.getParts()) {
			parts.add(part);
		}
		hfb.setParts(parts);
		return hfb;
	}

}

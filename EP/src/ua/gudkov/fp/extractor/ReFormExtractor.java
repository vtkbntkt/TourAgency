package ua.gudkov.fp.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.gudkov.fp.bean.ReFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.extractor.api.Extractor;

/**
 * Message replay form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class ReFormExtractor implements Extractor<ReFormBean> {

	@Override
	public ReFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		ReFormBean rmfb = new ReFormBean();
		rmfb.setEmail(request.getParameter(Const.ReplayingForm.EMAIL));
		rmfb.setMessage(request.getParameter(Const.ReplayingForm.MESSAGE));
		rmfb.setSubject(request.getParameter(Const.ReplayingForm.SUBJECT));
		return rmfb;
	}

}

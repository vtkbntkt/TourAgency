package ua.gudkov.fp.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


import ua.gudkov.fp.bean.ContactFormBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.extractor.api.Extractor;

/**
 * Contact form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class ContactFormExtractor implements Extractor<ContactFormBean> {

	@Override
	public ContactFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		ContactFormBean cfb = new ContactFormBean();
		cfb.setEmail(request.getParameter(Const.ContactForm.EMAIL));
		cfb.setMessage(request.getParameter(Const.ContactForm.MESSAGE));
		cfb.setName(request.getParameter(Const.ContactForm.NAME));
		cfb.setFilePart(request.getPart(Const.File.FILE_FIELD));
		return cfb;
	}

}

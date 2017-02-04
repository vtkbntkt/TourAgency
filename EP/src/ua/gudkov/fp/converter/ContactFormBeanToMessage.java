package ua.gudkov.fp.converter;



import ua.gudkov.fp.bean.ContactFormBean;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.Message;

/**
 * Contact form bean to message entity converter.
 * 
 * @author A.Gudkov
 *
 */
public class ContactFormBeanToMessage implements Converter<ContactFormBean, Message> {

	@Override
	public Message convert(ContactFormBean cfb) {
		Message msg = new Message();
		msg.setEmail(cfb.getEmail());
		msg.setMsg(cfb.getMessage());
		msg.setName(cfb.getName());
		return msg;
	}

}

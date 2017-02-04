package ua.gudkov.fp.converter;

import ua.gudkov.fp.bean.RegisterFormBean;
import ua.gudkov.fp.converter.api.Converter;
import ua.gudkov.fp.entity.Role;
import ua.gudkov.fp.entity.User;

/**
 * Registration form bean to user entity converter.
 * 
 * @author A.Gudkov
 *
 */
public class RegisterFormBeanToUser implements Converter<RegisterFormBean, User> {

	@Override
	public User convert(RegisterFormBean rfb) {
		User user = new User();
		user.setEmail(rfb.getEmail());
		user.setFirstName(rfb.getFirstName());
		user.setLastName(rfb.getLastName());
		user.setPassword(rfb.getPassword());
		user.setRoleId(Role.CLIENT.ordinal());
		return user;
	}

}

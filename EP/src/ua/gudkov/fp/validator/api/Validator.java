package ua.gudkov.fp.validator.api;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import ua.gudkov.fp.entity.ErrorHolder;

/**
 * Validator interface.
 * 
 * @author A.Gudkov
 *
 */
public interface Validator<E> {

	/**
	 * Validate user form bean.
	 *
	 * @param e
	 *            the form bean
	 * @return the error holder
	 */
	ErrorHolder validate(E e);

	/**
	 * Returns true if the text matches to the regular expression, returns false
	 * if it doesn`t.
	 * 
	 * @param inputText
	 *            input text
	 * @param regEx
	 *            regular expression
	 * @return true if the text matches to the regular expression, false if it
	 *         doesn`t
	 */
	default boolean isValidByPattern(String inputText, String regEx) {
		return !(StringUtils.isEmpty(inputText) || !Pattern.matches(regEx, inputText));
	}
	
	default boolean isEqual(String inputText1, String inputText2) {
        return !(StringUtils.isEmpty(inputText1) || StringUtils.isEmpty(inputText2)) && inputText1.equals(inputText2);
    }
}

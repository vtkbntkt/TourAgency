package ua.gudkov.fp.extractor.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Extractor interface.
 * 
 * @author A.Gudkov
 *
 */
public interface Extractor<T> {

	/**
	 * Extracts bean from request.
	 * 
	 * @param request
	 *            the http servlet request
	 * @return the extracted bean
	 * @throws IOException
	 * @throws ServletException
	 */
	T extract(HttpServletRequest request) throws IOException, ServletException;

}

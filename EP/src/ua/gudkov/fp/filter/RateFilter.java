package ua.gudkov.fp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.BankCode;
import ua.gudkov.fp.service.api.CurrencyService;

/**
 * Currency rate updating implementation.
 * 
 * @author A.Gudkov
 *
 */
@WebFilter({ "/tour", "/tours", "/orders" })
public class RateFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(RateFilter.class);
	private CurrencyService currencyService;

	/**
	 * Receives current rate according to current currency and put it into session.
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String currency = (String) session.getAttribute(Const.SessionAttr.CURR_ATTR);

		if (BankCode.getValue(currency) != null) {
			double rate = currencyService.findByName(currency).getRate();
			session.setAttribute(Const.SessionAttr.RATE_ATTR, rate);
		}
		chain.doFilter(request, response);

	}

	public void init(FilterConfig fConfig) throws ServletException {
		currencyService = (CurrencyService) fConfig.getServletContext()
				.getAttribute(Const.ContextAttr.CURRENCY_SERVICE);
		LOG.debug("Filter initialization finished");

	}

	public void destroy() {
		LOG.debug("Filter destruction finished");
	}

}

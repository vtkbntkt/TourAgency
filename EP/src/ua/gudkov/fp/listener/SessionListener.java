package ua.gudkov.fp.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.BankCode;
import ua.gudkov.fp.service.api.CurrencyService;

/**
 * Provides establishes default language, currency and currency rate values
 * establishing while a session creating.
 * 
 * @author A.Gudkov
 */
@WebListener
public class SessionListener implements HttpSessionListener {

	private static final Logger LOG = Logger.getLogger(SessionListener.class);

	/**
	 * Default constructor.
	 */
	public SessionListener() {
		LOG.debug("Session listener constructor starts");
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */

	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		ServletContext servletContext = session.getServletContext();
		CurrencyService currencyService = (CurrencyService) servletContext
				.getAttribute(Const.ContextAttr.CURRENCY_SERVICE);

		String carrency = BankCode.UAH.getName();
		session.setAttribute(Const.SessionAttr.CURR_ATTR, carrency);
		LOG.trace("Currency has established ---> " + carrency);

		double rate = currencyService.findByName(carrency).getRate();
		session.setAttribute(Const.SessionAttr.RATE_ATTR, rate);
		LOG.trace("Rate has established ---> " + rate);

		String lang = Const.LangForm.RU_LANG;
		session.setAttribute(Const.SessionAttr.LANG_ATTR, lang);
		LOG.trace("Language has established ---> " + lang);

	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		LOG.debug("Session destroy started.");
		LOG.debug("Session destroy finished.");
	}

}

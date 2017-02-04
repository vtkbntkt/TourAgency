package ua.gudkov.fp.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.dao.DAOFactory;
import ua.gudkov.fp.dao.api.CurrencyDAO;
import ua.gudkov.fp.dao.api.DiscountDAO;
import ua.gudkov.fp.dao.api.HotelDAO;
import ua.gudkov.fp.dao.api.MessageDAO;
import ua.gudkov.fp.dao.api.OrderDAO;
import ua.gudkov.fp.dao.api.TourDAO;
import ua.gudkov.fp.dao.api.UserDAO;
import ua.gudkov.fp.db.DBManager;
import ua.gudkov.fp.service.api.CaptchaService;
import ua.gudkov.fp.service.api.CurrencyService;
import ua.gudkov.fp.service.api.DiscountService;
import ua.gudkov.fp.service.api.HotelService;
import ua.gudkov.fp.service.api.MsgService;
import ua.gudkov.fp.service.api.OrderService;
import ua.gudkov.fp.service.api.TourService;
import ua.gudkov.fp.service.api.UserService;
import ua.gudkov.fp.service.CaptchaServiceImpl;
import ua.gudkov.fp.service.CurrencyServiceImpl;
import ua.gudkov.fp.service.DiscountServiceImpl;
import ua.gudkov.fp.service.HotelServiceImpl;
import ua.gudkov.fp.service.MsgServiceImpl;
import ua.gudkov.fp.service.OrderServiceImpl;
import ua.gudkov.fp.service.TourServiceImpl;
import ua.gudkov.fp.service.UserServiceImpl;

/**
 * Context listener.
 * 
 * @author A.Gudkov
 * 
 */
public class ContextListener implements ServletContextListener {
	private static ServletContext context;
	private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.debug("Context initialize starts.");
		context = sce.getServletContext();
		DAOFactory mySqlFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);

		DBManager dbManager = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(Const.Connection.OBJECT_TO_LOCK_UP_NAME);
			dbManager = new DBManager(ds);
		} catch (NamingException e) {
			LOGGER.warn(e);
			throw new RuntimeException(e);
		}

		MessageDAO messageDAO = mySqlFactory.getMessageDAO();
		MsgService msgService = new MsgServiceImpl(messageDAO, dbManager);
		context.setAttribute(Const.ContextAttr.MSG_SERVICE, msgService);

		UserDAO userDAO = mySqlFactory.getUserDAO();
		UserService userService = new UserServiceImpl(userDAO, dbManager);
		context.setAttribute(Const.ContextAttr.USER_SERVICE, userService);

		HotelDAO hotelDAO = mySqlFactory.getHotelDAO();
		HotelService hotelService = new HotelServiceImpl(hotelDAO, dbManager);
		context.setAttribute(Const.ContextAttr.HOTEL_SERVICE, hotelService);

		TourDAO tourDAO = mySqlFactory.getTourDAO();
		TourService tourService = new TourServiceImpl(tourDAO, dbManager);
		context.setAttribute(Const.ContextAttr.TOUR_SERVICE, tourService);

		CurrencyDAO currencyDAO = mySqlFactory.getCurrencyDAO();
		CurrencyService currencyService = new CurrencyServiceImpl(currencyDAO, dbManager);
		context.setAttribute(Const.ContextAttr.CURRENCY_SERVICE, currencyService);

		DiscountDAO discountDAO = mySqlFactory.getDiscountDAO();
		DiscountService discountService = new DiscountServiceImpl(discountDAO, dbManager);
		context.setAttribute(Const.ContextAttr.DISCOUNT_SERVICE, discountService);

		OrderDAO orderDAO = mySqlFactory.getOrderDAO();
		OrderService orderService = new OrderServiceImpl(orderDAO, dbManager);
		context.setAttribute(Const.ContextAttr.ORDER_SERVICE, orderService);

		int timeoutValue = Integer.parseInt(context.getInitParameter(Const.ContextAttr.CAPTCHA_TIMEOUT));
		CaptchaService captchaService = new CaptchaServiceImpl(timeoutValue);
		context.setAttribute(Const.ContextAttr.CAPTCHA_SERVICE, captchaService);

		LOGGER.debug("Context initialize finished.");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.debug("Context destroy started.");
		ServletContext servletContext = sce.getServletContext();
		servletContext.removeAttribute(Const.ContextAttr.MSG_SERVICE);
		servletContext.removeAttribute(Const.ContextAttr.USER_SERVICE);
		servletContext.removeAttribute(Const.ContextAttr.HOTEL_SERVICE);
		servletContext.removeAttribute(Const.ContextAttr.TOUR_SERVICE);
		servletContext.removeAttribute(Const.ContextAttr.CURRENCY_SERVICE);
		servletContext.removeAttribute(Const.ContextAttr.DISCOUNT_SERVICE);
		servletContext.removeAttribute(Const.ContextAttr.ORDER_SERVICE);
		servletContext.removeAttribute(Const.ContextAttr.CAPTCHA_SERVICE);
		LOGGER.debug("Context destroy finished.");

	}

}

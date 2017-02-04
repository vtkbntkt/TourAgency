package ua.gudkov.fp.dao;

import ua.gudkov.fp.dao.api.CurrencyDAO;
import ua.gudkov.fp.dao.api.DiscountDAO;
import ua.gudkov.fp.dao.api.HotelDAO;
import ua.gudkov.fp.dao.api.MessageDAO;
import ua.gudkov.fp.dao.api.OrderDAO;
import ua.gudkov.fp.dao.api.TourDAO;
import ua.gudkov.fp.dao.api.UserDAO;

/**
 * DAO factory.
 * 
 * @author A.Gudkov
 *
 */
public abstract class DAOFactory {
	/*
	 * DAO types supported by the factory
	 */
	public static final int MySQL = 1;

	/**
	 * Returns message DAO.
	 * 
	 * @return messageDAO
	 */
	public abstract MessageDAO getMessageDAO();
	
	/**
	 * Returns user DAO.
	 * 
	 * @return userDAO
	 */
	public abstract UserDAO getUserDAO();
	
	/**
	 * Returns hotel DAO.
	 * 
	 * @return hotelDAO
	 */
	public abstract HotelDAO getHotelDAO();
	

	/**
	 * Returns tour DAO.
	 * 
	 * @return tourlDAO
	 */
	public abstract TourDAO getTourDAO();
	
	/**
	 * Returns currency DAO.
	 * 
	 * @return currencyDAO
	 */
	public abstract CurrencyDAO getCurrencyDAO();
	
	/**
	 * Returns discount DAO.
	 * 
	 * @return discountDAO
	 */
	public abstract DiscountDAO getDiscountDAO();
	

	/**
	 * Returns order DAO.
	 * 
	 * @return orderDAO
	 */
	public abstract OrderDAO getOrderDAO();

	/**
	 * Returns DAO factory according to type.
	 * 
	 * @param whichFactory
	 *            type of DAO factory
	 * @return DAO factory
	 */
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MySQL:
			return new MySqlDAOFactory();
		default:
			return null;
		}
	}

}

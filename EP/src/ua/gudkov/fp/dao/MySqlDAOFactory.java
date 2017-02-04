package ua.gudkov.fp.dao;

import ua.gudkov.fp.dao.api.CurrencyDAO;
import ua.gudkov.fp.dao.api.DiscountDAO;
import ua.gudkov.fp.dao.api.HotelDAO;
import ua.gudkov.fp.dao.api.MessageDAO;
import ua.gudkov.fp.dao.api.OrderDAO;
import ua.gudkov.fp.dao.api.TourDAO;
import ua.gudkov.fp.dao.api.UserDAO;

/**
 * MySQL implementation of DAO factory.
 * 
 * @author A.Gudkov
 *
 */
public class MySqlDAOFactory extends DAOFactory {

	@Override
	public MessageDAO getMessageDAO() {
		return new MySqlMessageDAO();
	}

	@Override
	public UserDAO getUserDAO() {
		return new MySqlUserDAO();
	}

	@Override
	public HotelDAO getHotelDAO() {
		return new MySqlHotelDAO();
	}

	@Override
	public TourDAO getTourDAO() {
		return new MySqlTourDAO();
	}

	@Override
	public CurrencyDAO getCurrencyDAO() {
		return new MySqlCurrencyDAO();
	}

	@Override
	public OrderDAO getOrderDAO() {
		return new MySqlOrderDAO();
	}

	@Override
	public DiscountDAO getDiscountDAO() {
		return new MySqlDiscountDAO();
	}

}

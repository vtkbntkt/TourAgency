package ua.gudkov.fp.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.gudkov.fp.dao.api.HotelDAO;
import ua.gudkov.fp.db.DBManager;
import ua.gudkov.fp.db.api.Operation;
import ua.gudkov.fp.entity.Hotel;
import ua.gudkov.fp.service.api.HotelService;

/**
 * Hotel service implementation.
 * 
 * @author A.Gudkov
 *
 */
public class HotelServiceImpl implements HotelService {

	private HotelDAO hotelDAO;
	private DBManager dbManager;
	private static final Logger LOG = Logger.getLogger(HotelServiceImpl.class);

	public HotelServiceImpl(HotelDAO hotelDAO, DBManager dbManager) {
		this.hotelDAO = hotelDAO;
		this.dbManager = dbManager;
		LOG.debug("Service created");
	}

	@Override
	public boolean addHotel(Hotel hotel) {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() {
				return hotelDAO.insertHotel((hotel));
			}
		});
	}

	@Override
	public List<Hotel> findAll() {
		return dbManager.execute(new Operation<List<Hotel>>() {
			@Override
			public List<Hotel> execute() {
				return hotelDAO.findAll();
			}
		});
	}

	@Override
	public List<String> countryValues() {
		return dbManager.execute(new Operation<List<String>>() {
			@Override
			public List<String> execute() {
				return hotelDAO.countryValues();
			}
		});
	}

	@Override
	public List<String> ratingValues() {
		return dbManager.execute(new Operation<List<String>>() {
			@Override
			public List<String> execute() {
				return hotelDAO.ratingValues();
			}
		});
	}

}

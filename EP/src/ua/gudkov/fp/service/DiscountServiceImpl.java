package ua.gudkov.fp.service;

import org.apache.log4j.Logger;

import ua.gudkov.fp.dao.api.DiscountDAO;
import ua.gudkov.fp.db.DBManager;
import ua.gudkov.fp.db.api.Operation;
import ua.gudkov.fp.entity.Discount;
import ua.gudkov.fp.service.api.DiscountService;

/**
 * Discount service implementation.
 * 
 * @author A.Gudkov
 *
 */
public class DiscountServiceImpl implements DiscountService {
	private DiscountDAO discountDAO;
	private DBManager dbManager;
	private static final Logger LOG = Logger.getLogger(DiscountServiceImpl.class);

	public DiscountServiceImpl(DiscountDAO discountDAO, DBManager dbManager) {
		this.discountDAO = discountDAO;
		this.dbManager = dbManager;
		LOG.debug("Service created");
	}

	@Override
	public Discount findDiscount(long id) {
		return dbManager.execute(new Operation<Discount>() {
			@Override
			public Discount execute() {
				return discountDAO.findById(id);
			}

		});
	}

	@Override
	public boolean updateDiscount(long id, double perTourDiscount, double maxDiscount) {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() {
				return discountDAO.updateDiscount(id, perTourDiscount, maxDiscount);
			}

		});
	}

	@Override
	public Discount findDiscount() {
		return findDiscount(0);
	}

}

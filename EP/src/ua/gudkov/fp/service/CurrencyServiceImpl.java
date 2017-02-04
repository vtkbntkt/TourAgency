package ua.gudkov.fp.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.gudkov.fp.dao.api.CurrencyDAO;
import ua.gudkov.fp.db.DBManager;
import ua.gudkov.fp.db.api.Operation;
import ua.gudkov.fp.entity.BankCode;
import ua.gudkov.fp.entity.Currency;
import ua.gudkov.fp.service.api.CurrencyService;

/**
 * Currency service implementation.
 * 
 * @author A.Gudkov
 *
 */
public class CurrencyServiceImpl implements CurrencyService {
	private CurrencyDAO currencyDAO;
	private DBManager dbManager;
	private static final Logger LOG = Logger.getLogger(CurrencyServiceImpl.class);

	public CurrencyServiceImpl(CurrencyDAO currencyDAO, DBManager dbManager) {
		this.currencyDAO = currencyDAO;
		this.dbManager = dbManager;
		LOG.debug("Service created");
	}

	@Override
	public List<Currency> findAll() {
		return dbManager.execute(new Operation<List<Currency>>() {
			@Override
			public List<Currency> execute() {
				return currencyDAO.findAll();
			}
		});
	}

	@Override
	public Currency findByName(String name) {
		return dbManager.execute(new Operation<Currency>() {
			@Override
			public Currency execute() {
				return currencyDAO.findByName(name);
			}
		});
	}

	@Override
	public boolean updateRate(BankCode bc, double rate) {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() {
				return currencyDAO.updateRate(bc.ordinal(), rate);
			}
		});
	}

}

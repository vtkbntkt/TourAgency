package ua.gudkov.fp.dao.api;

import java.util.List;

import ua.gudkov.fp.entity.Currency;

/**
 * Currency DAO interface.
 * 
 * @author A.Gudkov
 *
 */
public interface CurrencyDAO {

	/**
	 * Find currency by its name (bank code).
	 * 
	 * @param name
	 *            currency name
	 * @return
	 */
	Currency findByName(String name);

	/**
	 * Returns list of all currencies contained in the base.
	 * 
	 * @return list of currencies
	 */
	List<Currency> findAll();

	/**
	 * Updates currency rate.
	 * 
	 * @param bc
	 *            currency bank code
	 * @param rate
	 *            rate
	 * @return true if the rate is updated, false otherwise
	 */
	boolean updateRate(int bc, double rate);

}

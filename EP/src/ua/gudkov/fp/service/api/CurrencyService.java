package ua.gudkov.fp.service.api;

import java.util.List;

import ua.gudkov.fp.entity.BankCode;
import ua.gudkov.fp.entity.Currency;

/**
 * Currency service interface.
 * 
 * @author A.Gudkov
 *
 */
public interface CurrencyService {

	/**
	 * Returns list of all currencies contained in the base.
	 * 
	 * @return list of currencies
	 */
	List<Currency> findAll();

	/**
	 * Returns currency found by its name.
	 * 
	 * @param name
	 *            of currency
	 * @return found currency or null if currency with given name does not exist
	 *         in the base.
	 */
	Currency findByName(String name);

	/**
	 * Updates currency rate
	 * 
	 * @param bc
	 *            bank code of currency
	 * @param rate
	 *            currency rate
	 * @return true if rate is updated, false otherwise
	 */
	boolean updateRate(BankCode bc, double rate);

}

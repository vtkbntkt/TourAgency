package ua.gudkov.fp.dao.api;

import ua.gudkov.fp.entity.Discount;

/**
 * Discount DAO interface.
 * 
 * @author A.Gudkov
 *
 */
public interface DiscountDAO {

	/**
	 * Returns discount found by its id
	 * 
	 * @param id
	 *            discount id
	 * @return discount
	 */
	Discount findById(long id);

	/**
	 * Updates discount by its id
	 * 
	 * @param id
	 *            discount id
	 * @param perTourDiscount
	 *            discount per tour value
	 * @param maxDiscount
	 *            maximum discount value
	 * @return true if the discount is updated, false otherwise
	 */
	boolean updateDiscount(long id, double perTourDiscount, double maxDiscount);

}

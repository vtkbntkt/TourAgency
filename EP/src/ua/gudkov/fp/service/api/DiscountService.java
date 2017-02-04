package ua.gudkov.fp.service.api;

import ua.gudkov.fp.entity.Discount;

/**
 * Discount service interface.
 * 
 * @author A.Gudkov
 *
 */
public interface DiscountService {

	/**
	 * Returns discount found by its id.
	 * 
	 * @param id
	 *            discount id
	 * @return discount found by its id or null if discount with given id does
	 *         not exist.
	 */
	Discount findDiscount(long id);

	/**
	 * Updates discount maximum value and discount per tour.
	 * 
	 * @param id
	 *            discount id
	 * @param perTourDiscount
	 *            discount per tour value
	 * @param maxDiscount
	 *            maximum discount value
	 * @return true if discount is updated, false otherwise
	 */
	boolean updateDiscount(long id, double perTourDiscount, double maxDiscount);

	/**
	 * Returns discount found by its id. Method calls findDiscount(long id) with
	 * default value 0;
	 * 
	 * @return discount found by id
	 */
	Discount findDiscount();

}

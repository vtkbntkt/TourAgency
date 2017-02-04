package ua.gudkov.fp.dao.api;

import java.util.List;

import ua.gudkov.fp.entity.FullTour;
import ua.gudkov.fp.entity.Tour;
import ua.gudkov.fp.entity.TourFilter;

/**
 * Tour DAO interface.
 * 
 * @author A.Gudkov
 *
 */
public interface TourDAO {

	/**
	 * Deletes tour found by its id
	 * 
	 * @param tourId
	 *            tour id
	 * @return true if the tour is deleted, false otherwise
	 */
	boolean deleteTourById(long tourId);

	/**
	 * Updates tour status found by tour id
	 * 
	 * @param tourId
	 *            tour id
	 * @return true if the tour status is updated, false otherwise
	 */
	boolean updateTourStatus(long tourId, int status);

	/**
	 * Updates tour.
	 * 
	 * @param tour
	 *            the tour
	 * @return true if the tour is updated, false otherwise
	 */
	boolean updateTour(Tour tour);

	/**
	 * Adds tour into data base.
	 * 
	 * @param tour
	 *            the tour
	 * @return true if the tour is added into database, false otherwise.
	 */
	boolean insertTour(Tour tour);

	/**
	 * Returns detailed tour contained on the base found by its id.
	 * 
	 * @return detailed tour
	 */
	FullTour findFullTourById(long tourId);

	/**
	 * Returns tour found by its id
	 * 
	 * @param tourId
	 *            tour id
	 * @return tour
	 */
	Tour findTourById(long tourId);

	/**
	 * Returns list of detailed tours according to the filter.
	 * 
	 * @param tourFilter
	 *            tour filter
	 * @return list of detailed tours
	 */
	List<FullTour> findToursByFilter(TourFilter tourFilter);

	/**
	 * Returns tour number according to the filter.
	 * 
	 * @param tourFilter
	 *            tour filter
	 * @return tour number
	 */
	long findTourNumberByFilter(TourFilter tourFilter);

	/**
	 * Returns list of unique night values of all available tours.
	 * 
	 * @return list of night values
	 */
	List<String> nightCountValues();

	/**
	 * Returns list of unique persons values of all available tours.
	 * 
	 * @return list of persons values
	 */
	List<String> personCountValues();

	/**
	 * Returns list of unique dates when all available tours starts.
	 * 
	 * @return list of departure dates
	 */
	List<String> deptDateValues();

	/**
	 * Returns list of unique types values of all available tours.
	 * 
	 * 
	 * @return list of tour types
	 */
	List<String> tourTypeValues();

}

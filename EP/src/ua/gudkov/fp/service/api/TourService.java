package ua.gudkov.fp.service.api;

import java.util.List;

import ua.gudkov.fp.entity.FullTour;
import ua.gudkov.fp.entity.Tour;
import ua.gudkov.fp.entity.TourFilter;

/**
 * Tour service interface.
 * 
 * @author A.Gudkov
 *
 */
public interface TourService {

	/**
	 * Updates tour
	 * 
	 * @param tour
	 *            tour
	 * @return true if the tour was updated, false otherwise
	 */
	boolean updateTour(Tour tour);

	/**
	 * Removes tour found by its id.
	 * 
	 * @param tourId
	 *            tour id
	 * @return true if tour is updated, false otherwise
	 */
	boolean removeTour(long tourId);

	/**
	 * Updates tour status found by its id
	 * 
	 * @param tourId
	 *            tour id
	 * @return true if status is changed, false otherwise
	 */
	boolean changeStatus(long tourId);

	/**
	 * Adds a tour into the data base.
	 * 
	 * @param tour
	 *            the tour
	 * @return true if the tour is added into the data base, false otherwise
	 */
	boolean addTour(Tour tour);

	/**
	 * Return detailed tour found by its id.
	 * 
	 * @param tourId
	 *            the tour id
	 * @return detailed tour
	 */
	FullTour getFullTour(long tourId);

	/**
	 * Return tour found by its id.
	 * 
	 * @param tourId
	 *            tour id
	 * @return tour
	 */
	Tour getTour(long tourId);

	/**
	 * Returns list if detailed tours found by its id
	 * 
	 * @param tourFilter
	 *            the tour filter
	 * @return detailed tour list
	 */
	List<FullTour> getFullTours(TourFilter tourFilter);

	/**
	 * Returns tour number calculated according to the filter values
	 * 
	 * @param tourFilter
	 * @return tour number
	 */
	long getFullTourNum(TourFilter tourFilter);

	/**
	 * Returns list of unique night values. Night values extracted from
	 * available tours.
	 * 
	 * @return list of night values
	 */
	List<String> nightCountValues();

	/**
	 * Returns list of unique tour type values. Tour type values extracted from
	 * available tours.
	 * 
	 * @return number of tour type values
	 */
	List<String> tourTypeValues();

	/**
	 * Returns list of unique tour person values. Tour person values extracted
	 * from available tours.
	 * 
	 * @return number of tour person values
	 */
	List<String> personCountValues();

	/**
	 * Returns list of unique tour departure dates. Departure dates extracted
	 * from available tours.
	 * 
	 * @return number of departure dates
	 */
	List<String> deptDateValues();

}

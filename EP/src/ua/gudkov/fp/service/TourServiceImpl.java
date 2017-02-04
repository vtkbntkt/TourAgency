package ua.gudkov.fp.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.gudkov.fp.dao.api.TourDAO;
import ua.gudkov.fp.db.DBManager;
import ua.gudkov.fp.db.api.Operation;
import ua.gudkov.fp.entity.FullTour;
import ua.gudkov.fp.entity.Tour;
import ua.gudkov.fp.entity.TourFilter;
import ua.gudkov.fp.service.api.TourService;

/**
 * Tour service implementation.
 * 
 * @author A.Gudkov
 *
 */
public class TourServiceImpl implements TourService {

	private TourDAO tourDAO;
	private DBManager dbManager;
	private static final Logger LOG = Logger.getLogger(TourServiceImpl.class);

	public TourServiceImpl(TourDAO tourDAO, DBManager dbManager) {
		this.tourDAO = tourDAO;
		this.dbManager = dbManager;
		LOG.debug("Service created");
	}

	@Override
	public boolean addTour(Tour tour) {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() {
				return tourDAO.insertTour((tour));
			}
		});
	}

	@Override
	public FullTour getFullTour(long tourId) {
		return dbManager.execute(new Operation<FullTour>() {
			@Override
			public FullTour execute() {
				return tourDAO.findFullTourById(tourId);
			}

		});
	}

	@Override
	public List<FullTour> getFullTours(TourFilter tourFilter) {
		return dbManager.execute(new Operation<List<FullTour>>() {
			@Override
			public List<FullTour> execute() {
				return tourDAO.findToursByFilter(tourFilter);
			}

		});
	}

	@Override
	public long getFullTourNum(TourFilter tourFilter) {
		return dbManager.execute(new Operation<Long>() {
			@Override
			public Long execute() {
				return tourDAO.findTourNumberByFilter(tourFilter);
			}

		});
	}

	@Override
	public List<String> nightCountValues() {
		return dbManager.execute(new Operation<List<String>>() {
			@Override
			public List<String> execute() {
				return tourDAO.nightCountValues();
			}

		});
	}

	@Override
	public List<String> tourTypeValues() {
		return dbManager.execute(new Operation<List<String>>() {
			@Override
			public List<String> execute() {
				return tourDAO.tourTypeValues();
			}

		});
	}

	@Override
	public List<String> personCountValues() {
		return dbManager.execute(new Operation<List<String>>() {
			@Override
			public List<String> execute() {
				return tourDAO.personCountValues();
			}

		});
	}

	@Override
	public List<String> deptDateValues() {
		return dbManager.execute(new Operation<List<String>>() {
			@Override
			public List<String> execute() {
				return tourDAO.deptDateValues();
			}

		});
	}

	@Override
	public Tour getTour(long tourId) {
		return dbManager.execute(new Operation<Tour>() {
			@Override
			public Tour execute() {
				return tourDAO.findTourById(tourId);
			}

		});
	}

	@Override
	public boolean changeStatus(long tourId) {
		Tour tour = getTour(tourId);
		if (tour != null) {
			int status = (tour.isHot() ? 0 : 1);
			return dbManager.execute(new Operation<Boolean>() {
				@Override
				public Boolean execute() {
					return tourDAO.updateTourStatus(tourId, status);
				}

			});
		}
		return false;
	}

	@Override
	public boolean removeTour(long tourId) {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() {
				return tourDAO.deleteTourById(tourId);
			}

		});
	}

	@Override
	public boolean updateTour(Tour tour) {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() {
				return tourDAO.updateTour(tour);
			}

		});
	}

}

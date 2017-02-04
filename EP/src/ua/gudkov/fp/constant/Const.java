package ua.gudkov.fp.constant;

/**
 * All constants.
 * 
 * @author A.Gudkov
 *
 */
public class Const {

	/**
	 * The connection values.
	 */
	public static final class Connection {
		public static final String OBJECT_TO_LOCK_UP_NAME = "java:comp/env/jdbc/ep";

		private Connection() {
		}
	}
	/*
	 * public static final class Path { public static final String CONTACTUS =
	 * "contactus.jsp"; public static final String LOGIN = "login.jsp";
	 * 
	 * private Path() { } }
	 */

	/**
	 * Constants to be used by filters.
	 */
	public static final class FilterConstants {
		public static final String MAX_FILE_SIZE = "maxFileSize";
		public static final String FILE_EXTENSION = "fileExtension";
		public static final String ENCODING = "encoding";
		public static final String ADMIN_ROLE = "admin";
		public static final String MANAGER_ROLE = "manager";
		public static final String CLIENT_ROLE = "client";
		public static final String COMMON_ACCESS = "common";
		public static final String NOCONTROL_ACCESS = "out-of-control";

		private FilterConstants() {
		}
	}

	/**
	 * Context attributes.
	 */
	public static final class ContextAttr {
		public static final String USER_SERVICE = "userService";
		public static final String MSG_SERVICE = "msgService";
		public static final String HOTEL_SERVICE = "hotelService";
		public static final String TOUR_SERVICE = "tourService";
		public static final String CURRENCY_SERVICE = "currencyService";
		public static final String DISCOUNT_SERVICE = "discountService";
		public static final String ORDER_SERVICE = "orderService";
		public static final String CAPTCHA_SERVICE = "captchaService";
		public static final String CAPTCHA_TIMEOUT = "captchaTimeOut";

		private ContextAttr() {
		}
	}

	/**
	 * File allocation parameters.
	 */
	public static final class File {
		public static final String FILE_FIELD = "file";
		public static final String DEF_DIR = "C:/uploads/";
		public static final String HOTEL_PHOTOS_DIR = "hotel_photos/";
		public static final String TOUR_PHOTOS_DIR = "tour_photos/";
		public static final String ATTACHMENTS_DIR = "attachments/";
		public static final String DOCS_DIR = "docs/";
		public static final String DOCS_PATH = "C:/uploads/docs/";
		public static final String FILE_EXTENSION = ".jpg";

		private File() {
		}
	}

	/**
	 * Language choice form parameters
	 */
	public static final class LangForm {
		public static final String LANG = "lang";
		public static final String DEF_LANG = "ru";
		public static final String RU_LANG = "ru";
		public static final String EN_LANG = "en";

		private LangForm() {
		}
	}

	/**
	 * Currency choice form parameters
	 */
	public static final class CurrencyForm {
		public static final String CURRENCY_OPTION = "currencyOption";

		private CurrencyForm() {
		}
	}

	/**
	 * Contact us form parameters
	 */
	public static final class ContactForm {
		public static final String NAME = "name";
		public static final String EMAIL = "email";
		public static final String MESSAGE = "msg";
		public static final String FILE = "file";
		public static final String NOTICE = "notice";

		private ContactForm() {
		}
	}

	/**
	 * Tour adding form parameters
	 */
	public static final class TourForm {
		public static final String TYPE = "tourtype";
		public static final String DATE = "depdate";
		public static final String NIGHTS = "nightNum";
		public static final String COST = "cost";
		public static final String PERSONS = "peopleNum";
		public static final String HOTEL = "hotel";
		public static final String FILE = "file";

		private TourForm() {
		}
	}

	/**
	 * Tour edit form parameters
	 */
	public static final class TourEditForm {
		public static final String ID = "tourId";
		public static final String TYPE = "tourtype";
		public static final String DATE = "depdate";
		public static final String NIGHTS = "nightNum";
		public static final String COST = "cost";
		public static final String PERSONS = "peopleNum";
		public static final String HOTEL = "hotel";
		public static final String FILE = "file";

		private TourEditForm() {
		}
	}

	/**
	 * Message replaying form parameters
	 */
	public static final class ReplayingForm {
		public static final String EMAIL = "msgemail";
		public static final String SUBJECT = "msgsubject";
		public static final String MESSAGE = "msg";
		public static final String NOTICE = "notice";

		private ReplayingForm() {
		}
	}

	/**
	 * Login form parameters
	 */
	public static final class LoginForm {
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
		public static final String CAPTCHA_VALUE = "captchaValue";

		private LoginForm() {
		}
	}

	/**
	 * Registration form parameters
	 */
	public static final class SignUpForm {
		public static final String FIRST_NAME = "firstname";
		public static final String LAST_NAME = "lastname";
		public static final String EMAIL = "email";
		public static final String PWD = "pwd";
		public static final String REPEATED_PWD = "repwd";

		private SignUpForm() {
		}
	}

	/**
	 * Hotel adding form parameters
	 */
	public static final class HotelForm {
		public static final String NAME = "hotelName";
		public static final String RAITING = "hotelRate";
		public static final String COUNTRY = "hotelCountry";
		public static final String SITY = "hotelCity";
		public static final String DESCRIPTION = "description";
		public static final String FILE = "file";

		private HotelForm() {
		}
	}

	/**
	 * Currency editing form parameters
	 */
	public static final class CurrencyEditForm {
		public static final String BANK_CODE = "code";
		public static final String RATE = "rate";

		private CurrencyEditForm() {
		}
	}

	/**
	 * Discount editing form parameters
	 */
	public static final class DiscountEditForm {
		public static final String PER_TOUR_DISCOUNT = "discountPerTour";
		public static final String MAX_DISCOUNT = "maxDiscount";

		private DiscountEditForm() {
		}
	}

	/**
	 * Tour filter form parameters
	 */
	public static final class TourFilterForm {
		public static final String COUNTRY = "country";
		public static final String TYPE = "type";
		public static final String COST_FROM = "costFrom";
		public static final String COST_TO = "costTo";
		public static final String PERSON_NUM = "person_num";
		public static final String RATING = "rating";
		public static final String NIGHT_NUM = "night_num";
		public static final String DEPT_DATE = "dept_date";
		public static final String SORTING = "sorting";
		public static final String ITEMS_PER_PAGE = "itemsPerPage";
		public static final String CURRENT_PAGE = "currentPage";

		private TourFilterForm() {
		}
	}

	/**
	 * Simple filter form parameters
	 */
	public static final class SimpleFilterForm {
		public static final String ITEMS_PER_PAGE = "itemsPerPage";
		public static final String CURRENT_PAGE = "currentPage";

		private SimpleFilterForm() {
		}
	}

	/**
	 * Successful messages to be sent user, also contains keys to be translated
	 */
	public static final class SuccessMsg {
		public static final String CONTACTUS_MSG = "contactUsSending";
		public static final String CONTACTUS_SEND_KEY = "contactus.success.send";

		public static final String HOTEL_MSG = "hotelAdding";
		public static final String HOTEL_ADD_KEY = "hotel.success.add";

		public static final String TOUR_MSG = "tourAdding";
		public static final String TOUR_ADD_KEY = "tour.success.add";

		public static final String REPLAYING_MSG = "reSending";
		public static final String RE_SEND_KEY = "re.success.send";

		public static final String RATE_MSG = "rateUpdate";
		public static final String RATE_UPDATE_KEY = "rate.success.update";

		public static final String DISCOUNT_MSG = "discUpdate";
		public static final String DISC_UPDATE_KEY = "disc.success.update";

		private SuccessMsg() {
		}
	}

	/**
	 * Represents errors keys should be translated into defined language
	 *
	 */
	public final class ErrorConstants {
		public static final String CONTACTUS_ERRORS = "contactUsErrors";
		public static final String CONTACTUS_NAME_KEY = "contactus.error.name";
		public static final String CONTACTUS_EMAIL_KEY = "contactus.error.email";
		public static final String CONTACTUS_MSG_KEY = "contactus.error.msg";
		public static final String CONTACTUS_FILE_KEY = "contactus.error.file";
		public static final String CONTACTUS_SEND_KEY = "contactus.error.send";

		public static final String LOGIN_ERRORS = "loginErrors";
		public static final String LOGIN_NAME_KEY = "login.error.name";
		public static final String LOGIN_PWD_KEY = "login.error.pwd";
		public static final String LOGIN_MSG_KEY = "login.error.msg";

		public static final String CAPTCHA_VALUE_KEY = "captcha.error.value";
		public static final String CAPTCHA_TIME_KEY = "captcha.error.time";

		public static final String REPLAYING_ERRORS = "replayingErrors";
		public static final String RE_EMAIL_KEY = "re.error.email";
		public static final String RE_MSG_KEY = "re.error.msg";
		public static final String RE_SUBJECT_KEY = "re.error.subject";
		public static final String RE_SEND_KEY = "re.error.send";

		public static final String REGISTER_ERRORS = "registerErrors";
		public static final String REGISTER_FIRST_NAME_KEY = "register.error.firstname";
		public static final String REGISTER_LAST_NAME_KEY = "register.error.lastname";
		public static final String REGISTER_EMAIL_KEY = "register.error.email";
		public static final String REGISTER_PWD_KEY = "register.error.pwd";
		public static final String REGISTER_RE_PWD_KEY = "register.error.repwd";
		public static final String REGISTER_MSG_KEY = "register.error.msg";

		public static final String ADDHOTEL_ERRORS = "addhotelErrors";
		public static final String ADDHOTEL_NAME_KEY = "addhotel.error.name";
		public static final String ADDHOTEL_CITY_KEY = "addhotel.error.city";
		public static final String ADDHOTEL_COUNTRY_KEY = "addhotel.error.country";
		public static final String ADDHOTEL_RAITING_KEY = "addhotel.error.raiting";
		public static final String ADDHOTEL_DESC_KEY = "addhotel.error.desc";
		public static final String ADDHOTEL_FILE_KEY = "addhotel.error.files";
		public static final String ADDHOTEL_MSG_KEY = "addhotel.error.msg";

		public static final String ADDTOUR_ERRORS = "addtourErrors";
		public static final String ADDTOUR_MSG_KEY = "addtour.error.msg";
		public static final String ADDTOUR_COST_KEY = "addtour.error.cost";
		public static final String ADDTOUR_DATE_KEY = "addtour.error.date";
		public static final String ADDTOUR_HOTEL_KEY = "addtour.error.hotel";
		public static final String ADDTOUR_NIGHTS_KEY = "addtour.error.nights";
		public static final String ADDTOUR_PERSONS_KEY = "addtour.error.persons";
		public static final String ADDTOUR_TYPE_KEY = "addtour.error.type";
		public static final String ADDTOUR_FILE_KEY = "addtour.error.file";

		public static final String EDITTOUR_ERRORS = "edittourErrors";
		public static final String EDITTOUR_MSG_KEY = "edittour.error.msg";
		public static final String EDITTOUR_COST_KEY = "edittour.error.cost";
		public static final String EDITTOUR_DATE_KEY = "edittour.error.date";
		public static final String EDITTOUR_HOTEL_KEY = "edittour.error.hotel";
		public static final String EDITTOUR_NIGHTS_KEY = "edittour.error.nights";
		public static final String EDITTOUR_PERSONS_KEY = "edittour.error.persons";
		public static final String EDITTOUR_TYPE_KEY = "edittour.error.type";
		public static final String EDITTOUR_FILE_KEY = "edittour.error.file";
		public static final String EDITTOUR_ID_KEY = "edittour.error.id";

		public static final String EDITCURR_ERRORS = "editcurrErrors";
		public static final String EDITCURR_RATE_KEY = "editcurr.error.rate";
		public static final String EDITCURR_CODE_KEY = "editcurr.error.code";
		public static final String EDITCURR_MSG_KEY = "editcurr.error.msg";

		public static final String EDITDISC_ERRORS = "editdiscErrors";
		public static final String EDITDISC_FORMAT_KEY = "editdisc.error.format";
		public static final String EDITDISC_VALUE_KEY = "editdisc.error.value";
		public static final String EDITDISC_MSG_KEY = "editdisc.error.msg";

		private ErrorConstants() {
		}
	}

	/**
	 * Regular expression to validate input fields
	 */
	public static final class RegexConstants {
		public static final String EMAIL_REG_EXP = "^([a-zA-Z0-9_-]+\\.)*[a-zA-Z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\."
				+ "[a-z]{2,4}$";
		public static final String NAME_REG_EXP = "^[A-ZÀ-ß][a-zà-ÿ]{1,19}$";
		public static final String CONTENT_TYPE_REG_EXP = "^image/(gif|jpeg|pjpeg|png)$";
		public static final String PASSWORD_REG_EXP = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$";

		// public static final String PHONE_REG_EXP = "^(\\d{3}) (\\d{3})
		// (\\d{4})$";
		public static final String DATE_REG_EXP = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";
		public static final String COST_REG_EXP = "^[1-9]\\d*$";

		private RegexConstants() {
		}
	}

	/**
	 * Session attributes
	 */
	public final class SessionAttr {
		public static final String CONTACTUS_FORM = "contactForm";
		public static final String LANG_ATTR = "language";
		public static final String CURR_ATTR = "currency";
		public static final String RATE_ATTR = "currentRate";
		public static final String LOGIN_FORM = "loginForm";
		public static final String REGISTER_FORM = "registerForm";
		public static final String USER = "user";
		public static final String RE_MSG_FORM = "reForm";
		public static final String HOTEL_FORM = "hotelForm";
		public static final String TOUR_FORM = "tourForm";
		public static final String TOUR_EDIT_FORM = "tourEditForm";
		public static final String CURR_LIST = "currencyList";
		public static final String CURR_EDIT_FORM = "currEditForm";
		public static final String CAPTCHA = "captcha";

		private SessionAttr() {
		}
	}

	/**
	 * Request attributes
	 */
	public final class RequestAttr {
		public static final String MSG_LIST = "messageList";
		public static final String SINGLE_MSG = "singlemsg";
		public static final String TYPE_LIST = "typeList";
		public static final String HOTEL_LIST = "hotelList";
		public static final String SINGLE_TOUR = "singleTour";
		public static final String DATE_VALUES = "dateValues";
		public static final String PERSON_VALUES = "personValues";
		public static final String NIGHT_VALUES = "nightValues";
		public static final String TYPE_VALUES = "typeValues";
		public static final String CONTRY_VALUES = "countryValues";
		public static final String RATING_VALUES = "ratingValues";
		public static final String ITEMS_TOTAL = "itemsTotal";
		public static final String TOUR_LIST = "tourList";
		public static final String CURRENT_DISCOUNT = "currentDiscount";
		public static final String ORDER_LIST = "orderList";
		public static final String ORDER_STATUS_LIST = "orderStatusList";
		public static final String USER_LIST = "userList";
		public static final String IMAGE_NAME_LIST = "images";

		private RequestAttr() {
		}
	}

	/**
	 * Request parameters given without form usage
	 */
	public final class RequestParam {
		public static final String MSG_ID = "idmsg";
		public static final String FILE_NAME = "filename";
		public static final String DEL_MSG_ID = "delIds";
		public static final String DIR_ID = "dirId";
		public static final String TOUR_ID = "tourId";
		public static final String ORDER_ID = "orderId";
		public static final String ORDER_STATUS = "orderStatus";
		public static final String USER_ID = "userId";

		private RequestParam() {
		}
	}

	/**
	 * SQL queries
	 */
	public static final class Query {
		public static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email like ? ";
		public static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
		public static final String FIND_TOUR_BY_ID = "SELECT * FROM tours WHERE id= ?";
		public static final String FIND_CURRENCY_BY_NAME = "SELECT * FROM currencies WHERE name like ? ";
		public static final String FIND_DISCOUNT_BY_ID = "SELECT * FROM discounts WHERE id=? ";
		public static final String ADD_USER = "INSERT INTO users (email,password, first_name, last_name, user_status,role_id) VALUES (?,?,?,?,?,?)";
		public static final String DEL_MSG_BY_ID = "DELETE FROM messages WHERE id=?";
		public static final String FIND_ALL_MSG = "SELECT * FROM messages ORDER BY date DESC LIMIT ?,?";
		public static final String FIND_ALL_USERS = "SELECT users.id,email,first_name,last_name,user_statuses.name,roles.name FROM users JOIN user_statuses ON users.user_status=user_statuses.id JOIN roles ON users.role_id=roles.id WHERE role_id!=0 LIMIT ?,?";
		public static final String FIND_ALL_HOTELS = "SELECT * FROM hotels ORDER BY name";
		public static final String FIND_ALL_CURRENCIES = "SELECT * FROM currencies";
		public static final String ADD_MSG = "INSERT INTO `messages` VALUES (DEFAULT, DEFAULT, ?,?,?)";
		public static final String ADD_HOTEL = "INSERT INTO `hotels` VALUES (DEFAULT, ?, ?,?,?,?)";
		public static final String ADD_TOUR = "INSERT INTO `tours` VALUES (DEFAULT, ?, ?,?,?,DEFAULT,?,?)";
		public static final String ADD_ORDER = "INSERT INTO `orders` VALUES (DEFAULT, DEFAULT, ?,?,?,DEFAULT)";
		public static final String FIND_MSG_BY_ID = "SELECT * FROM messages WHERE id=?";
		public static final String UPDATE_TOUR_STATUS = "UPDATE tours SET ishot=? WHERE id = ?";
		public static final String UPDATE_ORDER_STATUS = "UPDATE orders SET order_status=? WHERE id = ?";
		public static final String UPDATE_USER_STATUS = "UPDATE users SET user_status=? WHERE id = ?";
		public static final String UPDATE_RATE = "UPDATE currencies SET rate=? WHERE id = ?";
		public static final String UPDATE_DISCOUNT = "UPDATE discounts SET percent_per_tour=?,max_prcent=? WHERE id = ?";
		public static final String COUNT_ORDER_BY_USERID_STATUSID = "SELECT COUNT(*) FROM orders WHERE user_id=? AND order_status=?";
		public static final String UPDATE_TOUR = "UPDATE tours SET type_id=?, price=?,person_count=?,hotel_id=?,dept_date=?,night_count=? WHERE id=?";
		public static final String FIND_ORDERS_BY_USERID = "SELECT orders.*,users.first_name,last_name  FROM orders JOIN users ON orders.user_id=users.id WHERE user_id=? ORDER BY order_date DESC LIMIT ?,?";
		public static final String COUNT_ALL_USERS = "SELECT COUNT(*) FROM users WHERE role_id!=0";
		public static final String COUNT_ALL_MESSAGES = "SELECT COUNT(*) FROM messages";
		public static final String COUNT_ORDER_BY_USERID = "SELECT COUNT(*) FROM orders WHERE user_id=?";
		public static final String COUNT_ORDER_BY_STATUSID = "SELECT COUNT(*) FROM orders WHERE order_status=?";
		public static final String FIND_ORDERS_BY_STATUSID = "SELECT orders.*,users.first_name,last_name  FROM orders JOIN users ON orders.user_id=users.id WHERE order_status=? ORDER BY order_date DESC LIMIT ?,?";
		public static final String FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE id=?";
		public static final String VAL_ALL_STARS = "SELECT DISTINCT (hotels.raiting) FROM hotels ORDER BY hotels.raiting";
		public static final String VAL_ALL_COUNTRIES = "SELECT DISTINCT (hotels.country) FROM hotels ORDER BY hotels.country";
		public static final String VAL_ALL_DATES = "SELECT DISTINCT (tours.dept_date) FROM tours WHERE dept_date >= CURDATE() ORDER BY tours.dept_date";
		public static final String VAL_ALL_NIGHTS = "SELECT DISTINCT (tours.night_count) FROM tours ORDER BY tours.night_count";
		public static final String VAL_ALL_PERSONS = "SELECT DISTINCT (tours.person_count) FROM tours ORDER BY tours.person_count";
		public static final String VAL_ALL_TYPES = "SELECT DISTINCT(tour_type.name) FROM tours INNER JOIN tour_type ON tours.type_id=tour_type.id";
		public static final String DELETE_TOUR = "DELETE FROM tours WHERE id=?";

		private Query() {
		}
	}

	/**
	 * Application error messages, to be shown in log file
	 *
	 */
	public static final class ErrMsg {
		public static final String CLOSE_RESULTSET = "Cannot close a result set";
		public static final String CLOSE_STATEMENT = "Cannot close a statement";
		public static final String INSERT_MSG = "Cannot insert a message";
		public static final String INSERT_HOTEL = "Cannot insert a hotel";
		public static final String FIND_ALL_MSG = "Cannot obtain all messages";
		public static final String FIND_ALL_USERS = "Cannot obtain all users";
		public static final String FIND_ALL_HOTELS = "Cannot obtain all hotels";
		public static final String FIND_ALL_CURRENCIES = "Cannot obtain all currencies";
		public static final String FIND_MSG = "Cannot obtain message by its id ";
		public static final String FIND_FULL_TOUR = "Cannot obtain full tour by its id ";
		public static final String FIND_TOUR = "Cannot obtain the tour by its id ";
		public static final String DEL_MSG = "Cannot delete message by its id ";
		public static final String UPDATE_TOUR_STATUS = "Cannot update tour status";
		public static final String UPDATE_ORDER_STATUS = "Cannot update order status";
		public static final String UPDATE_USER_STATUS = "Cannot update user status";
		public static final String UPDATE_RATE = "Cannot update currency rate";
		public static final String UPDATE_DISCOUNT = "Cannot update discount";
		public static final String UPDATE_TOUR = "Cannot update tour";
		public static final String DELETE_TOUR = "Cannot delete tour by its id";
		public static final String CONNECTION = "Connection error";
		public static final String WRITE_FILE = "Cannot write a file";
		public static final String FIND_USER_BY_EMAIL = "Cannot obtain user by its email";
		public static final String FIND_USER_BY_ID = "Cannot obtain user by its id";
		public static final String FIND_CURRENCY_BY_NAME = "Cannot obtain currency by its name";
		public static final String FIND_DISCOUNT_BY_ID = "Cannot obtain discount by its id";
		public static final String INSERT_USER = "Cannot insert user";
		public static final String INSERT_TOUR = "Cannot insert tour";
		public static final String FIND_VALUES_BY_COLUMN = "Cannot obtain all values by column";
		public static final String FIND_TOURS_BY_FILTER = "Cannot obtain all tours by filter";
		public static final String FIND_TOUR_NUMBER_BY_FILTER = "Cannot obtain tour number by filter";
		public static final String COUNT_ORDER_BY_USERID_STATUSID = "Cannot obtain order number by user and status id";
		public static final String COUNT_ORDER_BY_USERID = "Cannot obtain order number by user id";
		public static final String COUNT_ORDER_BY_STATUSID = "Cannot obtain order number by status id";
		public static final String COUNT_ALL_MESSAGES = "Cannot obtain message number";
		public static final String COUNT_ALL_USERS = "Cannot obtain user number";
		public static final String FIND_ORDER_BY_USERID = "Cannot obtain orders by user id";
		public static final String FIND_ORDER_BY_STATUSID = "Cannot obtain orders by status id";
		public static final String FIND_ORDER_BY_ID = "Cannot obtain order by its id";

		private ErrMsg() {
		}
	}

	/**
	 * Mail parameters
	 */
	public static final class Mail {
		public static final String USERNAME = "tasksummary4@gmail.com";
		public static final String PASSWORD = "ha160886gai";
		public static final String FROM_ADDRESS = "tasksummary4@gmail.com";
		public static final String FROM_NAME = "Epam Project";

		private Mail() {
		}

	}

	/**
	 * Represents urls of the application
	 *
	 */
	public static final class Url {

		/*
		 * Servlet paths
		 */
		public static final class Servlet {
			public static final String CONTACT_US = "contactus";
			public static final String TOURS = "tours";
			public static final String SIGN_IN = "login";
			public static final String ORDERS = "orders";
			public static final String SIGN_UP = "register";
			public static final String EDIT_CURRENCY = "edit_currency";
			public static final String MESSAGES = "messages";
			public static final String RE_MSG = "msgreplaying";
			public static final String EDIT_DISCOUNT = "edit_discount";
			public static final String HOTEL = "hotel";
			public static final String ADD_TOUR = "touradding";
			public static final String EDIT_TOUR = "edit_tour";
			public static final String USERS = "users";
			public static final String VIEW_TOUR = "tour";

			private Servlet() {
			}

		}

		/*
		 * Jsps
		 */
		public static final class Jsp {
			public static final String CONTACT_US = "contactus.jsp";
			public static final String SIGN_IN = "signin.jsp";
			public static final String SIGN_UP = "signup.jsp";
			public static final String EDIT_CURRENCY = "/WEB-INF/jsp/edit_currency.jsp";
			public static final String VIEW_MSG = "/WEB-INF/jsp/view_message.jsp";
			public static final String MESSAGES = "/WEB-INF/jsp/messages.jsp";
			public static final String RE_MSG = "/WEB-INF/jsp/replay_message.jsp";
			public static final String EDIT_DISCOUNT = "/WEB-INF/jsp/edit_discount.jsp";
			public static final String ORDERS = "/WEB-INF/jsp/orders.jsp";
			public static final String ADD_HOTEL = "/WEB-INF/jsp/add_hotel.jsp";
			public static final String VIEW_TOUR = "/WEB-INF/jsp/view_tour.jsp";
			public static final String FIND_TOUR = "/WEB-INF/jsp/find_tour.jsp";
			public static final String USERS = "/WEB-INF/jsp/users.jsp";
			public static final String ADD_TOUR = "/WEB-INF/jsp/add_tour.jsp";
			public static final String EDIT_TOUR = "/WEB-INF/jsp/edit_tour.jsp";

			private Jsp() {
			}
		}

		private Url() {
		}

	}

	/**
	 * Http Headers
	 *
	 */
	public class HttpHeaders {
		public static final String REFERER = "Referer";
		private HttpHeaders() {
		}
	}

}

package ua.gudkov.fp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.entity.Role;
import ua.gudkov.fp.entity.User;

/**
 * Filters invoked servlet paths and according to determined user role denies or
 * allows access.
 * 
 * @author A.Gudkov
 *
 */
public class AccessFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(AccessFilter.class);
	private Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
	private List<String> commons = new ArrayList<String>();
	private List<String> outOfControl = new ArrayList<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.debug("Filter initialization starts");

		accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter(Const.FilterConstants.ADMIN_ROLE)));
		accessMap.put(Role.MANAGER, asList(filterConfig.getInitParameter(Const.FilterConstants.MANAGER_ROLE)));
		accessMap.put(Role.CLIENT, asList(filterConfig.getInitParameter(Const.FilterConstants.CLIENT_ROLE)));
		LOG.trace("Access map --> " + accessMap);

		commons = asList(filterConfig.getInitParameter(Const.FilterConstants.COMMON_ACCESS));
		LOG.trace("Common commands --> " + commons);

		outOfControl = asList(filterConfig.getInitParameter(Const.FilterConstants.NOCONTROL_ACCESS));
		LOG.trace("Out of control commands --> " + outOfControl);

		LOG.debug("Filter initialization finished");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String IP = httpRequest.getRemoteAddr();

		if (accessAllowed(request)) {
			chain.doFilter(request, response);
		} else {
			LOG.trace("Access denied --> " + IP);
			request.getRequestDispatcher(Const.Url.Servlet.TOURS).forward(request, response);

		}
	}

	/**
	 * Determines user role and appropriate servlet path.
	 * 
	 * @param request ServletRequest
	 * @return true is access allowed, otherwise false
	 */
	private boolean accessAllowed(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String url = httpRequest.getServletPath();
		if (outOfControl.contains(url)) {
			return true;
		}

		HttpSession session = httpRequest.getSession(false);
		if (session == null) {
			return false;
		}

		User user = (User) session.getAttribute(Const.SessionAttr.USER);
		if (user == null) {
			return false;
		}

		Role userRole = Role.values()[user.getRoleId()];
		if (userRole == null) {
			return false;
		}

		return accessMap.get(userRole).contains(url) || commons.contains(url);
	}

	/**
	 * Extracts parameter values from string.
	 * 
	 * @param str
	 *            parameter values string.
	 * @return list of parameter values.
	 */
	private List<String> asList(String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}

		return list;
	}

	
	public void destroy() {
		LOG.debug("Filter destruction finished");
	}

}

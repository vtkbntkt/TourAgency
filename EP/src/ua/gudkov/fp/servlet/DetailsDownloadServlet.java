package ua.gudkov.fp.servlet;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;

import ua.gudkov.fp.entity.Order;
import ua.gudkov.fp.service.api.OrderService;
import ua.gudkov.fp.util.FileManager;
import ua.gudkov.fp.util.PdfCreator;

/**
 * Servlet implementation class DetailsDownloadServlet
 */
@WebServlet("/download_details")
public class DetailsDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(DetailsDownloadServlet.class);
	private static final String PDF_FILE_NAME = "bank_details.pdf";
	private static final String TEMPLATE_FILE_NAME = "template_bank_details.txt";

	private ServletContext servletContext;
	private OrderService orderService;

	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
		orderService = (OrderService) servletContext.getAttribute(Const.ContextAttr.ORDER_SERVICE);
		LOG.debug("Details download servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Order order = getOrder(request);
		if (Objects.nonNull(order)) {
			List<String> template = FileManager.bankDetailsFromText(Const.File.DOCS_PATH + TEMPLATE_FILE_NAME);
			if (PdfCreator.createBankDetails(template, order, Const.File.DOCS_PATH + PDF_FILE_NAME)) {
				File pdfFile = new File(Const.File.DOCS_PATH + PDF_FILE_NAME);
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=" + PDF_FILE_NAME);
				response.setContentLength((int) pdfFile.length());
				FileInputStream fileInputStream = new FileInputStream(pdfFile);
				OutputStream responseOutputStream = response.getOutputStream();
				int bytes;
				while ((bytes = fileInputStream.read()) != -1) {
					responseOutputStream.write(bytes);
				}
				fileInputStream.close();
			}
		}
	}

	/**
	 * Extracts order id from request and then receives order found by the id.
	 * 
	 * @param request
	 *            request
	 * @return order
	 */
	private Order getOrder(HttpServletRequest request) {
		String orderId = request.getParameter(Const.RequestParam.ORDER_ID);
		if (StringUtils.isNumeric(orderId)) {
			return orderService.getOrder(Long.valueOf(orderId));
		}
		return null;
	}

}

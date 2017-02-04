package ua.gudkov.fp.util;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import ua.gudkov.fp.entity.Order;

/**
 * PDF file creation implementation.
 * 
 * @author A.Gudkov
 *
 */
public final class PdfCreator {
	private static final Logger LOG = Logger.getLogger(PdfCreator.class);

	/**
	 * Creates bank details according to given template and order information.
	 * Then save the bank details as PDF file.
	 * 
	 * @param template
	 *            part of bank details to be added
	 * @param order
	 *            the order
	 * @param path
	 *            path to save pdf file
	 * @return true if bank details is created and then saved using given path
	 */
	public static boolean createBankDetails(List<String> template, Order order, String path) {
		if (!template.isEmpty()) {
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
				document.open();
				for (String str : template) {
					document.add(new Paragraph(str));
				}
				document.add(new Paragraph(orderToReference(order)));
				document.close();
			} catch (Exception ex) {
				LOG.error("Can not create document", ex);
				return false;
			}
		}
		return true;
	}

	/**
	 * Extracts needed values from order and convert them to bank details
	 * reference.
	 * 
	 * @param order
	 *            the order
	 * @return reference to be added into bank details
	 */
	public static String orderToReference(Order order) {
		StringBuilder sb = new StringBuilder().append("REFERENCE: ").append("AMOUNT: ").append(order.getTotalCost())
				.append("USD, ").append("ORDER ID:").append(order.getId()).append(", CUSTOMER ID:")
				.append(order.getUserId());
		return sb.toString();
	}

}

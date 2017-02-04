package ua.gudkov.fp.servlet;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.util.FileManager;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet(urlPatterns = { "/attachments", "/hotel_photos", "/tour_photos" })

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ImageServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
		LOG.debug("Image servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter(Const.RequestParam.FILE_NAME);
		String fileDir = request.getParameter(Const.RequestParam.DIR_ID);
		String mainDir = request.getServletPath();

		File image = getImageFile(fileName, mainDir, fileDir);
		if (Objects.nonNull(image)) {
			RenderedImage ri = ImageIO.read(image);
			ImageIO.write(ri, FilenameUtils.getExtension(image.getName()), response.getOutputStream());
		} else {
			LOG.trace("Image not found ---> " + image);
		}
	}

	/**
	 * Returns image file found by given location.
	 * 
	 * @param fileName file name
	 * @param mainDir name of folder where appropriate image folders exists
	 * @param fileDir name of folder where appropriate images exists
	 * @return file
	 */
	private File getImageFile(String fileName, String mainDir, String fileDir) {
		if (Objects.nonNull(fileName)) {
			return FileManager.getImage(mainDir, fileDir, fileName);
		}
		return FileManager.getImage(mainDir, fileDir);
	}

}

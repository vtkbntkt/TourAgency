package ua.gudkov.fp.servlet;

import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.gudkov.fp.bean.CaptchaBean;
import ua.gudkov.fp.constant.Const;
import ua.gudkov.fp.service.api.CaptchaService;
import ua.gudkov.fp.util.Drawer;

/**
 * Servlet implementation class CaptchaServlet
 */
@WebServlet("/captcha.jpg")
public class CaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CaptchaServlet.class);
	private static final String CONTENT_TYPE = "image/jpeg";
	private static final String FORMAT_NAME = "jpg";
	
	private CaptchaService captchaService;

	@Override
	public void init() throws ServletException {
		super.init();
		captchaService = (CaptchaService) getServletContext().getAttribute(Const.ContextAttr.CAPTCHA_SERVICE);
		LOG.debug("Captcha servlet initialization finished");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		CaptchaBean captchaBean = captchaService.putCaptcha(request);
		LOG.trace("captchaBean ----> " + captchaBean);
		RenderedImage captchaImage = Drawer.drawCaptcha(captchaBean.getValue());
		ImageIO.write(captchaImage, FORMAT_NAME, response.getOutputStream());
	}

}

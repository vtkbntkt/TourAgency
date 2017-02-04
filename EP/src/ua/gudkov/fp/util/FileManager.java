package ua.gudkov.fp.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import ua.gudkov.fp.constant.Const;

/**
 * File manager implementation. Provides image saving, updating, reading,
 * removing. Also the class provides essential file/folder manipulation as
 * creation, removing, clearing.
 * 
 * @author A.Gudkov
 *
 */
public final class FileManager {
	private static final Logger LOG = Logger.getLogger(FileManager.class);

	private FileManager() {

	}

	/**
	 * Saves part into given location with defined file name
	 * 
	 * @param part
	 *            part
	 * @param path
	 *            path
	 * @param fileName
	 *            file name
	 * @return
	 */
	private static boolean saveFile(Part part, String path, String fileName) {
		try {
			part.write(path + fileName);
		} catch (IOException e) {
			LOG.error(Const.ErrMsg.WRITE_FILE, e);
			return false;
		}
		return true;
	}

	/**
	 * Saves image into defined folder.
	 * 
	 * @param part
	 *            part
	 * @param mainDir
	 *            main directory
	 * @param imgDir
	 *            image directory
	 * @return true if the image is saved, false otherwise
	 */
	public static boolean saveImage(Part part, String mainDir, String imgDir) {
		Part outPart = filterPart(part);
		if (outPart != null && createDir(mainDir, imgDir)) {
			String fileName = outPart.getSubmittedFileName();
			if (saveFile(outPart, Const.File.DEF_DIR + mainDir + imgDir + File.separator, fileName)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Updates image located in the given path.
	 * 
	 * @param part
	 *            part
	 * @param mainDir
	 *            main directory
	 * @param imgDir
	 *            image directory
	 * @return true if image is updated, false otherwise
	 */
	public static boolean updateImage(Part part, String mainDir, String imgDir) {
		Part outPart = filterPart(part);
		File folder = new File(Const.File.DEF_DIR + mainDir + File.separator + imgDir);
		if (outPart != null) {
			if (folder.exists()) {
				clearFolder(folder);
				String fileName = outPart.getSubmittedFileName();
				if (saveFile(outPart, Const.File.DEF_DIR + mainDir + imgDir + File.separator, fileName)) {
					return true;
				}

			} else {
				return saveImage(part, mainDir, imgDir);
			}
		}
		return false;
	}

	/**
	 * Saves list of images in given location
	 * 
	 * @param parts
	 *            part list
	 * @param mainDir
	 *            main directory
	 * @param imgDir
	 *            image directory
	 */
	public static void saveImages(List<Part> parts, String mainDir, String imgDir) {
		List<Part> outParts = filterParts(parts);
		if (!outParts.isEmpty() && createDir(mainDir, imgDir)) {
			for (Part part : outParts) {
				String fileName = part.getSubmittedFileName();
				saveFile(part, Const.File.DEF_DIR + mainDir + imgDir + File.separator, fileName);
			}

		}

	}

	/**
	 * Returns image from given location
	 * 
	 * @param mainDir
	 *            main directory
	 * @param imgDir
	 *            image directory
	 * @return true if the image is extracted, false otherwise
	 */
	public static File getImage(String mainDir, String imgDir) {
		File folder = new File(Const.File.DEF_DIR + mainDir + File.separator + imgDir);
		if (folder.exists()) {
			List<File> files = getFilesFromFolder(folder);
			if (!files.isEmpty()) {
				return files.get(0);
			}
		}
		return null;
	}

	/**
	 * Deletes image located in given directory
	 * 
	 * @param mainDir
	 *            main directory
	 * @param imgDir
	 *            image directory
	 * @return true if the image is deleted, false otherwise
	 */
	public static boolean delImage(String mainDir, String imgDir) {
		File folder = new File(Const.File.DEF_DIR + mainDir + File.separator + imgDir);
		if (folder.exists()) {
			clearFolder(folder);
			if (folder.delete()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes all files existing in given directory
	 * 
	 * @param folder
	 *            folder
	 */
	private static void clearFolder(File folder) {
		String[] files = folder.list();
		for (String file : files) {
			new File(folder.getPath(), file).delete();
		}

	}

	/**
	 * Returns image with given path and image name
	 * 
	 * @param mainDir
	 *            main directory
	 * @param imgDir
	 *            image directory
	 * @param fileName
	 *            image name
	 * @return image
	 */
	public static File getImage(String mainDir, String imgDir, String fileName) {
		File image = new File(Const.File.DEF_DIR + mainDir + File.separator + imgDir + File.separator + fileName);
		if (image.exists()) {
			return image;
		}
		return null;
	}

	/**
	 * Extracts file names from folder defined by given path
	 * 
	 * @param mainDir
	 *            main directory
	 * @param imgDir
	 *            image directory
	 * @return list of file names
	 */
	public static List<String> getFileNames(String mainDir, String imgDir) {
		List<String> names = new ArrayList<String>();
		File folder = new File(Const.File.DEF_DIR + mainDir + File.separator + imgDir);
		if (folder.exists()) {
			List<File> files = getFilesFromFolder(folder);
			if (!files.isEmpty()) {
				for (File file : files) {
					names.add(file.getName());
				}
			}
		}
		return names;
	}

	/**
	 * Returns all files existing in given folder
	 * 
	 * @param folder
	 *            folder
	 * @return file list
	 */
	private static List<File> getFilesFromFolder(File folder) {
		List<File> files = new ArrayList<File>();
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				files.add(file);
			}
		}
		return files;

	}

	/**
	 * Returns list of existing parts.
	 * 
	 * @param parts
	 *            part list
	 * @return part list
	 */
	public static List<Part> filterParts(List<Part> parts) {
		List<Part> filteredParts = new ArrayList<Part>();
		for (Part part : parts) {
			if (filterPart(part) != null) {
				filteredParts.add(part);
			}
		}
		return filteredParts;
	}

	/**
	 * Returns part if it exists or null otherwise
	 * 
	 * @param part
	 *            part
	 * @return part
	 */
	public static Part filterPart(Part part) {
		if (!StringUtils.isEmpty(part.getSubmittedFileName())) {
			return part;
		}
		return null;
	}

	/**
	 * Creates directory using given path and directory name
	 * 
	 * @param path
	 *            path
	 * @param dir
	 *            directory name
	 * @return true if the directory is created or false otherwise
	 */
	public static boolean createDir(String path, String dir) {
		File file = new File(Const.File.DEF_DIR + path + dir);
		if (!file.exists()) {
			if (file.mkdir()) {
				LOG.trace("Directory is created ---> " + file);
				return true;
			} else {
				LOG.trace("Failed to create directory ---> " + file);
				return false;
			}
		}
		LOG.trace("Directory already exists ---> " + file);
		return true;
	}

	/**
	 * Extracts strings from bank detail template and puts them to list.
	 * 
	 * @param path
	 *            path
	 * @return list of strings
	 */
	public static List<String> bankDetailsFromText(String path) {
		List<String> details = new ArrayList<String>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(path), "UTF-8");
			while (scanner.hasNextLine()) {
				details.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			LOG.error("Failed to read bank details ---> " + e);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		return details;
	}
}

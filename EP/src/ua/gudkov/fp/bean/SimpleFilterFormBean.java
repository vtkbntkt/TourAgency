package ua.gudkov.fp.bean;

import java.io.Serializable;

/**
 * Simple filter form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class SimpleFilterFormBean implements Serializable {
	private static final long serialVersionUID = 8709616229048453857L;
	private String itemsPerPage;
	private String currentPage;

	public SimpleFilterFormBean(String itemsPerPage, String currentPage) {
		super();
		this.itemsPerPage = itemsPerPage;
		this.currentPage = currentPage;
	}

	public SimpleFilterFormBean() {
		super();
	}

	public String getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(String itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public String toString() {
		return "SimpleFilterBean [itemsPerPage=" + itemsPerPage + ", currentPage=" + currentPage + "]";
	}

}

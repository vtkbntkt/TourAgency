package ua.gudkov.fp.entity;

import java.io.Serializable;

/**
 * Simple filter entity.
 * 
 * @author A.Gudkov
 *
 */
public class SimpleFilter implements Serializable {
	private static final long serialVersionUID = -1332380333793585596L;
	private long startRow = 1;
	private int rowNum = 10;

	public SimpleFilter(long startRow, int rowNum) {
		super();
		this.startRow = startRow;
		this.rowNum = rowNum;
	}

	public SimpleFilter() {
		super();
	}

	public long getStartRow() {
		return startRow;
	}

	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	@Override
	public String toString() {
		return "SimpleFilter [startRow=" + startRow + ", rowNum=" + rowNum + "]";
	}

}

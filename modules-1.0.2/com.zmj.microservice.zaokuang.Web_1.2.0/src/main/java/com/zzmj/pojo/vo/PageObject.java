package com.zzmj.pojo.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

public class PageObject<T> implements Serializable {
	/**
	 * @Fields: 序列化UID
	 */
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private static final long serialVersionUID = -2242427859893671328L;

	/**
	 * @Fields:分页查询的页面
	 */
	private Integer pageNo = 1;
	/**
	 * @Fields: 每页显示的条数
	 */
	private Integer pageSize = 11;
	/**
	 * @Fields: 结果总行数,即count(*)结果
	 */
	private Integer rowCount = 0;
	/**
	 * @Fields: 最大页数
	 */
	private Integer pageCount = 0;
	/**
	 * @Fields: 结果集
	 */
	private List<T> records;

	public PageObject() {
		super();
	}

	public PageObject(Integer pageNo, Integer pageSize, Integer rowCount, List<T> records) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.records = records;
		int y = rowCount % pageSize;
		if (y != 0)
			y = 1;
		this.pageCount = (int) (rowCount / pageSize + y);
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getPageCount() {
		// pageCount没有set方法,直接计算得出
		return pageCount;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

}
package com.zzmj.pojo.entity;

import java.util.ArrayList;
import java.util.List;

public class ZZDeviceExample {
	/**
	 * 主键字段
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	protected String pk_name = "Id";

	/**
	 * 排序字段
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	protected String orderByClause;

	/**
	 * 去重复
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	protected boolean distinct;

	/**
	 * 条件集
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	protected List<Criteria> oredCriteria;

	public ZZDeviceExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setPk_name(String pk_name) {
		this.pk_name = pk_name;
	}

	public String getPk_name() {
		return pk_name;
	}

	/**
	 * 排序字段
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * 设置去重复
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * 条件查询要先创建Criteria
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * 
	 * 内类部，系统内部调用1
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("Id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("Id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("Id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("Id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("Id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("Id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("Id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("Id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("Id like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("Id not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("Id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("Id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("Id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("Id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIsNull() {
			addCriterion("Device_Id is null");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIsNotNull() {
			addCriterion("Device_Id is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceIdEqualTo(String value) {
			addCriterion("Device_Id =", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotEqualTo(String value) {
			addCriterion("Device_Id <>", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdGreaterThan(String value) {
			addCriterion("Device_Id >", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdGreaterThanOrEqualTo(String value) {
			addCriterion("Device_Id >=", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdLessThan(String value) {
			addCriterion("Device_Id <", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdLessThanOrEqualTo(String value) {
			addCriterion("Device_Id <=", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdLike(String value) {
			addCriterion("Device_Id like", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotLike(String value) {
			addCriterion("Device_Id not like", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIn(List<String> values) {
			addCriterion("Device_Id in", values, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotIn(List<String> values) {
			addCriterion("Device_Id not in", values, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdBetween(String value1, String value2) {
			addCriterion("Device_Id between", value1, value2, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotBetween(String value1, String value2) {
			addCriterion("Device_Id not between", value1, value2, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceNameIsNull() {
			addCriterion("Device_Name is null");
			return (Criteria) this;
		}

		public Criteria andDeviceNameIsNotNull() {
			addCriterion("Device_Name is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceNameEqualTo(String value) {
			addCriterion("Device_Name =", value, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceNameNotEqualTo(String value) {
			addCriterion("Device_Name <>", value, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceNameGreaterThan(String value) {
			addCriterion("Device_Name >", value, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceNameGreaterThanOrEqualTo(String value) {
			addCriterion("Device_Name >=", value, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceNameLessThan(String value) {
			addCriterion("Device_Name <", value, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceNameLessThanOrEqualTo(String value) {
			addCriterion("Device_Name <=", value, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceNameLike(String value) {
			addCriterion("Device_Name like", value, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceNameNotLike(String value) {
			addCriterion("Device_Name not like", value, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceNameIn(List<String> values) {
			addCriterion("Device_Name in", values, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceNameNotIn(List<String> values) {
			addCriterion("Device_Name not in", values, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceNameBetween(String value1, String value2) {
			addCriterion("Device_Name between", value1, value2, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceNameNotBetween(String value1, String value2) {
			addCriterion("Device_Name not between", value1, value2, "deviceName");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasIsNull() {
			addCriterion("Device_Alias is null");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasIsNotNull() {
			addCriterion("Device_Alias is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasEqualTo(String value) {
			addCriterion("Device_Alias =", value, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasNotEqualTo(String value) {
			addCriterion("Device_Alias <>", value, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasGreaterThan(String value) {
			addCriterion("Device_Alias >", value, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasGreaterThanOrEqualTo(String value) {
			addCriterion("Device_Alias >=", value, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasLessThan(String value) {
			addCriterion("Device_Alias <", value, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasLessThanOrEqualTo(String value) {
			addCriterion("Device_Alias <=", value, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasLike(String value) {
			addCriterion("Device_Alias like", value, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasNotLike(String value) {
			addCriterion("Device_Alias not like", value, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasIn(List<String> values) {
			addCriterion("Device_Alias in", values, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasNotIn(List<String> values) {
			addCriterion("Device_Alias not in", values, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasBetween(String value1, String value2) {
			addCriterion("Device_Alias between", value1, value2, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceAliasNotBetween(String value1, String value2) {
			addCriterion("Device_Alias not between", value1, value2, "deviceAlias");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmIsNull() {
			addCriterion("Device_Firm is null");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmIsNotNull() {
			addCriterion("Device_Firm is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmEqualTo(String value) {
			addCriterion("Device_Firm =", value, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmNotEqualTo(String value) {
			addCriterion("Device_Firm <>", value, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmGreaterThan(String value) {
			addCriterion("Device_Firm >", value, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmGreaterThanOrEqualTo(String value) {
			addCriterion("Device_Firm >=", value, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmLessThan(String value) {
			addCriterion("Device_Firm <", value, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmLessThanOrEqualTo(String value) {
			addCriterion("Device_Firm <=", value, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmLike(String value) {
			addCriterion("Device_Firm like", value, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmNotLike(String value) {
			addCriterion("Device_Firm not like", value, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmIn(List<String> values) {
			addCriterion("Device_Firm in", values, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmNotIn(List<String> values) {
			addCriterion("Device_Firm not in", values, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmBetween(String value1, String value2) {
			addCriterion("Device_Firm between", value1, value2, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceFirmNotBetween(String value1, String value2) {
			addCriterion("Device_Firm not between", value1, value2, "deviceFirm");
			return (Criteria) this;
		}

		public Criteria andDeviceModelIsNull() {
			addCriterion("Device_Model is null");
			return (Criteria) this;
		}

		public Criteria andDeviceModelIsNotNull() {
			addCriterion("Device_Model is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceModelEqualTo(String value) {
			addCriterion("Device_Model =", value, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceModelNotEqualTo(String value) {
			addCriterion("Device_Model <>", value, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceModelGreaterThan(String value) {
			addCriterion("Device_Model >", value, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceModelGreaterThanOrEqualTo(String value) {
			addCriterion("Device_Model >=", value, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceModelLessThan(String value) {
			addCriterion("Device_Model <", value, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceModelLessThanOrEqualTo(String value) {
			addCriterion("Device_Model <=", value, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceModelLike(String value) {
			addCriterion("Device_Model like", value, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceModelNotLike(String value) {
			addCriterion("Device_Model not like", value, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceModelIn(List<String> values) {
			addCriterion("Device_Model in", values, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceModelNotIn(List<String> values) {
			addCriterion("Device_Model not in", values, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceModelBetween(String value1, String value2) {
			addCriterion("Device_Model between", value1, value2, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceModelNotBetween(String value1, String value2) {
			addCriterion("Device_Model not between", value1, value2, "deviceModel");
			return (Criteria) this;
		}

		public Criteria andDeviceImgIsNull() {
			addCriterion("Device_Img is null");
			return (Criteria) this;
		}

		public Criteria andDeviceImgIsNotNull() {
			addCriterion("Device_Img is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceImgEqualTo(String value) {
			addCriterion("Device_Img =", value, "deviceImg");
			return (Criteria) this;
		}

		public Criteria andDeviceImgNotEqualTo(String value) {
			addCriterion("Device_Img <>", value, "deviceImg");
			return (Criteria) this;
		}

		public Criteria andDeviceImgGreaterThan(String value) {
			addCriterion("Device_Img >", value, "deviceImg");
			return (Criteria) this;
		}

		public Criteria andDeviceImgGreaterThanOrEqualTo(String value) {
			addCriterion("Device_Img >=", value, "deviceImg");
			return (Criteria) this;
		}

		public Criteria andDeviceImgLessThan(String value) {
			addCriterion("Device_Img <", value, "deviceImg");
			return (Criteria) this;
		}

		public Criteria andDeviceImgLessThanOrEqualTo(String value) {
			addCriterion("Device_Img <=", value, "deviceImg");
			return (Criteria) this;
		}

		public Criteria andDeviceImgLike(String value) {
			addCriterion("Device_Img like", value, "deviceImg");
			return (Criteria) this;
		}

		public Criteria andDeviceImgNotLike(String value) {
			addCriterion("Device_Img not like", value, "deviceImg");
			return (Criteria) this;
		}

		public Criteria andDeviceImgIn(List<String> values) {
			addCriterion("Device_Img in", values, "deviceImg");
			return (Criteria) this;
		}

		public Criteria andDeviceImgNotIn(List<String> values) {
			addCriterion("Device_Img not in", values, "deviceImg");
			return (Criteria) this;
		}

		public Criteria andDeviceImgBetween(String value1, String value2) {
			addCriterion("Device_Img between", value1, value2, "deviceImg");
			return (Criteria) this;
		}

		public Criteria andDeviceImgNotBetween(String value1, String value2) {
			addCriterion("Device_Img not between", value1, value2, "deviceImg");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdIsNull() {
			addCriterion("DeviceType_Id is null");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdIsNotNull() {
			addCriterion("DeviceType_Id is not null");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdEqualTo(String value) {
			addCriterion("DeviceType_Id =", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdNotEqualTo(String value) {
			addCriterion("DeviceType_Id <>", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdGreaterThan(String value) {
			addCriterion("DeviceType_Id >", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdGreaterThanOrEqualTo(String value) {
			addCriterion("DeviceType_Id >=", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdLessThan(String value) {
			addCriterion("DeviceType_Id <", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdLessThanOrEqualTo(String value) {
			addCriterion("DeviceType_Id <=", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdLike(String value) {
			addCriterion("DeviceType_Id like", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdNotLike(String value) {
			addCriterion("DeviceType_Id not like", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdIn(List<String> values) {
			addCriterion("DeviceType_Id in", values, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdNotIn(List<String> values) {
			addCriterion("DeviceType_Id not in", values, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdBetween(String value1, String value2) {
			addCriterion("DeviceType_Id between", value1, value2, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria anddeviceTypeIdNotBetween(String value1, String value2) {
			addCriterion("DeviceType_Id not between", value1, value2, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andSortcodeIsNull() {
			addCriterion("SortCode is null");
			return (Criteria) this;
		}

		public Criteria andSortcodeIsNotNull() {
			addCriterion("SortCode is not null");
			return (Criteria) this;
		}

		public Criteria andSortcodeEqualTo(String value) {
			addCriterion("SortCode =", value, "sortCode");
			return (Criteria) this;
		}

		public Criteria andSortcodeNotEqualTo(String value) {
			addCriterion("SortCode <>", value, "sortCode");
			return (Criteria) this;
		}

		public Criteria andSortcodeGreaterThan(String value) {
			addCriterion("SortCode >", value, "sortCode");
			return (Criteria) this;
		}

		public Criteria andSortcodeGreaterThanOrEqualTo(String value) {
			addCriterion("SortCode >=", value, "sortCode");
			return (Criteria) this;
		}

		public Criteria andSortcodeLessThan(String value) {
			addCriterion("SortCode <", value, "sortCode");
			return (Criteria) this;
		}

		public Criteria andSortcodeLessThanOrEqualTo(String value) {
			addCriterion("SortCode <=", value, "sortCode");
			return (Criteria) this;
		}

		public Criteria andSortcodeLike(String value) {
			addCriterion("SortCode like", value, "sortCode");
			return (Criteria) this;
		}

		public Criteria andSortcodeNotLike(String value) {
			addCriterion("SortCode not like", value, "sortCode");
			return (Criteria) this;
		}

		public Criteria andSortcodeIn(List<String> values) {
			addCriterion("SortCode in", values, "sortCode");
			return (Criteria) this;
		}

		public Criteria andSortcodeNotIn(List<String> values) {
			addCriterion("SortCode not in", values, "sortCode");
			return (Criteria) this;
		}

		public Criteria andSortcodeBetween(String value1, String value2) {
			addCriterion("SortCode between", value1, value2, "sortCode");
			return (Criteria) this;
		}

		public Criteria andSortcodeNotBetween(String value1, String value2) {
			addCriterion("SortCode not between", value1, value2, "sortCode");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeIsNull() {
			addCriterion("UpdateTime is null");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeIsNotNull() {
			addCriterion("UpdateTime is not null");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeEqualTo(String value) {
			addCriterion("UpdateTime =", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeNotEqualTo(String value) {
			addCriterion("UpdateTime <>", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeGreaterThan(String value) {
			addCriterion("UpdateTime >", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeGreaterThanOrEqualTo(String value) {
			addCriterion("UpdateTime >=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeLessThan(String value) {
			addCriterion("UpdateTime <", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeLessThanOrEqualTo(String value) {
			addCriterion("UpdateTime <=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeLike(String value) {
			addCriterion("UpdateTime like", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeNotLike(String value) {
			addCriterion("UpdateTime not like", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeIn(List<String> values) {
			addCriterion("UpdateTime in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeNotIn(List<String> values) {
			addCriterion("UpdateTime not in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeBetween(String value1, String value2) {
			addCriterion("UpdateTime between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeNotBetween(String value1, String value2) {
			addCriterion("UpdateTime not between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andIsdelIsNull() {
			addCriterion("isDel is null");
			return (Criteria) this;
		}

		public Criteria andIsdelIsNotNull() {
			addCriterion("isDel is not null");
			return (Criteria) this;
		}

		public Criteria andIsdelEqualTo(String value) {
			addCriterion("isDel =", value, "isdel");
			return (Criteria) this;
		}

		public Criteria andIsdelNotEqualTo(String value) {
			addCriterion("isDel <>", value, "isdel");
			return (Criteria) this;
		}

		public Criteria andIsdelGreaterThan(String value) {
			addCriterion("isDel >", value, "isdel");
			return (Criteria) this;
		}

		public Criteria andIsdelGreaterThanOrEqualTo(String value) {
			addCriterion("isDel >=", value, "isdel");
			return (Criteria) this;
		}

		public Criteria andIsdelLessThan(String value) {
			addCriterion("isDel <", value, "isdel");
			return (Criteria) this;
		}

		public Criteria andIsdelLessThanOrEqualTo(String value) {
			addCriterion("isDel <=", value, "isdel");
			return (Criteria) this;
		}

		public Criteria andIsdelLike(String value) {
			addCriterion("isDel like", value, "isdel");
			return (Criteria) this;
		}

		public Criteria andIsdelNotLike(String value) {
			addCriterion("isDel not like", value, "isdel");
			return (Criteria) this;
		}

		public Criteria andIsdelIn(List<String> values) {
			addCriterion("isDel in", values, "isdel");
			return (Criteria) this;
		}

		public Criteria andIsdelNotIn(List<String> values) {
			addCriterion("isDel not in", values, "isdel");
			return (Criteria) this;
		}

		public Criteria andIsdelBetween(String value1, String value2) {
			addCriterion("isDel between", value1, value2, "isdel");
			return (Criteria) this;
		}

		public Criteria andIsdelNotBetween(String value1, String value2) {
			addCriterion("isDel not between", value1, value2, "isdel");
			return (Criteria) this;
		}
	}

	/**
	 * zz_device
	 * 
	 * @ibatorgenerated do_not_delete_during_merge 2019-06-22 15:39:21
	 */
	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	/**
	 * 
	 * 内类部，系统内部调用1
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}
}
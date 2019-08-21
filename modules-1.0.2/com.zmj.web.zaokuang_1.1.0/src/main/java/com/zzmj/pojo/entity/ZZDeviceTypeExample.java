package com.zzmj.pojo.entity;

import java.util.ArrayList;
import java.util.List;

public class ZZDeviceTypeExample {
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

	public ZZDeviceTypeExample() {
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

		public Criteria andDevicetypeIdIsNull() {
			addCriterion("DeviceType_Id is null");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdIsNotNull() {
			addCriterion("DeviceType_Id is not null");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdEqualTo(String value) {
			addCriterion("DeviceType_Id =", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdNotEqualTo(String value) {
			addCriterion("DeviceType_Id <>", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdGreaterThan(String value) {
			addCriterion("DeviceType_Id >", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdGreaterThanOrEqualTo(String value) {
			addCriterion("DeviceType_Id >=", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdLessThan(String value) {
			addCriterion("DeviceType_Id <", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdLessThanOrEqualTo(String value) {
			addCriterion("DeviceType_Id <=", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdLike(String value) {
			addCriterion("DeviceType_Id like", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdNotLike(String value) {
			addCriterion("DeviceType_Id not like", value, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdIn(List<String> values) {
			addCriterion("DeviceType_Id in", values, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdNotIn(List<String> values) {
			addCriterion("DeviceType_Id not in", values, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdBetween(String value1, String value2) {
			addCriterion("DeviceType_Id between", value1, value2, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeIdNotBetween(String value1, String value2) {
			addCriterion("DeviceType_Id not between", value1, value2, "deviceTypeId");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameIsNull() {
			addCriterion("DeviceType_Name is null");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameIsNotNull() {
			addCriterion("DeviceType_Name is not null");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameEqualTo(String value) {
			addCriterion("DeviceType_Name =", value, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameNotEqualTo(String value) {
			addCriterion("DeviceType_Name <>", value, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameGreaterThan(String value) {
			addCriterion("DeviceType_Name >", value, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameGreaterThanOrEqualTo(String value) {
			addCriterion("DeviceType_Name >=", value, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameLessThan(String value) {
			addCriterion("DeviceType_Name <", value, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameLessThanOrEqualTo(String value) {
			addCriterion("DeviceType_Name <=", value, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameLike(String value) {
			addCriterion("DeviceType_Name like", value, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameNotLike(String value) {
			addCriterion("DeviceType_Name not like", value, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameIn(List<String> values) {
			addCriterion("DeviceType_Name in", values, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameNotIn(List<String> values) {
			addCriterion("DeviceType_Name not in", values, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameBetween(String value1, String value2) {
			addCriterion("DeviceType_Name between", value1, value2, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeNameNotBetween(String value1, String value2) {
			addCriterion("DeviceType_Name not between", value1, value2, "deviceTypeName");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasIsNull() {
			addCriterion("DeviceType_Alias is null");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasIsNotNull() {
			addCriterion("DeviceType_Alias is not null");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasEqualTo(String value) {
			addCriterion("DeviceType_Alias =", value, "deviceTypeAlias");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasNotEqualTo(String value) {
			addCriterion("DeviceType_Alias <>", value, "deviceTypeAlias");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasGreaterThan(String value) {
			addCriterion("DeviceType_Alias >", value, "deviceTypeAlias");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasGreaterThanOrEqualTo(String value) {
			addCriterion("DeviceType_Alias >=", value, "deviceTypeAlias");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasLessThan(String value) {
			addCriterion("DeviceType_Alias <", value, "deviceTypeAlias");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasLessThanOrEqualTo(String value) {
			addCriterion("DeviceType_Alias <=", value, "deviceTypeAlias");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasLike(String value) {
			addCriterion("DeviceType_Alias like", value, "deviceTypeAlias");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasNotLike(String value) {
			addCriterion("DeviceType_Alias not like", value, "deviceTypeAlias");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasIn(List<String> values) {
			addCriterion("DeviceType_Alias in", values, "deviceTypeAlias");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasNotIn(List<String> values) {
			addCriterion("DeviceType_Alias not in", values, "deviceTypeAlias");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasBetween(String value1, String value2) {
			addCriterion("DeviceType_Alias between", value1, value2, "deviceTypeAlias");
			return (Criteria) this;
		}

		public Criteria andDevicetypeAliasNotBetween(String value1, String value2) {
			addCriterion("DeviceType_Alias not between", value1, value2, "deviceTypeAlias");
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
			addCriterion("isDel =", value, "isDel");
			return (Criteria) this;
		}

		public Criteria andIsdelNotEqualTo(String value) {
			addCriterion("isDel <>", value, "isDel");
			return (Criteria) this;
		}

		public Criteria andIsdelGreaterThan(String value) {
			addCriterion("isDel >", value, "isDel");
			return (Criteria) this;
		}

		public Criteria andIsdelGreaterThanOrEqualTo(String value) {
			addCriterion("isDel >=", value, "isDel");
			return (Criteria) this;
		}

		public Criteria andIsdelLessThan(String value) {
			addCriterion("isDel <", value, "isDel");
			return (Criteria) this;
		}

		public Criteria andIsdelLessThanOrEqualTo(String value) {
			addCriterion("isDel <=", value, "isDel");
			return (Criteria) this;
		}

		public Criteria andIsdelLike(String value) {
			addCriterion("isDel like", value, "isDel");
			return (Criteria) this;
		}

		public Criteria andIsdelNotLike(String value) {
			addCriterion("isDel not like", value, "isDel");
			return (Criteria) this;
		}

		public Criteria andIsdelIn(List<String> values) {
			addCriterion("isDel in", values, "isDel");
			return (Criteria) this;
		}

		public Criteria andIsdelNotIn(List<String> values) {
			addCriterion("isDel not in", values, "isDel");
			return (Criteria) this;
		}

		public Criteria andIsdelBetween(String value1, String value2) {
			addCriterion("isDel between", value1, value2, "isDel");
			return (Criteria) this;
		}

		public Criteria andIsdelNotBetween(String value1, String value2) {
			addCriterion("isDel not between", value1, value2, "isDel");
			return (Criteria) this;
		}
	}

	/**
	 * zz_devicetype
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
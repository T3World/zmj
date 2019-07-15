package com.zzmj.pojo.entity;

import java.util.ArrayList;
import java.util.List;

public class ZZDeviceAttributeExample {
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

	public ZZDeviceAttributeExample() {
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

		public Criteria andAttributeIdIsNull() {
			addCriterion("Attribute_Id is null");
			return (Criteria) this;
		}

		public Criteria andAttributeIdIsNotNull() {
			addCriterion("Attribute_Id is not null");
			return (Criteria) this;
		}

		public Criteria andAttributeIdEqualTo(String value) {
			addCriterion("Attribute_Id =", value, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeIdNotEqualTo(String value) {
			addCriterion("Attribute_Id <>", value, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeIdGreaterThan(String value) {
			addCriterion("Attribute_Id >", value, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeIdGreaterThanOrEqualTo(String value) {
			addCriterion("Attribute_Id >=", value, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeIdLessThan(String value) {
			addCriterion("Attribute_Id <", value, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeIdLessThanOrEqualTo(String value) {
			addCriterion("Attribute_Id <=", value, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeIdLike(String value) {
			addCriterion("Attribute_Id like", value, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeIdNotLike(String value) {
			addCriterion("Attribute_Id not like", value, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeIdIn(List<String> values) {
			addCriterion("Attribute_Id in", values, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeIdNotIn(List<String> values) {
			addCriterion("Attribute_Id not in", values, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeIdBetween(String value1, String value2) {
			addCriterion("Attribute_Id between", value1, value2, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeIdNotBetween(String value1, String value2) {
			addCriterion("Attribute_Id not between", value1, value2, "attributeId");
			return (Criteria) this;
		}

		public Criteria andAttributeNameIsNull() {
			addCriterion("Attribute_Name is null");
			return (Criteria) this;
		}

		public Criteria andAttributeNameIsNotNull() {
			addCriterion("Attribute_Name is not null");
			return (Criteria) this;
		}

		public Criteria andAttributeNameEqualTo(String value) {
			addCriterion("Attribute_Name =", value, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeNameNotEqualTo(String value) {
			addCriterion("Attribute_Name <>", value, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeNameGreaterThan(String value) {
			addCriterion("Attribute_Name >", value, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeNameGreaterThanOrEqualTo(String value) {
			addCriterion("Attribute_Name >=", value, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeNameLessThan(String value) {
			addCriterion("Attribute_Name <", value, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeNameLessThanOrEqualTo(String value) {
			addCriterion("Attribute_Name <=", value, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeNameLike(String value) {
			addCriterion("Attribute_Name like", value, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeNameNotLike(String value) {
			addCriterion("Attribute_Name not like", value, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeNameIn(List<String> values) {
			addCriterion("Attribute_Name in", values, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeNameNotIn(List<String> values) {
			addCriterion("Attribute_Name not in", values, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeNameBetween(String value1, String value2) {
			addCriterion("Attribute_Name between", value1, value2, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeNameNotBetween(String value1, String value2) {
			addCriterion("Attribute_Name not between", value1, value2, "attributeName");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasIsNull() {
			addCriterion("Attribute_Alias is null");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasIsNotNull() {
			addCriterion("Attribute_Alias is not null");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasEqualTo(String value) {
			addCriterion("Attribute_Alias =", value, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasNotEqualTo(String value) {
			addCriterion("Attribute_Alias <>", value, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasGreaterThan(String value) {
			addCriterion("Attribute_Alias >", value, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasGreaterThanOrEqualTo(String value) {
			addCriterion("Attribute_Alias >=", value, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasLessThan(String value) {
			addCriterion("Attribute_Alias <", value, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasLessThanOrEqualTo(String value) {
			addCriterion("Attribute_Alias <=", value, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasLike(String value) {
			addCriterion("Attribute_Alias like", value, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasNotLike(String value) {
			addCriterion("Attribute_Alias not like", value, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasIn(List<String> values) {
			addCriterion("Attribute_Alias in", values, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasNotIn(List<String> values) {
			addCriterion("Attribute_Alias not in", values, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasBetween(String value1, String value2) {
			addCriterion("Attribute_Alias between", value1, value2, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeAliasNotBetween(String value1, String value2) {
			addCriterion("Attribute_Alias not between", value1, value2, "attributeAlias");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitIsNull() {
			addCriterion("Attribute_Unit is null");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitIsNotNull() {
			addCriterion("Attribute_Unit is not null");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitEqualTo(String value) {
			addCriterion("Attribute_Unit =", value, "attributeUnit");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitNotEqualTo(String value) {
			addCriterion("Attribute_Unit <>", value, "attributeUnit");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitGreaterThan(String value) {
			addCriterion("Attribute_Unit >", value, "attributeUnit");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitGreaterThanOrEqualTo(String value) {
			addCriterion("Attribute_Unit >=", value, "attributeUnit");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitLessThan(String value) {
			addCriterion("Attribute_Unit <", value, "attributeUnit");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitLessThanOrEqualTo(String value) {
			addCriterion("Attribute_Unit <=", value, "attributeUnit");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitLike(String value) {
			addCriterion("Attribute_Unit like", value, "attributeUnit");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitNotLike(String value) {
			addCriterion("Attribute_Unit not like", value, "attributeUnit");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitIn(List<String> values) {
			addCriterion("Attribute_Unit in", values, "attributeUnit");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitNotIn(List<String> values) {
			addCriterion("Attribute_Unit not in", values, "attributeUnit");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitBetween(String value1, String value2) {
			addCriterion("Attribute_Unit between", value1, value2, "attributeUnit");
			return (Criteria) this;
		}

		public Criteria andAttributeUnitNotBetween(String value1, String value2) {
			addCriterion("Attribute_Unit not between", value1, value2, "attributeUnit");
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
	 * zz_deviceattribute
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
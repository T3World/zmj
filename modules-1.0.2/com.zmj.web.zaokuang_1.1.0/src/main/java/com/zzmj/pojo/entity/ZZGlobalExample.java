package com.zzmj.pojo.entity;

import java.util.ArrayList;
import java.util.List;

public class ZZGlobalExample {
	/**
	 * 主键字段
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	protected String pk_name = "Id";

	/**
	 * 排序字段
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	protected String orderByClause;

	/**
	 * 去重复
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	protected boolean distinct;

	/**
	 * 条件集
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	protected List<Criteria> oredCriteria;

	public ZZGlobalExample() {
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
	 * @ibatorgenerated 2018-12-08 14:48:37
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
	 * @ibatorgenerated 2018-12-08 14:48:37
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
	 * @ibatorgenerated 2018-12-08 14:48:37
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
	 * @ibatorgenerated 2018-12-08 14:48:37
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

		public Criteria andGlobalIdIsNull() {
			addCriterion("Global_Id is null");
			return (Criteria) this;
		}

		public Criteria andGlobalIdIsNotNull() {
			addCriterion("Global_Id is not null");
			return (Criteria) this;
		}

		public Criteria andGlobalIdEqualTo(String value) {
			addCriterion("Global_Id =", value, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalIdNotEqualTo(String value) {
			addCriterion("Global_Id <>", value, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalIdGreaterThan(String value) {
			addCriterion("Global_Id >", value, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalIdGreaterThanOrEqualTo(String value) {
			addCriterion("Global_Id >=", value, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalIdLessThan(String value) {
			addCriterion("Global_Id <", value, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalIdLessThanOrEqualTo(String value) {
			addCriterion("Global_Id <=", value, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalIdLike(String value) {
			addCriterion("Global_Id like", value, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalIdNotLike(String value) {
			addCriterion("Global_Id not like", value, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalIdIn(List<String> values) {
			addCriterion("Global_Id in", values, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalIdNotIn(List<String> values) {
			addCriterion("Global_Id not in", values, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalIdBetween(String value1, String value2) {
			addCriterion("Global_Id between", value1, value2, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalIdNotBetween(String value1, String value2) {
			addCriterion("Global_Id not between", value1, value2, "globalId");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeIsNull() {
			addCriterion("Global_ShearerPosTime is null");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeIsNotNull() {
			addCriterion("Global_ShearerPosTime is not null");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeEqualTo(String value) {
			addCriterion("Global_ShearerPosTime =", value, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeNotEqualTo(String value) {
			addCriterion("Global_ShearerPosTime <>", value, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeGreaterThan(String value) {
			addCriterion("Global_ShearerPosTime >", value, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeGreaterThanOrEqualTo(String value) {
			addCriterion("Global_ShearerPosTime >=", value, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeLessThan(String value) {
			addCriterion("Global_ShearerPosTime <", value, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeLessThanOrEqualTo(String value) {
			addCriterion("Global_ShearerPosTime <=", value, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeLike(String value) {
			addCriterion("Global_ShearerPosTime like", value, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeNotLike(String value) {
			addCriterion("Global_ShearerPosTime not like", value, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeIn(List<String> values) {
			addCriterion("Global_ShearerPosTime in", values, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeNotIn(List<String> values) {
			addCriterion("Global_ShearerPosTime not in", values, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeBetween(String value1, String value2) {
			addCriterion("Global_ShearerPosTime between", value1, value2, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalShearerpostimeNotBetween(String value1, String value2) {
			addCriterion("Global_ShearerPosTime not between", value1, value2, "globalShearerpostime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeIsNull() {
			addCriterion("Global_CheTime is null");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeIsNotNull() {
			addCriterion("Global_CheTime is not null");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeEqualTo(String value) {
			addCriterion("Global_CheTime =", value, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeNotEqualTo(String value) {
			addCriterion("Global_CheTime <>", value, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeGreaterThan(String value) {
			addCriterion("Global_CheTime >", value, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeGreaterThanOrEqualTo(String value) {
			addCriterion("Global_CheTime >=", value, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeLessThan(String value) {
			addCriterion("Global_CheTime <", value, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeLessThanOrEqualTo(String value) {
			addCriterion("Global_CheTime <=", value, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeLike(String value) {
			addCriterion("Global_CheTime like", value, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeNotLike(String value) {
			addCriterion("Global_CheTime not like", value, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeIn(List<String> values) {
			addCriterion("Global_CheTime in", values, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeNotIn(List<String> values) {
			addCriterion("Global_CheTime not in", values, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeBetween(String value1, String value2) {
			addCriterion("Global_CheTime between", value1, value2, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalChetimeNotBetween(String value1, String value2) {
			addCriterion("Global_CheTime not between", value1, value2, "globalChetime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeIsNull() {
			addCriterion("Global_DiffPressureTime is null");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeIsNotNull() {
			addCriterion("Global_DiffPressureTime is not null");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeEqualTo(String value) {
			addCriterion("Global_DiffPressureTime =", value, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeNotEqualTo(String value) {
			addCriterion("Global_DiffPressureTime <>", value, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeGreaterThan(String value) {
			addCriterion("Global_DiffPressureTime >", value, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeGreaterThanOrEqualTo(String value) {
			addCriterion("Global_DiffPressureTime >=", value, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeLessThan(String value) {
			addCriterion("Global_DiffPressureTime <", value, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeLessThanOrEqualTo(String value) {
			addCriterion("Global_DiffPressureTime <=", value, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeLike(String value) {
			addCriterion("Global_DiffPressureTime like", value, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeNotLike(String value) {
			addCriterion("Global_DiffPressureTime not like", value, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeIn(List<String> values) {
			addCriterion("Global_DiffPressureTime in", values, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeNotIn(List<String> values) {
			addCriterion("Global_DiffPressureTime not in", values, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeBetween(String value1, String value2) {
			addCriterion("Global_DiffPressureTime between", value1, value2, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalDiffpressuretimeNotBetween(String value1, String value2) {
			addCriterion("Global_DiffPressureTime not between", value1, value2, "globalDiffpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeIsNull() {
			addCriterion("Global_MinePressureTime is null");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeIsNotNull() {
			addCriterion("Global_MinePressureTime is not null");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeEqualTo(String value) {
			addCriterion("Global_MinePressureTime =", value, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeNotEqualTo(String value) {
			addCriterion("Global_MinePressureTime <>", value, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeGreaterThan(String value) {
			addCriterion("Global_MinePressureTime >", value, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeGreaterThanOrEqualTo(String value) {
			addCriterion("Global_MinePressureTime >=", value, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeLessThan(String value) {
			addCriterion("Global_MinePressureTime <", value, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeLessThanOrEqualTo(String value) {
			addCriterion("Global_MinePressureTime <=", value, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeLike(String value) {
			addCriterion("Global_MinePressureTime like", value, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeNotLike(String value) {
			addCriterion("Global_MinePressureTime not like", value, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeIn(List<String> values) {
			addCriterion("Global_MinePressureTime in", values, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeNotIn(List<String> values) {
			addCriterion("Global_MinePressureTime not in", values, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeBetween(String value1, String value2) {
			addCriterion("Global_MinePressureTime between", value1, value2, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalMinepressuretimeNotBetween(String value1, String value2) {
			addCriterion("Global_MinePressureTime not between", value1, value2, "globalMinepressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeIsNull() {
			addCriterion("Global_SettingPressureTime is null");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeIsNotNull() {
			addCriterion("Global_SettingPressureTime is not null");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeEqualTo(String value) {
			addCriterion("Global_SettingPressureTime =", value, "globalSettingpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeNotEqualTo(String value) {
			addCriterion("Global_SettingPressureTime <>", value, "globalSettingpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeGreaterThan(String value) {
			addCriterion("Global_SettingPressureTime >", value, "globalSettingpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeGreaterThanOrEqualTo(String value) {
			addCriterion("Global_SettingPressureTime >=", value, "globalSettingpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeLessThan(String value) {
			addCriterion("Global_SettingPressureTime <", value, "globalSettingpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeLessThanOrEqualTo(String value) {
			addCriterion("Global_SettingPressureTime <=", value, "globalSettingpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeLike(String value) {
			addCriterion("Global_SettingPressureTime like", value, "globalSettingpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeNotLike(String value) {
			addCriterion("Global_SettingPressureTime not like", value, "globalSettingpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeIn(List<String> values) {
			addCriterion("Global_SettingPressureTime in", values, "globalSettingpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeNotIn(List<String> values) {
			addCriterion("Global_SettingPressureTime not in", values, "globalSettingpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeBetween(String value1, String value2) {
			addCriterion("Global_SettingPressureTime between", value1, value2, "globalSettingpressuretime");
			return (Criteria) this;
		}

		public Criteria andGlobalSettingpressuretimeNotBetween(String value1, String value2) {
			addCriterion("Global_SettingPressureTime not between", value1, value2, "globalSettingpressuretime");
			return (Criteria) this;
		}
	}

	/**
	 * ZZ_Global
	 * 
	 * @ibatorgenerated do_not_delete_during_merge 2018-12-08 14:48:37
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
	 * @ibatorgenerated 2018-12-08 14:48:37
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
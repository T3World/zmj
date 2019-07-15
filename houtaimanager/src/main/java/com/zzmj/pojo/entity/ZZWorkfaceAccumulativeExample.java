package com.zzmj.pojo.entity;

import java.util.ArrayList;
import java.util.List;

public class ZZWorkfaceAccumulativeExample {
	/**
	 * 主键字段
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	protected String pk_name = "Workface_Id";

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

	public ZZWorkfaceAccumulativeExample() {
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

		public Criteria andWorkfaceIdIsNull() {
			addCriterion("Workface_Id is null");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdIsNotNull() {
			addCriterion("Workface_Id is not null");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdEqualTo(String value) {
			addCriterion("Workface_Id =", value, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdNotEqualTo(String value) {
			addCriterion("Workface_Id <>", value, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdGreaterThan(String value) {
			addCriterion("Workface_Id >", value, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdGreaterThanOrEqualTo(String value) {
			addCriterion("Workface_Id >=", value, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdLessThan(String value) {
			addCriterion("Workface_Id <", value, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdLessThanOrEqualTo(String value) {
			addCriterion("Workface_Id <=", value, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdLike(String value) {
			addCriterion("Workface_Id like", value, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdNotLike(String value) {
			addCriterion("Workface_Id not like", value, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdIn(List<String> values) {
			addCriterion("Workface_Id in", values, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdNotIn(List<String> values) {
			addCriterion("Workface_Id not in", values, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdBetween(String value1, String value2) {
			addCriterion("Workface_Id between", value1, value2, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceIdNotBetween(String value1, String value2) {
			addCriterion("Workface_Id not between", value1, value2, "workfaceId");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeIsNull() {
			addCriterion("`Workface_ RunningTime` is null");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeIsNotNull() {
			addCriterion("`Workface_ RunningTime` is not null");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeEqualTo(String value) {
			addCriterion("`Workface_ RunningTime` =", value, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeNotEqualTo(String value) {
			addCriterion("`Workface_ RunningTime` <>", value, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeGreaterThan(String value) {
			addCriterion("`Workface_ RunningTime` >", value, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeGreaterThanOrEqualTo(String value) {
			addCriterion("`Workface_ RunningTime` >=", value, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeLessThan(String value) {
			addCriterion("`Workface_ RunningTime` <", value, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeLessThanOrEqualTo(String value) {
			addCriterion("`Workface_ RunningTime` <=", value, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeLike(String value) {
			addCriterion("`Workface_ RunningTime` like", value, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeNotLike(String value) {
			addCriterion("`Workface_ RunningTime` not like", value, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeIn(List<String> values) {
			addCriterion("`Workface_ RunningTime` in", values, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeNotIn(List<String> values) {
			addCriterion("`Workface_ RunningTime` not in", values, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeBetween(String value1, String value2) {
			addCriterion("`Workface_ RunningTime` between", value1, value2, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceRunningtimeNotBetween(String value1, String value2) {
			addCriterion("`Workface_ RunningTime` not between", value1, value2, "workfaceRunningtime");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionIsNull() {
			addCriterion("`Workface_ AccuPropulsion` is null");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionIsNotNull() {
			addCriterion("`Workface_ AccuPropulsion` is not null");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionEqualTo(String value) {
			addCriterion("`Workface_ AccuPropulsion` =", value, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionNotEqualTo(String value) {
			addCriterion("`Workface_ AccuPropulsion` <>", value, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionGreaterThan(String value) {
			addCriterion("`Workface_ AccuPropulsion` >", value, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionGreaterThanOrEqualTo(String value) {
			addCriterion("`Workface_ AccuPropulsion` >=", value, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionLessThan(String value) {
			addCriterion("`Workface_ AccuPropulsion` <", value, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionLessThanOrEqualTo(String value) {
			addCriterion("`Workface_ AccuPropulsion` <=", value, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionLike(String value) {
			addCriterion("`Workface_ AccuPropulsion` like", value, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionNotLike(String value) {
			addCriterion("`Workface_ AccuPropulsion` not like", value, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionIn(List<String> values) {
			addCriterion("`Workface_ AccuPropulsion` in", values, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionNotIn(List<String> values) {
			addCriterion("`Workface_ AccuPropulsion` not in", values, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionBetween(String value1, String value2) {
			addCriterion("`Workface_ AccuPropulsion` between", value1, value2, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceAccupropulsionNotBetween(String value1, String value2) {
			addCriterion("`Workface_ AccuPropulsion` not between", value1, value2, "workfaceAccupropulsion");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalIsNull() {
			addCriterion("Workface_ExcessCoal is null");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalIsNotNull() {
			addCriterion("Workface_ExcessCoal is not null");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalEqualTo(String value) {
			addCriterion("Workface_ExcessCoal =", value, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalNotEqualTo(String value) {
			addCriterion("Workface_ExcessCoal <>", value, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalGreaterThan(String value) {
			addCriterion("Workface_ExcessCoal >", value, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalGreaterThanOrEqualTo(String value) {
			addCriterion("Workface_ExcessCoal >=", value, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalLessThan(String value) {
			addCriterion("Workface_ExcessCoal <", value, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalLessThanOrEqualTo(String value) {
			addCriterion("Workface_ExcessCoal <=", value, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalLike(String value) {
			addCriterion("Workface_ExcessCoal like", value, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalNotLike(String value) {
			addCriterion("Workface_ExcessCoal not like", value, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalIn(List<String> values) {
			addCriterion("Workface_ExcessCoal in", values, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalNotIn(List<String> values) {
			addCriterion("Workface_ExcessCoal not in", values, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalBetween(String value1, String value2) {
			addCriterion("Workface_ExcessCoal between", value1, value2, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceExcesscoalNotBetween(String value1, String value2) {
			addCriterion("Workface_ExcessCoal not between", value1, value2, "workfaceExcesscoal");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberIsNull() {
			addCriterion("Workface_CycleNumber is null");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberIsNotNull() {
			addCriterion("Workface_CycleNumber is not null");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberEqualTo(String value) {
			addCriterion("Workface_CycleNumber =", value, "workfaceCyclenumber");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberNotEqualTo(String value) {
			addCriterion("Workface_CycleNumber <>", value, "workfaceCyclenumber");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberGreaterThan(String value) {
			addCriterion("Workface_CycleNumber >", value, "workfaceCyclenumber");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberGreaterThanOrEqualTo(String value) {
			addCriterion("Workface_CycleNumber >=", value, "workfaceCyclenumber");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberLessThan(String value) {
			addCriterion("Workface_CycleNumber <", value, "workfaceCyclenumber");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberLessThanOrEqualTo(String value) {
			addCriterion("Workface_CycleNumber <=", value, "workfaceCyclenumber");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberLike(String value) {
			addCriterion("Workface_CycleNumber like", value, "workfaceCyclenumber");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberNotLike(String value) {
			addCriterion("Workface_CycleNumber not like", value, "workfaceCyclenumber");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberIn(List<String> values) {
			addCriterion("Workface_CycleNumber in", values, "workfaceCyclenumber");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberNotIn(List<String> values) {
			addCriterion("Workface_CycleNumber not in", values, "workfaceCyclenumber");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberBetween(String value1, String value2) {
			addCriterion("Workface_CycleNumber between", value1, value2, "workfaceCyclenumber");
			return (Criteria) this;
		}

		public Criteria andWorkfaceCyclenumberNotBetween(String value1, String value2) {
			addCriterion("Workface_CycleNumber not between", value1, value2, "workfaceCyclenumber");
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
	}

	/**
	 * zz_wofkfaceaccumulative
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
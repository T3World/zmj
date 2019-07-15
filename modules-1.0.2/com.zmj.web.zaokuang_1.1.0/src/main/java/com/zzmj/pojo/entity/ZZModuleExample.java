package com.zzmj.pojo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZZModuleExample {
    /**
     * 主键字段
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    protected String pk_name = "Id";

    /**
     * 排序字段
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    protected String orderByClause;

    /**
     * 去重复
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    protected boolean distinct;

    /**
     * 条件集
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    protected List<Criteria> oredCriteria;

    public ZZModuleExample() {
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

        public Criteria andMIdIsNull() {
            addCriterion("M_Id is null");
            return (Criteria) this;
        }

        public Criteria andMIdIsNotNull() {
            addCriterion("M_Id is not null");
            return (Criteria) this;
        }

        public Criteria andMIdEqualTo(String value) {
            addCriterion("M_Id =", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotEqualTo(String value) {
            addCriterion("M_Id <>", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdGreaterThan(String value) {
            addCriterion("M_Id >", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdGreaterThanOrEqualTo(String value) {
            addCriterion("M_Id >=", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLessThan(String value) {
            addCriterion("M_Id <", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLessThanOrEqualTo(String value) {
            addCriterion("M_Id <=", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLike(String value) {
            addCriterion("M_Id like", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotLike(String value) {
            addCriterion("M_Id not like", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdIn(List<String> values) {
            addCriterion("M_Id in", values, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotIn(List<String> values) {
            addCriterion("M_Id not in", values, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdBetween(String value1, String value2) {
            addCriterion("M_Id between", value1, value2, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotBetween(String value1, String value2) {
            addCriterion("M_Id not between", value1, value2, "mId");
            return (Criteria) this;
        }

        public Criteria andMPidIsNull() {
            addCriterion("M_PId is null");
            return (Criteria) this;
        }

        public Criteria andMPidIsNotNull() {
            addCriterion("M_PId is not null");
            return (Criteria) this;
        }

        public Criteria andMPidEqualTo(String value) {
            addCriterion("M_PId =", value, "mPid");
            return (Criteria) this;
        }

        public Criteria andMPidNotEqualTo(String value) {
            addCriterion("M_PId <>", value, "mPid");
            return (Criteria) this;
        }

        public Criteria andMPidGreaterThan(String value) {
            addCriterion("M_PId >", value, "mPid");
            return (Criteria) this;
        }

        public Criteria andMPidGreaterThanOrEqualTo(String value) {
            addCriterion("M_PId >=", value, "mPid");
            return (Criteria) this;
        }

        public Criteria andMPidLessThan(String value) {
            addCriterion("M_PId <", value, "mPid");
            return (Criteria) this;
        }

        public Criteria andMPidLessThanOrEqualTo(String value) {
            addCriterion("M_PId <=", value, "mPid");
            return (Criteria) this;
        }

        public Criteria andMPidLike(String value) {
            addCriterion("M_PId like", value, "mPid");
            return (Criteria) this;
        }

        public Criteria andMPidNotLike(String value) {
            addCriterion("M_PId not like", value, "mPid");
            return (Criteria) this;
        }

        public Criteria andMPidIn(List<String> values) {
            addCriterion("M_PId in", values, "mPid");
            return (Criteria) this;
        }

        public Criteria andMPidNotIn(List<String> values) {
            addCriterion("M_PId not in", values, "mPid");
            return (Criteria) this;
        }

        public Criteria andMPidBetween(String value1, String value2) {
            addCriterion("M_PId between", value1, value2, "mPid");
            return (Criteria) this;
        }

        public Criteria andMPidNotBetween(String value1, String value2) {
            addCriterion("M_PId not between", value1, value2, "mPid");
            return (Criteria) this;
        }

        public Criteria andMNameIsNull() {
            addCriterion("M_Name is null");
            return (Criteria) this;
        }

        public Criteria andMNameIsNotNull() {
            addCriterion("M_Name is not null");
            return (Criteria) this;
        }

        public Criteria andMNameEqualTo(String value) {
            addCriterion("M_Name =", value, "mName");
            return (Criteria) this;
        }

        public Criteria andMNameNotEqualTo(String value) {
            addCriterion("M_Name <>", value, "mName");
            return (Criteria) this;
        }

        public Criteria andMNameGreaterThan(String value) {
            addCriterion("M_Name >", value, "mName");
            return (Criteria) this;
        }

        public Criteria andMNameGreaterThanOrEqualTo(String value) {
            addCriterion("M_Name >=", value, "mName");
            return (Criteria) this;
        }

        public Criteria andMNameLessThan(String value) {
            addCriterion("M_Name <", value, "mName");
            return (Criteria) this;
        }

        public Criteria andMNameLessThanOrEqualTo(String value) {
            addCriterion("M_Name <=", value, "mName");
            return (Criteria) this;
        }

        public Criteria andMNameLike(String value) {
            addCriterion("M_Name like", value, "mName");
            return (Criteria) this;
        }

        public Criteria andMNameNotLike(String value) {
            addCriterion("M_Name not like", value, "mName");
            return (Criteria) this;
        }

        public Criteria andMNameIn(List<String> values) {
            addCriterion("M_Name in", values, "mName");
            return (Criteria) this;
        }

        public Criteria andMNameNotIn(List<String> values) {
            addCriterion("M_Name not in", values, "mName");
            return (Criteria) this;
        }

        public Criteria andMNameBetween(String value1, String value2) {
            addCriterion("M_Name between", value1, value2, "mName");
            return (Criteria) this;
        }

        public Criteria andMNameNotBetween(String value1, String value2) {
            addCriterion("M_Name not between", value1, value2, "mName");
            return (Criteria) this;
        }

        public Criteria andMValueIsNull() {
            addCriterion("M_Value is null");
            return (Criteria) this;
        }

        public Criteria andMValueIsNotNull() {
            addCriterion("M_Value is not null");
            return (Criteria) this;
        }

        public Criteria andMValueEqualTo(String value) {
            addCriterion("M_Value =", value, "mValue");
            return (Criteria) this;
        }

        public Criteria andMValueNotEqualTo(String value) {
            addCriterion("M_Value <>", value, "mValue");
            return (Criteria) this;
        }

        public Criteria andMValueGreaterThan(String value) {
            addCriterion("M_Value >", value, "mValue");
            return (Criteria) this;
        }

        public Criteria andMValueGreaterThanOrEqualTo(String value) {
            addCriterion("M_Value >=", value, "mValue");
            return (Criteria) this;
        }

        public Criteria andMValueLessThan(String value) {
            addCriterion("M_Value <", value, "mValue");
            return (Criteria) this;
        }

        public Criteria andMValueLessThanOrEqualTo(String value) {
            addCriterion("M_Value <=", value, "mValue");
            return (Criteria) this;
        }

        public Criteria andMValueLike(String value) {
            addCriterion("M_Value like", value, "mValue");
            return (Criteria) this;
        }

        public Criteria andMValueNotLike(String value) {
            addCriterion("M_Value not like", value, "mValue");
            return (Criteria) this;
        }

        public Criteria andMValueIn(List<String> values) {
            addCriterion("M_Value in", values, "mValue");
            return (Criteria) this;
        }

        public Criteria andMValueNotIn(List<String> values) {
            addCriterion("M_Value not in", values, "mValue");
            return (Criteria) this;
        }

        public Criteria andMValueBetween(String value1, String value2) {
            addCriterion("M_Value between", value1, value2, "mValue");
            return (Criteria) this;
        }

        public Criteria andMValueNotBetween(String value1, String value2) {
            addCriterion("M_Value not between", value1, value2, "mValue");
            return (Criteria) this;
        }

        public Criteria andMUrlIsNull() {
            addCriterion("M_Url is null");
            return (Criteria) this;
        }

        public Criteria andMUrlIsNotNull() {
            addCriterion("M_Url is not null");
            return (Criteria) this;
        }

        public Criteria andMUrlEqualTo(String value) {
            addCriterion("M_Url =", value, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMUrlNotEqualTo(String value) {
            addCriterion("M_Url <>", value, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMUrlGreaterThan(String value) {
            addCriterion("M_Url >", value, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMUrlGreaterThanOrEqualTo(String value) {
            addCriterion("M_Url >=", value, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMUrlLessThan(String value) {
            addCriterion("M_Url <", value, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMUrlLessThanOrEqualTo(String value) {
            addCriterion("M_Url <=", value, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMUrlLike(String value) {
            addCriterion("M_Url like", value, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMUrlNotLike(String value) {
            addCriterion("M_Url not like", value, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMUrlIn(List<String> values) {
            addCriterion("M_Url in", values, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMUrlNotIn(List<String> values) {
            addCriterion("M_Url not in", values, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMUrlBetween(String value1, String value2) {
            addCriterion("M_Url between", value1, value2, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMUrlNotBetween(String value1, String value2) {
            addCriterion("M_Url not between", value1, value2, "mUrl");
            return (Criteria) this;
        }

        public Criteria andMIconIsNull() {
            addCriterion("M_Icon is null");
            return (Criteria) this;
        }

        public Criteria andMIconIsNotNull() {
            addCriterion("M_Icon is not null");
            return (Criteria) this;
        }

        public Criteria andMIconEqualTo(String value) {
            addCriterion("M_Icon =", value, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMIconNotEqualTo(String value) {
            addCriterion("M_Icon <>", value, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMIconGreaterThan(String value) {
            addCriterion("M_Icon >", value, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMIconGreaterThanOrEqualTo(String value) {
            addCriterion("M_Icon >=", value, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMIconLessThan(String value) {
            addCriterion("M_Icon <", value, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMIconLessThanOrEqualTo(String value) {
            addCriterion("M_Icon <=", value, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMIconLike(String value) {
            addCriterion("M_Icon like", value, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMIconNotLike(String value) {
            addCriterion("M_Icon not like", value, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMIconIn(List<String> values) {
            addCriterion("M_Icon in", values, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMIconNotIn(List<String> values) {
            addCriterion("M_Icon not in", values, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMIconBetween(String value1, String value2) {
            addCriterion("M_Icon between", value1, value2, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMIconNotBetween(String value1, String value2) {
            addCriterion("M_Icon not between", value1, value2, "mIcon");
            return (Criteria) this;
        }

        public Criteria andMDesIsNull() {
            addCriterion("M_Des is null");
            return (Criteria) this;
        }

        public Criteria andMDesIsNotNull() {
            addCriterion("M_Des is not null");
            return (Criteria) this;
        }

        public Criteria andMDesEqualTo(String value) {
            addCriterion("M_Des =", value, "mDes");
            return (Criteria) this;
        }

        public Criteria andMDesNotEqualTo(String value) {
            addCriterion("M_Des <>", value, "mDes");
            return (Criteria) this;
        }

        public Criteria andMDesGreaterThan(String value) {
            addCriterion("M_Des >", value, "mDes");
            return (Criteria) this;
        }

        public Criteria andMDesGreaterThanOrEqualTo(String value) {
            addCriterion("M_Des >=", value, "mDes");
            return (Criteria) this;
        }

        public Criteria andMDesLessThan(String value) {
            addCriterion("M_Des <", value, "mDes");
            return (Criteria) this;
        }

        public Criteria andMDesLessThanOrEqualTo(String value) {
            addCriterion("M_Des <=", value, "mDes");
            return (Criteria) this;
        }

        public Criteria andMDesLike(String value) {
            addCriterion("M_Des like", value, "mDes");
            return (Criteria) this;
        }

        public Criteria andMDesNotLike(String value) {
            addCriterion("M_Des not like", value, "mDes");
            return (Criteria) this;
        }

        public Criteria andMDesIn(List<String> values) {
            addCriterion("M_Des in", values, "mDes");
            return (Criteria) this;
        }

        public Criteria andMDesNotIn(List<String> values) {
            addCriterion("M_Des not in", values, "mDes");
            return (Criteria) this;
        }

        public Criteria andMDesBetween(String value1, String value2) {
            addCriterion("M_Des between", value1, value2, "mDes");
            return (Criteria) this;
        }

        public Criteria andMDesNotBetween(String value1, String value2) {
            addCriterion("M_Des not between", value1, value2, "mDes");
            return (Criteria) this;
        }

        public Criteria andCreatepersonIsNull() {
            addCriterion("CreatePerson is null");
            return (Criteria) this;
        }

        public Criteria andCreatepersonIsNotNull() {
            addCriterion("CreatePerson is not null");
            return (Criteria) this;
        }

        public Criteria andCreatepersonEqualTo(String value) {
            addCriterion("CreatePerson =", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonNotEqualTo(String value) {
            addCriterion("CreatePerson <>", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonGreaterThan(String value) {
            addCriterion("CreatePerson >", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonGreaterThanOrEqualTo(String value) {
            addCriterion("CreatePerson >=", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonLessThan(String value) {
            addCriterion("CreatePerson <", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonLessThanOrEqualTo(String value) {
            addCriterion("CreatePerson <=", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonLike(String value) {
            addCriterion("CreatePerson like", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonNotLike(String value) {
            addCriterion("CreatePerson not like", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonIn(List<String> values) {
            addCriterion("CreatePerson in", values, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonNotIn(List<String> values) {
            addCriterion("CreatePerson not in", values, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonBetween(String value1, String value2) {
            addCriterion("CreatePerson between", value1, value2, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonNotBetween(String value1, String value2) {
            addCriterion("CreatePerson not between", value1, value2, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("CreateTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("CreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("CreateTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("CreateTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("CreateTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CreateTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("CreateTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CreateTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("CreateTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("CreateTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("CreateTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CreateTime not between", value1, value2, "createtime");
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

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("UpdateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("UpdateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("UpdateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UpdateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("UpdateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("UpdateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("UpdateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("UpdateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("UpdateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("UpdateTime not between", value1, value2, "updatetime");
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
            addCriterion("SortCode =", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotEqualTo(String value) {
            addCriterion("SortCode <>", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeGreaterThan(String value) {
            addCriterion("SortCode >", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeGreaterThanOrEqualTo(String value) {
            addCriterion("SortCode >=", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLessThan(String value) {
            addCriterion("SortCode <", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLessThanOrEqualTo(String value) {
            addCriterion("SortCode <=", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLike(String value) {
            addCriterion("SortCode like", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotLike(String value) {
            addCriterion("SortCode not like", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeIn(List<String> values) {
            addCriterion("SortCode in", values, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotIn(List<String> values) {
            addCriterion("SortCode not in", values, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeBetween(String value1, String value2) {
            addCriterion("SortCode between", value1, value2, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotBetween(String value1, String value2) {
            addCriterion("SortCode not between", value1, value2, "sortcode");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`Status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`Status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`Status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`Status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`Status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`Status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`Status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`Status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`Status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`Status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`Status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`Status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`Status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`Status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNull() {
            addCriterion("IsDel is null");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNotNull() {
            addCriterion("IsDel is not null");
            return (Criteria) this;
        }

        public Criteria andIsdelEqualTo(Integer value) {
            addCriterion("IsDel =", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotEqualTo(Integer value) {
            addCriterion("IsDel <>", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThan(Integer value) {
            addCriterion("IsDel >", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsDel >=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThan(Integer value) {
            addCriterion("IsDel <", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThanOrEqualTo(Integer value) {
            addCriterion("IsDel <=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelIn(List<Integer> values) {
            addCriterion("IsDel in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotIn(List<Integer> values) {
            addCriterion("IsDel not in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelBetween(Integer value1, Integer value2) {
            addCriterion("IsDel between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotBetween(Integer value1, Integer value2) {
            addCriterion("IsDel not between", value1, value2, "isdel");
            return (Criteria) this;
        }
    }

    /**
     * ZZ_Module
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
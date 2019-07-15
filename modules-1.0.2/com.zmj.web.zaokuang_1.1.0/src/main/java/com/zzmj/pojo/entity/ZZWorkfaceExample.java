package com.zzmj.pojo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZZWorkfaceExample {
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

    public ZZWorkfaceExample() {
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

        public Criteria andOrgIdIsNull() {
            addCriterion("Org_Id is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("Org_Id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("Org_Id =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("Org_Id <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("Org_Id >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("Org_Id >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("Org_Id <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("Org_Id <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("Org_Id like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("Org_Id not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("Org_Id in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("Org_Id not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("Org_Id between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("Org_Id not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameIsNull() {
            addCriterion("Workface_Name is null");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameIsNotNull() {
            addCriterion("Workface_Name is not null");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameEqualTo(String value) {
            addCriterion("Workface_Name =", value, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameNotEqualTo(String value) {
            addCriterion("Workface_Name <>", value, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameGreaterThan(String value) {
            addCriterion("Workface_Name >", value, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("Workface_Name >=", value, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameLessThan(String value) {
            addCriterion("Workface_Name <", value, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameLessThanOrEqualTo(String value) {
            addCriterion("Workface_Name <=", value, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameLike(String value) {
            addCriterion("Workface_Name like", value, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameNotLike(String value) {
            addCriterion("Workface_Name not like", value, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameIn(List<String> values) {
            addCriterion("Workface_Name in", values, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameNotIn(List<String> values) {
            addCriterion("Workface_Name not in", values, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameBetween(String value1, String value2) {
            addCriterion("Workface_Name between", value1, value2, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceNameNotBetween(String value1, String value2) {
            addCriterion("Workface_Name not between", value1, value2, "workfaceName");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasIsNull() {
            addCriterion("Workface_Alias is null");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasIsNotNull() {
            addCriterion("Workface_Alias is not null");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasEqualTo(String value) {
            addCriterion("Workface_Alias =", value, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasNotEqualTo(String value) {
            addCriterion("Workface_Alias <>", value, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasGreaterThan(String value) {
            addCriterion("Workface_Alias >", value, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasGreaterThanOrEqualTo(String value) {
            addCriterion("Workface_Alias >=", value, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasLessThan(String value) {
            addCriterion("Workface_Alias <", value, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasLessThanOrEqualTo(String value) {
            addCriterion("Workface_Alias <=", value, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasLike(String value) {
            addCriterion("Workface_Alias like", value, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasNotLike(String value) {
            addCriterion("Workface_Alias not like", value, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasIn(List<String> values) {
            addCriterion("Workface_Alias in", values, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasNotIn(List<String> values) {
            addCriterion("Workface_Alias not in", values, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasBetween(String value1, String value2) {
            addCriterion("Workface_Alias between", value1, value2, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceAliasNotBetween(String value1, String value2) {
            addCriterion("Workface_Alias not between", value1, value2, "workfaceAlias");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeIsNull() {
            addCriterion("Workface_Type is null");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeIsNotNull() {
            addCriterion("Workface_Type is not null");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeEqualTo(Integer value) {
            addCriterion("Workface_Type =", value, "workfaceType");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeNotEqualTo(Integer value) {
            addCriterion("Workface_Type <>", value, "workfaceType");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeGreaterThan(Integer value) {
            addCriterion("Workface_Type >", value, "workfaceType");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Workface_Type >=", value, "workfaceType");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeLessThan(Integer value) {
            addCriterion("Workface_Type <", value, "workfaceType");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("Workface_Type <=", value, "workfaceType");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeIn(List<Integer> values) {
            addCriterion("Workface_Type in", values, "workfaceType");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeNotIn(List<Integer> values) {
            addCriterion("Workface_Type not in", values, "workfaceType");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeBetween(Integer value1, Integer value2) {
            addCriterion("Workface_Type between", value1, value2, "workfaceType");
            return (Criteria) this;
        }

        public Criteria andWorkfaceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("Workface_Type not between", value1, value2, "workfaceType");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateIsNull() {
            addCriterion("Workface_State is null");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateIsNotNull() {
            addCriterion("Workface_State is not null");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateEqualTo(Integer value) {
            addCriterion("Workface_State =", value, "workfaceState");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateNotEqualTo(Integer value) {
            addCriterion("Workface_State <>", value, "workfaceState");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateGreaterThan(Integer value) {
            addCriterion("Workface_State >", value, "workfaceState");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("Workface_State >=", value, "workfaceState");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateLessThan(Integer value) {
            addCriterion("Workface_State <", value, "workfaceState");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateLessThanOrEqualTo(Integer value) {
            addCriterion("Workface_State <=", value, "workfaceState");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateIn(List<Integer> values) {
            addCriterion("Workface_State in", values, "workfaceState");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateNotIn(List<Integer> values) {
            addCriterion("Workface_State not in", values, "workfaceState");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateBetween(Integer value1, Integer value2) {
            addCriterion("Workface_State between", value1, value2, "workfaceState");
            return (Criteria) this;
        }

        public Criteria andWorkfaceStateNotBetween(Integer value1, Integer value2) {
            addCriterion("Workface_State not between", value1, value2, "workfaceState");
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
     * ZZ_Workface
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
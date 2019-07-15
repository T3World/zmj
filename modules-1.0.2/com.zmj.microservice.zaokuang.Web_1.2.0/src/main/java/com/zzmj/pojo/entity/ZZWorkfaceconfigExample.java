package com.zzmj.pojo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZZWorkfaceconfigExample {
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

    public ZZWorkfaceconfigExample() {
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

        public Criteria andBeltTypeIsNull() {
            addCriterion("Belt_Type is null");
            return (Criteria) this;
        }

        public Criteria andBeltTypeIsNotNull() {
            addCriterion("Belt_Type is not null");
            return (Criteria) this;
        }

        public Criteria andBeltTypeEqualTo(String value) {
            addCriterion("Belt_Type =", value, "beltType");
            return (Criteria) this;
        }

        public Criteria andBeltTypeNotEqualTo(String value) {
            addCriterion("Belt_Type <>", value, "beltType");
            return (Criteria) this;
        }

        public Criteria andBeltTypeGreaterThan(String value) {
            addCriterion("Belt_Type >", value, "beltType");
            return (Criteria) this;
        }

        public Criteria andBeltTypeGreaterThanOrEqualTo(String value) {
            addCriterion("Belt_Type >=", value, "beltType");
            return (Criteria) this;
        }

        public Criteria andBeltTypeLessThan(String value) {
            addCriterion("Belt_Type <", value, "beltType");
            return (Criteria) this;
        }

        public Criteria andBeltTypeLessThanOrEqualTo(String value) {
            addCriterion("Belt_Type <=", value, "beltType");
            return (Criteria) this;
        }

        public Criteria andBeltTypeLike(String value) {
            addCriterion("Belt_Type like", value, "beltType");
            return (Criteria) this;
        }

        public Criteria andBeltTypeNotLike(String value) {
            addCriterion("Belt_Type not like", value, "beltType");
            return (Criteria) this;
        }

        public Criteria andBeltTypeIn(List<String> values) {
            addCriterion("Belt_Type in", values, "beltType");
            return (Criteria) this;
        }

        public Criteria andBeltTypeNotIn(List<String> values) {
            addCriterion("Belt_Type not in", values, "beltType");
            return (Criteria) this;
        }

        public Criteria andBeltTypeBetween(String value1, String value2) {
            addCriterion("Belt_Type between", value1, value2, "beltType");
            return (Criteria) this;
        }

        public Criteria andBeltTypeNotBetween(String value1, String value2) {
            addCriterion("Belt_Type not between", value1, value2, "beltType");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureIsNull() {
            addCriterion("Font_MinPressure is null");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureIsNotNull() {
            addCriterion("Font_MinPressure is not null");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureEqualTo(String value) {
            addCriterion("Font_MinPressure =", value, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureNotEqualTo(String value) {
            addCriterion("Font_MinPressure <>", value, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureGreaterThan(String value) {
            addCriterion("Font_MinPressure >", value, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureGreaterThanOrEqualTo(String value) {
            addCriterion("Font_MinPressure >=", value, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureLessThan(String value) {
            addCriterion("Font_MinPressure <", value, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureLessThanOrEqualTo(String value) {
            addCriterion("Font_MinPressure <=", value, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureLike(String value) {
            addCriterion("Font_MinPressure like", value, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureNotLike(String value) {
            addCriterion("Font_MinPressure not like", value, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureIn(List<String> values) {
            addCriterion("Font_MinPressure in", values, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureNotIn(List<String> values) {
            addCriterion("Font_MinPressure not in", values, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureBetween(String value1, String value2) {
            addCriterion("Font_MinPressure between", value1, value2, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMinpressureNotBetween(String value1, String value2) {
            addCriterion("Font_MinPressure not between", value1, value2, "fontMinpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureIsNull() {
            addCriterion("Font_MaxPressure is null");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureIsNotNull() {
            addCriterion("Font_MaxPressure is not null");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureEqualTo(String value) {
            addCriterion("Font_MaxPressure =", value, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureNotEqualTo(String value) {
            addCriterion("Font_MaxPressure <>", value, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureGreaterThan(String value) {
            addCriterion("Font_MaxPressure >", value, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureGreaterThanOrEqualTo(String value) {
            addCriterion("Font_MaxPressure >=", value, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureLessThan(String value) {
            addCriterion("Font_MaxPressure <", value, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureLessThanOrEqualTo(String value) {
            addCriterion("Font_MaxPressure <=", value, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureLike(String value) {
            addCriterion("Font_MaxPressure like", value, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureNotLike(String value) {
            addCriterion("Font_MaxPressure not like", value, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureIn(List<String> values) {
            addCriterion("Font_MaxPressure in", values, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureNotIn(List<String> values) {
            addCriterion("Font_MaxPressure not in", values, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureBetween(String value1, String value2) {
            addCriterion("Font_MaxPressure between", value1, value2, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andFontMaxpressureNotBetween(String value1, String value2) {
            addCriterion("Font_MaxPressure not between", value1, value2, "fontMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureIsNull() {
            addCriterion("Back_MinPressure is null");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureIsNotNull() {
            addCriterion("Back_MinPressure is not null");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureEqualTo(String value) {
            addCriterion("Back_MinPressure =", value, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureNotEqualTo(String value) {
            addCriterion("Back_MinPressure <>", value, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureGreaterThan(String value) {
            addCriterion("Back_MinPressure >", value, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureGreaterThanOrEqualTo(String value) {
            addCriterion("Back_MinPressure >=", value, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureLessThan(String value) {
            addCriterion("Back_MinPressure <", value, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureLessThanOrEqualTo(String value) {
            addCriterion("Back_MinPressure <=", value, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureLike(String value) {
            addCriterion("Back_MinPressure like", value, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureNotLike(String value) {
            addCriterion("Back_MinPressure not like", value, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureIn(List<String> values) {
            addCriterion("Back_MinPressure in", values, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureNotIn(List<String> values) {
            addCriterion("Back_MinPressure not in", values, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureBetween(String value1, String value2) {
            addCriterion("Back_MinPressure between", value1, value2, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMinpressureNotBetween(String value1, String value2) {
            addCriterion("Back_MinPressure not between", value1, value2, "backMinpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureIsNull() {
            addCriterion("Back_MaxPressure is null");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureIsNotNull() {
            addCriterion("Back_MaxPressure is not null");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureEqualTo(String value) {
            addCriterion("Back_MaxPressure =", value, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureNotEqualTo(String value) {
            addCriterion("Back_MaxPressure <>", value, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureGreaterThan(String value) {
            addCriterion("Back_MaxPressure >", value, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureGreaterThanOrEqualTo(String value) {
            addCriterion("Back_MaxPressure >=", value, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureLessThan(String value) {
            addCriterion("Back_MaxPressure <", value, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureLessThanOrEqualTo(String value) {
            addCriterion("Back_MaxPressure <=", value, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureLike(String value) {
            addCriterion("Back_MaxPressure like", value, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureNotLike(String value) {
            addCriterion("Back_MaxPressure not like", value, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureIn(List<String> values) {
            addCriterion("Back_MaxPressure in", values, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureNotIn(List<String> values) {
            addCriterion("Back_MaxPressure not in", values, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureBetween(String value1, String value2) {
            addCriterion("Back_MaxPressure between", value1, value2, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andBackMaxpressureNotBetween(String value1, String value2) {
            addCriterion("Back_MaxPressure not between", value1, value2, "backMaxpressure");
            return (Criteria) this;
        }

        public Criteria andSupportDirIsNull() {
            addCriterion("Support_Dir is null");
            return (Criteria) this;
        }

        public Criteria andSupportDirIsNotNull() {
            addCriterion("Support_Dir is not null");
            return (Criteria) this;
        }

        public Criteria andSupportDirEqualTo(Integer value) {
            addCriterion("Support_Dir =", value, "supportDir");
            return (Criteria) this;
        }

        public Criteria andSupportDirNotEqualTo(Integer value) {
            addCriterion("Support_Dir <>", value, "supportDir");
            return (Criteria) this;
        }

        public Criteria andSupportDirGreaterThan(Integer value) {
            addCriterion("Support_Dir >", value, "supportDir");
            return (Criteria) this;
        }

        public Criteria andSupportDirGreaterThanOrEqualTo(Integer value) {
            addCriterion("Support_Dir >=", value, "supportDir");
            return (Criteria) this;
        }

        public Criteria andSupportDirLessThan(Integer value) {
            addCriterion("Support_Dir <", value, "supportDir");
            return (Criteria) this;
        }

        public Criteria andSupportDirLessThanOrEqualTo(Integer value) {
            addCriterion("Support_Dir <=", value, "supportDir");
            return (Criteria) this;
        }

        public Criteria andSupportDirIn(List<Integer> values) {
            addCriterion("Support_Dir in", values, "supportDir");
            return (Criteria) this;
        }

        public Criteria andSupportDirNotIn(List<Integer> values) {
            addCriterion("Support_Dir not in", values, "supportDir");
            return (Criteria) this;
        }

        public Criteria andSupportDirBetween(Integer value1, Integer value2) {
            addCriterion("Support_Dir between", value1, value2, "supportDir");
            return (Criteria) this;
        }

        public Criteria andSupportDirNotBetween(Integer value1, Integer value2) {
            addCriterion("Support_Dir not between", value1, value2, "supportDir");
            return (Criteria) this;
        }

        public Criteria andSupportCountIsNull() {
            addCriterion("Support_Count is null");
            return (Criteria) this;
        }

        public Criteria andSupportCountIsNotNull() {
            addCriterion("Support_Count is not null");
            return (Criteria) this;
        }

        public Criteria andSupportCountEqualTo(Integer value) {
            addCriterion("Support_Count =", value, "supportCount");
            return (Criteria) this;
        }

        public Criteria andSupportCountNotEqualTo(Integer value) {
            addCriterion("Support_Count <>", value, "supportCount");
            return (Criteria) this;
        }

        public Criteria andSupportCountGreaterThan(Integer value) {
            addCriterion("Support_Count >", value, "supportCount");
            return (Criteria) this;
        }

        public Criteria andSupportCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("Support_Count >=", value, "supportCount");
            return (Criteria) this;
        }

        public Criteria andSupportCountLessThan(Integer value) {
            addCriterion("Support_Count <", value, "supportCount");
            return (Criteria) this;
        }

        public Criteria andSupportCountLessThanOrEqualTo(Integer value) {
            addCriterion("Support_Count <=", value, "supportCount");
            return (Criteria) this;
        }

        public Criteria andSupportCountIn(List<Integer> values) {
            addCriterion("Support_Count in", values, "supportCount");
            return (Criteria) this;
        }

        public Criteria andSupportCountNotIn(List<Integer> values) {
            addCriterion("Support_Count not in", values, "supportCount");
            return (Criteria) this;
        }

        public Criteria andSupportCountBetween(Integer value1, Integer value2) {
            addCriterion("Support_Count between", value1, value2, "supportCount");
            return (Criteria) this;
        }

        public Criteria andSupportCountNotBetween(Integer value1, Integer value2) {
            addCriterion("Support_Count not between", value1, value2, "supportCount");
            return (Criteria) this;
        }

        public Criteria andConveyorDirIsNull() {
            addCriterion("Conveyor_Dir is null");
            return (Criteria) this;
        }

        public Criteria andConveyorDirIsNotNull() {
            addCriterion("Conveyor_Dir is not null");
            return (Criteria) this;
        }

        public Criteria andConveyorDirEqualTo(Integer value) {
            addCriterion("Conveyor_Dir =", value, "conveyorDir");
            return (Criteria) this;
        }

        public Criteria andConveyorDirNotEqualTo(Integer value) {
            addCriterion("Conveyor_Dir <>", value, "conveyorDir");
            return (Criteria) this;
        }

        public Criteria andConveyorDirGreaterThan(Integer value) {
            addCriterion("Conveyor_Dir >", value, "conveyorDir");
            return (Criteria) this;
        }

        public Criteria andConveyorDirGreaterThanOrEqualTo(Integer value) {
            addCriterion("Conveyor_Dir >=", value, "conveyorDir");
            return (Criteria) this;
        }

        public Criteria andConveyorDirLessThan(Integer value) {
            addCriterion("Conveyor_Dir <", value, "conveyorDir");
            return (Criteria) this;
        }

        public Criteria andConveyorDirLessThanOrEqualTo(Integer value) {
            addCriterion("Conveyor_Dir <=", value, "conveyorDir");
            return (Criteria) this;
        }

        public Criteria andConveyorDirIn(List<Integer> values) {
            addCriterion("Conveyor_Dir in", values, "conveyorDir");
            return (Criteria) this;
        }

        public Criteria andConveyorDirNotIn(List<Integer> values) {
            addCriterion("Conveyor_Dir not in", values, "conveyorDir");
            return (Criteria) this;
        }

        public Criteria andConveyorDirBetween(Integer value1, Integer value2) {
            addCriterion("Conveyor_Dir between", value1, value2, "conveyorDir");
            return (Criteria) this;
        }

        public Criteria andConveyorDirNotBetween(Integer value1, Integer value2) {
            addCriterion("Conveyor_Dir not between", value1, value2, "conveyorDir");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeIsNull() {
            addCriterion("Pressure_ChartType is null");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeIsNotNull() {
            addCriterion("Pressure_ChartType is not null");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeEqualTo(String value) {
            addCriterion("Pressure_ChartType =", value, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeNotEqualTo(String value) {
            addCriterion("Pressure_ChartType <>", value, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeGreaterThan(String value) {
            addCriterion("Pressure_ChartType >", value, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeGreaterThanOrEqualTo(String value) {
            addCriterion("Pressure_ChartType >=", value, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeLessThan(String value) {
            addCriterion("Pressure_ChartType <", value, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeLessThanOrEqualTo(String value) {
            addCriterion("Pressure_ChartType <=", value, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeLike(String value) {
            addCriterion("Pressure_ChartType like", value, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeNotLike(String value) {
            addCriterion("Pressure_ChartType not like", value, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeIn(List<String> values) {
            addCriterion("Pressure_ChartType in", values, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeNotIn(List<String> values) {
            addCriterion("Pressure_ChartType not in", values, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeBetween(String value1, String value2) {
            addCriterion("Pressure_ChartType between", value1, value2, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andPressureCharttypeNotBetween(String value1, String value2) {
            addCriterion("Pressure_ChartType not between", value1, value2, "pressureCharttype");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeIsNull() {
            addCriterion("ShearerPos_CacheTime is null");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeIsNotNull() {
            addCriterion("ShearerPos_CacheTime is not null");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeEqualTo(Date value) {
            addCriterion("ShearerPos_CacheTime =", value, "shearerposCachetime");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeNotEqualTo(Date value) {
            addCriterion("ShearerPos_CacheTime <>", value, "shearerposCachetime");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeGreaterThan(Date value) {
            addCriterion("ShearerPos_CacheTime >", value, "shearerposCachetime");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ShearerPos_CacheTime >=", value, "shearerposCachetime");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeLessThan(Date value) {
            addCriterion("ShearerPos_CacheTime <", value, "shearerposCachetime");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeLessThanOrEqualTo(Date value) {
            addCriterion("ShearerPos_CacheTime <=", value, "shearerposCachetime");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeIn(List<Date> values) {
            addCriterion("ShearerPos_CacheTime in", values, "shearerposCachetime");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeNotIn(List<Date> values) {
            addCriterion("ShearerPos_CacheTime not in", values, "shearerposCachetime");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeBetween(Date value1, Date value2) {
            addCriterion("ShearerPos_CacheTime between", value1, value2, "shearerposCachetime");
            return (Criteria) this;
        }

        public Criteria andShearerposCachetimeNotBetween(Date value1, Date value2) {
            addCriterion("ShearerPos_CacheTime not between", value1, value2, "shearerposCachetime");
            return (Criteria) this;
        }
    }

    /**
     * ZZ_WorkfaceConfig
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
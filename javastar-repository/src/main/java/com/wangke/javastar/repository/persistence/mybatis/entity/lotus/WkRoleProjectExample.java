package com.wangke.javastar.repository.persistence.mybatis.entity.lotus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WkRoleProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageIndex;

    protected Integer pageCount;

    public WkRoleProjectExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

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

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex=pageIndex;
    }

    public Integer getPageIndex() {
        return this.pageIndex;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount=pageCount;
    }

    public Integer getPageCount() {
        return this.pageCount;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        @JsonIgnore
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
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProjectKeyIsNull() {
            addCriterion("`project_key` is null");
            return (Criteria) this;
        }

        public Criteria andProjectKeyIsNotNull() {
            addCriterion("`project_key` is not null");
            return (Criteria) this;
        }

        public Criteria andProjectKeyEqualTo(String value) {
            addCriterion("`project_key` =", value, "projectKey");
            return (Criteria) this;
        }

        public Criteria andProjectKeyNotEqualTo(String value) {
            addCriterion("`project_key` <>", value, "projectKey");
            return (Criteria) this;
        }

        public Criteria andProjectKeyGreaterThan(String value) {
            addCriterion("`project_key` >", value, "projectKey");
            return (Criteria) this;
        }

        public Criteria andProjectKeyGreaterThanOrEqualTo(String value) {
            addCriterion("`project_key` >=", value, "projectKey");
            return (Criteria) this;
        }

        public Criteria andProjectKeyLessThan(String value) {
            addCriterion("`project_key` <", value, "projectKey");
            return (Criteria) this;
        }

        public Criteria andProjectKeyLessThanOrEqualTo(String value) {
            addCriterion("`project_key` <=", value, "projectKey");
            return (Criteria) this;
        }

        public Criteria andProjectKeyLike(String value) {
            addCriterion("`project_key` like", value, "projectKey");
            return (Criteria) this;
        }

        public Criteria andProjectKeyNotLike(String value) {
            addCriterion("`project_key` not like", value, "projectKey");
            return (Criteria) this;
        }

        public Criteria andProjectKeyIn(List<String> values) {
            addCriterion("`project_key` in", values, "projectKey");
            return (Criteria) this;
        }

        public Criteria andProjectKeyNotIn(List<String> values) {
            addCriterion("`project_key` not in", values, "projectKey");
            return (Criteria) this;
        }

        public Criteria andProjectKeyBetween(String value1, String value2) {
            addCriterion("`project_key` between", value1, value2, "projectKey");
            return (Criteria) this;
        }

        public Criteria andProjectKeyNotBetween(String value1, String value2) {
            addCriterion("`project_key` not between", value1, value2, "projectKey");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampIsNull() {
            addCriterion("`create_timestamp` is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampIsNotNull() {
            addCriterion("`create_timestamp` is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampEqualTo(Date value) {
            addCriterion("`create_timestamp` =", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampNotEqualTo(Date value) {
            addCriterion("`create_timestamp` <>", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampGreaterThan(Date value) {
            addCriterion("`create_timestamp` >", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("`create_timestamp` >=", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampLessThan(Date value) {
            addCriterion("`create_timestamp` <", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampLessThanOrEqualTo(Date value) {
            addCriterion("`create_timestamp` <=", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampIn(List<Date> values) {
            addCriterion("`create_timestamp` in", values, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampNotIn(List<Date> values) {
            addCriterion("`create_timestamp` not in", values, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampBetween(Date value1, Date value2) {
            addCriterion("`create_timestamp` between", value1, value2, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampNotBetween(Date value1, Date value2) {
            addCriterion("`create_timestamp` not between", value1, value2, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andRoleKeyIsNull() {
            addCriterion("`role_key` is null");
            return (Criteria) this;
        }

        public Criteria andRoleKeyIsNotNull() {
            addCriterion("`role_key` is not null");
            return (Criteria) this;
        }

        public Criteria andRoleKeyEqualTo(String value) {
            addCriterion("`role_key` =", value, "roleKey");
            return (Criteria) this;
        }

        public Criteria andRoleKeyNotEqualTo(String value) {
            addCriterion("`role_key` <>", value, "roleKey");
            return (Criteria) this;
        }

        public Criteria andRoleKeyGreaterThan(String value) {
            addCriterion("`role_key` >", value, "roleKey");
            return (Criteria) this;
        }

        public Criteria andRoleKeyGreaterThanOrEqualTo(String value) {
            addCriterion("`role_key` >=", value, "roleKey");
            return (Criteria) this;
        }

        public Criteria andRoleKeyLessThan(String value) {
            addCriterion("`role_key` <", value, "roleKey");
            return (Criteria) this;
        }

        public Criteria andRoleKeyLessThanOrEqualTo(String value) {
            addCriterion("`role_key` <=", value, "roleKey");
            return (Criteria) this;
        }

        public Criteria andRoleKeyLike(String value) {
            addCriterion("`role_key` like", value, "roleKey");
            return (Criteria) this;
        }

        public Criteria andRoleKeyNotLike(String value) {
            addCriterion("`role_key` not like", value, "roleKey");
            return (Criteria) this;
        }

        public Criteria andRoleKeyIn(List<String> values) {
            addCriterion("`role_key` in", values, "roleKey");
            return (Criteria) this;
        }

        public Criteria andRoleKeyNotIn(List<String> values) {
            addCriterion("`role_key` not in", values, "roleKey");
            return (Criteria) this;
        }

        public Criteria andRoleKeyBetween(String value1, String value2) {
            addCriterion("`role_key` between", value1, value2, "roleKey");
            return (Criteria) this;
        }

        public Criteria andRoleKeyNotBetween(String value1, String value2) {
            addCriterion("`role_key` not between", value1, value2, "roleKey");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampIsNull() {
            addCriterion("`last_update_timestamp` is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampIsNotNull() {
            addCriterion("`last_update_timestamp` is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampEqualTo(Date value) {
            addCriterion("`last_update_timestamp` =", value, "lastUpdateTimestamp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampNotEqualTo(Date value) {
            addCriterion("`last_update_timestamp` <>", value, "lastUpdateTimestamp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampGreaterThan(Date value) {
            addCriterion("`last_update_timestamp` >", value, "lastUpdateTimestamp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("`last_update_timestamp` >=", value, "lastUpdateTimestamp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampLessThan(Date value) {
            addCriterion("`last_update_timestamp` <", value, "lastUpdateTimestamp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampLessThanOrEqualTo(Date value) {
            addCriterion("`last_update_timestamp` <=", value, "lastUpdateTimestamp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampIn(List<Date> values) {
            addCriterion("`last_update_timestamp` in", values, "lastUpdateTimestamp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampNotIn(List<Date> values) {
            addCriterion("`last_update_timestamp` not in", values, "lastUpdateTimestamp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampBetween(Date value1, Date value2) {
            addCriterion("`last_update_timestamp` between", value1, value2, "lastUpdateTimestamp");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimestampNotBetween(Date value1, Date value2) {
            addCriterion("`last_update_timestamp` not between", value1, value2, "lastUpdateTimestamp");
            return (Criteria) this;
        }

        void setCriteria(List<Criterion> criteria) {
            this.criteria=criteria;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
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

        public Criterion() {
            super();
        }
    }
}
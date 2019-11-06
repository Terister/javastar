package com.wangke.javastar.repository.persistence.mybatis.entity.lotus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WkProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageIndex;

    protected Integer pageCount;

    public WkProjectExample() {
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

        public Criteria andProjectIdIsNull() {
            addCriterion("`project_id` is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("`project_id` is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("`project_id` =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("`project_id` <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("`project_id` >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`project_id` >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("`project_id` <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("`project_id` <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("`project_id` in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("`project_id` not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("`project_id` between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`project_id` not between", value1, value2, "projectId");
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

        public Criteria andProjectNameIsNull() {
            addCriterion("`project_name` is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("`project_name` is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("`project_name` =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("`project_name` <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("`project_name` >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("`project_name` >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("`project_name` <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("`project_name` <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("`project_name` like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("`project_name` not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("`project_name` in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("`project_name` not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("`project_name` between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("`project_name` not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectPathIsNull() {
            addCriterion("`project_path` is null");
            return (Criteria) this;
        }

        public Criteria andProjectPathIsNotNull() {
            addCriterion("`project_path` is not null");
            return (Criteria) this;
        }

        public Criteria andProjectPathEqualTo(String value) {
            addCriterion("`project_path` =", value, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectPathNotEqualTo(String value) {
            addCriterion("`project_path` <>", value, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectPathGreaterThan(String value) {
            addCriterion("`project_path` >", value, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectPathGreaterThanOrEqualTo(String value) {
            addCriterion("`project_path` >=", value, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectPathLessThan(String value) {
            addCriterion("`project_path` <", value, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectPathLessThanOrEqualTo(String value) {
            addCriterion("`project_path` <=", value, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectPathLike(String value) {
            addCriterion("`project_path` like", value, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectPathNotLike(String value) {
            addCriterion("`project_path` not like", value, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectPathIn(List<String> values) {
            addCriterion("`project_path` in", values, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectPathNotIn(List<String> values) {
            addCriterion("`project_path` not in", values, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectPathBetween(String value1, String value2) {
            addCriterion("`project_path` between", value1, value2, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectPathNotBetween(String value1, String value2) {
            addCriterion("`project_path` not between", value1, value2, "projectPath");
            return (Criteria) this;
        }

        public Criteria andProjectDescIsNull() {
            addCriterion("`project_desc` is null");
            return (Criteria) this;
        }

        public Criteria andProjectDescIsNotNull() {
            addCriterion("`project_desc` is not null");
            return (Criteria) this;
        }

        public Criteria andProjectDescEqualTo(String value) {
            addCriterion("`project_desc` =", value, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectDescNotEqualTo(String value) {
            addCriterion("`project_desc` <>", value, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectDescGreaterThan(String value) {
            addCriterion("`project_desc` >", value, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectDescGreaterThanOrEqualTo(String value) {
            addCriterion("`project_desc` >=", value, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectDescLessThan(String value) {
            addCriterion("`project_desc` <", value, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectDescLessThanOrEqualTo(String value) {
            addCriterion("`project_desc` <=", value, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectDescLike(String value) {
            addCriterion("`project_desc` like", value, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectDescNotLike(String value) {
            addCriterion("`project_desc` not like", value, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectDescIn(List<String> values) {
            addCriterion("`project_desc` in", values, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectDescNotIn(List<String> values) {
            addCriterion("`project_desc` not in", values, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectDescBetween(String value1, String value2) {
            addCriterion("`project_desc` between", value1, value2, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectDescNotBetween(String value1, String value2) {
            addCriterion("`project_desc` not between", value1, value2, "projectDesc");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyIsNull() {
            addCriterion("`project_parent_key` is null");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyIsNotNull() {
            addCriterion("`project_parent_key` is not null");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyEqualTo(Integer value) {
            addCriterion("`project_parent_key` =", value, "projectParentKey");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyNotEqualTo(Integer value) {
            addCriterion("`project_parent_key` <>", value, "projectParentKey");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyGreaterThan(Integer value) {
            addCriterion("`project_parent_key` >", value, "projectParentKey");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyGreaterThanOrEqualTo(Integer value) {
            addCriterion("`project_parent_key` >=", value, "projectParentKey");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyLessThan(Integer value) {
            addCriterion("`project_parent_key` <", value, "projectParentKey");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyLessThanOrEqualTo(Integer value) {
            addCriterion("`project_parent_key` <=", value, "projectParentKey");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyIn(List<Integer> values) {
            addCriterion("`project_parent_key` in", values, "projectParentKey");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyNotIn(List<Integer> values) {
            addCriterion("`project_parent_key` not in", values, "projectParentKey");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyBetween(Integer value1, Integer value2) {
            addCriterion("`project_parent_key` between", value1, value2, "projectParentKey");
            return (Criteria) this;
        }

        public Criteria andProjectParentKeyNotBetween(Integer value1, Integer value2) {
            addCriterion("`project_parent_key` not between", value1, value2, "projectParentKey");
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
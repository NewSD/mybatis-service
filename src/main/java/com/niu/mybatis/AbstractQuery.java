package com.niu.mybatis;

import com.github.pagehelper.PageHelper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.lang.reflect.ParameterizedType;

/**
 * Created by ami on 2018/11/20.
 */
public abstract class AbstractQuery<T> {
    private Integer pageNo;
    private Integer pageSize;
    private Class<T> modelClass;

    public AbstractQuery() {
        this((Class)null);
    }

    public AbstractQuery(Class<T> modelClass) {
        if(modelClass == null) {
            ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
            this.modelClass = (Class)pt.getActualTypeArguments()[0];
        } else {
            this.modelClass = modelClass;
        }

    }

    public Example toExample() {
        Example example = new Example(this.modelClass);
        Criteria condition = example.createCriteria();
        this.addCondition(condition);
        if(null != this.pageNo && null != this.pageSize) {
            PageHelper.startPage(this.pageNo.intValue() + 1, this.pageSize.intValue());
        }

        return example;
    }

    protected abstract void addCondition(Criteria var1);

    public void setPage(int pageNo, int pageSize) {
        this.pageNo = Integer.valueOf(pageNo);
        this.pageSize = Integer.valueOf(pageSize);
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Class<T> getModelClass() {
        return this.modelClass;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setModelClass(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof AbstractQuery)) {
            return false;
        } else {
            AbstractQuery<?> other = (AbstractQuery)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$pageNo = this.getPageNo();
                    Object other$pageNo = other.getPageNo();
                    if(this$pageNo == null) {
                        if(other$pageNo == null) {
                            break label47;
                        }
                    } else if(this$pageNo.equals(other$pageNo)) {
                        break label47;
                    }

                    return false;
                }

                Object this$pageSize = this.getPageSize();
                Object other$pageSize = other.getPageSize();
                if(this$pageSize == null) {
                    if(other$pageSize != null) {
                        return false;
                    }
                } else if(!this$pageSize.equals(other$pageSize)) {
                    return false;
                }

                Object this$modelClass = this.getModelClass();
                Object other$modelClass = other.getModelClass();
                if(this$modelClass == null) {
                    if(other$modelClass != null) {
                        return false;
                    }
                } else if(!this$modelClass.equals(other$modelClass)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof AbstractQuery;
    }

    public int hashCode() {
        int result = 1;
        Object $pageNo = this.getPageNo();
        result = result * 59 + ($pageNo == null?43:$pageNo.hashCode());
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null?43:$pageSize.hashCode());
        Object $modelClass = this.getModelClass();
        result = result * 59 + ($modelClass == null?43:$modelClass.hashCode());
        return result;
    }

    public String toString() {
        return "AbstractQuery(pageNo=" + this.getPageNo() + ", pageSize=" + this.getPageSize() + ", modelClass=" + this.getModelClass() + ")";
    }
}

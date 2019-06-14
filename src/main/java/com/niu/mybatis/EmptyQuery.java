//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.niu.mybatis;

import tk.mybatis.mapper.entity.Example.Criteria;

public class EmptyQuery<T> extends AbstractQuery<T> {
    private Class<T> modelClass;

    protected void addCondition(Criteria condition) {
    }

    public EmptyQuery(Class<T> modelClass) {
        super(modelClass);
    }

    public Class<T> getModelClass() {
        return this.modelClass;
    }

    public void setModelClass(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof EmptyQuery)) {
            return false;
        } else {
            EmptyQuery<?> other = (EmptyQuery)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
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
        return other instanceof EmptyQuery;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $modelClass = this.getModelClass();
        result = result * 59 + ($modelClass == null?43:$modelClass.hashCode());
        return result;
    }

    public String toString() {
        return "EmptyQuery(modelClass=" + this.getModelClass() + ")";
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.niu.common.model;

public class DalContext {
    private int totalCount;

    public DalContext() {
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof DalContext)) {
            return false;
        } else {
            DalContext other = (DalContext)o;
            return !other.canEqual(this)?false:this.getTotalCount() == other.getTotalCount();
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof DalContext;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + this.getTotalCount();
        return result;
    }

    public String toString() {
        return "DalContext(totalCount=" + this.getTotalCount() + ")";
    }
}

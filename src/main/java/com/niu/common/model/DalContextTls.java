//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.niu.common.model;

public class DalContextTls {
    private static ThreadLocal<DalContext> dalContextTls = new ThreadLocal();

    public DalContextTls() {
    }

    public static DalContext getOrInitDalContext() {
        DalContext val = (DalContext)dalContextTls.get();
        if(val == null) {
            val = new DalContext();
            dalContextTls.set(val);
        }

        return val;
    }

    public static DalContext getDalContext() {
        return (DalContext)dalContextTls.get();
    }

    public static void setDalContextTls(DalContext dalContext) {
        dalContextTls.set(dalContext);
    }
}

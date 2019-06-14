//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.niu.common.utils;

import com.niu.common.model.DalContext;
import com.niu.common.model.DalContextTls;

public class PageUtils {
    public PageUtils() {
    }

    public static void cacheTotalCount(int totalCount) {
        DalContext context = DalContextTls.getOrInitDalContext();
        context.setTotalCount(totalCount);
        DalContextTls.setDalContextTls(context);
    }

    public static int getTotalCount() {
        return DalContextTls.getDalContext().getTotalCount();
    }
}

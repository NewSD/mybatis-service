package com.niu.common;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * Created by ami on 2018/11/20.
 */
public interface SumMapper<T> {

    @SelectProvider(
            type = SpecialProvider.class,
            method = "dynamicSQL"
    )
    double sumByQuery(@Param("example") Object var1, @Param("sumColumnName") String var2);
}

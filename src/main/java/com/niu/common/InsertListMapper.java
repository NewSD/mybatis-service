package com.niu.common;

import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * Created by ami on 2018/11/20.
 */
public interface InsertListMapper<T>  {
    @InsertProvider(
            type = SpecialProvider.class,
            method = "dynamicSQL"
    )
    int insertList(List<T> var1);
}

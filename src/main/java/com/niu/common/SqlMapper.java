package com.niu.common;

import tk.mybatis.mapper.common.Mapper;

/**
 * Created by ami on 2018/11/20.
 */
public interface SqlMapper<T> extends Mapper<T>, InsertListMapper<T>, SumMapper<T> {


}

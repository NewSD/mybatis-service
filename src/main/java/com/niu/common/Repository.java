package com.niu.common;

import java.util.List;

/**
 * Created by ami on 2018/11/20.
 */
public interface Repository<I,T> {

    void insert(T var1);

    void insert(List<T> var1);

    void deleteById(I var1);

    void deleteByIds(List<I> var1);

    void deleteByCondition(Object var1);

    void update(T var1);

    void updateByIds(List<I> var1, T var2);

    void updateByCondition(Object var1, T var2);

    T findById(I var1);

    List<T> findByIds(List<I> var1);

    List<T> findByCondition(Object var1);

    List<T> findAll();

    I getId(T var1);

}

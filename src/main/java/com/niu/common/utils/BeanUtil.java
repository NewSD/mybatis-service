//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.niu.common.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.function.Consumer;

public class BeanUtil {
    public BeanUtil() {
    }

    public static <S, D> D map(S source, Class<D> destinationClass) {
        Object target = null;

        try {
            target = destinationClass.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (InstantiationException var4) {
            var4.printStackTrace();
        } catch (IllegalAccessException var5) {
            var5.printStackTrace();
        }

        return (D) target;
    }

    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<D> destinationClass) {
        List<D> resList = Lists.newArrayList();
        sourceList.forEach((x) -> {
            resList.add(map(x, destinationClass));
        });
        return resList;
    }

    public static void copyPropertiesExcludeNULL(Object src, Object target) {
        if(null != src && null != target) {
            BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
        }

    }

    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet();
        PropertyDescriptor[] var4 = pds;
        int var5 = pds.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            PropertyDescriptor pd = var4[var6];
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return (String[])emptyNames.toArray(result);
    }

    public static void ifNonNull(Object o, Consumer consumer) {
        if(Objects.nonNull(o)) {
            consumer.accept(o);
        }

    }

    public static void ifNotBlank(String o, Consumer consumer) {
        if(StringUtils.isNotBlank(o)) {
            consumer.accept(o);
        }

    }

    public static void ifNotEmpty(Collection o, Consumer<Collection> consumer) {
        if(CollectionUtils.isNotEmpty(o)) {
            consumer.accept(o);
        }

    }
}

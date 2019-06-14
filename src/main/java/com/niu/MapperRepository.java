//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.niu;

import com.google.common.collect.Lists;
import com.niu.common.Repository;
import com.niu.common.SqlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import javax.persistence.Id;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MapperRepository<I, T> implements Repository<I, T> {
    private static final Logger log = LoggerFactory.getLogger(MapperRepository.class);
    private static final Integer MAX_RECORDS = Integer.valueOf(1024);
    @Autowired
    protected SqlMapper<T> mapper;
    private Class<T> entityClass;

    public MapperRepository() {
        ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
        this.entityClass = (Class)pt.getActualTypeArguments()[1];
    }

    public MapperRepository(Class<T> entityClass, SqlMapper<T> mapper) {
        this.entityClass = entityClass;
        this.mapper = mapper;
    }

    public void insert(T model) {
        this.mapper.insertSelective(model);
    }

    public void insert(List<T> models) {
        this.mapper.insertList(models);
    }

    public void deleteById(I id) {
        this.mapper.deleteByPrimaryKey(id);
    }

    public void deleteByIds(List<I> ids) {
        Example condition = this.idsCondition(ids);
        if(condition != null) {
            this.mapper.deleteByExample(condition);
        }

    }

    public void deleteByCondition(Object condition) {
        if(condition != null) {
            this.mapper.deleteByExample(condition);
        }

    }

    public void update(T model) {
        this.mapper.updateByPrimaryKeySelective(model);
    }

    public void updateByIds(List<I> ids, T model) {
        Example condition = this.idsCondition(ids);
        if(condition != null) {
            this.mapper.updateByExampleSelective(model, condition);
        }

    }

    public void updateByCondition(Object condition, T model) {
        if(condition != null) {
            this.mapper.updateByExampleSelective(model, condition);
        }

    }

    public T findById(I id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public List<T> findByIds(List<I> ids) {
        if(!CollectionUtils.isEmpty(ids) && ids.size() > MAX_RECORDS.intValue()) {
            log.warn("findByIds with huge id set: size = {}", Integer.valueOf(ids.size()));
        }

        Example condition = this.idsCondition(ids);
        return (List)(condition == null?Lists.newArrayList():this.findByCondition(condition));
    }

    public List<T> findByCondition(Object condition) {
        List<T> resultSet = this.mapper.selectByExample(condition);
        if(!CollectionUtils.isEmpty(resultSet) && resultSet.size() > MAX_RECORDS.intValue()) {
            log.warn("findByCondition returns huge result set: size = {}", Integer.valueOf(resultSet.size()));
        }

        return resultSet;
    }

    public List<T> findAll() {
        List<T> resultSet = this.mapper.selectAll();
        if(!CollectionUtils.isEmpty(resultSet) && resultSet.size() > MAX_RECORDS.intValue()) {
            log.warn("findAll returns huge result set: size = {}", Integer.valueOf(resultSet.size()));
        }

        return resultSet;
    }

    public I getId(T model) {
        Object id = Lists.newArrayList(this.entityClass.getDeclaredFields()).stream().filter((f) -> {
            return f.isAnnotationPresent(Id.class);
        }).findFirst().map((f) -> {
            try {
                return this.entityClass.getMethod("get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1), new Class[0]);
            } catch (NoSuchMethodException var3) {
                return null;
            }
        }).filter(Objects::nonNull).map((m) -> {
            try {
                return m.invoke(model, new Object[0]);
            } catch (InvocationTargetException | IllegalAccessException var3) {
                return null;
            }
        }).filter(Objects::nonNull).orElse((Object)null);
        return (I)id;
    }

    private Example idsCondition(List<I> ids) {
        if(CollectionUtils.isEmpty(ids)) {
            return null;
        } else {
            Set<EntityColumn> columnList = EntityHelper.getPKColumns(this.entityClass);
            EntityColumn pkColumn = (EntityColumn)columnList.iterator().next();
            Example example = new Example(this.entityClass);
            example.createCriteria().andIn(pkColumn.getProperty(), ids);
            return example;
        }
    }
}

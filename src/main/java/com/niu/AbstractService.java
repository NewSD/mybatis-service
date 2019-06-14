//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.niu;

import com.niu.common.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public abstract class AbstractService<I, T> {
    private static final Logger log = LoggerFactory.getLogger(AbstractService.class);
    @Autowired(
            required = false
    )
    protected Repository<I, T> innerRepository;
    protected Repository<I, T> repository;
    private Type keyType;
    private Type modelType;

    public AbstractService() {
        Class subClass;
        for (subClass = this.getClass(); !(subClass.getGenericSuperclass() instanceof ParameterizedType) && subClass.getSuperclass() != AbstractService.class; subClass = subClass.getSuperclass()) {
            ;
        }
        this.keyType = ((ParameterizedType) subClass.getGenericSuperclass()).getActualTypeArguments()[0];
        this.modelType = ((ParameterizedType) subClass.getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected void initInnerRepository() {
    }

    @PostConstruct
    private void initRepository() {
        if (this.innerRepository == null) {
            this.initInnerRepository();
        }
        Objects.requireNonNull(this.innerRepository, "Initialize inner repository failed");
        this.repository = this.innerRepository;

    }


    public abstract List<T> findByQuery(Object var1);

    public abstract List<T> findByQuery(Object var1, String var2);

    public T findOneByQuery(Object query) {
        List<T> itemList = this.findByQuery(query);
        return CollectionUtils.isEmpty(itemList) ? null : itemList.get(0);
    }

    public abstract List<T> findByQueryPage(Object var1, String var2, int var3, int var4);

    public List<T> findByQueryPage(Object query, int pageNo, int pageSize) {
        return this.findByQueryPage(query, (String) null, pageNo, pageSize);
    }

    public void deleteById(I id) {
        this.repository.deleteById(id);
    }

    public void deleteByIds(List<I> ids) {
        this.repository.deleteByIds(ids);
    }

    public void deleteByQuery(Object query) {
        throw new UnsupportedOperationException();
    }

    public void updateById(T model) {
        this.repository.update(model);
    }

    public void updateByIds(T model, List<I> ids) {
        this.repository.updateByIds(ids, model);
    }

    public void updateByQuery(T model, Object query) {
        throw new UnsupportedOperationException();
    }

    public abstract int count();

    public abstract int countByQuery(Object var1);

    public T getById(I id) {
        return this.repository.findById(id);
    }

    public List<T> getListByIds(List<I> ids) {
        return this.repository.findByIds(ids);
    }

    public void insert(T model) {
        this.repository.insert(model);
    }

    public void insertList(List<T> models) {
        this.repository.insert(models);
    }

    public void insertIfNotExist(List<T> modelList) {
        throw new UnsupportedOperationException();
    }

    public void insertIfNotExist(List<T> modelList, String fieldName) {
        throw new UnsupportedOperationException();
    }

    public Type getKeyType() {
        return this.keyType;
    }

    public Type getModelType() {
        return this.modelType;
    }
}

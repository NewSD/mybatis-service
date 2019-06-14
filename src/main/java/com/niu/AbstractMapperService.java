package com.niu;

import com.github.pagehelper.Page;
import com.niu.common.SqlMapper;
import com.niu.common.utils.PageUtils;
import com.niu.mybatis.AbstractQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class AbstractMapperService<I, T> extends AbstractService<I, T> {
    @Autowired
    protected SqlMapper<T> mapper;
    @Autowired
    private TransactionTemplate transactionTemplate;

    public AbstractMapperService() {
    }

    protected void initInnerRepository() {
        this.innerRepository = new MapperRepository((Class) this.getModelType(), this.mapper);
    }

    public List<T> findByQuery(Object query) {
        return this.findByQuery((AbstractQuery) query);
    }

    public List<T> findByQuery(Object query, String orderByClause) {
        return this.findByQuery((AbstractQuery) query, orderByClause);
    }

    public List<T> findByQueryPage(Object query, String orderByClause, int pageNo, int pageSize) {
        return this.findByQueryPage((AbstractQuery) query, orderByClause, pageNo, pageSize);
    }

    public List<T> findByQueryForUpdate(AbstractQuery<T> query) {
        return this.findByQuery(query, true, (String) null);
    }

    public List<T> findByQuery(AbstractQuery<T> query) {
        return this.findByQuery((AbstractQuery) query, (String) null);
    }

    public List<T> findByQuery(AbstractQuery<T> query, String orderByClause) {
        return this.findByQuery(query, false, orderByClause);
    }

    public List<T> findByQuery(AbstractQuery<T> query, boolean forUpdate, String orderByClause) {
        Example condition = query.toExample();
        condition.setForUpdate(forUpdate);
        if (StringUtils.isNotBlank(orderByClause)) {
            condition.setOrderByClause(orderByClause);
        }

        return this.repository.findByCondition(condition);
    }

    public T findOneByQuery(AbstractQuery<T> query) {
        List<T> itemList = this.findByQuery(query);
        return CollectionUtils.isEmpty(itemList) ? null : itemList.get(0);
    }

    public T findOneByQueryForUpdate(AbstractQuery<T> query) {
        List<T> itemList = this.findByQueryForUpdate(query);
        return CollectionUtils.isEmpty(itemList) ? null : itemList.get(0);
    }

    public List<T> findByQueryPage(AbstractQuery<T> query, String orderByClause, int pageNo, int pageSize) {
        query.setPage(pageNo, pageSize);
        List<T> itemList = this.findByQuery(query, orderByClause);
        PageUtils.cacheTotalCount((int) ((Page) itemList).getTotal());
        return itemList;
    }

    public List<T> findByQueryPage(AbstractQuery<T> query, int pageNo, int pageSize) {
        return this.findByQueryPage((AbstractQuery) query, (String) null, pageNo, pageSize);
    }

    public void deleteByQuery(final AbstractQuery<T> query) {
        this.repository.deleteByCondition(query.toExample());
    }

    public void updateByQuery(final T model, final AbstractQuery<T> query) {
        this.repository.updateByCondition(query.toExample(), model);
    }

    public int count() {
        return this.mapper.selectCountByExample(new Example((Class) this.getModelType()));
    }

    public int countByQuery(Object query) {
        return this.countByQuery((AbstractQuery) query);
    }

    public int countByQuery(AbstractQuery<T> query) {
        return this.mapper.selectCountByExample(query.toExample());
    }
}

package com.leeeeo.mydict.models;

import com.leeeeo.mydict.apps.AppEngine;

import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;

/**
 * Created by Jacob on 16/5/17.
 * Email: Jacob.Deng@about-bob.com
 */
public abstract class BaseModelManager<T, K> {
    public String TAG = this.getClass().getSimpleName();
    AbstractDao<T, K> dao = null;

    private Property globalLibName = null;

    public BaseModelManager(AbstractDao pDao) {
        this.dao = pDao;
        Property p[] = dao.getProperties();
        for (Property property : p) {
            if (property.columnName.equalsIgnoreCase("name_lib")) {
                this.globalLibName = property;
            }
        }
    }

    public QueryBuilder<T> queryBuilder() {
        return dao.queryBuilder().where(globalLibName.eq(AppEngine.getInstance().getGlobalLibName()));
    }

    public void clear(WhereCondition whereCondition, WhereCondition... conditions) {
        dao.deleteInTx(queryBuilder().where(whereCondition, conditions).list());
    }

    public void clear() {
        dao.deleteInTx(queryBuilder().list());
    }

    public T get(WhereCondition whereCondition) {
        return queryBuilder().where(whereCondition).unique();
    }

    public List<T> list() {
        return queryBuilder().list();
    }

    public long count() {
        return queryBuilder().count();
    }

    public long count(WhereCondition whereCondition, WhereCondition... condMore) {
        return queryBuilder().where(whereCondition, condMore).count();
    }

    public List<T> list(WhereCondition whereCondition, WhereCondition... condMore) {
        return queryBuilder().where(whereCondition, condMore).list();
    }

    public List<T> list(int limit, int offset, boolean isDESC, Property orderby, WhereCondition cond1, WhereCondition... condMore) {
        if (isDESC) {
            return queryBuilder().limit(limit).offset(offset).orderDesc(orderby).where(cond1, condMore).list();
        } else {
            return queryBuilder().limit(limit).offset(offset).orderAsc(orderby).where(cond1, condMore).list();

        }
    }

    public List<T> listOrderBy(boolean isDESC, Property orderby) {
        if (isDESC) {
            return queryBuilder().orderDesc(orderby).list();
        } else {
            return queryBuilder().orderAsc(orderby).list();
        }
    }

    public List<T> listor(int limit, int offset, boolean isDESC, Property orderby, WhereCondition cond1, WhereCondition cond2, WhereCondition... condMore) {
        if (isDESC) {
            return queryBuilder().whereOr(cond1, cond2, condMore).orderDesc(orderby).limit(limit).offset(offset).list();
        } else {
            return queryBuilder().whereOr(cond1, cond2, condMore).orderAsc(orderby).limit(limit).offset(offset).list();
        }
    }

    public List<T> listor(boolean isDESC, Property orderby, WhereCondition cond1, WhereCondition cond2, WhereCondition... condMore) {
        if (isDESC) {
            return queryBuilder().whereOr(cond1, cond2, condMore).orderDesc(orderby).list();
        } else {
            return queryBuilder().whereOr(cond1, cond2, condMore).orderAsc(orderby).list();
        }
    }

    public List<T> list(int limit, int offset, WhereCondition whereCondition, WhereCondition... condMore) {
        return queryBuilder().limit(limit).offset(offset).where(whereCondition).list();
    }

    public long create(T t) {
        return this.dao.insert(t);
    }

    public void create(List<T> ts) {
        //this.dao.insertInTx(ts);
        this.dao.insertOrReplaceInTx(ts);
    }

    public void update(T t) {
        this.dao.update(t);
    }

    public void updateForce(List<T> ts) {
        this.dao.insertOrReplaceInTx(ts);
    }

    public void update(List<T> ts) {
        this.dao.updateInTx(ts);
    }

    public long size(WhereCondition whereCondition) {
        if (null == whereCondition) {
            return queryBuilder().count();
        } else {
            return queryBuilder().where(whereCondition).count();
        }

    }
}

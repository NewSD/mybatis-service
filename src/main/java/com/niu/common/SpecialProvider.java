package com.niu.common;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by ami on 2018/11/20.
 */
public class SpecialProvider extends MapperTemplate{
    public SpecialProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String insertList(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var5 = columnList.iterator();

        while(var5.hasNext()) {
            EntityColumn column = (EntityColumn)var5.next();
            if(column.isInsertable()) {
                sql.append(column.getColumnHolder("record") + ",");
            }
        }

        sql.append("</trim>");
        sql.append("</foreach>");
        return sql.toString();
    }

    public String insertUseGeneratedKeys(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, true, false, false));
        sql.append(SqlHelper.insertValuesColumns(entityClass, true, false, false));
        return sql.toString();
    }

    public String sumByQuery(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        if(this.isCheckExampleEntityClass()) {
            sql.append(SqlHelper.exampleCheck(entityClass));
        }

        sql.append(selectSumColumn());
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append(exampleWhereClause());
        sql.append(SqlHelper.exampleForUpdate());
        return sql.toString();
    }

    public static String selectSumColumn() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("COALESCE(").append("SUM(").append("${sumColumnName}").append(")").append(",0) ");
        return sql.toString();
    }

    public static String exampleWhereClause() {
        return "<if test=\"_parameter != null\"><where>\n  <foreach collection=\"example.oredCriteria\" item=\"criteria\">\n    <if test=\"criteria.valid\">\n      ${@tk.mybatis.mapper.util.OGNL@andOr(criteria)}      <trim prefix=\"(\" prefixOverrides=\"and |or \" suffix=\")\">\n        <foreach collection=\"criteria.criteria\" item=\"criterion\">\n          <choose>\n            <when test=\"criterion.noValue\">\n              ${@tk.mybatis.mapper.util.OGNL@andOr(criterion)} ${criterion.condition}\n            </when>\n            <when test=\"criterion.singleValue\">\n              ${@tk.mybatis.mapper.util.OGNL@andOr(criterion)} ${criterion.condition} #{criterion.value}\n            </when>\n            <when test=\"criterion.betweenValue\">\n              ${@tk.mybatis.mapper.util.OGNL@andOr(criterion)} ${criterion.condition} #{criterion.value} and #{criterion.secondValue}\n            </when>\n            <when test=\"criterion.listValue\">\n              ${@tk.mybatis.mapper.util.OGNL@andOr(criterion)} ${criterion.condition}\n              <foreach close=\")\" collection=\"criterion.value\" item=\"listItem\" open=\"(\" separator=\",\">\n                #{listItem}\n              </foreach>\n            </when>\n          </choose>\n        </foreach>\n      </trim>\n    </if>\n  </foreach>\n</where></if>";
    }
}

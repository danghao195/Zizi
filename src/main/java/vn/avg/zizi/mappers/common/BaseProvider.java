package vn.avg.zizi.mappers.common;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;

import vn.avg.zizi.common.CommonConstants;
import vn.avg.zizi.entity.BaseEntity;
import vn.avg.zizi.mappers.common.annotation.Column;
import vn.avg.zizi.mappers.common.annotation.PrimaryKey;
import vn.avg.zizi.mappers.common.annotation.Table;
import vn.avg.zizi.utils.StringUtil;

/**
 * <p>ファイル名 : UnivMstMapper</p>
 * <p>説明 : UnivMstMapper</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
public class BaseProvider {

    /** The Constant CREATE_CLASS. */
    private static final String CREATE_CLASS = "createClass";

    /** The Constant CREATE_TIME. */
    private static final String CREATE_TIME = "createTime";

    /** The Constant CREATE_USER. */
    private static final String CREATE_USER = "createUser";

    /** The Constant DELETE_FLG_0. */
    private static final String DELETE_FLG_0 = "DELETE_FLG = 0";

    /** The Constant AS_NAME. */
    private static final String AS_NAME = " AS name";

    /** The Constant AS_CODE. */
    private static final String AS_CODE = " AS code";

    /** The Constant DELETE_FLAG. */
    private static final String DELETE_FLAG = "deleteFlg";

    /**
     * FORMAT_STRING_SQL_3
     */
    private static final String FORMAT_STRING_SQL_3 = "#{";

    /**
     * STRING_COMMA
     */
    private static final String STRING_COMMA = ",";

    /**
     * STRING_AS
     */
    private static final String STRING_AS = " AS ";

    /**
     * FORMAT_STRING_SQL_2
     */
    private static final String FORMAT_STRING_SQL_2 = "}";

    /**
     * FORMAT_STRING_SQL_1
     */
    private static final String FORMAT_STRING_SQL_1 = " = #{";
    /**
     * FORMAT_STRING_SQL_1
     */
    private static final String FORMAT_STRING_SQL_NOT_EQUAL = " <> #{";
    /**
     * SQL_COUNT_ALL_RECODE
     */
    private static final String SQL_COUNT_ALL_RECODE = "count(*)";

    /**
     * FORMAT_STRING_SQL_4
     */
    private static final String FORMAT_STRING_SQL_4 = " = \"";

    /**
     * FORMAT_STRING_SQL_5
     */
    private static final String FORMAT_STRING_SQL_5 = "\"";

    /**
     * INSERT_QUERY
     */
    private static final String INSERT_QUERY = "INSERT INTO ";

    /**
     * DELETE_TIME
     */
    private static final String DELETE_TIME = "deleteTime";

    /**
     * VALUE_START
     */
    private static final String VALUE_START = " ( ";

    /**
     * VALUE_END
     */
    private static final String VALUE_END = " ) ";

    /**
     * COLLECTION_LIST
     */
    private static final String COLLECTION_LIST = "collection[{0}].";

    /**
     * VALUES
     */
    private static final String VALUES = " VALUES ";

    /**
     * UPDATE Query
     */
    private static final String UPDATE_QUERY = "UPDATE {0} SET ";

    /**
     * WHERE Query
     */
    private static final String WHERE_QUERY = " WHERE ";

    /**
     * AND name
     */
    private static final String AND_NAME = " AND ";

    /**
     * lastUpdateTime value
     */
    private static final String LAST_UPDATE_TIME = "lastUpdateTime";

    /**
     * UPDATE_TIME column
     */
    private static final String UPDATE_TIME = "UPDATE_TIME";

    /**
     * Select by primary key.
     *
     * @param entity the entity
     * @return the string
     */
    public String selectByPrimaryKey(Object entity) {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                String selectClause = "";
                // SELECT("*");
                FROM(tableName);

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);

                }

                for (Field field : table.getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);
                }
                selectClause = selectClause.substring(0, selectClause.length() - 1);
                SELECT(selectClause);
            }

            private String genarateSelectedField(String selectClause, Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    selectClause = selectClause + field.getAnnotation(PrimaryKey.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                } else if (field.isAnnotationPresent(Column.class)) {
                    selectClause = selectClause + field.getAnnotation(Column.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                }
                return selectClause;
            }
        }.toString();
        return sql;
    }

    /**
     * select all
     * 
     * @param classType Class<? extends Object>
     * @return String
     */
    public String selectAll(Class<? extends Object> classType) {

        Class<? extends Object> table = classType;

        String tableName = table.getAnnotation(Table.class).name();
        String sql = new SQL() {
            {
                String selectClause = "";

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);

                }

                for (Field field : table.getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);
                }
                selectClause = selectClause.substring(0, selectClause.length() - 1);
                SELECT(selectClause);
                FROM(tableName);

            }

            private String genarateSelectedField(String selectClause, Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    selectClause = selectClause + field.getAnnotation(PrimaryKey.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                } else if (field.isAnnotationPresent(Column.class)) {
                    selectClause = selectClause + field.getAnnotation(Column.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                }
                return selectClause;
            }
        }.toString();
        return sql;
    }

    /**
     * 
     * <p>説明 : deleteLogicAll</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since 2018/03/07
     * @param entity    class entity
     * @return  sql delete logic all
     */
    public String deleteLogicAll(Object entity) {

        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                UPDATE(tableName);
                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (CREATE_USER.equals(field.getName()) || CREATE_TIME.equals(field.getName()) || CREATE_CLASS.equals(field.getName())) {
                        continue;
                    }

                    if (field.isAnnotationPresent(Column.class)) {
                        if (DELETE_FLAG.equals(field.getName())) {
                            SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_4 + CommonConstants.DB_DELETED + FORMAT_STRING_SQL_5);
                        } else {
                            SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                        }
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * Check exist of recode by primary key
     * 
     * @param entity Object
     * @return String
     */
    public String existPrimaryKey(Object entity) {

        BaseEntity baseEntity = (BaseEntity) entity;
//        if (StringUtil.isEmpty(baseEntity.getDeleteFlg())) {
//            baseEntity.setDeleteFlg("0");
//        }

        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();
        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);
                for (Field field : table.getDeclaredFields()) {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * Check exist of recode by example record
     * 
     * @param entity Object
     * @return String
     * @throws Exception Exception
     */
    public String existWithExample(Object entity) throws Exception {

        BaseEntity baseEntity = (BaseEntity) entity;

        Class<? extends Object> table = entity.getClass();

        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);
                for (Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                        if (field.get(entity) != null) {
                            if (field.isAnnotationPresent(Column.class)) {
                                WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                            } else {
                                WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                            }
                        }

                    }

                }
            }
        }.toString();
        
//        if ("1".equals(baseEntity.getDeleteFlg())) {
//            sql += " AND DELETE_FLG = 1";
//        } else {
//            sql += " AND DELETE_FLG = 0";
//        }
        
        return sql;
    }

    /**
     * <p>説明 : FIXME Kiểm tra xem có tồn tại một record khác PK nhưng có data của cột với data của record cần check</p> 
     * @author : [truong.pq]
     * @since [2018/02/23]
     * @param entity : Table Entity
     * @return True : duplicate
     * @throws Exception : Exception
     */
    public String isDuplicateValueColum(Object entity) throws Exception {

        Class<? extends Object> table = entity.getClass();

        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);

                for (Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        genarateWhereField(field);
                    }
                }
            }

            private void genarateWhereField(Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_NOT_EQUAL + field.getName() + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(Column.class)) {
                    WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                }
            }

        }.toString();
        return sql;
    }

    /**
     * select all record with example record
     * 
     * @param entity Object
     * @return String
     * @throws Exception Exception
     */
    public String selectWithExample(Object entity) throws Exception {

        BaseEntity baseEntity = (BaseEntity) entity;

        Class<? extends Object> table = entity.getClass();

        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                String selectClause = "";

                // SELECT("*");
                FROM(tableName);
                for (Field field : entity.getClass().getDeclaredFields()) {
                    selectClause = genSqlForSelectWithExample(entity, selectClause, field);
                }

                for (Field field : entity.getClass().getSuperclass().getDeclaredFields()) {
                    selectClause = genSqlForSelectWithExample(entity, selectClause, field);
                }

                selectClause = selectClause.substring(0, selectClause.length() - 1);
                SELECT(selectClause);
            }

            private String genSqlForSelectWithExample(Object entity, String selectClause, Field field) throws IllegalAccessException {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                    if (field.get(entity) != null) {
                        if (field.isAnnotationPresent(Column.class)) {
                            WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);

                        } else {
                            WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);

                        }
                    }
                }

                if (field.isAnnotationPresent(Column.class)) {
                    selectClause = selectClause + field.getAnnotation(Column.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                } else if (field.isAnnotationPresent(PrimaryKey.class)) {
                    selectClause = selectClause + field.getAnnotation(PrimaryKey.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                }
                return selectClause;
            }
        }.toString();
        
//        if ("1".equals(baseEntity.getDeleteFlg())) {
//            sql += " AND DELETE_FLG = 1";
//        } else {
//            sql += " AND DELETE_FLG = 0";
//        }
        
        return sql;
    }

    /**
     * delete by primary key
     * 
     * @param entity Object
     * @return String
     */
    public String deleteByPrimaryKey(Object entity) {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                DELETE_FROM(tableName);
                for (Field field : table.getDeclaredFields()) {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * delete with Example record
     * 
     * @param entity Object
     * @return String
     * @throws Exception Exception
     */
    public String deleteWithExample(Object entity) throws Exception {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                DELETE_FROM(tableName);
                for (Field field : entity.getClass().getDeclaredFields()) {
                    genSqlForDeleteWithExample(entity, field);
                }

                for (Field field : entity.getClass().getSuperclass().getDeclaredFields()) {
                    genSqlForDeleteWithExample(entity, field);
                }
            }

            private void genSqlForDeleteWithExample(Object entity, Field field) throws IllegalAccessException {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                    if (field.get(entity) != null) {
                        if (field.isAnnotationPresent(Column.class)) {
                            WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                        } else {
                            WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                        }
                    }

                }
            }
        }.toString();
        return sql;
    }

    /**
     * insert
     * 
     * @param entity Object
     * @return String
     */
    public String insert(Object entity) {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                INSERT_INTO(tableName);
                for (Field field : table.getDeclaredFields()) {
                    genSqlForInsert(field);
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    genSqlForInsert(field);
                }
            }

            private void genSqlForInsert(Field field) {
                if (field.isAnnotationPresent(Column.class)) {
                    VALUES(field.getAnnotation(Column.class).name(), FORMAT_STRING_SQL_3 + field.getName() + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(PrimaryKey.class)) {
                    if (!field.getAnnotation(PrimaryKey.class).autoIncre()) {
                        VALUES(field.getAnnotation(PrimaryKey.class).name(), FORMAT_STRING_SQL_3 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * update by primaryKey
     *  
     * @param entity Object
     * @return String
     * @throws IllegalAccessException 
     */
    public String updateByPrimaryKey(Object entity) throws IllegalAccessException {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                UPDATE(tableName);
                for (Field field : table.getDeclaredFields()) {
                    field.setAccessible(true);
                    genSqlForUpdate(field);
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (CREATE_USER.equals(field.getName()) || CREATE_TIME.equals(field.getName()) || CREATE_CLASS.equals(field.getName())) {
                        continue;
                    }
                    field.setAccessible(true);
                    genSqlForUpdate(field);
                }
            }

            private void genSqlForUpdate(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(Column.class)) {
                    SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                }

                // check exclusive when update
                if (LAST_UPDATE_TIME.equals(field.getName()) && field.get(entity) != null) {
                    WHERE(UPDATE_TIME + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                }
            }
        }.toString();
        return sql;
    }

    /**
     * update not null field by primaryKey
     *  
     * @param entity Object
     * @return String
     * @throws IllegalAccessException 
     */
    public String updateNotNullByPK(Object entity) throws IllegalAccessException {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql;
        sql = new SQL() {
            {
                UPDATE(tableName);
                for (Field field : table.getDeclaredFields()) {
                    field.setAccessible(true);
                    genSqlForUpdate(field);
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (CREATE_USER.equals(field.getName()) || CREATE_TIME.equals(field.getName()) || CREATE_CLASS.equals(field.getName())) {
                        continue;
                    }

                    field.setAccessible(true);
                    genSqlForUpdate(field);
                }
            }

            private void genSqlForUpdate(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(Column.class)) {
                    if (field.get(entity) != null) {
                        SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }

                // check exclusive when update
                if (LAST_UPDATE_TIME.equals(field.getName()) && field.get(entity) != null) {
                    WHERE(UPDATE_TIME + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                }
            }
        }.toString();

        return sql;
    }

    /**
     * checkExclusive
     * 
     * @param entity Object
     * @return String
     */
    public String checkExclusive(Object entity) {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);
                for (Field field : table.getDeclaredFields()) {
                    genSqlForCheckExclusive(field);
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    genSqlForCheckExclusive(field);
                }
            }

            private void genSqlForCheckExclusive(Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                }

                if (field.isAnnotationPresent(Column.class) && "UPDATE_TIME".equals(field.getAnnotation(Column.class).name())) {
                    WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                }
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : getSelectItem</p> 
     * @author : truong.dx
     * @since [2017/12/25]
     * @param tableNm String
     * @param colCode String
     * @param colName String
     * @return  string SQL
     */
    public String getSelectItem(String tableNm, String colCode, String colName) {
        String sql = new SQL() {
            {
                SELECT(colCode + AS_CODE, colName + AS_NAME);
                FROM(tableNm);
                WHERE(DELETE_FLG_0);

            }
        }.toString();
        return sql;
    }

    /**
     * 
     * <p>説明 : get SelectItem With Condition</p> 
     * @author : hung.nq
     * @since [2018/02/08]
     * @param tableNm tablename
     * @param colCd Column cd
     * @param colNm Column name
     * @param where where clause
     * @return string SQL
     */
    public String getSelectItemWithCondition(String tableNm, String colCd, String colNm, String where) {
        String sql = new SQL() {
            {
                SELECT(colCd + AS_CODE, colNm + AS_NAME);
                FROM(tableNm);
                WHERE(DELETE_FLG_0 + String.format(StringUtils.isEmpty(where) ? "" : " AND %s", where));
            }
        }.toString();
        return sql;
    }

    /**
     * 
     * <p>説明 : getColValue</p> 
     * @author : hung.nq
     * @since [2018/02/08]
     * @param tableNm tablename
     * @param colNm Column name
     * @param where where clause
     * @return string SQL
     */
    public String getColValueWithCondition(String tableNm, String colNm, String where) {
        String sql = new SQL() {
            {
                SELECT(colNm + AS_NAME);
                FROM(tableNm);
                WHERE(DELETE_FLG_0 + String.format(StringUtils.isEmpty(where) ? "" : " AND %s", where));
            }
        }.toString();
        return sql;
    }

    /**
     * 
     * <p>説明 : getColValue</p> 
     * @author : hung.nq
     * @since [2018/02/08]
     * @param tableNm tablename
     * @param colNm Column name
     * @return string SQL
     */
    public String getColValue(String tableNm, String colNm) {
        String sql = new SQL() {
            {
                SELECT(colNm + AS_NAME);
                FROM(tableNm);
                WHERE(DELETE_FLG_0);
            }
        }.toString();
        return sql;
    }

    /**
     * 
     * <p>説明 : Delete all table</p> 
     * @author : hung.nq
     * @since [2018/02/08]
     * @param tableNm table name
     * @return string SQL
     */
    public String deleteTable(String tableNm) {
        String sql = new SQL() {
            {
                DELETE_FROM(tableNm);
            }
        }.toString();
        return sql;
    }

    /**
     * 
     * <p>説明 : Delete Logic Object</p> 
     * @author : minh.ls
     * @since 2018/02/06
     * @param entity Entity
     * @return SQL query
     * @throws IllegalAccessException 
     */
    public String deleteLogicByPK(Object entity) throws IllegalAccessException {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                UPDATE(tableName);
                for (Field field : table.getDeclaredFields()) {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (CREATE_USER.equals(field.getName()) || CREATE_TIME.equals(field.getName()) || CREATE_CLASS.equals(field.getName())) {
                        continue;
                    }

                    if (field.isAnnotationPresent(Column.class)) {
                        if (DELETE_FLAG.equals(field.getName())) {
                            SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_4 + CommonConstants.DB_DELETED + FORMAT_STRING_SQL_5);
                        } else {
                            SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                        }
                    }

                    // check exclusive when update
                    field.setAccessible(true);
                    if (LAST_UPDATE_TIME.equals(field.getName()) && field.get(entity) != null) {
                        WHERE(UPDATE_TIME + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * 
     * <p>説明 : selectAllWithoutAnotation</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since 2018/02/26
     * @param tblName   Table name
     * @return  Select * from {table}
     */
    public String selectAllWithoutAnotation(String tblName) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(tblName);
            }
        }.toString();
        return sql;
    }

    /**
     * 
     * <p>説明 : select All Keys</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since 2018/03/07
     * @param entity            Entity class
     * @param <T>               Class entity
     * @return  SQL select all keys in a table
     */
    public <T extends BaseEntity> String selectAllKeys(Class<T> entity) {
        String tableName = entity.getAnnotation(Table.class).name();
        String sql = new SQL() {
            {
                StringBuilder fieldKey = new StringBuilder();
                for (Field field : entity.getDeclaredFields()) {
                    if (field.getAnnotation(PrimaryKey.class) != null) {
                        fieldKey.append(field.getAnnotation(PrimaryKey.class).name() + STRING_AS + field.getName() + CommonConstants.COMMA);
                    }
                }
                fieldKey = fieldKey.deleteCharAt(fieldKey.length() - 1);
                SELECT(fieldKey.toString());
                FROM(tableName);
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : insert list record</p> 
     * @author : minh.ls
     * @param <T>
     * @since : 2018/03/12
     * @param entityListObj entity list object
     * @param <T> : Entity
     * @return insert query
     */
    @SuppressWarnings("unchecked")
    public <T> String insertList(Object entityListObj) {
        StrictMap<?> map = (StrictMap<?>) entityListObj;
        List<T> entityList = (List<T>) map.get("collection");

        StringBuilder sql = new StringBuilder();

        if (entityList.size() > 0) {
            Class<? extends Object> table = entityList.get(0).getClass();

            // get insert query
            sql.append(genSqlInsertHeader(table));

            // gen body query
            sql.append(genSqlInsertBody(entityList));
        }
        return sql.toString();
    }

    /**
     * <p>説明 : update list record</p> 
     * @author : minh.ls
     * @param <T>
     * @since : 2018/03/12
     * @param entityListObj List Entity
     * @param <T> : Entity
     * @return value
     */
    @SuppressWarnings("unchecked")
    public <T> String updateList(Object entityListObj) {
        StrictMap<?> map = (StrictMap<?>) entityListObj;
        List<T> entityList = (List<T>) map.get("collection");

        StringBuilder sql = new StringBuilder();
        if (entityList.size() > 0) {
            for (int i = 0; i < entityList.size(); i++) {
                sql.append(genSqlUpdate(entityList.get(i), i));
            }
        }
        return sql.toString();
    }

    /**
     * 
     * <p>説明 : select All Keys With Example</p> 
     * @author minh.ls
     * @since 2018/05/31
     * @param entity Object entity
     * @return  SQL select all keys in a table
     * @throws IllegalAccessException 
     */
    public String selectAllKeysWithExample(Object entity) throws IllegalAccessException {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();
        String sql = new SQL() {
            {
                StringBuilder fieldKey = new StringBuilder();
                for (Field field : entity.getClass().getDeclaredFields()) {
                    if (field.getAnnotation(PrimaryKey.class) != null) {
                        fieldKey.append(field.getAnnotation(PrimaryKey.class).name() + STRING_AS + field.getName() + CommonConstants.COMMA);
                    }
                }
                fieldKey = fieldKey.deleteCharAt(fieldKey.length() - 1);
                SELECT(fieldKey.toString());
                FROM(tableName);
                for (Field field : entity.getClass().getDeclaredFields()) {
                    genSqlForSelectWithExample(entity, field);
                }

                for (Field field : entity.getClass().getSuperclass().getDeclaredFields()) {
                    genSqlForSelectWithExample(entity, field);
                }

            }

            private void genSqlForSelectWithExample(Object entity, Field field) throws IllegalAccessException {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                    if (field.get(entity) != null) {
                        if (field.isAnnotationPresent(Column.class)) {
                            WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);

                        } else {
                            WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);

                        }
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : Gen sql insert</p> 
     * @author : minh.ls
     * @since : 2018/03/12
     * @param obj Class
     * @param <T> class
     * @return insert into query
     */
    private <T> String genSqlInsertHeader(Class<T> obj) {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append(INSERT_QUERY);
        sqlQuery.append(obj.getAnnotation(Table.class).name());
        sqlQuery.append(VALUE_START);

        // gen column
        for (Field field : obj.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                if (field.getAnnotation(Column.class) == null) {
                    sqlQuery.append(field.getAnnotation(PrimaryKey.class).name());
                } else {
                    sqlQuery.append(field.getAnnotation(Column.class).name());
                }

                sqlQuery.append(STRING_COMMA);
            }
        }

        for (Field field : obj.getSuperclass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {

                if (field.getAnnotation(Column.class) == null) {
                    sqlQuery.append(field.getAnnotation(PrimaryKey.class).name());
                } else {
                    sqlQuery.append(field.getAnnotation(Column.class).name());
                }

                if (!DELETE_TIME.equals(field.getName())) {
                    sqlQuery.append(STRING_COMMA);
                }
            }
        }

        sqlQuery.append(VALUE_END);
        sqlQuery.append(VALUES);

        return sqlQuery.toString();
    }

    /**
     * <p>説明 : gen insert body</p> 
     * @author : minh.ls
     * @since : 2018/03/12
     * @param objList List<T>
     * @param <T> class
     * @return insert body
     */
    private <T> String genSqlInsertBody(List<T> objList) {
        StringBuilder sqlQuery = new StringBuilder();
        for (int i = 0; i < objList.size(); i++) {

            Class<? extends Object> obj = objList.get(i).getClass();

            sqlQuery.append(VALUE_START);

            // gen body
            for (Field field : obj.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                    sqlQuery.append(FORMAT_STRING_SQL_3);
                    sqlQuery.append(MessageFormat.format(COLLECTION_LIST, i));
                    sqlQuery.append(field.getName());
                    sqlQuery.append(FORMAT_STRING_SQL_2);
                    sqlQuery.append(STRING_COMMA);
                }
            }

            for (Field field : obj.getSuperclass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                    sqlQuery.append(FORMAT_STRING_SQL_3);
                    sqlQuery.append(MessageFormat.format(COLLECTION_LIST, i));
                    sqlQuery.append(field.getName());
                    sqlQuery.append(FORMAT_STRING_SQL_2);

                    if (!DELETE_TIME.equals(field.getName())) {
                        sqlQuery.append(STRING_COMMA);
                    }
                }
            }

            sqlQuery.append(VALUE_END);

            if (objList.size() > 1 && i < objList.size() - 1) {
                sqlQuery.append(STRING_COMMA);
            }
        }
        return sqlQuery.toString();
    }

    /**
     * 
     * <p>説明 : Des</p> 
     * @author : minh.ls
     * @param <T>
     * @since : 2018/03/12
     * @param obj Class
     * @param <T> Entity class
     * @param index index of class in List
     * @return String sql update
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    private <T> String genSqlUpdate(Object obj, Integer index) {

        Class<? extends Object> table = obj.getClass();

        StringBuilder query = new StringBuilder();
        StringBuilder setQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();

        // set update query
        query.append(MessageFormat.format(UPDATE_QUERY, table.getAnnotation(Table.class).name()));

        for (Field field : table.getDeclaredFields()) {
            if (CREATE_USER.equals(field.getName()) || CREATE_TIME.equals(field.getName()) || CREATE_CLASS.equals(field.getName())) {
                continue;
            }

            if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                field.setAccessible(true);

                // gen set data for update
                try {
                    if (field.isAnnotationPresent(Column.class) && field.get(obj) != null) {
                        setQuery.append(field.getAnnotation(Column.class).name());
                        setQuery.append(FORMAT_STRING_SQL_1);
                        setQuery.append(MessageFormat.format(COLLECTION_LIST, index));
                        setQuery.append(field.getName());
                        setQuery.append(FORMAT_STRING_SQL_2);
                        setQuery.append(STRING_COMMA);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                // gen where
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    if (whereQuery.length() > 0) {
                        whereQuery.append(AND_NAME);
                    }

                    whereQuery.append(field.getAnnotation(PrimaryKey.class).name());
                    whereQuery.append(FORMAT_STRING_SQL_1);
                    whereQuery.append(MessageFormat.format(COLLECTION_LIST, index));
                    whereQuery.append(field.getName());
                    whereQuery.append(FORMAT_STRING_SQL_2);
                }
            }
        }

        for (Field field : table.getSuperclass().getDeclaredFields()) {
            if (CREATE_USER.equals(field.getName()) || CREATE_TIME.equals(field.getName()) || CREATE_CLASS.equals(field.getName())) {
                continue;
            }

            if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                field.setAccessible(true);

                // gen set data for update
                try {
                    if (field.isAnnotationPresent(Column.class) && field.get(obj) != null) {
                        setQuery.append(field.getAnnotation(Column.class).name());
                        setQuery.append(FORMAT_STRING_SQL_1);
                        setQuery.append(MessageFormat.format(COLLECTION_LIST, index));
                        setQuery.append(field.getName());
                        setQuery.append(FORMAT_STRING_SQL_2);
                        setQuery.append(STRING_COMMA);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                // gen where
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    if (whereQuery.length() > 0) {
                        whereQuery.append(AND_NAME);
                    }

                    whereQuery.append(field.getAnnotation(PrimaryKey.class).name());
                    whereQuery.append(FORMAT_STRING_SQL_1);
                    whereQuery.append(MessageFormat.format(COLLECTION_LIST, index));
                    whereQuery.append(field.getName());
                    whereQuery.append(FORMAT_STRING_SQL_2);
                }
            }

        }

        // set sql query
        String setStr = setQuery.toString();
        query.append(setStr.substring(0, setStr.length() - 1));
        query.append(WHERE_QUERY);
        query.append(whereQuery.toString() + ";");

        return query.toString();
    }

    /**
     * 
     * <p>説明 : Delete Logic With Example</p> 
     * @author : minh.ls
     * @since 2018/05/31
     * @param entity Entity
     * @return SQL query
     * @throws Exception Exception
     */
    public String deleteLogicWithExample(Object entity) throws Exception {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                UPDATE(tableName);
                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (CREATE_USER.equals(field.getName()) || CREATE_TIME.equals(field.getName()) || CREATE_CLASS.equals(field.getName())) {
                        continue;
                    }

                    if (field.isAnnotationPresent(Column.class)) {
                        if (DELETE_FLAG.equals(field.getName())) {
                            SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_4 + CommonConstants.DB_DELETED + FORMAT_STRING_SQL_5);
                        } else {
                            SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                        }
                    }

                    // check exclusive when update
                    field.setAccessible(true);
                    if (LAST_UPDATE_TIME.equals(field.getName()) && field.get(entity) != null) {
                        WHERE(UPDATE_TIME + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }

                for (Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                        if (field.get(entity) != null) {
                            if (field.isAnnotationPresent(Column.class)) {
                                WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                            } else {
                                WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                            }
                        }

                    }
                }
            }
        }.toString();
        return sql;
    }
}

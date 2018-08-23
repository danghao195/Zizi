package vn.avg.zizi.mappers.common;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import vn.avg.zizi.mappers.common.BaseProvider;

/**
 * 
 * <p>ファイル名 : BaseMapper</p>
 * <p>説明 : BaseMapper</p>
 * @author truong.pq
 * @since 2017/12/06
 * @param <T>
 */
public interface BaseMapper<T> {

    /**
     * get one record by primary key
     * 
     * @param record T
     * @return T
     */
    @SelectProvider(type = BaseProvider.class, method = "selectByPrimaryKey")
    T selectByPK(T record);

    /**
     * get all record from table
     * 
     * @param entityClass Class<?>
     * @return List<T>
     */
    @SelectProvider(type = BaseProvider.class, method = "selectAll")
    List<T> selectAll(Class<?> entityClass);

    /**
     * select records with example info
     * 
     * @param record T
     * @return List<T>
     */
    @SelectProvider(type = BaseProvider.class, method = "selectWithExample")
    List<T> selectWithExample(T record);

    /**
     * check exist record with primary key info
     * 
     * @param record T
     * @return Boolean
     */
    @SelectProvider(type = BaseProvider.class, method = "existPrimaryKey")
    Boolean existWithPK(T record);

    /**
     * <p>説明 : FIXME Kiểm tra xem có tồn tại một record khác PK nhưng có data của cột với data của record cần check</p> 
     * @author : truong.pq
     * @since : 2018/02/23
     * @param record : row of table to check
     * @return True : duplicate
     */
    @SelectProvider(type = BaseProvider.class, method = "isDuplicateValueColum")
    Boolean isDuplicateValueColum(T record);

    /**
     * count record with primary key info
     * 
     * @param record T
     * @return Integer
     */
    @SelectProvider(type = BaseProvider.class, method = "existWithExample")
    Integer countWithExample(T record);

    /**
     * delete record with primary key info
     * 
     * @param record T
     * @return Integer
     */
    @DeleteProvider(type = BaseProvider.class, method = "deleteByPrimaryKey")
    Integer deleteByPK(T record);

    /**
     * delete records with example info
     * 
     * @param record T
     * @return Integer
     */
    @DeleteProvider(type = BaseProvider.class, method = "deleteWithExample")
    Integer deleteWithExample(T record);

    /**
     * insert record into
     * 
     * @param record T
     * @return Integer
     */
    @InsertProvider(type = BaseProvider.class, method = "insert")
    Integer insert(T record);

    /**
     * update record with primary key
     * 
     * @param record T
     * @return Integer
     */
    @UpdateProvider(type = BaseProvider.class, method = "updateByPrimaryKey")
    Integer updateByPK(T record);

    /**
     * update record with primary key
     * 
     * @param record T
     * @return Integer
     */
    @UpdateProvider(type = BaseProvider.class, method = "updateNotNullByPK")
    Integer updateNotNullByPK(T record);

    /**
     * check exclusive
     * 
     * @param record T
     * @return True if it same update time otherwise is False
     */
    @SelectProvider(type = BaseProvider.class, method = "checkExclusive")
    Boolean checkExclusive(T record);

    /**
     * Delete allTable
     * <p>説明 : deleteTable</p> 
     * @author : [dts.bp51]
     * @since : [2018/01/16]
     * @param tableNm table name
     * @return Integer
     */
    @DeleteProvider(type = BaseProvider.class, method = "deleteTable")
    Integer deleteTable(String tableNm);

    /**
     * update record with primary key
     * 
     * @param record T
     * @return Integer
     */
    @UpdateProvider(type = BaseProvider.class, method = "deleteLogicByPK")
    Integer deleteLogicByPK(T record);

    /**
     * update record with primary key
     * 
     * @param record  Class Object
     * @return Integer
     */
    @UpdateProvider(type = BaseProvider.class, method = "deleteLogicAll")
    Integer deleteLogicAll(T record);

    /**
     * get all record from table
     * 
     * @param entityClass Class<?>
     * @return List<T>
     */
    @SelectProvider(type = BaseProvider.class, method = "selectAllKeys")
    List<T> selectAllKeys(Class<T> entityClass);

    /**
     * insert list record into
     * 
     * @param record T
     * @return Integer
     */
    @InsertProvider(type = BaseProvider.class, method = "insertList")
    Integer insertList(List<T> record);

    /**
     * update list record into
     * 
     * @param record T
     * @return Integer
     */
    @UpdateProvider(type = BaseProvider.class, method = "updateList")
    Integer updateList(List<T> record);

    /**
     * get all record from table
     * 
     * @param entityClass Class<?>
     * @return List<T>
     */
    @SelectProvider(type = BaseProvider.class, method = "selectAllKeysWithExample")
    List<T> selectAllKeysWithExample(T record);

    /**
     * delete Logic With Example
     * 
     * @param record T
     * @return Integer
     */
    @UpdateProvider(type = BaseProvider.class, method = "deleteLogicWithExample")
    Integer deleteLogicWithExample(T record);
}

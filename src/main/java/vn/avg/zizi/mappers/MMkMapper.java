/**
 * File name : MMkMapper.java
 * @author : hao.dv
 * @since : 2018/08/21
 */
package vn.avg.zizi.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import vn.avg.zizi.dto.MMkInfoDTO;
import vn.avg.zizi.dto.MkDTO;
import vn.avg.zizi.dto.search.MkSearchDTO;
import vn.avg.zizi.entity.MMkEntity;
import vn.avg.zizi.mappers.common.BaseMapper;

/**
 * <p> File name : MMkMapper</p>
 * <p> Description : MMk Mapper</p>
 * @author hao.dv
 * @since 2018/08/21
 */
@Mapper
public interface MMkMapper extends BaseMapper<MMkEntity> {

    /**
     * <p>Description : count mk by search</p>
     * @param mkSearchDTO MkSearchDTO
     * @return int
     */
    int countMk(MkSearchDTO mkSearchDTO);
    /**
     * 
     * <p>Description : FIXME get all record</p>
     * @return FIXME all record in table maker
     */
    List<MMkEntity> getAllRecord();
    /**
     * <p>Description : get list mk by search</p>
     * @param mkSearchDTO MkSearchDTO
     * @return List<MkDTO> FIXME danh sach maker
     */
    List<MkDTO> getMkList(MkSearchDTO mkSearchDTO);

    /**
     * <p>Description : getMakerNameList</p>
     * @return List<String> FIXME danh sach maker name
     */
    List<String> getMakerNameList();

    /**
     * 
     * <p>Description : Maker code is already exist</p>
     * @param mkCd      Maker code
     * @return  Number of record
     */
    int countByMkCd(@Param("mk_cd") Integer mkCd);
    
    /**
     * <p>Description : get MMk Info</p>
     * @param productType String
     * @param makerCd Integer
     * @return List<MMkInfoDTO>
     */
    MMkInfoDTO getMMkInfo( @Param("maker_code") Integer makerCd);

    /**
     *    
     * <p>Description : FIXME get list mkCd</p> 
     * @param mkSearchDTO MkSearchDTO
     * @return  List maker code
     */
    List<Integer> getMkCdList(MkSearchDTO mkSearchDTO);

}

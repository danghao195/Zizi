/**
 * File name : MNG31020XServiceImpl.java
 * @author : hao.dv
 * @since : 2018/08/20
 */
package vn.avg.zizi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.avg.zizi.common.CommonConstants;
import vn.avg.zizi.dto.MMkInfoDTO;
import vn.avg.zizi.dto.MkDTO;
import vn.avg.zizi.dto.SelectItemDTO;
import vn.avg.zizi.dto.search.BaseSearchDTO;
import vn.avg.zizi.dto.search.MkSearchDTO;
import vn.avg.zizi.entity.MMkEntity;
import vn.avg.zizi.mappers.MMkMapper;
import vn.avg.zizi.output.BaseOutput;
import vn.avg.zizi.output.MNG31020XOutput;
import vn.avg.zizi.utils.PageUtil;
import vn.avg.zizi.utils.Utilities;

/**
 * <p> File name : MNG31020XServiceImpl <p>
 * <p> Description : Maker service</p>
 * @author hao.dv
 * @since 2018/08/20
 */
@Service("MNG31020XService")
public class MNG31020XServiceImpl extends BaseService implements MNG31020XService {

    /**  Manufacturer Mapper */
    @Autowired
    private MMkMapper makerMapper;

    /**  Common service*/
    @Autowired
    private CommonService commonService;

    /**  sort */
    private static final String DEFAULT_SORT = "M_MK.MK_CD";

    /**  Manufacturer code name */
    private static final String MK_CD = "mkCd";

    /**  Sort list */
    private static final String[] HEADER_SORT = { DEFAULT_SORT, "M_MK.MK_NM", "M_MK.PULLDOWN_DISP_SEQ", "M_MK.CREATE_TIME", "M_MK.UPDATE_TIME" };

    /**
     * <p>Description : Maker search screen Initial data</p>
     * @author hao.dv
     * @since 2018/08/20
     * @param input {@link Map<String,Object>}
     * @return BaseOutput {@link BaseOutput}
     */
    @Override
    public BaseOutput initSearch(Map<String, Object> input) {
        return loadSearchData();
    }
    
    /**
     * <p>Description : Maker information search</p>
     *
     * @author hao.dv
     * @since 2018/08/20
     * @param searchData Search condition
     * @return BaseOutput {@link BaseOutput}
     */
    @Override
    public BaseOutput doSearch(BaseSearchDTO searchData) {
        // Retrieve search form data
        MNG31020XOutput output = loadSearchData();

        MkSearchDTO mkSearchDTO = (MkSearchDTO) searchData;
        PageUtil.initSearchDTO(mkSearchDTO, HEADER_SORT, DEFAULT_SORT);

        List<MkDTO> makerDTOList = makerMapper.getMkList(mkSearchDTO);
        output.setPage(searchData.getPage());
        //Get number of searches
        int totalRowCount =   this.countMakerList(mkSearchDTO);
        output.setTotalRowCount(totalRowCount);
        // 	Check that all records of the page have been deleted
        if (CollectionUtils.isEmpty(makerDTOList) && totalRowCount > 0) {
            int page = PageUtil.getPage(output.getPage(), totalRowCount);
            PageUtil.updatePage(page, searchData, mkSearchDTO, HEADER_SORT, DEFAULT_SORT);
            makerDTOList = makerMapper.getMkList(mkSearchDTO);
        }
        // Search result setting
        output.setDtoList(makerDTOList);
//        output.setMkNmList(commonService.getMakerNameList());
        return output;
    }
    
    /**
     * <p>Description : Maker information</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param input Search condition
     * @return BaseOuput {@link BaseOutput}
     */
    @SuppressWarnings("unchecked")
    @Override
    public BaseOutput initDetail(Map<String, Object> input) {
        MNG31020XOutput output = new MNG31020XOutput();
        int makerCd = Integer.parseInt((String) input.get(MK_CD));
        // Maker information
        MMkInfoDTO mMkInfoDTO = makerMapper.getMMkInfo( makerCd );
        if (mMkInfoDTO == null) {
            return null;
        }
        //Maker code list
        List<Integer> mkCdList = (List<Integer>) input.get(CommonConstants.RESULT_CD);
        if (mkCdList == null) {
            MkSearchDTO mkSearchDTO = (MkSearchDTO) input.get(CommonConstants.SEARCH_DTO_NAME);
            PageUtil.initSearchDTO(mkSearchDTO, HEADER_SORT, DEFAULT_SORT);
            mkCdList = this.getMkCdList(mkSearchDTO);
        }

        MkDTO mkDTO = commonService.map(mMkInfoDTO, MkDTO.class);
        List<String> itemDivCdList = new ArrayList<String>();
        List<String> itemDivNmList = new ArrayList<String>();

        for (SelectItemDTO selectItem : mMkInfoDTO.getItmDivList()) {
            itemDivCdList.add(selectItem.getCode());
            itemDivNmList.add(selectItem.getName());
        }

        // next code · previous code
        Object nextCd = Utilities.getNextElement(makerCd, mkCdList);
        Object preCd = Utilities.getPrevElement(makerCd, mkCdList);

        // Next code · Previous code setting
        output.setNextObj(nextCd);
        output.setPreviousObj(preCd);
        mkDTO.setItmDivCdNmList(itemDivNmList);
        output.setDetailDTO(mkDTO);
        output.setResultCdList(mkCdList);
        return output;
    }
    

    /**
     * <p>Description :  Initial data acquisition of update Maker screen</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param input Update Information
     * @return BaseOutput {@link BaseOutput}
     */
    @Override
    public BaseOutput initUpdate(Map<String, Object> input) {

        MNG31020XOutput output = getInitUpdateData();
        int makerCd = Integer.parseInt((String) input.get(MK_CD));
        // メーカー情報取得
        MMkInfoDTO mMkInfoDTO =  makerMapper.getMMkInfo( makerCd );

        if (mMkInfoDTO == null) {
            return null;
        }

        MkDTO mkDTO = commonService.map(mMkInfoDTO, MkDTO.class);
        // 言語一覧の値を設定する
        // メーカー商品区分一覧の値を設定する
        List<String> itemDivList = new ArrayList<String>();
        List<String> itmDivCdNmList = new ArrayList<String>();

        for (SelectItemDTO selectItem : mMkInfoDTO.getItmDivList()) {
            itemDivList.add(selectItem.getCode());
            itmDivCdNmList.add(selectItem.getName());
        }

        mkDTO.setItmDivCdList(itemDivList);
        mkDTO.setItmDivCdNmList(itmDivCdNmList);
        output.setDetailDTO(mkDTO);
        return output;

    }
    /**
     * <p>Description : Initial of update screen - data acquisition</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @return MNG31020XOutput {@link MNG31020XOutput}
     */
    private MNG31020XOutput getInitUpdateData() {
        MNG31020XOutput output = new MNG31020XOutput();
//        List<SelectItemDTO> mstItmDivList = commonService.getCodeNameList(UnivMstConstants.S380);
//        output.setItmDivList(mstItmDivList);
        return output;
    }
    /**
     * <p> Description: Maker information </p>
     *
     * @author hao.dv
     * @since 2018/08/22
     * @param mkSearchDTO {@link MkSearchDTO}
     * @return List {@link List<Integer>}
     */
    private List<Integer> getMkCdList(MkSearchDTO mkSearchDTO) {
        return makerMapper.getMkCdList(mkSearchDTO);
    }
    
    /**
     * <p>Description: Check the number of makers</p>
     *
     * @author hao.dv
     * @since 2018/08/21
     * @param mkSearchDTO {@link MkSearchDTO}
     * @return int {@link int}
     */
    private int countMakerList(MkSearchDTO mkSearchDTO) {
        return makerMapper.countMk(mkSearchDTO);
    }
    /**
     * <p>Description :  Initial data acquisition of search screen</p>
     * @author hao.dv
     * @since 2018/08/20
     * @return MNG31020XOutput {@link MNG31020XOutput}
     */
    private MNG31020XOutput loadSearchData() {
        MNG31020XOutput output = new MNG31020XOutput();
        makerMapper.getMakerNameList();
        List<MMkEntity> entities = makerMapper.getAllRecord();
        return output;
    }

	@Override
	public boolean checkExclusive(MMkEntity makerEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int validatePullDown(MMkEntity entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> validateItmDivCd(int mkCd, List<String> itmDivCdList) {
		// TODO Auto-generated method stub
		return null;
	}


}

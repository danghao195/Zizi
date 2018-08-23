/**
 * ファイル名 : MNG31020XController.java
 * 作成者 : hao.dv
 * 作成日時 : 2018/08/20
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
 */

package vn.avg.zizi.controller;

import java.util.HashMap;
import java.util.Map;

//import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.avg.zizi.common.CommonConstants;
//import vn.avg.zizi.common.CommonConstants;
import vn.avg.zizi.common.ScreenConstants;
import vn.avg.zizi.dto.MkDTO;
import vn.avg.zizi.dto.search.MkSearchDTO;
import vn.avg.zizi.forms.MMkForm;
import vn.avg.zizi.forms.SearchMakerForm;
import vn.avg.zizi.output.MNG31020XOutput;
import vn.avg.zizi.service.MNG31020XService;
import vn.avg.zizi.vo.MNG31020XVO;

/**
 * <p> クラス名 : MNG31020XController <p>
 * <p> 説明 : メーカー情報のコントローラ</p>
 *
 * @author hao.dv
 * @since 2018/08/20
 */
@Controller
public class MNG31020XController  extends PaginationController {
    
    /**  Search form name */
    private static final String SEARCH_MAKER_FORM = "searchMakerForm";

    /**  Registration form name */
    private static final String MAKER_FORM = "MMkForm";

    /**  VOName */
    private static final String M_MK_VO = "mMkVO";

    /**  Maker code */
    private static final String MK_CD = "mkCd";

    /**  Manufacturer code list name */
    private static final String LIST_MK_CD = "mkCdList";

    /** CSV name */
    private static final String CSV_SESSION_NAME = "csvSessionName";

    /**  Maker service*/
    @Autowired
    private MNG31020XService mng31020XService;

    /**
     * <p>Explanation: Acquire setting value</p>
     * @author hao.dv
     * @since 2018/08/20
     * @return Map<String, Object> {@link Map<String, Object>}
     */
    private Map<String, Object> getConfig() {
        Map<String, Object> config = new HashMap<String, Object>();
        /// 検索画面ID
        config.put(CommonConstants.SEARCH_SCREEN, ScreenConstants.MNG310201);
        // 紹介画面ID
        config.put(CommonConstants.DETAIL_SCREEN, ScreenConstants.MNG310202);
        // 更新画面ID
        config.put(CommonConstants.UPDATE_SCREEN, ScreenConstants.MNG310203);
        // 登録画面ID
        config.put(CommonConstants.INSERT_SCREEN, ScreenConstants.MNG310204);
        // CSV登録画面ID
        config.put(CommonConstants.CSV_SCREEN, ScreenConstants.MNG310205);
        // 検索フォーム名称
        config.put(CommonConstants.SEARCH_FORM, SEARCH_MAKER_FORM);
        // VO名
        config.put(CommonConstants.VO, M_MK_VO);
        // 検索結果名商
        config.put(CommonConstants.RESULT_CD, LIST_MK_CD);
        // 更新・登録フォーム名
        config.put(CommonConstants.UPDATE_FORM, MAKER_FORM);
        // 検索クラス
        config.put(CommonConstants.SEARCH_CLAZZ, SearchMakerForm.class);
        // 更新クラス
        config.put(CommonConstants.UPDATE_FOM_CLAZZ, MMkForm.class);
        // OUTPUTクラス
        config.put(CommonConstants.OUTPUT_CLAZZ, MNG31020XOutput.class);
        config.put(CommonConstants.VO_CLAZZ, MNG31020XVO.class);
//        // 検索結果DTOクラス
        config.put(CommonConstants.DTO_SEARCH_CLAZZ, MkSearchDTO.class);
//        // 紹介DTOクラス
        config.put(CommonConstants.DTO_CLAZZ, MkDTO.class);
        // CSV情報保存明所
        config.put(CommonConstants.CSV_SESSION_NAME, CSV_SESSION_NAME);
        return config;
    }

    
    /**
     * <p> Explanation: Initial data acquisition</p>
     *
     * @author hao.dv
     * @since 2018/08/20
     * @param isSearch {@link boolean}
     * @return ModelAndView {@link ModelAndView}
     */
    @GetMapping(ScreenConstants.MNG310201)
    public ModelAndView initSearch(@RequestParam(value = "isSearch", required = false, defaultValue = "false") boolean isSearch) {
        ModelAndView modelAndView = super.initSearch(isSearch, mng31020XService, getConfig());
        return modelAndView;
    }

    /**
     * <p> Explanation: Manufacturer information information search</p>
     *
     * @author hao.dv
     * @since 2018/08/20
     * @param searchMakerForm {@link SearchMakerForm}
     * @param result {@link BindingResult}
     * @return ModelAndView {@link ModelAndView}
     */
    @PostMapping(ScreenConstants.MNG310201)
    public ModelAndView doSearch(SearchMakerForm searchMakerForm, BindingResult result) {
        // Clear the grade session list
        removeObjectFromSession(LIST_MK_CD);
        return super.doSearch(searchMakerForm, result, mng31020XService, getConfig());
    }
    
    /**
     * <p> Description: Maker Information</p>
     *
     * @author hao.dv
     * @since 2018/08/22
     * @param mkCd 
     * @return ModelAndView {@link ModelAndView}
     */
    @GetMapping(ScreenConstants.MNG310202)
    public ModelAndView initDetail(@RequestParam("mkCd") String mkCd) {
        Map<String, Object> initDetailInfo = new HashMap<String, Object>();
        initDetailInfo.put(MK_CD, mkCd);
        return super.intDetail(initDetailInfo, mng31020XService, getConfig());
    }
    
    /**
     * <p> 説明 : メーカー情報更新画面の初期データ取得</p>
     *
     * @author duc.bv
     * @since 2018/5/31
     * @param mkCd メーカーコード
     * @return ModelAndView {@link ModelAndView}
     */
    @GetMapping(ScreenConstants.MNG310203)
    public ModelAndView initUpdate(@RequestParam(MK_CD) String mkCd) {
        Map<String, Object> initDetailInfo = new HashMap<String, Object>();
        initDetailInfo.put(MK_CD, mkCd);
        return super.initUpdate(initDetailInfo, mng31020XService, getConfig());
    }

    /**
     * <p>Description: Obtain screen title</p>
     *
     * @author hao.dv
     * @since 2018/8/20
     * @return String Title Contents
     */
    public String getTitle() {
        return "MNG3102XX.header.title";
    }

    /**
     * <p>Description: Get menu list</p>
     *
     * @author hao.dv
     * @since 2018/08/20
     * @return String[] SubMenu list
     */
    public String[] getTabMenu() {
        return new String[] { ScreenConstants.MNG310201, ScreenConstants.MNG310204, ScreenConstants.MNG310205 };
    }
}

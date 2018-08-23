package vn.avg.zizi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.MappingException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import vn.avg.zizi.common.CommonConstants;
import vn.avg.zizi.common.MergeFormHandler;
import vn.avg.zizi.dto.MasterBaseDTO;
import vn.avg.zizi.dto.search.BaseSearchDTO;
import vn.avg.zizi.forms.AppForm;
import vn.avg.zizi.output.BaseOutput;
import vn.avg.zizi.service.MasterBaseService;
import vn.avg.zizi.utils.CheckUtil;
import vn.avg.zizi.utils.Utilities;
import vn.avg.zizi.vo.MasterBaseVO;

/**
 * <p>File name : Pagination controller</p>
 * <p>Description    : FIXME TableController</p>
 * @author : hao.dv
 * @since 2018/08/21
 */
public abstract class PaginationController extends AppController {
    /** PAGE_NUM_ON_SCREEN = 5 */
    private static final int PAGE_NUM_ON_SCREEN = 10;

    /** PAGE = "page" */
    private static final String PAGE = "page";

    /** CURENT_PAGE = "currentPage" */
    private static final String TABLE_CURENT_PAGE = "currentPage";

    /** TOTAL_ROW_COUNT = "totalRowCount" */
    private static final String TOTAL_RECORD = "totalRecord";

    /** DISPLAY_DATA = "displayData" */
    private static final String DISPLAY_DATA = "displayData";

    /** START_ROW = "startRow"*/
    private static final String START_ROW = "startRow";

    /** TOTAL_PAGE = "totalPage" */
    protected static final String TOTAL_PAGE = "totalPage";

    /**
     * FIXME Confirm lại xem có cần thiết tạo ra method này không?
     * <p>Description : getFirstElement</p> 
     * @author : hao.dv
     * @since  : 2018/08/21
     * @param page FIXME page id
     * @return FIXME start row
     */
    protected int getFirstElement(int page) {
        int currentRow = page;
        if (page < 1) {
            currentRow = 1;
        }

        int startRow = (currentRow - 1) * CommonConstants.GRID_ROW_COUNT;
        return startRow;
    }

    /**
     * 
     * <p>Description : setDisplayDataOnPage</p> 
     * @author : hao.dv
     * @since  : 2018/08/21
     * @param page int
     * @param totalPage int
     * @return displayData List<Integer>
     */
    protected List<Integer> setDisplayDataOnPage(int page, int totalPage) {
        int last = 0;
        int isOdd = PAGE_NUM_ON_SCREEN % 2;
        if (page > (PAGE_NUM_ON_SCREEN / 2 + isOdd)) {
            last = Math.min(page + (PAGE_NUM_ON_SCREEN / 2), totalPage);
        } else {
            last = Math.min(PAGE_NUM_ON_SCREEN, totalPage);
        }
        int first = Math.max(1, last - (PAGE_NUM_ON_SCREEN - 1));
        List<Integer> displayData = new ArrayList<Integer>();
        for (int i = first; i <= last; i++) {
            displayData.add(i);
        }
        return displayData;
    }

    /**
    *
    * <p>Description : getPrevElement</p>
    * @author : hao.dv
    * @since  : 2018/08/21
    * @param mdCd Integer
    * @param list List<Integer>
    * @return Integer
    */
    protected int getPrevElement(int mdCd, List<Integer> list) {
        int idPrev = -1;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == mdCd) {
                    idPrev = (i == 0) ? -1 : list.get(i - 1);
                    break;
                }
            }
        }
        return idPrev;
    }

    /**
     * 
     * <p>Description : getPrevElement</p> 
     * @author : hao.dv
     * @since 2018/08/21
     * @param mdCd mdCd
     * @param list list
     * @return String
     */
    protected String getPrevElement(String mdCd, List<String> list) {
        String idPrev = "";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(mdCd)) {
                    idPrev = (i == 0) ? "" : list.get(i - 1);
                    break;
                }
            }
        }
        return idPrev;
    }

    /**
     * <p>Description : get prev obj detail</p> 
     * @author : hao.dv
     * @since  : 2018/08/21
     * @param item HashMap<String, Object>
     * @param itmList List<HashMap<String, Object>>
     * @return HashMap<String, Object>
     */
    protected HashMap<String, Object> getPrevElementByHashMap(HashMap<String, Object> item, List<HashMap<String, Object>> itmList) {
        int pos = itmList.indexOf(item);
        if (pos > 0) {
            return itmList.get(pos - 1);
        }
        return null;
    }

    /**
     * <p>Description : get next obj detail</p> 
     * @author : hao.dv
     * @since  : 2018/08/21
     * @param item HashMap<String, Object>
     * @param itmList List<HashMap<String, Object>>
     * @return HashMap<String, Object>
     */
    protected HashMap<String, Object> getNextElementByHashMap(HashMap<String, Object> item, List<HashMap<String, Object>> itmList) {
        int pos = itmList.indexOf(item);
        if (pos < itmList.size() - 1 && pos != -1) {
            return itmList.get(pos + 1);
        }
        return null;
    }

    /**
    *
    * <p>Description : getNextElement</p>
    * @author : hao.dv
    * @since 2018/08/21
    * @param mdCd Integer
    * @param list List<Integer>
    * @return Integer
    */
    protected int getNextElement(int mdCd, List<Integer> list) {
        int idNext = -1;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == mdCd) {
                    idNext = (i == (list.size() - 1)) ? -1 : list.get(i + 1);
                    break;
                }
            }
        }

        return idNext;
    }

    /**
     * 
     * <p>Description : getNextElement</p> 
     * @author : hao.dv
     * @since 2018/08/21
     * @param cd code
     * @param list list
     * @return String
     */
    protected String getNextElement(String cd, List<String> list) {
        String idNext = "";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(cd)) {
                    idNext = (i == (list.size() - 1)) ? "" : list.get(i + 1);
                    break;
                }
            }
        }

        return idNext;
    }

    /**
     * 
     * <p>Description : Binding data pagination</p> 
     * @author : hao.dv
     * @since 2018/08/21
     * @param modelAndView ModelAndView
     * @param page int
     * @param totalRecord int
     */
    protected void setPaginationData(ModelAndView modelAndView, int page, int totalRecord) {
        int offset = getOffsetByPage(page);
        int totalPage = getTotalPage(totalRecord);

        modelAndView.addObject(TOTAL_RECORD, totalRecord);
        modelAndView.addObject(DISPLAY_DATA, setDisplayDataOnPage(page, totalPage));
        modelAndView.addObject(START_ROW, offset);
        modelAndView.addObject(PAGE, 1);
        modelAndView.addObject(TABLE_CURENT_PAGE, page == 0 ? 1 : page);
        modelAndView.addObject(TOTAL_PAGE, totalPage);
    }

    /**
     * 
     * <p>Description : Binding data pagination with one page only</p> 
     * @author : hao.dv
     * @since 2018/08/21
     * @param modelAndView ModelAndView
     * @param page int
     * @param totalRecord int
     */
    protected void setPaginationData(ModelAndView modelAndView, int totalRecord) {
        int offset = getOffsetByPage(1);

        modelAndView.addObject(TOTAL_RECORD, totalRecord);
        modelAndView.addObject(START_ROW, offset);
        modelAndView.addObject(PAGE, 1);
        modelAndView.addObject(TABLE_CURENT_PAGE, 1);
        modelAndView.addObject(TOTAL_PAGE, 1);
    }

    /**
     * <p>Description : FIXME get curent record by page index</p> 
     * @author : hao.dv
     * @since 2018/08/21
     * @param page page number
     * @return curent record
     */
    protected int getOffsetByPage(Integer page) {
        if (page == null || page <= 1) {
            return 0;
        } else {
            return (page - 1) * CommonConstants.GRID_ROW_COUNT;
        }
    }

    /**
     * <p>Description : FIXME get total page by total record</p> 
     * @author : hao.dv
     * @since 2018/02/10
     * @param totalRecord total of record
     * @return total page
     */
    private int getTotalPage(int totalRecord) {
        return (int) Math.ceil((double) totalRecord / CommonConstants.GRID_ROW_COUNT);
    }

    /**
     * <p>Description : FIXME copy data when paging</p> 
     * @author : hao.dv
     * @since 2018/08/21
     * @param formSource : AppForm Source
     * @param formDestination : AppForm Destination
     */
    protected void copyPaginationSortData(AppForm formSource, AppForm formDestination) {
        int page = formSource.getPage();
        String sortType = formSource.getSortType();
        int sortIndex = formSource.getSortIndex();

        Utilities.map(formDestination, formSource);

        formSource.setPage(page);
        formSource.setSortType(sortType);
        formSource.setSortIndex(sortIndex);
    }

    /**
     * mng3104Service
     */
    protected ModelAndView initSearch(boolean isSearch, MasterBaseService service, Map<String, Object> configData) {
        String viewName = (String) configData.get(CommonConstants.SEARCH_SCREEN);
        ModelAndView modelAndView = createView(configData, viewName);
        // get VO class
        Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
        // get form name
        String formName = (String) configData.get(CommonConstants.SEARCH_FORM);
        // Acquire search condition
        AppForm appForm = (AppForm) getObjectFromSession(session, formName);
        // VO Name
        String voName = (String) configData.get(CommonConstants.VO);
        // Object voOBJ
        Object voObj;
        // Search VO
        MasterBaseVO searchVO = null;
        // Save to session
        addObjectToSession(CommonConstants.CURRENT_TAB, CommonConstants.ONE_NUMBER);
        // edit tab active
        if (configData.get(CommonConstants.CURRENT_TAB) != null) {
            addObjectToSession(CommonConstants.CURRENT_TAB, configData.get(CommonConstants.CURRENT_TAB));
        }
        try {
            // Performing search check
            if (appForm != null && isSearch) {
                // Perform search
                return doSearch(appForm, null, service, configData);
            } else {
                voObj = voClazz.newInstance();
                searchVO = (MasterBaseVO) voObj;
                // get form class
                Class<?> formClazz = (Class<?>) configData.get(CommonConstants.SEARCH_CLAZZ);
                // Form Initial
                if (appForm == null) {
                    Object object = formClazz.newInstance();
                    appForm = (AppForm) object;
                }
                // Initial data acquisition
                BaseOutput output = null;
                if (configData.get(CommonConstants.USE_CONFIG) != null) {
                    output = service.initSearch(configData);
                } else {
                    output = service.initSearch(null);
                }
                // Convert Search VO
                if (output != null) {
                    searchVO = (MasterBaseVO) modelMapper.map(output, voClazz);
                }
            }
        } catch (Exception ex) {
            logger.error(Arrays.asList(ex.getStackTrace()).stream().map(Objects::toString).collect(Collectors.joining("\n")));
        }
        setDataInitSearch(modelAndView, appForm, searchVO, formName, voName);
        return modelAndView;
    }

    /**
     * 
     * <p>Description : Search processing</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param searchForm
     * @param result BindingResult
     * @param mng31040XService Corresponding service
     * @param configData Setting of applicable Controller
     * @return ModelAndView
     */
    protected ModelAndView doSearch(AppForm searchForm, BindingResult result, MasterBaseService mng31040XService, Map<String, Object> configData) {
        return doSearch(searchForm, result, mng31040XService, configData, false);
    }

    /**
     * 
     * <p>Description: Search processing</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param searchForm
     * @param result BindingResult
     * @param service Corresponding service
     * @param configData Setting of applicable Controller
     * @param withValidate Check flag
     * @return ModelAndView
     */
    protected ModelAndView doSearch(AppForm searchForm, BindingResult result, MasterBaseService service, Map<String, Object> configData, boolean withValidate) {
        String viewName = (String) configData.get(CommonConstants.SEARCH_SCREEN);
        ModelAndView modelAndView = createView(configData, viewName);
        try {
            searchForm = (AppForm) Utilities.trim(searchForm);
            searchForm.setScreenName(viewName);
            if (withValidate) {
                if (result.hasErrors()) {
                    reloadSearchErrorData(modelAndView, service, configData, searchForm);
                    return modelAndView;
                }
            }

            // get form name
            String formName = (String) configData.get(CommonConstants.SEARCH_FORM);
            // Click check of page sort
            if (searchForm.isPaging()) {
                // Acquire search condition of session
                AppForm gradeSearch = (AppForm) getObjectFromSession(session, formName);
                copyPaginationSortData(searchForm, gradeSearch);
            }
            Class<?> searchDTO = (Class<?>) configData.get(CommonConstants.DTO_SEARCH_CLAZZ);

            BaseSearchDTO searchConditionDTO = (BaseSearchDTO) searchDTO.newInstance();
            // Convert form to DTO
            modelMapper.map(searchForm, searchConditionDTO);
            // Search performed
            BaseOutput baseOuput = service.doSearch(searchConditionDTO);
            searchConditionDTO.setPage(baseOuput.getPage());
            // Save search condition in session
            if (searchConditionDTO.getLimit() > 0) {
                setPaginationData(modelAndView, baseOuput.getPage(), baseOuput.getTotalRowCount());
            } else {
                setPaginationData(modelAndView, baseOuput.getTotalRowCount());
            }
            // Save search condition in session
            addObjectToSession(formName, searchForm);
            String voName = (String) configData.get(CommonConstants.VO);
            Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
            MasterBaseVO baseVO = (MasterBaseVO) modelMapper.map(baseOuput, voClazz);
            baseVO.initHeader();
            baseVO.setHeaderSize(Utilities.getHeaderSize(baseVO.getHeaders()));
            // Inject data into form
            setDataInitSearch(modelAndView, searchForm, baseVO, formName, voName);
        } catch (Exception e) {
            logger.error(Arrays.asList(e.getStackTrace()).stream().map(Objects::toString).collect(Collectors.joining("\n")));
            reloadSearchErrorData(modelAndView, service, configData, searchForm);
        }
        // Response data setting
        return modelAndView;
    }

    private void setDataInitSearch(ModelAndView modelAndView, AppForm appForm, MasterBaseVO masterBaseVO, String formName, String voName) {
        // Response data setting
        modelAndView.addObject(formName, appForm);
        modelAndView.addObject(voName, masterBaseVO);
    }

    protected ModelAndView intDetail(Map<String, Object> initInfo, MasterBaseService service, Map<String, Object> configData) {
        return intDetail(initInfo, service, configData, false);
    }

    /**
     * 
     * <p>Description : Search processing</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param searchForm
     * @param result BindingResult
     * @param service Corresponding service
     * @param configData Setting of applicable Controller
     * @param withValidate Check flag
     * @return ModelAndView
     */
    protected ModelAndView initDetail(AppForm searchForm, Map<String, Object> initInfo, BindingResult result, MasterBaseService service,
            Map<String, Object> configData, boolean ignoreSearchData) {
        // init detail screen
        String viewName = (String) configData.get(CommonConstants.DETAIL_SCREEN);
        removeObjectFromSession(CommonConstants.CURRENT_TAB);
        // Create ModelAndView
        ModelAndView modelAndView = createView(configData, viewName);
        try {
            String searchResultCdKey = (String) configData.get(CommonConstants.RESULT_CD);
            Object resultResult = getObjectFromSession(searchResultCdKey);
            initInfo.put(CommonConstants.RESULT_CD, resultResult);
            // get form class
            Class<?> formClazz = (Class<?>) configData.get(CommonConstants.SEARCH_CLAZZ);
            // get VO class
            Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
            // get form name
            String formName = (String) configData.get(CommonConstants.SEARCH_FORM);
            // Acquire search condition
            AppForm appForm = resultResult == null ? (AppForm) getObjectFromSession(session, formName) : null;
            // Search condition check
            appForm = appForm == null ? (AppForm) formClazz.newInstance() : appForm;
            Class<?> dtoClazz = (Class<?>) configData.get(CommonConstants.DTO_SEARCH_CLAZZ);
            // Convert to DTO
            if (!ignoreSearchData) {
                BaseSearchDTO searchData = (BaseSearchDTO) modelMapper.map(appForm, dtoClazz);
                initInfo.put(CommonConstants.SEARCH_DTO_NAME, searchData);
            }

            String resultCd = (String) configData.get(CommonConstants.RESULT_CD);
            initInfo.put(CommonConstants.RESULT_CD, getObjectFromSession(resultCd));

            // init search in detail screen
            String searchFormInDetailSceenName = (String) configData.get(CommonConstants.SEARCH_FORM_IN_DETAIL_SCREEN);
            BaseSearchDTO searchConditionInDetailScreenDTO = null;
            if (CheckUtil.isNotEmpty(searchFormInDetailSceenName)) {

                Class<?> searchInDetailScreenDTO = (Class<?>) configData.get(CommonConstants.DTO_SEARCH_CLAZZ_IN_DETAIL_SCREEN);
                searchConditionInDetailScreenDTO = (BaseSearchDTO) searchInDetailScreenDTO.newInstance();
                // Convert form to DTO
                modelMapper.map(searchForm, searchConditionInDetailScreenDTO);
                initInfo.put(CommonConstants.SEARCH_DTO_IN_DETAIL_SCREEN, searchConditionInDetailScreenDTO);
            }

            BaseOutput baseOuput = service.initDetail(initInfo);

            // init search in detail screen
            if (CheckUtil.isNotEmpty(searchFormInDetailSceenName) && baseOuput.getViewScreen() != null) {
                // Search performed
                searchConditionInDetailScreenDTO.setPage(baseOuput.getViewScreen().getPage());
                // Page setting
                if (searchConditionInDetailScreenDTO.getLimit() > 0) {
                    setPaginationData(modelAndView, baseOuput.getViewScreen().getPage(), baseOuput.getViewScreen().getTotalRowCount());
                } else {
                    setPaginationData(modelAndView, baseOuput.getViewScreen().getTotalRowCount());
                }
                // Save search condition in session
                addObjectToSession(searchFormInDetailSceenName, searchForm);

                // Display data setting of initial data
                // loadUpdateInfo(modelAndView, baseOuput, configData, appForm);
                // MasterBaseVO baseVO = (MasterBaseVO) modelMapper.map(baseOuput, voClazz);
                // baseVO.initHeaderInViewScreen(searchFormInDetailSceenName);
                // baseVO.setHeaderSizeInViewScreen(Utilities.getHeaderSize(baseVO.getHeadersInViewScreen()));
            }

            modelAndView.addObject(CommonConstants.ACTION, CommonConstants.DETAIL_ACTION);
            if (baseOuput == null || baseOuput.getDetailDTO() == null) {
                return modelAndView;
            }

            // Acquire screen information
            addObjectToSession(resultCd, baseOuput.getResultCdList());
            MasterBaseVO outPutVO = (MasterBaseVO) modelMapper.map(baseOuput, voClazz);
            // Response data setting
            modelAndView.addObject((String) configData.get(CommonConstants.VO), outPutVO);
        } catch (Exception e) {
            logger.error(Arrays.asList(e.getStackTrace()).stream().map(Objects::toString).collect(Collectors.joining("\n")));
        }
        return modelAndView;
    }

    /**
     * 
     * <p>Description :Introduction screen</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param initInfo introduction data
     * @param service　Corresponding service
     * @param configData　Setting of applicable Controller
     * @return modelAndView Screen representation
     */
    protected ModelAndView intDetail(Map<String, Object> initInfo, MasterBaseService service, Map<String, Object> configData, boolean ignoreSearchData) {
        // init detail screen
        String viewName = (String) configData.get(CommonConstants.DETAIL_SCREEN);
        removeObjectFromSession(CommonConstants.CURRENT_TAB);
        //Create ModelAndView
        ModelAndView modelAndView = createView(configData, viewName);
        try {
            String searchResultCdKey = (String) configData.get(CommonConstants.RESULT_CD);
            Object resultResult = getObjectFromSession(searchResultCdKey);
            initInfo.put(CommonConstants.RESULT_CD, resultResult);
            // get form class
            Class<?> formClazz = (Class<?>) configData.get(CommonConstants.SEARCH_CLAZZ);
            // get VO class
            Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
            // get form name
            String formName = (String) configData.get(CommonConstants.SEARCH_FORM);
            // Acquire search condition
            AppForm appForm = resultResult == null ? (AppForm) getObjectFromSession(session, formName) : null;
            // Search condition check
            appForm = appForm == null ? (AppForm) formClazz.newInstance() : appForm;
            Class<?> dtoClazz = (Class<?>) configData.get(CommonConstants.DTO_SEARCH_CLAZZ);
            // Convert to DTO
            if (!ignoreSearchData) {
                BaseSearchDTO searchData = (BaseSearchDTO) modelMapper.map(appForm, dtoClazz);
                initInfo.put(CommonConstants.SEARCH_DTO_NAME, searchData);
            }

            String resultCd = (String) configData.get(CommonConstants.RESULT_CD);
            initInfo.put(CommonConstants.RESULT_CD, getObjectFromSession(resultCd));
            BaseOutput baseOuput = service.initDetail(initInfo);
            modelAndView.addObject(CommonConstants.ACTION, CommonConstants.DETAIL_ACTION);
            if (baseOuput == null || baseOuput.getDetailDTO() == null) {
                return modelAndView;
            }
            // Acquisition of grade information
            addObjectToSession(resultCd, baseOuput.getResultCdList());
            MasterBaseVO outPutVO = (MasterBaseVO) modelMapper.map(baseOuput, voClazz);
            // Response data setting
            modelAndView.addObject((String) configData.get(CommonConstants.VO), outPutVO);
        } catch (Exception e) {
            logger.error(e.getCause());
            logger.error(Arrays.asList(e.getStackTrace()).stream().map(Objects::toString).collect(Collectors.joining("\n")));
        }
        return modelAndView;
    }

    /**
     * 
     * <p>Description : update</p> 
     * @author : hao.dv
     * @since : 2018/08/23
     * @param initInfo
     * @param service
     * @param configData
     * @return FIXME Mô tả ý nghĩa param/return
     */
    protected ModelAndView initUpdate(Map<String, Object> initInfo, MasterBaseService service, Map<String, Object> configData) {
        return initUpdate(initInfo, service, configData, null, null);
    }
    protected ModelAndView initUpdate(Map<String, Object> initInfo, MasterBaseService service, Map<String, Object> configData, AppForm appForm,
            MergeFormHandler mergeFormHandler) {
        // init detail screen
        String viewName = (String) configData.get(CommonConstants.UPDATE_SCREEN);
        // Create ModelAndView
        ModelAndView modelAndView = createView(configData, viewName);
        modelAndView.addObject(CommonConstants.SCREEN_ACTION, CommonConstants.REGISTER_ACTION);
        try {
            // screen information
            BaseOutput output = service.initUpdate(initInfo);
            if (output == null || output.getDetailDTO() == null) {
//                modelAndView.addObject(CommonConstants.MESSAGE, new MessageVO(MessageType.ERROR.toString(), MessageConstants.MSG_468));
                return modelAndView;
            }
            // Display data setting of initial data
            loadUpdateInfo(modelAndView, output, configData, appForm, mergeFormHandler);
            modelAndView.addObject(CommonConstants.ACTION, CommonConstants.UPDATE_ACTION);
            removeObjectFromSession(CommonConstants.CURRENT_TAB);
        } catch (Exception e) {
            logger.error(Arrays.asList(e.getStackTrace()).stream().map(Objects::toString).collect(Collectors.joining("\n")));
            return modelAndView;
        }
        return modelAndView;
    }
    
    /**
     * 
     * <p>Description : initial table display data of update / registration screen</p> 
     * @author : hao.dv
     * @since : 2018/08/22
     * @param modelAndView ModelAndView
     * @param gradeOutput GradeOutput
     */
    private void loadUpdateInfo(ModelAndView modelAndView, BaseOutput output, Map<String, Object> configData, AppForm appForm,
            MergeFormHandler mergeFormHandler) {
        // Create screen form
        if (appForm == null) {
            Class<?> form = (Class<?>) configData.get(CommonConstants.UPDATE_FOM_CLAZZ);
            appForm = (AppForm) modelMapper.map(output.getDetailDTO(), form);
        } else if (mergeFormHandler != null) {
            mergeFormHandler.exec(output, appForm);
        }
        //Create screen form
        Class<?> vo = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);

        MasterBaseVO resultVO = (MasterBaseVO) modelMapper.map(output, vo);
        String VO = (String) configData.get(CommonConstants.VO);
        modelAndView.addObject(VO, resultVO);
        modelAndView.addObject((String) configData.get(CommonConstants.UPDATE_FORM), appForm);
    }
    
    
    
    /**
     * 
     * <p>Description : Initial add screen with data</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param initInfo Intitial inforomation
     * @param service {@link MasterBaseService}
     * @param configData Configuration cast and routing
     * @return {@link ModelAndView} Mô nghĩa param/return
     */
    protected ModelAndView initAdd(Map<String, Object> initInfo, MasterBaseService service, Map<String, Object> configData) {
        return initAdd(initInfo, service, configData, null);
    }

    /**
     * <p>Description : FIXME Mô tả ý nghia method</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param initInfo Intitial inforomation
     * @param service {@link MasterBaseService}
     * @param configData Configuration cast and routing
     * @return {@link ModelAndView} Mô nghĩa param/return
     */
    protected ModelAndView initAdd(Map<String, Object> initInfo, MasterBaseService service, Map<String, Object> configData, AppForm appForm) {
        // init detail screen
        String viewName = (String) configData.get(CommonConstants.INSERT_SCREEN);
        ModelAndView modelAndView = createView(configData, viewName);
        modelAndView.addObject(CommonConstants.SCREEN_ACTION, CommonConstants.REGISTER_ACTION);
        addObjectToSession(CommonConstants.CURRENT_TAB, CommonConstants.TWO_NUMBER);
        try {
            BaseOutput output = service.initInsert(initInfo);

            // get VO class
            Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
            MasterBaseVO outputVO = (MasterBaseVO) modelMapper.map(output, voClazz);

            // Response data setting+
            modelAndView.addObject((String) configData.get(CommonConstants.VO), outputVO);

            // Create screen form
            if (appForm == null) {
                Class<?> form = (Class<?>) configData.get(CommonConstants.UPDATE_FOM_CLAZZ);
                appForm = (AppForm) form.newInstance();
            }

            String VO = (String) configData.get(CommonConstants.VO);
            modelAndView.addObject(VO, outputVO);

            modelAndView.addObject((String) configData.get(CommonConstants.UPDATE_FORM), appForm);

        } catch (Exception e) {
            logger.error(Arrays.asList(e.getStackTrace()).stream().map(Objects::toString).collect(Collectors.joining("\n")));
            return modelAndView;
        }
        return modelAndView;
    }

    /**
     * 
     * <p>Description : FIXME Mô tả ý nghia method</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param appForm
     * @param result
     * @param service
     * @param configData
     * @return FIXME Mô nghĩa param/return
     */
    protected ModelAndView doAdd(AppForm appForm, BindingResult result, MasterBaseService service, Map<String, Object> configData) {
        // init detail screen
        String viewName = (String) configData.get(CommonConstants.INSERT_SCREEN);
        ModelAndView modelAndView = createView(configData, viewName);

        appForm.setScreenName(viewName);
        // Form check
        modelAndView.addObject(CommonConstants.SCREEN_ACTION, CommonConstants.REGISTER_ACTION);
        // Mapping data
        Class<?> dtoClazz = (Class<?>) configData.get(CommonConstants.DTO_CLAZZ);
        MasterBaseDTO dto = null;
        try {
            dto = (MasterBaseDTO) modelMapper.map(appForm, dtoClazz);
        } catch (MappingException me) {
            logger.warn("Mapping false {}", me.getMessage());
        }

        if (result.hasErrors()) {
            // Acquire error data
            reloadInsertErrorData(modelAndView, service, configData, appForm, dto);
            // Screen layout back
            return modelAndView;
        }

        try {
            Map<String, Object> updateInfo = new HashMap<String, Object>();

            updateInfo.put(CommonConstants.DTO_NAME, dto);

            // Update screen
            BaseOutput output = service.doInsert(updateInfo);

            // get VO class
            Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
            MasterBaseVO outputVO = (MasterBaseVO) modelMapper.map(output, voClazz);

            // Response data setting
            modelAndView.addObject((String) configData.get(CommonConstants.VO), outputVO);

            modelAndView.addObject(CommonConstants.ACTION, CommonConstants.REGISTER_ACTION);
            // Viewer name setting
            setViewName(modelAndView, (String) configData.get(CommonConstants.DETAIL_SCREEN));

        } catch (RollbackException ex) {
            logger.error(Arrays.asList(ex.getStackTrace()).stream().map(Objects::toString).collect(Collectors.joining("\n")));
            modelAndView.addObject(CommonConstants.ACTION, CommonConstants.ERROR_ACTION);
            // Acquire error data
            // Add message
            reloadInsertErrorData(modelAndView, service, configData, appForm, dto);
            return modelAndView;
        } catch (Exception e) {
            logger.error(Arrays.asList(e.getStackTrace()).stream().map(Objects::toString).collect(Collectors.joining("\n")));
            modelAndView.addObject(CommonConstants.ACTION, CommonConstants.ERROR_ACTION);
            // Acquire error data
            reloadInsertErrorData(modelAndView, service, configData, appForm, dto);
            return modelAndView;
        }
        return modelAndView;
    }

    /**
     * 
     * <p>Description : Acquire initial table display data of update / registration screen</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param modelAndView ModelAndView
     */
    private void reloadInsertErrorData(ModelAndView modelAndView, MasterBaseService service, Map<String, Object> configData, AppForm appForm,
            MasterBaseDTO dto) {
        configData.put(CommonConstants.APP_TRANSFER_DTO, dto);
        // Acquire screen information*/
        modelAndView.addObject((String) configData.get(CommonConstants.UPDATE_FORM), appForm);
        BaseOutput output = service.doInsertError(configData);
        if (output != null) {
            // get VO class
            Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
            MasterBaseVO outputVO = (MasterBaseVO) modelMapper.map(output, voClazz);
            // Response data setting+
            modelAndView.addObject((String) configData.get(CommonConstants.VO), outputVO);
            String vo = (String) configData.get(CommonConstants.VO);
            modelAndView.addObject(vo, outputVO);
        }
    }

    /**
     * 
     * <p>Description : reloadUpdateErrorData</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param modelAndView ModelAndView
     */
    private void reloadUpdateErrorData(ModelAndView modelAndView, MasterBaseService service, Map<String, Object> configData, AppForm appForm,
            MasterBaseDTO dto) {
        configData.put(CommonConstants.APP_TRANSFER_DTO, dto);
        modelAndView.addObject((String) configData.get(CommonConstants.UPDATE_FORM), appForm);
        // Acquire screen information*/
        BaseOutput output = service.doUpdateError(configData);
        if (output != null) {
            // get VO class
            Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
            MasterBaseVO outputVO = (MasterBaseVO) modelMapper.map(output, voClazz);
            // Response data setting+
            modelAndView.addObject((String) configData.get(CommonConstants.VO), outputVO);
            String vo = (String) configData.get(CommonConstants.VO);
            modelAndView.addObject(vo, outputVO);
        }
    }

    /**
     * 
     * <p>Description : reloadUpdateConfirmErrorData</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param modelAndView ModelAndView
     */
    private void reloadUpdateConfirmErrorData(ModelAndView modelAndView, MasterBaseService service, Map<String, Object> configData, AppForm appForm) {
        modelAndView.addObject((String) configData.get(CommonConstants.UPDATE_FORM), appForm);
        // Screen information*/
        BaseOutput output = service.doUpdateConfirmError(createDtoWhenUpdateConfirmError(appForm));
        if (output != null) {
            // get VO class
            Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
            MasterBaseVO outputVO = (MasterBaseVO) modelMapper.map(output, voClazz);
            // Response data setting+
            modelAndView.addObject((String) configData.get(CommonConstants.VO), outputVO);
            String vo = (String) configData.get(CommonConstants.VO);
            modelAndView.addObject(vo, outputVO);
        }
    }

    /**
     * 
     * <p>Description : createDtoWhenUpdateConfirmError</p> 
     * @author : hao.dv
     * @since : 2018/05/41
     * @return Map<String, Object>
     */
    protected Map<String, Object> createDtoWhenUpdateConfirmError(AppForm appForm) {
        return new HashMap<String, Object>();
    }

    /**
     * 
     * <p>Description : reloadReportErrorData</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param modelAndView ModelAndView
     */
    private void reloadReportErrorData(ModelAndView modelAndView, MasterBaseService service, Map<String, Object> configData, AppForm appForm, MasterBaseDTO dto,
            Map<String, Object> updateInfo) {
        modelAndView.addObject((String) configData.get(CommonConstants.UPDATE_FORM), appForm);
        // Screen information*/
        BaseOutput output = service.doReportError(updateInfo);
        if (output != null) {
            // get VO class
            Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
            MasterBaseVO outputVO = (MasterBaseVO) modelMapper.map(output, voClazz);
            // Response data setting+
            modelAndView.addObject((String) configData.get(CommonConstants.VO), outputVO);
            String vo = (String) configData.get(CommonConstants.VO);
            modelAndView.addObject(vo, outputVO);
        }
    }

    /**
     * 
     * <p>Description : reloadDeleteErrorData</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param modelAndView ModelAndView
     */
    private void reloadDeleteErrorData(ModelAndView modelAndView, MasterBaseService service, Map<String, Object> configData, AppForm appForm,
            MasterBaseDTO dto) {
        configData.put(CommonConstants.APP_TRANSFER_DTO, dto);
        // modelAndView.addObject((String) configData.get(CommonConstants.DELETE_FORM), appForm);
        // Screen information*/
        BaseOutput output = service.doDeleteError(configData);
        if (output != null) {
            // get VO class
            Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
            MasterBaseVO outputVO = (MasterBaseVO) modelMapper.map(output, voClazz);
            if (output.getDetailDTO() != null) {
                modelMapper.map(output.getDetailDTO(), appForm);
                modelAndView.addObject((String) configData.get(CommonConstants.DELETE_FORM), appForm);
            }
            // レスポンスデータ設定+
            modelAndView.addObject((String) configData.get(CommonConstants.VO), outputVO);
            String vo = (String) configData.get(CommonConstants.VO);
            modelAndView.addObject(vo, outputVO);
        }
    }

    /**
     * 
     * <p>Description : reloadSearchErrorData</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param modelAndView ModelAndView
     */
    private void reloadSearchErrorData(ModelAndView modelAndView, MasterBaseService service, Map<String, Object> configData, AppForm appForm) {
        // Acquire screen information*/
        BaseOutput output = service.initSearch(configData);
        if (output != null) {
            // get VO class
            Class<?> voClazz = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
            MasterBaseVO outputVO = (MasterBaseVO) modelMapper.map(output, voClazz);
            // Response data setting+
            String voName = (String) configData.get(CommonConstants.VO);
            // get form name
            String formName = (String) configData.get(CommonConstants.SEARCH_FORM);
            setDataInitSearch(modelAndView, appForm, outputVO, formName, voName);
        }
    }


    /**
     * 
     * <p>Description : Acquire data of deletion information screen</p> 
     * @author : hao.dv
     * @since : 2018/08/21
     * @param modelAndView ModelAndView
     * @param output BaseOutput
     * @param configData Setting of applicable Controller
     */
    private void loadDeleteInfo(ModelAndView modelAndView, BaseOutput output, Map<String, Object> configData) {
        Class<?> vo = (Class<?>) configData.get(CommonConstants.VO_CLAZZ);
        MasterBaseVO resultVO = (MasterBaseVO) modelMapper.map(output, vo);
        String VO = (String) configData.get(CommonConstants.VO);
        modelAndView.addObject(VO, resultVO);
    }


    /**
     * 
     * <p>Description : dowload csv data</p> 
     * @author hao.dv
     * @since 2018/08/21
     * @param response Http Servlet Response
     * @param service Master Base Service
     * @param configData Map configuration
     */
    protected void doDownloadCSV(HttpServletResponse response, MasterBaseService service, Map<String, Object> configData) {
        try {
            // get form name
            String formName = (String) configData.get(CommonConstants.SEARCH_FORM);
            logger.debug("Form name {}", formName);

            // Search condition
            AppForm appForm = (AppForm) getObjectFromSession(session, formName);
            logger.debug("Form value {}", appForm.toString());

            // FIXME get search form from session
            Class<?> searchDTO = (Class<?>) configData.get(CommonConstants.DTO_SEARCH_CLAZZ);
            logger.debug("Class dto search name {}", searchDTO.getClass().getName());

            BaseSearchDTO searchData = (BaseSearchDTO) searchDTO.newInstance();

            // フォームから、DTOに変換
            modelMapper.map(appForm, searchData);
            searchData.setLimit(-1);
            searchData.setOffset(-1);
            logger.debug("Convert Form to DTO {}", searchData.toString());

            // Set to map parameter
            Map<String, Object> conditionSearchDataDownload = new HashMap<String, Object>();
            conditionSearchDataDownload.put(CommonConstants.DTO_NAME, searchData);
            conditionSearchDataDownload.put(CommonConstants.SEARCH_SCREEN, configData.get(CommonConstants.SEARCH_SCREEN));

            // FIXME download csv
            service.doDownloadCSV(response, conditionSearchDataDownload);
        } catch (Exception e) {
            logger.error(Arrays.asList(e.getStackTrace()).stream().map(Objects::toString).collect(Collectors.joining("\n")));
        }
    }


    private ModelAndView createView(Map<String, Object> configData, String viewName) {
        String viewType = (String) configData.get(CommonConstants.VIEW_TYPE_KEY);
        if (viewType == null) {
            return createViewNameMaster(viewName);
        }
        if (CommonConstants.MASTER_VIEW_TYPE.equals(viewType)) {
            return createViewNameMaster(viewName);
        }
        if (CommonConstants.SHOHIN_VIEW_TYPE.equals(viewType)) {
            return createViewName(CommonConstants.ROOT_DIRECTORY_SHOHIN, viewName);
        }
        if (CommonConstants.INVOICE_VIEW_TYPE.equals(viewType)) {
            return createViewName(CommonConstants.ROOT_DIRECTORY_INVOICE, viewName);
        }
        return createViewNameMaster(viewName);
    }
    /**
     * 
     * <p>Description : initial model and View with view name</p> 
     * @author : hao.dv
     * @since 2018/08/21
     * @param rootDirectory root directory  
     * @param screenId 
     * @return ModelAndView
     */
    protected ModelAndView createViewName(String rootDirectory, String screenId) {
        ModelAndView modelAndView = new ModelAndView();
        this.setViewName(modelAndView, rootDirectory, screenId);
        modelAndView.addObject(CommonConstants.TITLE, getTitle());
        modelAndView.addObject(CommonConstants.TAB_MENU, getTabMenu());
        modelAndView.addObject(CommonConstants.TAB_MENU_LABEL, getTabMenuLabel());
        return modelAndView;
    }
    
    /**
     * 
     * <p>Description : initial model and View with view name</p> 
     * @author : hao.dv
     * @since 2018/08/21
     * @param screenId 
     * @return ModelAndView
     */
    protected ModelAndView createViewNameMaster(String screenId) {
        ModelAndView modelAndView = new ModelAndView();
        this.setViewName(modelAndView, CommonConstants.ROOT_DIRECTORY_MASTER, screenId);
        modelAndView.addObject(CommonConstants.TITLE, getTitle());
        modelAndView.addObject(CommonConstants.TAB_MENU, getTabMenu());
        modelAndView.addObject(CommonConstants.TAB_MENU_LABEL, getTabMenuLabel());
        return modelAndView;
    }
    
    
    
}

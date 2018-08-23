/**
* ファイル名 : TrkDelvTrfSearchForm.java
* 作成者 : minh.ls
* 作成日時 : 2018/5/31
* Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
*/
package vn.avg.zizi.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import vn.avg.zizi.dto.BaseDTO;
import vn.avg.zizi.dto.search.BaseSearchDTO;
import vn.avg.zizi.output.BaseOutput;

/**
 * 
 * <p>クラス名 : MasterBaseService</p>
 * <p>説明 : MasterBaseService Interface</p>
 * @author minh.ls
 * @since 2018/03/14
 */
public interface MasterBaseService {

    /**
     * <p>説明 : 検索画面初期データ取得</p> 
     * @author : duc.bv
     * @since : 2018/02/26
     * @return BaseOutput 初期データ
     */
    default BaseOutput initSearch(Map<String, Object> input) {
        return null;
    };

    /**
     * <p>説明 : 検索処理</p> 
     * @author : duc.bv
     * @since : 2018/02/26
     * @return BaseOutput 初期データ
     */
    default BaseOutput doSearch(BaseSearchDTO searchData) {
        return null;
    };

    default BaseOutput initDetail(Map<String, Object> input) {
        return null;
    };

    default BaseOutput initUpdate(Map<String, Object> input) {
        return null;
    };

    default BaseOutput doUpdate(Map<String, Object> input) {
        return null;
    };

    default BaseOutput doUpdateError(Map<String, Object> input) {
        return null;
    };

    default BaseOutput initInsert(Map<String, Object> input) {
        return null;
    };

    default BaseOutput doInsert(Map<String, Object> input) {
        return null;
    };

    default BaseOutput doInsertError(Map<String, Object> input) {
        return null;
    };

    default BaseOutput doReport(Map<String, Object> input) {
        return null;
    };

    default BaseOutput doReportError(Map<String, Object> input) {
        return null;
    };
    
    default BaseOutput doUpdateConfirm(Map<String, Object> input) {
        return null;
    };
    
    default BaseOutput doUpdateConfirmError(Map<String, Object> input) {
        return null;
    };

    /**
     * 
     * <p>説明 : doDownloadCSV</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @param response HttpServletResponse
     * @param input Parmeter map include List data search DTO
     */
    default void doDownloadCSV(HttpServletResponse response, Map<String, Object> input) {
    };

    /**
     * 
     * <p>説明 : CSVデータを登録 
     * @author : duc.bv
     * @since : 2018/03/12
     * @param csvDTO
     * @param username
     * @return FIXME Mô nghĩa param/return
     */
    default BaseOutput initDelete(Map<String, Object> input) {
        return null;
    };

    /**
     * 
     * <p>説明 : CSVファイルを読込む</p> 
     * @author : duc.bv
     * @param in ファイル内容
     * @since : 2018/03/02
     * @return List<GradeFormatDTO>
     */
    default BaseOutput doDelete(Map<String, Object> input) {
        return null;
    };

    default BaseOutput doDeleteError(Map<String, Object> input) {
        return null;
    };


    /**
     * 
     * <p>説明 : initImportCSV</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @param input parameter
     * @return Base Output
     */
    default BaseOutput initImportCSV(Map<String, Object> input) {
        return null;
    };


    /**
     * <p>説明 : initCopy data</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @param input Condition
     * @return Data binding screen
     */
    default BaseOutput initCopy(Map<String, Object> input) {
        return null;
    };

}

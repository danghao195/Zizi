/**
 * ファイル名 : MMkForm.java
 * 作成者 : hung.pd
 * 作成日時 : 2018/5/31
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.forms;

import java.util.List;

import vn.avg.zizi.validator.annotation.HaftsizeNumber;
import vn.avg.zizi.validator.annotation.MaxLength;
import vn.avg.zizi.validator.annotation.Required;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>クラス名 : MMkForm</p>
 * <p>説明 : メーカーフォーム</p>
 * @author hung.pd
 * @since 2018/5/31
 */
@Setter
@Getter
public class MMkForm extends AppForm {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8360217224822800649L;

    // メーカー名
    private static final String MK_NM_INPUT = "mkNm";

    // 表示順序
    private static final String PULLDOWN_DISP_SEQ_INPUT = "pulldownDispSeq";

    // メーカー名日本語
    private static final String NM_JA_INPUT = "nmJa";

    // メーカー名英語
    private static final String NM_EN_INPUT = "nmEn";

    // メーカー名ロシア語
    private static final String NM_RU_INPUT = "nmRu";

    // メーカー名スペイン語
    private static final String NM_ES_INPUT = "nmEs";

    // 表示順序 max length
    private static final int PULLDOWN_DISP_SEQ_LENGTH = 20;

    // メーカー名 max length
    private static final int FIELD_NAME_LENGTH = 50;

    /** メーカーコード */
    private int mkCd;

    /** 表示順序 */
    @Required(displayFieldName = PULLDOWN_DISP_SEQ_INPUT)
    @HaftsizeNumber(displayFieldName = PULLDOWN_DISP_SEQ_INPUT)
    @MaxLength(displayFieldName = PULLDOWN_DISP_SEQ_INPUT, maxlength = PULLDOWN_DISP_SEQ_LENGTH)
    private String pulldownDspSeq;

    /** メーカー名 */
    @Required(displayFieldName = MK_NM_INPUT)
    @MaxLength(displayFieldName = MK_NM_INPUT, maxlength = FIELD_NAME_LENGTH)
    private String mkNm;

    /** メーカー名日本語 */
    @Required(displayFieldName = NM_JA_INPUT)
    @MaxLength(displayFieldName = NM_JA_INPUT, maxlength = FIELD_NAME_LENGTH)
    private String nmJa;

    /** メーカー名英語 */
    @Required(displayFieldName = NM_EN_INPUT)
    @MaxLength(displayFieldName = NM_EN_INPUT, maxlength = FIELD_NAME_LENGTH)
    private String nmEn;

    /** メーカー名ロシア語 */
    @Required(displayFieldName = NM_RU_INPUT)
    @MaxLength(displayFieldName = NM_RU_INPUT, maxlength = FIELD_NAME_LENGTH)
    private String nmRu;

    /** メーカー名スペイン語 */
    @Required(displayFieldName = NM_ES_INPUT)
    @MaxLength(displayFieldName = NM_ES_INPUT, maxlength = FIELD_NAME_LENGTH)
    private String nmEs;

    /** 商品区分 */
    private List<String> itmDivCdList;

	public int getMkCd() {
		return mkCd;
	}

	public void setMkCd(int mkCd) {
		this.mkCd = mkCd;
	}


	public String getPulldownDspSeq() {
		return pulldownDspSeq;
	}

	public void setPulldownDspSeq(String pulldownDspSeq) {
		this.pulldownDspSeq = pulldownDspSeq;
	}

	public String getMkNm() {
		return mkNm;
	}

	public void setMkNm(String mkNm) {
		this.mkNm = mkNm;
	}

	public String getNmJa() {
		return nmJa;
	}

	public void setNmJa(String nmJa) {
		this.nmJa = nmJa;
	}

	public String getNmEn() {
		return nmEn;
	}

	public void setNmEn(String nmEn) {
		this.nmEn = nmEn;
	}

	public String getNmRu() {
		return nmRu;
	}

	public void setNmRu(String nmRu) {
		this.nmRu = nmRu;
	}

	public String getNmEs() {
		return nmEs;
	}

	public void setNmEs(String nmEs) {
		this.nmEs = nmEs;
	}

	public List<String> getItmDivCdList() {
		return itmDivCdList;
	}

	public void setItmDivCdList(List<String> itmDivCdList) {
		this.itmDivCdList = itmDivCdList;
	}
    
}

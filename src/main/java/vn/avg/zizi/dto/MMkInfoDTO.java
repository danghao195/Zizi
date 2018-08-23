/**
 * ファイル名 : MMkInfoDTO.java
 * 作成者 : hung.pd
 * 作成日時 : 2018/5/31
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import vn.avg.zizi.mappers.common.annotation.HeaderTable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>クラス名 : MMkInfoDTO</p>
 * <p>説明 : メーカー情報</p>
 * @author hung.pd
 * @since 2018/5/31
 */
@Setter
@Getter
public class MMkInfoDTO extends BaseDTO {

    /**
     * mkCd
     */
    
    private Integer mkCd;

    /**
     * mkNm
     */
    @HeaderTable(value = "MNG510501.table.mkNm", showIndex = 0, sortIndex = 0)
    private String mkNm;

    /**
     * pulldownDispSeq
     */
    private BigInteger pulldownDspSeq;

    /**
     * updateTime
     */
    protected LocalDateTime updateTimeOld;

    /**
     * lgList
     */
    private List<LanguageDTO> lgList;

    // mstItmDivNmList
    private List<String> itmDivCdNmList;
    
    /**
     * univMstEntityList
     */
    private List<SelectItemDTO> itmDivList;

	public Integer getMkCd() {
		return mkCd;
	}

	public void setMkCd(Integer mkCd) {
		this.mkCd = mkCd;
	}

	public String getMkNm() {
		return mkNm;
	}

	public void setMkNm(String mkNm) {
		this.mkNm = mkNm;
	}

	public BigInteger getPulldownDspSeq() {
		return pulldownDspSeq;
	}

	public void setPulldownDspSeq(BigInteger pulldownDspSeq) {
		this.pulldownDspSeq = pulldownDspSeq;
	}

	public LocalDateTime getUpdateTimeOld() {
		return updateTimeOld;
	}

	public void setUpdateTimeOld(LocalDateTime updateTimeOld) {
		this.updateTimeOld = updateTimeOld;
	}

	public List<LanguageDTO> getLgList() {
		return lgList;
	}

	public void setLgList(List<LanguageDTO> lgList) {
		this.lgList = lgList;
	}

	public List<String> getItmDivCdNmList() {
		return itmDivCdNmList;
	}

	public void setItmDivCdNmList(List<String> itmDivCdNmList) {
		this.itmDivCdNmList = itmDivCdNmList;
	}

	public List<SelectItemDTO> getItmDivList() {
		return itmDivList;
	}

	public void setItmDivList(List<SelectItemDTO> itmDivList) {
		this.itmDivList = itmDivList;
	}
    
}

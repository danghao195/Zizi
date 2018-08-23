/**
 * File name : MkDTO.java
 * @author : hao.dv
 * @since : 2018/08/23
 */
package vn.avg.zizi.dto;

import java.time.LocalDateTime;
import java.util.List;

import vn.avg.zizi.common.CommonConstants;
import vn.avg.zizi.common.HeaderSizeConstants;
import vn.avg.zizi.mappers.common.annotation.HeaderTable;
import vn.avg.zizi.mappers.common.annotation.HeaderTable.AlignType;
import lombok.Getter;
import lombok.Setter;

/**
 * <p> File name : MkDTO <p>
 * <p> Description : Mk Information </p>
 *
 * @author hao.dv
 * @since 2018/08/23
 */
@Setter
@Getter
public class MkDTO extends MasterBaseDTO {

    /** The screen message */
    private String screenMessage;

    /**  Maker code */
    @HeaderTable(value = "MNG310201.table.mkCd", showIndex = 0, sortIndex = 0, typeElement = CommonConstants.ELEMENT_TYPE_LINK, size=HeaderSizeConstants.MNG310201_HEADER_1,
    		align = AlignType.AlignRight
    		)
    private Integer mkCd;

    /**  Maker */
    @HeaderTable(value = "MNG310201.table.mkNm", showIndex = 1, sortIndex = 1, size=HeaderSizeConstants.MNG310201_HEADER_2)
    private String mkNm;

    /**  Display order (for list) */
    @HeaderTable(value = "MNG310201.table.pulldownDspSeq", showIndex = 2, sortIndex = 2, size=HeaderSizeConstants.MNG310201_HEADER_3
    		,align = AlignType.AlignRight)
    private String pulldownDspSeq;

    /**  RECORD Update date and time */
    protected LocalDateTime updateTimeOld;

    /** The maker name list */
    private List<String> makerNameList;

    /** Product Category Code */
    private List<String> itmDivCdList;

    /** Product Category Name */
    private List<String> itmDivCdNmList;

    /**  RECORD Registered Date */
    @HeaderTable(value = "MNG310201.table.createTime", showIndex = 3, sortIndex = 3, typeElement = CommonConstants.ELEMENT_TYPE_DATE,
    		size=HeaderSizeConstants.MNG310201_HEADER_4, dateFormat= "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime createTime;


	public String getScreenMessage() {
		return screenMessage;
	}

	public void setScreenMessage(String screenMessage) {
		this.screenMessage = screenMessage;
	}

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


	public String getPulldownDspSeq() {
		return pulldownDspSeq;
	}

	public void setPulldownDspSeq(String pulldownDspSeq) {
		this.pulldownDspSeq = pulldownDspSeq;
	}

	public LocalDateTime getUpdateTimeOld() {
		return updateTimeOld;
	}

	public void setUpdateTimeOld(LocalDateTime updateTimeOld) {
		this.updateTimeOld = updateTimeOld;
	}

	public List<String> getMakerNameList() {
		return makerNameList;
	}

	public void setMakerNameList(List<String> makerNameList) {
		this.makerNameList = makerNameList;
	}

	public List<String> getItmDivCdList() {
		return itmDivCdList;
	}

	public void setItmDivCdList(List<String> itmDivCdList) {
		this.itmDivCdList = itmDivCdList;
	}

	public List<String> getItmDivCdNmList() {
		return itmDivCdNmList;
	}

	public void setItmDivCdNmList(List<String> itmDivCdNmList) {
		this.itmDivCdNmList = itmDivCdNmList;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}


    

}

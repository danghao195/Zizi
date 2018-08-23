package vn.avg.zizi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>File name : MasterBaseDTO</p> 
 * <p>Description : MasterBaseDTO</p>
 * @author hao.dv
 * @since 2018/08/23
 */
@Getter
@Setter
public class MasterBaseDTO extends BaseDTO {

    /** previousCd */
    private Integer previousCd;

    /** nextCd */
    private Integer nextCd;

	public Integer getPreviousCd() {
		return previousCd;
	}

	public void setPreviousCd(Integer previousCd) {
		this.previousCd = previousCd;
	}

	public Integer getNextCd() {
		return nextCd;
	}

	public void setNextCd(Integer nextCd) {
		this.nextCd = nextCd;
	}


}
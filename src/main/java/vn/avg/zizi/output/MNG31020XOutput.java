/**
 * ファイル名 : MNG31020XOutput.java
 * 作成者 : hung.pd
 * 作成日時 : 2018/5/31
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.output;

import java.util.List;

import vn.avg.zizi.dto.SelectItemDTO;
import vn.avg.zizi.dto.csv.MMKFormatDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>クラス名 : MNG31020XOutput</p>
 * <p>説明 : メーカー管理</p>
 * @author hung.pd
 * @since 2018/5/31
 */
@Setter
@Getter
public class MNG31020XOutput extends BaseOutput {

    private List<SelectItemDTO> itmDivList;

    private List<String> mkNmList;

    // CSVファイルの成功行
    private List<MMKFormatDTO> successList;
    // CSVファイルのエラー行
    private List<MMKFormatDTO> errorList;
	public List<SelectItemDTO> getItmDivList() {
		return itmDivList;
	}
	public void setItmDivList(List<SelectItemDTO> itmDivList) {
		this.itmDivList = itmDivList;
	}
	public List<String> getMkNmList() {
		return mkNmList;
	}
	public void setMkNmList(List<String> mkNmList) {
		this.mkNmList = mkNmList;
	}
	public List<MMKFormatDTO> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<MMKFormatDTO> successList) {
		this.successList = successList;
	}
	public List<MMKFormatDTO> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<MMKFormatDTO> errorList) {
		this.errorList = errorList;
	}
    
}

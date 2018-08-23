/**
 * ファイル名 : BaseOutput.java
 * 作成者 : linh.ptn
 * 作成日時 : 2018/5/31
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.output;

import java.util.List;

//import vn.avg.zizi.vo.MessageVO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>ファイル名 : BaseOutput.java</p>
 * <p>説明 : BaseOutput</p>
 * @author minh.ls
 * @since 2018/03/03
 */
@Getter
@Setter
public class BaseOutput {

    /** 検索結果のレコード数 */
    private int totalRowCount = 0;

    /** page */
    private Integer page = 1;

    /**
     * 検索結果のグレードコード
     */
    private List<?> resultCdList;

    /** Detail DTO */
    private Object detailDTO;

    /** List DTO in result search */
    private List<?> dtoList;

    /** 前のオプジェクト */
    private Object previousObj;

    /** 次のオプジェクト */
    private Object nextObj;

    /** ビュー画面 */
    private BaseOutput viewScreen;

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public List<?> getResultCdList() {
		return resultCdList;
	}

	public void setResultCdList(List<?> resultCdList) {
		this.resultCdList = resultCdList;
	}

	public Object getDetailDTO() {
		return detailDTO;
	}

	public void setDetailDTO(Object detailDTO) {
		this.detailDTO = detailDTO;
	}

	public List<?> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<?> dtoList) {
		this.dtoList = dtoList;
	}

	public Object getPreviousObj() {
		return previousObj;
	}

	public void setPreviousObj(Object previousObj) {
		this.previousObj = previousObj;
	}

	public Object getNextObj() {
		return nextObj;
	}

	public void setNextObj(Object nextObj) {
		this.nextObj = nextObj;
	}

	public BaseOutput getViewScreen() {
		return viewScreen;
	}

	public void setViewScreen(BaseOutput viewScreen) {
		this.viewScreen = viewScreen;
	}

    /** ビュー画面 */
//    private MessageVO messageVO;
}

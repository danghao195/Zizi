/**
 * ファイル名 : MNG01020XController.java
 * 作成者 : duc.bv
 * 作成日時 : 2018/5/31
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.avg.zizi.dto.TableHeaderDTO;
import vn.avg.zizi.utils.Utilities;

/**
 * 
* <p>ファイル名 : MasterBaseVO.java </p>
* <p>説明        : マスタDTO</p>
* @author   : duc.bv
* @since    : 2018/02/26
 */
@Getter
@Setter
public class MasterBaseVO {
	
	/** 前のオプジェクト */
    private Object previousObj;

    /** 次のオプジェクト */
    private Object nextObj;

    /** detailDTO */
    private Object detailDTO;

    /** List DTO in result search */
    private List<?> dtoList;

    /** headers */
    private List<TableHeaderDTO> headers;

    /**
     * table size
     */
    private int headerSize;
    
    /**
     * 
     * <p>説明 : FIXME initHeader</p> 
     */
    public void initHeader() {
        headers = Utilities.getHeader(new MasterBaseVO());
    }
    
    private MasterBaseVO viewScreen;

    
    //FIXME Truong hop trong man hinh view co table phan trang
    /** List DTO in result search */
    private List<?> dtoInViewScreenList;

    /**
     * table size
     */
    private int headerSizeInViewScreen;
    
    private List<TableHeaderDTO> headersInViewScreen;

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

	public List<TableHeaderDTO> getHeaders() {
		return headers;
	}

	public void setHeaders(List<TableHeaderDTO> headers) {
		this.headers = headers;
	}

	public int getHeaderSize() {
		return headerSize;
	}

	public void setHeaderSize(int headerSize) {
		this.headerSize = headerSize;
	}

	public MasterBaseVO getViewScreen() {
		return viewScreen;
	}

	public void setViewScreen(MasterBaseVO viewScreen) {
		this.viewScreen = viewScreen;
	}

	public List<?> getDtoInViewScreenList() {
		return dtoInViewScreenList;
	}

	public void setDtoInViewScreenList(List<?> dtoInViewScreenList) {
		this.dtoInViewScreenList = dtoInViewScreenList;
	}

	public int getHeaderSizeInViewScreen() {
		return headerSizeInViewScreen;
	}

	public void setHeaderSizeInViewScreen(int headerSizeInViewScreen) {
		this.headerSizeInViewScreen = headerSizeInViewScreen;
	}

	public List<TableHeaderDTO> getHeadersInViewScreen() {
		return headersInViewScreen;
	}

	public void setHeadersInViewScreen(List<TableHeaderDTO> headersInViewScreen) {
		this.headersInViewScreen = headersInViewScreen;
	}
    
}

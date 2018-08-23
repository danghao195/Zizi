/**
 * ファイル名 : TableHeaderDTO.java
 * 作成者 : ha.dv
 * 作成日時 : 2018/5/31
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p> クラス名 : TableHeaderDTO <p>
 * <p> 説明 : 検索結果のテーブルヘッダ情報</p>
 *
 * @author duc.bv
 * @since 2018/5/31
 */
@Getter
@Setter
@NoArgsConstructor
public class TableHeaderDTO {

    /** The header */
    private String header;

    /** The type */
    private String type;

    /** The field nm */
    private String fieldNm;

    /** The sort index */
    private String sortIndex;

    /** The show index */
    private String showIndex;

    /** The type element */
    private String typeElement;

    /** The use message resource */
    private boolean useMessageResource = false;

    /** The align */
    private String align;

    /** The size */
    private int size;

    /** The visible */
    private boolean visible = true;

    /** The link element */
    // link element
    private String linkElement;

    /** The date format */
    private String dateFormat = "yyyy/MM/dd";

    public TableHeaderDTO() {
		super();
	}

	/**
     * <p>説明 : FIXME Instantiates a new table header DTO</p>
     *
     * @param header {@link String}
     * @param type {@link String}
     * @param fieldNm {@link String}
     * @param sortIndex {@link String}
     * @param showIndex {@link String}
     * @param typeElement {@link String}
     * @param align {@link String}
     */
    public TableHeaderDTO(String header, String type, String fieldNm, String sortIndex, String showIndex, String typeElement, String align) {
        this.header = header;
        this.type = type;
        this.fieldNm = fieldNm;
        this.sortIndex = sortIndex;
        this.showIndex = showIndex;
        this.typeElement = typeElement;
        this.align = align;
    }

    /**
     * <p>説明 : FIXME Instantiates a new table header DTO</p>
     *
     * @param header {@link String}
     * @param type {@link String}
     * @param fieldNm {@link String}
     * @param sortIndex {@link String}
     * @param showIndex {@link String}
     * @param typeElement {@link String}
     */
    public TableHeaderDTO(String header, String type, String fieldNm, String sortIndex, String showIndex, String typeElement) {
        this.header = header;
        this.type = type;
        this.fieldNm = fieldNm;
        this.sortIndex = sortIndex;
        this.showIndex = showIndex;
        this.typeElement = typeElement;
    }

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFieldNm() {
		return fieldNm;
	}

	public void setFieldNm(String fieldNm) {
		this.fieldNm = fieldNm;
	}

	public String getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(String showIndex) {
		this.showIndex = showIndex;
	}

	public String getTypeElement() {
		return typeElement;
	}

	public void setTypeElement(String typeElement) {
		this.typeElement = typeElement;
	}

	public boolean isUseMessageResource() {
		return useMessageResource;
	}

	public void setUseMessageResource(boolean useMessageResource) {
		this.useMessageResource = useMessageResource;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getLinkElement() {
		return linkElement;
	}

	public void setLinkElement(String linkElement) {
		this.linkElement = linkElement;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
    
    
}

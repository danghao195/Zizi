package vn.avg.zizi.dto.search;

import vn.avg.zizi.common.CommonConstants;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>ファイル名 : BaseSearchDTO</p>
 * <p>説明 : BaseSearchDTO</p>
 * @author minh.ls
 * @since : 2018/02/10
 */
@Getter
@Setter
public class BaseSearchDTO {
    // current page
    private Integer page = 0;
    // sort index
    private int sortIndex;
    // sort type
    private String sortType;
    // for sql command
    // offset
    private int offset;
    // limit
    private int limit = CommonConstants.GRID_ROW_COUNT;
    // order by
    private String orderBy;
    // order type
    private String orderType;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}

package vn.avg.zizi.forms;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import vn.avg.zizi.common.CommonConstants;
import vn.avg.zizi.validator.common.ErrorInfoValue;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>ファイル名 : AppForm</p>
 * <p>説明 : AppForm</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Setter
@Getter
public class AppForm implements Serializable {

    private static final long serialVersionUID = -410827961360725279L;

    /** screenMessage */
    private String screenMessage;

    /** exclusiveMessage */
    private String exclusiveMessage;

    /** createTime */
    private LocalDateTime createTime;

    /** createUser */
    private String createUser;

    /** createClass */
    private String createClass;

    /**updateTime */
    @DateTimeFormat(pattern = CommonConstants.DATE_TIME_PATTERN)
    protected LocalDateTime updateTime;

    /**updateUser */
    private String updateUser;

    /**updateClass */
    private String updateClass;

    /**screenName */
    private String screenName;

    /**dayCalendar */
    private String dayCalendar;

    /**Error list */
    private List<ErrorInfoValue> error;

    /**current page number */
    private int page;

    /** paging flag*/
    private boolean paging = false;

    /**
     * ソートフィルドインデックス
     */
    private int sortIndex;

    /**
     * ソート順
     */
    private String sortType;

	public String getScreenMessage() {
		return screenMessage;
	}

	public void setScreenMessage(String screenMessage) {
		this.screenMessage = screenMessage;
	}

	public String getExclusiveMessage() {
		return exclusiveMessage;
	}

	public void setExclusiveMessage(String exclusiveMessage) {
		this.exclusiveMessage = exclusiveMessage;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateClass() {
		return createClass;
	}

	public void setCreateClass(String createClass) {
		this.createClass = createClass;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateClass() {
		return updateClass;
	}

	public void setUpdateClass(String updateClass) {
		this.updateClass = updateClass;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getDayCalendar() {
		return dayCalendar;
	}

	public void setDayCalendar(String dayCalendar) {
		this.dayCalendar = dayCalendar;
	}

	public List<ErrorInfoValue> getError() {
		return error;
	}

	public void setError(List<ErrorInfoValue> error) {
		this.error = error;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public boolean isPaging() {
		return paging;
	}

	public void setPaging(boolean paging) {
		this.paging = paging;
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
}

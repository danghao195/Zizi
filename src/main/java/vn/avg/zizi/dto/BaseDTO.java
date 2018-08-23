package vn.avg.zizi.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import vn.avg.zizi.common.CommonConstants;
import vn.avg.zizi.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>File name : BaseDTO</p>
 * <p>Description : BaseDTO</p>
 * @author hao.dv
 * @since 2018/08/23
 */
@Setter
@Getter
public class BaseDTO {
    /**
     * createUser
     */
    protected String createUser;

    /**
     * createClass
     */
    protected String createClass;

    /**
     * updateUser
     */
    protected String updateUser;

    /**
     * updateClass
     */
    protected String updateClass;

    /**
     * createTime
     */
    protected LocalDateTime createTime;

    /**
     * updateTime
     */
    @DateTimeFormat(pattern = CommonConstants.DATE_TIME_PATTERN)
    protected LocalDateTime updateTime;

    /**
     * deleteFlag
     */
    protected String deleteFlg;

    /**
     * deleteTime
     */
    protected LocalDateTime deleteTime;

    /** Last update time */
    protected LocalDateTime lastUpdateTime;

    /**
     * BaseDTO
     */
    public BaseDTO() {
        super();
    }

    /**
     * <p>Description : get updateTime for display</p> 
     * @author : hao.dv
     * @since : 2018/08/23
     * @return str updateTime
     */
    public String getUpdateTimeDisp() {
        return StringUtil.toStrDateFormat(updateTime, CommonConstants.DATE_FORMAT_YYYYMMDD);
    }

    /**
     * <p>Description : get createTime for display</p> 
     * @author : hao.dv
     * @since : 2018/08/23
     * @return String createTime
     */
    public String getCreateTimeDisp() {
        return StringUtil.toStrDateFormat(createTime, CommonConstants.DATE_FORMAT_YYYYMMDD);
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

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public LocalDateTime getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(LocalDateTime deleteTime) {
		this.deleteTime = deleteTime;
	}

	public LocalDateTime getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
    
}

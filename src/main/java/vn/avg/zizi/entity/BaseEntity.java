package vn.avg.zizi.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import vn.avg.zizi.mappers.common.annotation.Column;

/**
 * <p>File name : BaseEntity</p>
 * <p>Description : BaseEntity</p>
 * @author hao.dv
 * @since 2018/08/23
 */
@Setter
@Getter
public class BaseEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1264383025283185295L;

    /**
     * createTime
     */
    @Column(name = "CREATE_TIME")
    protected LocalDateTime createTime;

    /**
     * createUser
     */
    @Column(name = "CREATE_USER")
    protected String createUser;

    /**
     * createClass
     */
    @Column(name = "CREATE_CLASS")
    protected String createClass;

    /**
     * updateTime
     */
    @Column(name = "UPDATE_TIME")
    protected LocalDateTime updateTime;

    /**
     * updateUser
     */
    @Column(name = "UPDATE_USER")
    protected String updateUser;

    /**
     * updateClass
     */
    @Column(name = "UPDATE_CLASS")
    protected String updateClass;

    /**
     * deleteFlg
     */
    @Column(name = "DELETE_FLG")
    protected String deleteFlg;

    /**
     * deleteTime
     */
    @Column(name = "DELETE_TIME")
    protected LocalDateTime deleteTime;
    
    
    /** 最後更新時間 */
    protected LocalDateTime lastUpdateTime;
}

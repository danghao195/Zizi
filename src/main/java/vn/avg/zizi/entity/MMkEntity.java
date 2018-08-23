
package vn.avg.zizi.entity;

import vn.avg.zizi.mappers.common.annotation.Column;
import vn.avg.zizi.mappers.common.annotation.PrimaryKey;
import vn.avg.zizi.mappers.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * <p> File name : MMkEntity </p>
 * <p> Description : Maker entity </p>
 * @author hao.dv
 * @since 2018/05/31
 */
@Getter
@Setter
@Table(name = "M_MK")
public class MMkEntity extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** Maker code */
    @PrimaryKey(name = "MK_CD")
    private Integer mkCd;

    /** Pull down display order */
    @Column(name = "PULL_DOWN_DSP_SEQ")
    private Long pullDownDspSeq;

    /** Maker name */
    @Column(name = "MK_NM")
    private String mkNm;
    
    /**
     * <p>Description : Constructor</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     */
    public MMkEntity() {
        super();
    }
    
    /**
     * <p>Description : Constructor</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param deleteFlg 
     */
    public MMkEntity(String deleteFlg) {
        super();
//        this.deleteFlg = deleteFlg;
    }
    
    /**
     * <p>Description : Maker</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param obj Maker Entity
     * @return true/false {@link boolean}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MMkEntity other = (MMkEntity) obj;
        if (mkCd == null) {
            if (other.mkCd != null)
                return false;
        } else if (!mkCd.equals(other.mkCd))
            return false;
        return true;
    }

    /**
     * <p>Description : constructor</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param mkCd Maker code
     */
    public MMkEntity(Integer mkCd) {
        super();
        this.mkCd = mkCd;
    }
}

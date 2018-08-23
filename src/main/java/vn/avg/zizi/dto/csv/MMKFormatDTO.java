/**
 * ファイル名 : MMKFormatDTO.java
 * 作成者 : duc.bv
 * 作成日時 : 2018/5/31
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved.
 */
package vn.avg.zizi.dto.csv;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.avg.zizi.common.HeaderSizeConstants;
import vn.avg.zizi.mappers.common.annotation.HeaderCsv;
import vn.avg.zizi.mappers.common.annotation.HeaderTable;
import vn.avg.zizi.utils.Utilities;
import vn.avg.zizi.validator.annotation.HaftsizeNumber;
import vn.avg.zizi.validator.annotation.Ignore;
import vn.avg.zizi.validator.annotation.MaxLength;
import vn.avg.zizi.validator.annotation.Required;

/**
 * <p> ファイル名 : MMKFormatDTO <p>
 * <p> 説明 : MMKCSV行情報</p>
 *
 * @author duc.bv
 * @since 2018/5/31
 */
@Setter
@Getter
public class MMKFormatDTO extends BaseFormatDTO {

    /** メーカーコード */
//    @UniqueKey(message = MessageConstants.MSG_W_C0200)
    @Required
    @HaftsizeNumber
    @MaxLength(maxlength = 6)
    @HeaderCsv(name = "MNG310205.header.name.mkcd")
    @HeaderTable(value = "MNG310205.tbl.label.mkcd", showIndex = 0, size=HeaderSizeConstants.MNG310205_HEADER_0)
    private String mkCd;

    /** プルダウン表示順序 */
    @MaxLength(maxlength = 18)
    @Required
    @HaftsizeNumber
    @HeaderCsv(name = "MNG310205.header.name.ordDisplay")
    @HeaderTable(value = "MNG310205.tbl.label.orddisplay", showIndex = 1, size=HeaderSizeConstants.MNG310205_HEADER_1)
    private String pulldownDspSeq;

    /** メーカー名 */
    @MaxLength(maxlength = 50)
    @HeaderCsv(name = "MNG310205.header.name.mkNm")
    @HeaderTable(value = "MNG310205.tbl.label.mkNm", showIndex = 2, size=HeaderSizeConstants.MNG310205_HEADER_2)
    @Required
    private String mkNm;

    
    /**  日本語 */
    @MaxLength(maxlength = 50)
    @Required
    @HeaderCsv(name = "MNG310205.header.name.mkNmByLgJa")
    @HeaderTable(value = "MNG310205.tbl.label.mknmbylgja", showIndex = 3, size=HeaderSizeConstants.MNG310205_HEADER_3)
    private String nmJa;

    
    /**  英語 */
    @MaxLength(maxlength = 50)
    @Required
    @HeaderCsv(name = "MNG310205.header.name.mkNmByLgEn")
    @HeaderTable(value = "MNG310205.tbl.label.mknmbylgen", showIndex = 4, size=HeaderSizeConstants.MNG310205_HEADER_4)
    private String nmEn;

   
    /**  ロシア語 */
    @MaxLength(maxlength = 50)
    @Required
    @HeaderCsv(name = "MNG310205.header.name.mkNmByLgRu")
    @HeaderTable(value = "MNG310205.tbl.label.mknmbylgru", showIndex = 5, size=HeaderSizeConstants.MNG310205_HEADER_5)
    private String nmRu;

    
    /**  スペイン語 */
    @MaxLength(maxlength = 50)
    @Required
    @HeaderCsv(name = "MNG310205.header.name.mkNmByLgEs")
    @HeaderTable(value = "MNG310205.tbl.label.mknmbylges", showIndex = 6, size=HeaderSizeConstants.MNG310205_HEADER_6)
    private String nmEs;

    
    /**  商品区分コード */
    @MaxLength(maxlength = 1)
    @HaftsizeNumber
    @HeaderCsv(name = "MNG310205.header.name.itmDivCdJido")
    @HeaderTable(value = "MNG310205.tbl.label.itmDivCdJido", showIndex = 7, size=HeaderSizeConstants.MNG310205_HEADER_7)
    private String itmDivCdJido;

    
    /**  商品区分コード */
    @MaxLength(maxlength = 1)
    @HaftsizeNumber
    @HeaderCsv(name = "MNG310205.header.name.itmDivCdParts")
    @HeaderTable(value = "MNG310205.tbl.label.itmDivCdParts", showIndex = 8, size=HeaderSizeConstants.MNG310205_HEADER_8)
    private String itmDivCdParts;

    
    /**  商品区分コード */
    @MaxLength(maxlength = 1)
    @HaftsizeNumber
    @HeaderCsv(name = "MNG310205.header.name.itmDivCdOther")
    @HeaderTable(value = "MNG310205.tbl.label.itmDivCdOther", showIndex = 9, size=HeaderSizeConstants.MNG310205_HEADER_9)
    private String itmDivCdOther;

    
    /**  商品区分コード */
    @Ignore
    private List<String> itmDivCdNmList;

   
    /**  商品区分コード */
    @Ignore
    private List<String> itmDivCdList;
    
    
    /**
     * <p>説明 : 表示順序チェック </p>
     *
     * @author duc.bv
     * @since 2018/5/31
     * @param target the generic type
     * @return true/false {@link boolean}
     */
    public boolean validUnique(MMKFormatDTO target) {
    	return Utilities.compareStr(pulldownDspSeq, target.pulldownDspSeq) ? true : false;
    }
    
    /**
     * <p>説明 : 名称チェック</p>
     *
     * @author duc.bv
     * @since 2018/5/31
     * @param target {@link MMKFormatDTO}
     * @return true/false {@link boolean}
     */
    public boolean validUniqueName(MMKFormatDTO target) {
    	return false;
    }
}

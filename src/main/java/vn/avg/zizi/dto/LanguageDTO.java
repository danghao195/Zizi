package vn.avg.zizi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>ファイル名 : LanguageDTO</p>
 * <p>説明 : FIXME dto chứa thông tin ngôn ngữ</p>
 * @author truong.pq
 * @since 2018/02/06
 */
@Getter
@Setter
public class LanguageDTO {
    /**
     * lgDivCd
     */
    private String lgDivCd;

    /**
     * lgText
     */
    private String lgText;

    /**
     * newsExplan
     */
    private String newsExplan;

    public LanguageDTO(String lgDivCd, String lgText, String newsExplan) {
        super();
        this.lgDivCd = lgDivCd;
        this.lgText = lgText;
        this.newsExplan = newsExplan;
    }

    public LanguageDTO() {
        super();
    }

}

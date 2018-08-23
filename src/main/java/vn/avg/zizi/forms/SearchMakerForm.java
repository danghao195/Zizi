package vn.avg.zizi.forms;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>クラス名 : SearchMakerForm</p>
 * <p>説明 : メーカー検索フォーム</p>
 * @author hung.pd
 * @since 2018/5/31
 */
@Setter
@Getter
public class SearchMakerForm extends AppForm {
    /**
     * makerList
     */
    private static final long serialVersionUID = -1299154913247966621L;

    /**
     * mkNm
     */
    private String mkNm;

    /**
     * itmDivCd
     */
    private String itmDivCd;

    /**
     * productTypeList
     */
    private List<String> itmDivList;

	public String getMkNm() {
		return mkNm;
	}

	public void setMkNm(String mkNm) {
		this.mkNm = mkNm;
	}

	public String getItmDivCd() {
		return itmDivCd;
	}

	public void setItmDivCd(String itmDivCd) {
		this.itmDivCd = itmDivCd;
	}

	public List<String> getItmDivList() {
		return itmDivList;
	}

	public void setItmDivList(List<String> itmDivList) {
		this.itmDivList = itmDivList;
	}

}

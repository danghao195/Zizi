
package  vn.avg.zizi.dto.search;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <p> file name : MkSearchDTO <p>
 * <p> Description : search make</p>
 *
 * @author hao.dv
 * @since 2018/5/31
 */
@Getter
@Setter
public class MkSearchDTO extends BaseSearchDTO {
    
    /** メーカー名 */
    private String mkNm;
    
    /** 商品区分コード */
    private String itmDivCd;
    
    /** 商品区分データ取得 */
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

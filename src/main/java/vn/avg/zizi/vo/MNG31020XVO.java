package vn.avg.zizi.vo;

import java.util.List;

import vn.avg.zizi.dto.MkDTO;
import vn.avg.zizi.dto.SelectItemDTO;
import vn.avg.zizi.utils.Utilities;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>File name : MMkVO</p>
 * <p>Description : Maker management</p>
 * @author hao.dv
 * @since 2018/08/20
 */
@Getter
@Setter
public class MNG31020XVO extends MasterBaseVO {
    /**
     * list item div code
     */
    private List<SelectItemDTO> itmDivList;
    /**
     * list maker name
     */
    private List<String> mkNmList;
	public List<SelectItemDTO> getItmDivList() {
		return itmDivList;
	}
	public void setItmDivList(List<SelectItemDTO> itmDivList) {
		this.itmDivList = itmDivList;
	}
	public List<String> getMkNmList() {
		return mkNmList;
	}
	public void setMkNmList(List<String> mkNmList) {
		this.mkNmList = mkNmList;
	}

//    /**{@inheritDoc}*/
//    @Override
//    public void initHeader() {
//        setHeaders(Utilities.getHeader(new MkDTO()));
//    }
}

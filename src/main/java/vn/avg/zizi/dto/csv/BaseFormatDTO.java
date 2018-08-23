package vn.avg.zizi.dto.csv;

import java.util.Map;

import vn.avg.zizi.validator.annotation.Ignore;
//import vn.avg.zizi.vo.MessageCSV;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>ファイル名 : BaseFormatDTO</p>
 * <p>説明 : FIXME Set valid and message for row csv</p>
 * @author hung.pd
 * @since 2018/03/02
 */
@Getter
@Setter
public class BaseFormatDTO {
    /** Row number */
    @Ignore
    private Long rowNo;

    /** FIXME declare isValid*/
    @Ignore
    private Boolean isValid;

}

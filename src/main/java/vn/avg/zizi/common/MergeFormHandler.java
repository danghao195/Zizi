package vn.avg.zizi.common;

import vn.avg.zizi.forms.AppForm;
import vn.avg.zizi.output.BaseOutput;

/**
 * <p> File name : Command <p>
 * <p> Description : Command $</p>
 *
 * @author hao.dv
 * @since 2018/08/22
 * @param <T> the generic type
 */
public interface MergeFormHandler {

    /**
     * <p>Description : FIXME Exec</p>
     *
     * @author ha.dv
     * @since 2018/5/31
     * @param output {@link BaseOutput}
     * @param form {@link AppForm}
     */
    void exec(BaseOutput output, AppForm form);
}

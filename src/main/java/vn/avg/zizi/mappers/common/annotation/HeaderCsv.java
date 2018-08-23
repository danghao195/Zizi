package vn.avg.zizi.mappers.common.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * <p>ファイル名 : FIXME HeaderCsv</p>
 * @author hung.pd
 * @since 2018/02/28
 */

@Retention(RUNTIME)
public @interface HeaderCsv {
    /**
     * 
     * <p>説明 : name</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since 2018/02/28
     * @return Name header field value
     */
    String name() default "N/A";
}

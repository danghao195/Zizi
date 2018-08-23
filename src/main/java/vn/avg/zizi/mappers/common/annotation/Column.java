package vn.avg.zizi.mappers.common.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * <p>ファイル名 : FIXME Column</p>
 * <p>説明 : Column annotation</p>
 * @author truong.pq
 * @since 2017/12/06
 *
 */

@Retention(RUNTIME)
public @interface Column {
    /**
     * 
     * <p>説明 : name</p> 
     * @author : thien.nv
     * @since : 2017/12/27
     * @return String
     */
    String name() default "N/A";
}

package vn.avg.zizi.mappers.common.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * <p>ファイル名 : Table</p>
 * <p>説明 : Table annotation</p>
 * @author truong.pq
 * @since 2017/12/06
 *
 */
@Retention(RUNTIME)
public @interface Table {
    /**
     * 
     * <p>説明 : name</p> 
     * @author : thien.nv
     * @since : 2017/12/27
     * @return String
     */
    String name() default "N/A";
}

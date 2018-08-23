package vn.avg.zizi.mappers.common.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * <p>ファイル名 : PrimaryKey</p>
 * <p>説明 : PrimaryKey annotation</p>
 * @author truong.pq
 * @since 2017/12/06
 *
 */
@Retention(RUNTIME)
public @interface PrimaryKey {
    /**
     * 
     * <p>説明 : name</p> 
     * @author : thien.nv
     * @since : 2017/12/27
     * @return String
     */
    String name() default "N/A";
    boolean autoIncre() default false;
}

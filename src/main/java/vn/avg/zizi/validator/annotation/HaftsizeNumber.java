/**
 * ファイル名 : HaftsizeNumber.java
 * 作成者 : ha.dv
 * 作成日時 : 2018/5/31
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>ファイル名 : HaftsizeNumber</p>
 * <p>説明 : HaftsizeNumber</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface HaftsizeNumber {
    /**
     * 
     * <p>説明 : displayFieldName</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since 2018/03/03
     * @return field name 
     */
    String displayFieldName() default "N/A";

    /**
     * 
     * <p>説明 : message</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since 2018/03/03
     * @return message information
     */
    String message() default "";

    /**
     * <p>説明 : FIXME Allow sign and decimal</p>
     *
     * @author ha.dv
     * @since 2018/5/31
     * @return String {@link String}
     */
    boolean allowSignAndDecimal() default false;
}

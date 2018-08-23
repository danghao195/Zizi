package vn.avg.zizi.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>ファイル名 : MaxLength</p>
 * <p>説明 : MaxLength</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface MaxLength {
    int maxlength() default 1;

    String displayFieldName() default "N/A";
    
    String message() default "";
}

/**
 * ファイル名 : HeaderTable.java
 * 作成者 : ha.dv
 * 作成日時 : 2018/08/17
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.mappers.common.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * <p> File name : HeaderTable <p>
 * <p> Description : HeaderTable $</p>
 *
 * @author hao.dv
 * @since 2018/08/17
 */
@Retention(RUNTIME)
public @interface HeaderTable {

    /**
     *
     * @author hao.dv
     * @since 2018/08/17
     * @return String {@link String}
     */
    String value() default "N/A";

    /**
     * <p>Description : FIXME Show index</p>
     *
     * @author hao.dv
     * @since 2018/08/17
     * @return int {@link int}
     */
    public int showIndex() default -1;

    /**
     *
     * @author hao.dv
     * @since 2018/08/17
     * @return int {@link int}
     */
    // sort index
    public int sortIndex() default -1;

    /**
     *
     * @author hao.dv
     * @since 2018/08/17
     * @return String {@link String}
     */
    // type of element
    public String typeElement() default "N/A";

    /**
     * <p>Description : FIXME Align</p>
     *
     * @author hao.dv
     * @since 2018/08/17
     * @return AlignType {@link AlignType}
     */
    // align element
    public AlignType align() default AlignType.AlignLeft;

    /**
     * <p> File name : AlignType <p>
     *
     * @author hao.dv
     * @since 2018/08/17
     */
    enum AlignType {
        /** The Align right */
        AlignRight,
        /** The Align left */
        AlignLeft,
        /** The Align center */
        AlignCenter
    }

    /**
     *
     * @author hao.dv
     * @since 2018/08/17
     * @return String {@link String}
     */
    public String linkElement() default "N/A";

    /**
     *
     * @author hao.dv
     * @since 2018/08/17
     * @return int {@link int}
     */
    // size of column
    public int size() default 200;

    /**
     * 
     *
     * @author hao.dv
     * @since 2018/08/17
     * @return true/false {@link boolean}
     */
    public boolean visible() default true;

    /**
     * <p>Description : FIXME Date format</p>
     *
     * @author hao.dv
     * @since 2018/08/17
     * @return String {@link String}
     */
    public String dateFormat() default "N/A";
}

package vn.avg.zizi.utils;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>ファイル名 : StringUtil</p>
 * <p>説明  FIXME StringUtil</p>
 * @author : truong.pq
 * @since  : 2017/11/25
 */
public class StringUtil {

    /** SPACE_HEX_IN_ASCII */
    private static final int SPACE_HEX_IN_ASCII = 0x20;

    /**
     * オブジェクトを文字列に変換し、nullの場合空に変換する
     *
     * @param obj Object
     * @return String
     */
    public static String convertNull(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    /**
     * チェックオブジェクトがnullです
     *
     * @param str Object
     * @return 真のオブジェクト入力はnullまたは長さです=0
     */
    public static boolean isNullObject(Object str) {
        return str == null || str.toString().length() <= 0;
    }

    /**Check String is null
     *
     * @param str String
     * @return true String input is null or length = 0
     */
    public static boolean isNull(String str) {
        return str == null || str.trim().length() <= 0;
    }

    /**
     * ナル値あるいは空文字を「""」へ変換する場合、trim()を使用してスペースを削除します。
     *
     * @param str String 
     * @return String
     */
    public static String chgNull(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        return str.trim();
    }

    /**
     * 文字入力のトリム
     *
     * @param str String
     * @return String
     */
    public static String trimStr(String str) {
        if (str != null) {
            return str.trim();
        }
        return "";
    }

    /**
     * isEmpty
     *
     * @param str String
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * isNotEmpty
     *
     * @param str String
     * @return boolean
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * isUppercaseAlpha
     * 
     * @param c char
     * @return boolean
     */
    public static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    /**
     * isLowercaseAlpha
     * 
     * @param c char
     * @return boolean
     */
    public static boolean isLowercaseAlpha(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    /**
     * toUpperAscii
     * 
     * @param c char
     * @return char
     */
    public static char toUpperAscii(char c) {
        if (isLowercaseAlpha(c)) {
            c -= (char) SPACE_HEX_IN_ASCII;
        }
        return c;
    }

    /**
     * toLowerAscii
     * 
     * @param c char
     * @return char
     */
    public static char toLowerAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c += (char) SPACE_HEX_IN_ASCII;
        }
        return c;
    }

    /**
     * 
     * <p>説明 : camelhumpToUnderline</p> 
     * @author : [bp.thien.nv]
     * @since : [2017/12/26]
     * @param str String
     * @return String
     */
    public static String camelhumpToUnderline(String str) {
        final int size;
        final char[] chars;
        final StringBuilder sb = new StringBuilder((size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
        char c;
        for (int i = 0; i < size; i++) {
            c = chars[i];
            if (isUppercaseAlpha(c)) {
                sb.append('_').append(toLowerAscii(c));
            } else {
                sb.append(c);
            }
        }
        return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
    }

    /**
     * String to int
     * 
     * @param str String
     * @return Integer
     */
    public static Integer toInt(String str) {
        try {
            if (str != null && str.length() > 0) {
                return Integer.valueOf(str);
            } else {
                return 0;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * String to int
     * 
     * @param str String
     * @return Integer
     */
    public static Integer toInt(Object obj) {
        try {
            if (obj != null && obj.toString().length() > 0) {
                return Integer.valueOf(obj.toString());
            } else {
                return 0;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * String to long
     * 
     * @param str String 
     * @return Long
     */
    public static Long toLong(String str) {
        if (str != null && str.length() > 0) {
            return Long.valueOf(str);
        } else {
            return 0L;
        }
    }

    /**
     * <p>説明 : String to BigInteger</p> 
     * @author : [bp.thien.nv]
     * @since : [2017/12/21]
     * @param str String
     * @return BigInteger
     */
    public static BigInteger toBigInteger(String str) {
        if (str != null && str.length() > 0) {
            return new BigInteger(str);
        } else {
            return new BigInteger("0");
        }
    }

    /**
     * <p>説明 : Convert LocalDateTime to String Date by Format</p> 
     * @author : [minh.ls]
     * @since : [2018/02/02]
     * @param date LocalDateTime
     * @param format format date
     * @return String str date format
     */
    public static String toStrDateFormat(LocalDateTime date, String format) {
        if (date == null || isNull(format)) {
            return "";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return date.format(formatter);
        }
    }

    /**
     * <p>説明 : FIXME format Number With Commas</p> 
     * @author : truong.pq
     * @since : 2018/03/13
     * @param value : Integer
     * @return FIXME Dãy số được format theo format "#,###"
     */
    public static String formatNumberWithCommas(Integer value) {
        if (value == null) {
            return "";
        }

        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(value);

    }

    /**
     * <p>説明 : FIXME formatString with template and parameters</p> 
     * @author : truong.pq
     * @since : 2018/03/13
     * @param temp String template
     * @param args Object
     * @return FIXME Chuỗi được format cùng với tham số input
     */
    public static String formatString(String temp, Object... args) {
        return String.format(temp, args);
    }

    /**
     * 
     * <p>説明 : concat all String to one String </p> 
     * @author : hung.nq
     * @since : 2018/03/14
     * @param strings Array of String
     * @return String
     */
    public static String concat(String... strings) {
        if (strings == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            if (str != null) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    /**
     * 
     * <p>説明 : Spilit String </p> 
     * @author : hung.nq
     * @since : 2018/03/14
     * @param spilt String
     * @param sequence String
     * @return String[]
     */
    public static String[] splitWithTrim(String spilt, String sequence) {
        if (isEmpty(sequence)) {
            return null;
        }
        String[] values = sequence.split(spilt);
        if (values.length == 0) {
            return values;
        }
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i].trim();
        }
        return values;
    }

    /**
     * Capitalize a {@code String}, changing the first letter to
     * upper case as per {@link Character#toUpperCase(char)}.
     * No other letters are changed.
     * @param str the {@code String} to capitalize, may be {@code null}
     * @return the capitalized {@code String}, or {@code null} if the supplied
     * string is {@code null}
     */
    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    /**
     * Uncapitalize a {@code String}, changing the first letter to
     * lower case as per {@link Character#toLowerCase(char)}.
     * No other letters are changed.
     * @param str the {@code String} to uncapitalize, may be {@code null}
     * @return the uncapitalized {@code String}, or {@code null} if the supplied
     * string is {@code null}
     */
    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    /**
     * 
     * <p>説明 : change First Character Case </p> 
     * @author : hung.nq
     * @since : 2018/03/14
     * @param str String
     * @param capitalize boolean
     * @return String
     */
    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        if (capitalize) {
            sb.append(Character.toUpperCase(str.charAt(0)));
        } else {
            sb.append(Character.toLowerCase(str.charAt(0)));
        }
        sb.append(str.substring(1));
        return sb.toString();
    }

    /**
     * String to Double
     * 
     * @param str String 
     * @return Double
     */
    public static Double toDouble(String str) {
        if (str != null && str.length() > 0) {
            return Double.parseDouble(str);
        } else {
            return 0D;
        }
    }
}

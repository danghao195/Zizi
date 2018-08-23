package vn.avg.zizi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vn.avg.zizi.common.CommonConstants;

/**
 * <p>ファイル名 : CheckUtil</p>
 * <p>説明 : CheckUtil</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
public class CheckUtil {

    private static Logger logger = LogManager.getLogger(CheckUtil.class);
    /**Declare halfWidthEnglish*/
    private static final String[] halfWidthEnglish = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    /**Declare halfAlphabetNumberSymbol*/
    private static final String[] halfAlphabetNumberSymbol = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", ":",
            ";", "'", "\"", ",", "<", "/", "?", "`", "~" };

    /**
     * 
     * <p>説明 : FIXME Kiểm tra mật khẩu bao gồm kí tự hoa, thường, số</p> 
     * @author : thien.nv
     * @since : 2018/03/16
     * @param input String
     * @return boolean
     */
    public static boolean isMixALl(String input) {
        if (StringUtil.isNull(input)) {
            return true;
        }
        Pattern p = Pattern.compile("^(?:(?=.*[a-z])(?:(?=.*[A-Z])(?=.*[\\d\\W])|(?=.*\\W)(?=.*\\d))|(?=.*\\W)(?=.*[A-Z])(?=.*\\d)).{8,}$");
        Matcher m = p.matcher(input);

        return m.find();
    }

    /**
     * Input valid : Katakana/Numbers/Letters
     * 
     * @param input String
     * @return boolean
     */
    public static boolean isFullSize(String input) {
        if (StringUtil.isNull(input)) {
            return true;
        }
        Pattern p = Pattern.compile("^[ァ-ン,０-９,Ａ-ｚ]+$");
        Matcher m = p.matcher(input);

        return m.find();
    }

    /**
     * <p>説明 : Check if String is empty </p> 
     * @author : hung.nq
     * @since : 2018/03/14
     * @param s String
     * @return boolean
     */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().equals("");
    }

    /**
     * 
     * <p>説明 : Check if String is not empty  </p> 
     * @author : hung.nq
     * @since : 2018/03/14
     * @param strs  Array of String
     * @return boolean
     */
    public static boolean isNotEmpty(String... strs) {
        if (strs != null) {
            for (String s : strs) {
                if (isEmpty(s)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check HaftSizeAlphabet
     * 
     * @param input String
     * @return boolean
     */
    public static boolean isHaftSizeAlphabet(String input) {
        if (StringUtil.isNull(input)) {
            return true;
        }
        Pattern p = Pattern.compile("^[A-Z,a-z, ]+$");
        Matcher m = p.matcher(input);

        return m.find();
    }

    /**
     * チェック文字列が数値である
     * 
     * @param input String
     * @return 数値は、trueの場合はnullまたは空白である
     */
    public static boolean isHaftsizeNumeric(String input) {
        if (StringUtil.isNull(input)) {
            return true;
        }
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(input);
        return m.matches();
    }

    /**
     * チェック文字列が数値である
     * 
     * @param input String
     * @return 数値は、trueの場合はnullまたは空白である
     */
    public static boolean isHaftsizeNumericWithSignAndDecimal(String input) {
        if (StringUtil.isNull(input)) {
            return true;
        }
        Pattern p = Pattern.compile("^(-|)(\\d+\\.?\\d*|\\.\\d+)$");
        Matcher m = p.matcher(input);
        return m.matches();
    }

    /**
     * Check string is haft size alphabet and is haft size number
     *
     * @param input String
     * @return true if string is haft size engligh
     */
    public static boolean isHalfSizeAlphabetAndNumber(String input) {
        if (StringUtil.isNull(input)) {
            return true;
        }

        Pattern p = Pattern.compile("^[A-Z,a-z,0-9]+$");
        Matcher m = p.matcher(input);

        return m.find();
    }

    /**
     * Check HalfSizeAlphabetAndNumber
     * 
     * @param val String
     * @return boolean
     */
    public static boolean isHalfSizeAlphabetAndNumber2(String val) {
        if (StringUtil.isNull(val)) {
            return false;
        }
        char[] part = val.toCharArray();
        boolean isEnglish = false;
        for (char c : part) {
            isEnglish = false;
            for (String str : halfWidthEnglish) {
                if (str.equals(String.valueOf(c))) {
                    isEnglish = true;
                    break;
                }
            }
            if (!isEnglish) {
                return false;
            }
        }
        return true;
    }

    /**
     * check HalfAlphabet or Number or Symbol
     * 
     * @param input String
     * @return boolean
     */
    public static boolean isHalfAlphabetNumberSymbol(String input) {
        if (StringUtil.isNull(input)) {
            return false;
        }
        char[] part = input.toCharArray();
        boolean isHalfAlphabetNumberSymbol = false;
        for (char c : part) {
            isHalfAlphabetNumberSymbol = false;
            for (String str : halfAlphabetNumberSymbol) {
                if (str.equals(String.valueOf(c))) {
                    isHalfAlphabetNumberSymbol = true;
                    break;
                }
            }
            if (!isHalfAlphabetNumberSymbol) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check format of date string.
     * 
     * @param dateString            Date string value
     * @param formatString          Format date
     * @return boolean
     * true : string is valid date format
     * false : string is invalid date format
     */
    public static boolean isDateFormat(String dateString, String formatString) {
        if (StringUtil.isNull(dateString)) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
        try {
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * 
     * <p>説明 : valid exist date</p> 
     * @author : minh.ls
     * @param dateString String date
     * @param formatString date parten
     * @return true if exist
     */
    public static boolean isDateExist(String dateString, String formatString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(dateString);
        } catch (ParseException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    /**
     * Validate email address.
     * 
     * @param email Eメール
     * @return Eメールチェック結果
     */
    public static boolean isValidEmailAddress(String email) {
        if (StringUtil.isNull(email)) {
            return true;
        }
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\"
                + ".[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * integer pattern
     */
    private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");

    /**
     * FIXME　Check String input is number
     *
     * @param number FIXME　String
     * @return true: FIXME　String input is number. false: String input is not number
     */
    public static boolean isSignNumber(String number) {
        if (StringUtil.isNull(number)) {
            return true;
        }

        Pattern p = Pattern.compile("^[-]?[0-9]+$");
        Matcher m = p.matcher(number);

        return m.find();
    }

    /**
     * 
     * <p>説明 : Check String is Integer </p> 
     * @author : hung.nq
     * @since : 2018/03/14
     * @param str String
     * @return boolean
     */
    public static boolean isInteger(String str) {
        return !(str == null || str.length() == 0) && INT_PATTERN.matcher(str).matches();
    }

    /**
     * FIXME Check char input is number
     *
     * @param c char
     * @return true: char is number. false: char not number
     */
    public static boolean isDigit(char c) {
        int x = (int) c;
        if ((x >= 48 && x <= 57) || x == 45) {
            return true;
        }
        return false;
    }

    /**
     * <p>説明 : FIXME Check giá nhập có phải integer không</p> 
     * @author : truong.pq
     * @since : 2018/03/07
     * @param input value String
     * @return true : FIXME nếu giá trị nhập vào là số nguyên
     */
    public static boolean isIntegerNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * <p>説明 : FIXME Check SQL injection</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since 2018/02/07
     * @param value         script sql
     * @return True if error sql injection
     */
    public static boolean isSQLInjection(String value) {
        boolean isValid = false;
        if (StringUtils.isEmpty(value)) {
            return isValid;
        }
        String[] keyInject = { "create", "insert", "grant", "delete", "drop", "truncate", "select", ";", CommonConstants.SPACE_HAFTSIZE };
        for (String string : keyInject) {
            String lowerCase = value.toLowerCase();
            if (lowerCase.indexOf(string + CommonConstants.SPACE_HAFTSIZE) >= 0) {
                isValid = true;
            }
        }
        return isValid;
    }
/*

    *//**
     * <p>説明 : データ重複チェック</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since  2018/02/12
     * @param <T> タイプパラメータ
     * @param objectList オブジェクト一覧
     * @return <T> チェック結果
     *//*
    public static <T> boolean checkDuplicateFieldswithAnotation(List<T> objectList) {
        if (CollectionUtils.isEmpty(objectList)) {
            return false;
        }

        Set<T> set = new TreeSet<T>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (o1 instanceof BaseDTO || o1 instanceof BaseFormatDTO) { // For CSV
                    Field[] tempFields = o1.getClass().getDeclaredFields();
                    // Count key
                    int totalFields = 0;
                    String fieldShowMessage = null;
                    for (Field field : tempFields) {
                        if (field.isAnnotationPresent(UniqueKey.class)) {
                            totalFields++;
                            // Show message first field
                            if (fieldShowMessage == null) {
                                fieldShowMessage = field.getName();
                            }
                        }
                    }
                    int countFieldDuplicate = 0;
                    for (Field field : tempFields) {
                        try {
                            if (field.isAnnotationPresent(UniqueKey.class)) {
                                Object invoke1 = new PropertyDescriptor(field.getName(), o1.getClass()).getReadMethod().invoke(o1);
                                Object invoke2 = new PropertyDescriptor(field.getName(), o2.getClass()).getReadMethod().invoke(o2);
                                if (invoke1 != null && invoke2 != null && invoke1.equals(invoke2)) {
                                    countFieldDuplicate += 1;
                                }
                            }

                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
                            e.printStackTrace();
                        }
                    }

                    // Check duplicate add message error
                    if (totalFields > 0 && countFieldDuplicate == totalFields) {
                        BaseFormatDTO t1 = (BaseFormatDTO) o1;
                        BaseFormatDTO t2 = (BaseFormatDTO) o2;
                        if (!t1.getRowNo().equals(t2.getRowNo())) {
                            UniqueKey annotation;
                            String message = "";
                            try {
                                annotation = t1.getClass().getDeclaredField(fieldShowMessage).getAnnotation(UniqueKey.class);
                                message = annotation.message();
                            } catch (NoSuchFieldException | SecurityException e) {
                                // do nothing
                            }
                            MessageCSV messageCSV = null;
                            if (StringUtils.EMPTY.equals(message)) {
                                messageCSV = new MessageCSV(MessageConstants.MSG_9);
                            } else {
                                messageCSV = new MessageCSV(message);
                            }

                            Map<String, MessageCSV> messages1 = t1.getMessages();
                            if (messages1 == null) {
                                messages1 = new HashMap<>();
                            }
                            messages1.put(fieldShowMessage, messageCSV);
                            t1.setIsValid(false);
                            t1.setMessages(messages1);
                            //
                            Map<String, MessageCSV> messages2 = t2.getMessages();
                            if (messages2 == null) {
                                messages2 = new HashMap<>();
                            }
                            messages2.put(fieldShowMessage, messageCSV);
                            t2.setIsValid(false);
                            t2.setMessages(messages2);
                            return 0;
                        } else {
                            return 1;
                        }
                    } else {
                        return 1;
                    }
                } else if (o1 instanceof Integer || o1 instanceof String) {
                    if (o1.equals(o2)) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        });
        set.addAll(objectList);
        final ArrayList<T> newList = new ArrayList<T>(set);
        return newList.size() < objectList.size();
    }

    *//**
     * 
     * <p>説明 : マルチファイルチェック</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since  2018/02/12
     * @param file ファイル
     * @param fileName ファイル名
     * @param fileExtention ファイル拡張
     * @return String メッセージID
     *//*
    public static String checkMultipartFile(MultipartFile file, String fileName, FileExtension fileExtention) {

        // ファイル名をチェック
        if (StringUtils.isNotEmpty(fileName) && !file.getOriginalFilename().equals(fileName)) { // Wrong file name
            return MessageConstants.MSG_SC101;
        }

        // ファイルextentionsをチェック
        List<FileExtension> fileExtensions = Arrays.asList(FileExtension.values());
        if (!fileExtensions.contains(fileExtention)) {
            return MessageConstants.MSG_1413;
        }
        return null;
    }

    *//**
     * 
     * <p>説明 : compareDates</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since 2018/03/08
     * @param date1         Start date
     * @param date2         End date
     * @return      True end >= start
     *//*
    public static boolean compareDates(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse(date1, formatter);
        LocalDate stop = LocalDate.parse(date2, formatter);
        if (stop.isAfter(start) || stop.isEqual(start)) {
            return true;
        } else {
            return false;
        }
    }

    *//**
     * 
     * <p>説明 : compareDates</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since 2018/03/08
     * @param date1         Start date
     * @param date2         End date
     * @return      True end >= start
     *//*
    public static boolean compareDates(LocalDate date1, LocalDate date2) {
        if (date2.isAfter(date1) || date1.isEqual(date2)) {
            return true;
        } else {
            return false;
        }
    }

    *//**
     * 
     * <p>説明 : checkLanguage</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @since 2018/02/12
     * @param tTauPtsDTO   Tau outer part category
     * @param <T>		Object extends masterBaseDTO
     * @return True if it is equals 1234 or empty otherwise False
     *//*
    public static <T extends MasterBaseDTO> boolean checkLanguage(T t) {
        String strLg = "";
        for (String lang : CommonConstants.LIST_LANGUAGE_CD) {
            String nmByLg = t.getNmByLg(lang);
            if (nmByLg != null && StringUtils.isNotEmpty(nmByLg.trim())) {
                strLg += "1";
            }
        }
        logger.info("checkLanguage {}", strLg);
        // 入力ファイルの翻訳情報がある場合、４言語分ない場合エラー
        if (!"1111".equals(strLg) && !"".equals(strLg)) {
            return false;
        }
        return true;
    }

    *//**
     * <p>説明 : FIXME Check giá trị nhập vào có phải numberic hay ko</p> 
     * @author : truong.pq
     * @since : 2018/03/20
     * @param input inputValue
     * @return FIXME True : giá trị rỗng hoặc hợp lệ. False : không hợp lệ
     *//*
    public static boolean isNumeric(String input) {
        if (StringUtil.isEmpty(input)) {
            return true;
        }

        try {
            Double.parseDouble(input.replaceAll(",", ""));
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    *//**
     * <p>説明 : Check duplicate by list String, Integer..</p> 
     * @author hung.pd
     * @param list List
     * @param condition Value filter
     * @return True if data has more one element.
     *//*
    public static boolean checkDuplicateInList(List list, Object condition) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        List collect = (List) list.stream().filter(line -> condition.equals(line)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(collect) && collect.size() > 1) {
            return true;
        } else {
            return false;
        }
    }

    public static <T> List<T> SortList(List<T> objectList) {
        // Sort list before check duplicate
        List<T> objectListSort = objectList.stream().sorted(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (o1 instanceof BaseFormatDTO) {
                    Field[] fields = o1.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(HeaderCsv.class)) {
                            Object invoke1;
                            try {
                                invoke1 = new PropertyDescriptor(field.getName(), o1.getClass()).getReadMethod().invoke(o1);
                                Object invoke2 = new PropertyDescriptor(field.getName(), o2.getClass()).getReadMethod().invoke(o2);
                                if ((invoke1 == null && invoke2 != null) || (invoke1 != null && invoke2 == null)
                                        || (invoke1 != null && !invoke1.equals(invoke2))) {
                                    return 1;
                                } else {
                                    return 0;
                                }
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
                            }
                        }
                    }
                }
                return 0;

            }
        }).collect(Collectors.toList());
        return objectListSort;
    }

    *//**
     * 
     * <p>説明 : Check empty List</p> 
     * @author : KhanhNM
     * @since : 2018/05/31
     * @param collection List<?>
     * @return boolean
     *//*
    public static boolean isEmptyCollection(List<?> collection) {
        return (null == collection) || collection.size() == 0;
    }
*/
}

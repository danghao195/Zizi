package vn.avg.zizi.common;

import java.util.Arrays;
import java.util.List;

/**
 * <p>File name : Constants</p>
 * <p>Description : Constants</p>
 * @author hao.dv
 * @since 2018/08/16
 */
public class CommonConstants {
    // root directory
    /**  ROOT_DIRECTORY */
    public static final String ROOT_DIRECTORY = "sites/";

    /**  ROOT_DIRECTORY_MASTER */
    public static final String ROOT_DIRECTORY_MASTER = "sites/master/";

    /**  ROOT_DIRECTORY_SHOHIN */
    public static final String ROOT_DIRECTORY_SHOHIN = "sites/item/";

    /**  ROOT_DIRECTORY_CHUMON */
    public static final String ROOT_DIRECTORY_CHUMON = "sites/order/";

    /**  ROOT_DIRECTORY_HANBAI */
    public static final String ROOT_DIRECTORY_HANBAI = "sites/sale/";

    /**  ROOT_DIRECTORY_HANSOKU */
    public static final String ROOT_DIRECTORY_HANSOKU = "sites/hansoku/";

    public static final String ROOT_DIRECTORY_POPUP = "sites/popup/";

    public static final String ROOT_DIRECTORY_INVOICE = "sites/invoice/";

    /**  ROOT_DIRECTORY_GAIBU */
    public static final String ROOT_DIRECTORY_GAIBU = "sites/out/";

    // common constan
    /** Message type enum*/
    public static enum MessageType {
        /**Waring*/
        WARN,

        /**Error*/
        ERROR,

        /**Information*/
        INFO
    };

    /**
     * true value
     */
    public static final String TRUE_VAL = "TRUE";

    /**
     * false value
     */
    public static final String FALSE_VAL = "FALSE";

    // Langguage code
    /** LG_CD_JA */
    public static final String LG_CD_JA = "ja";

    /** LG_CD_EN */
    public static final String LG_CD_EN = "en";

    /** LG_CD_RU */
    public static final String LG_CD_RU = "ru";

    /** LG_CD_ES */
    public static final String LG_CD_ES = "es";

    /** LIST_LANGUAGE_CD */
    public static final List<String> LIST_LANGUAGE_CD = Arrays.asList(LG_CD_JA, LG_CD_EN, LG_CD_RU, LG_CD_ES);

    /** DB_AVAILABLE */
    public static final String DB_AVAILABLE = "0";

    /**  DB_DELETED */
    public static final String DB_DELETED = "1";

    /** NUM_0 */
    public static final int NUM_0 = 0;

    /** NUM_4 */
    public static final int NUM_4 = 4;

    /** NUM_6 */
    public static final int NUM_6 = 6;

    /** NUM_8 */
    public static final int NUM_8 = 8;

    /** NUM_640 */
    public static final int NUM_640 = 640;

    /** NUM_480 */
    public static final int NUM_480 = 480;

    /** NUM_120 */
    public static final int NUM_120 = 120;

    /** NUM_90 */
    public static final int NUM_90 = 90;

    /**  SORT_AZ */
    public static final String ASC = "ASC";

    /**  SORT_ZA */
    public static final String DESC = "DESC";

    /**  list sort type */
    public static final List<String> SORT_TYPE_LIST = Arrays.asList(ASC, DESC);

    /** BLANK */
    public static final String BLANK = "";

    /** EMPTY */
    public static final String EMPTY = "";

    /** TAB */
    public static final String TAB = "\t";

    /** The Constant COLON */
    public static final String COLON = ":";

    /** SINGLE_QUOTE */
    public static final String SINGLE_QUOTE = "'";

    /** DOUBLE_QUOTE*/
    public static final String DOUBLE_QUOTE = "\"";

    /** EMPTY */
    public static final String SPACE_HAFTSIZE = " ";

    /** COMMA */
    public static final String COMMA = ",";

    /** UNDERSCORE */
    public static final String UNDERSCORE = "_";

    /** DOT */
    public static final String DOT = ".";

    /** DATE_FORMAT_YYYYMMDD */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy/MM/dd";

    /** GRID_ROW_COUNT */
    public static final Integer GRID_ROW_COUNT = 5;

    /** PAGE SHOW COUNT */
    public static final Integer PAGE_SHOW_NUM = 10;

    /** MAX TINYINT VALUE */
    public static final int MAX_TINYINT_VALUE_MYSQL = 127;

    /** MAX SMALLINT VALUE */
    public static final int MAX_SMALLINT_VALUE_MYSQL = 32767;

    /** MAX MEDIUMINT VALUE */
    public static final int MAX_MEDIUMINT_VALUE_MYSQL = 8388607;

    /** MAX INT VALUE */
    public static final int MAX_INT_VALUE_MYSQL = 2147483647;

    /** MAX BIGINT VALUE */
    public static final int MAX_BIGINT_VALUE_MYSQL = 2 ^ 64 - 1;

    /** id next record detail */
    public static final String BTN_NEXT_ID = "idNext";

    /** id previous record detail */
    public static final String BTN_PREV_ID = "idPrev";

    /** submitSuccess Attribute. */
    public static final String SUBMIT_SUCCESS = "submitSuccess";

    /** CURRENT_TAB */
    public static final String CURRENT_TAB = "currentTab";

    /** UPDATE_TIME */
    public static final String UPDATE_TIME = "updateTime";

    /** IS_BACK*/
    public static final String IS_BACK = "isBack";

    /** UPDATE_NOTIFY*/
    public static final String MESSAGE = "message";

    /** csv extension */
    public static final String CSV_EXTENTION = ".csv";

    public static final String CSVEXTENTION = "csv";

    /** Zip extension */
    public static final String ZIP_EXTENTION = ".zip";

    /** Zip extension */
    public static final String PDF_EXTENTION = "pdf";

    /** shift_jis charset*/
    public static final String SHIFT_JIS_CHARSET = "SHIFT_JIS";

    /** ISO8859_1 charset*/
    public static final String ISO8859_1_CHARSET = "ISO8859_1";

    /** REDIRECT*/
    public static final String REDIRECT = "redirect:";

    /** FORWARD*/
    public static final String FORWARD = "forward:";

    /** title page*/
    public static final String TITLE = "title";

    /** TAB_MENU*/
    public static final String TAB_MENU = "tabmenu";

    /** TAB_MENU_LABEL*/
    public static final String TAB_MENU_LABEL = "tabmenulabel";
    /** LEFT MENU*/
    public static final String LEFT_MENU = "tauLeftMenu";

    /** not exist index */
    public static final Integer NOT_EXIST_INDEX = -1;

    /** csvFile*/
    public static final String FIELD_CSV_FILE = "csvFile";

    /** error action */
    public static final int ERROR_ACTION = 1;

    /** 更新画面コード*/
    public static final int DETAIL_ACTION = 2;

    /** 更新画面コード*/
    public static final int UPDATE_ACTION = 3;

    /** 登録画面コード*/
    public static final int REGISTER_ACTION = 4;

    /** 削除画面コード*/
    public static final int DELETE_ACTION = 5;

    /** 更新画面コード*/
    public static final String ACTION_SUCCESS = "actionSuccess";

    /** 更新画面コード*/
    public static final String ACTION_ADD = "addSuccess";


    /**CSV_CONTENT_TYPE フラグ*/
    public static final String CSV_CONTENT_TYPE = "application/octet-stream";

    /**CSV_CONTENT_TYPE フラグ*/
    public static final String ZIP_CONTENT_TYPE = "application/zip";

    /** NUMBER_FORMAT_4_DIGITAL*/
    public static final String NUMBER_FORMAT_4_DIGITAL = "%04d";

    /** NUMBER_FORMAT_5_DIGITAL*/
    public static final String NUMBER_FORMAT_5_DIGITAL = "%05d";

    /** NUMBER_FORMAT_6_DIGITAL*/
    public static final String NUMBER_FORMAT_6_DIGITAL = "%06d";

    /** MSG_RIQUIRED_INPUT_ERR*/
    public static final String NUMBER_FORMAT_7_DIGITAL = "%07d";

    /**存在しないコード*/
    public static final String NOT_EXIST_CD_STR = "-1";

    /** MSG_DUPLICATED_DATA */
    public static final String MSG_DUPLICATED_DATA = "ER0000";

    /**THREE_NUMBER */
    public static final int THREE_NUMBER = 3;

    /**ONE_NUMBER */
    public static final int ONE_NUMBER = 1;

    /**TOW_NUMBER */
    public static final int TWO_NUMBER = 2;

    /** CTRY_2DIGIT_JAPAN */
    public static final String CTRY_2DIGIT_JAPAN = "JA";


    /**THREE_NUMBER */
    public static final String UPLOAD_FILE_CSV_FORM = "uploadFileCSVForm";

    /**UTF8_CHARSET */
    // public static final String UTF8_CHARSET = "UTF-8";
    public static final String UTF8_CHARSET = "UTF8";

    /** action */
    public static final String ACTION = "action";

    /** STR_MONTH */
    public static final String STR_MONTH_PATTERN = "%02d月";

    /** STR_YEAR */
    public static final String STR_YEAR_PATTERN = "%s年/%s";

    /** MSG_S0106 */
    public static final String MSG_S0106 = "";

    /** SEARCH_DTO_NAME */
    public static final String SEARCH_DTO_NAME = "SEARCH_DTO_NAME";

    /** searchScreen */
    public static final String SEARCH_SCREEN = "searchScreen";

    /** insertScreen */
    public static final String INSERT_SCREEN = "insertScreen";

    /** createScreen */
    public static final String CREATE_SCREEN = "createScreen";

    /** updateScreen */
    public static final String UPDATE_SCREEN = "updateScreen";

    /** updateConfirmScreen */
    public static final String UPDATE_CONFIRM_SCREEN = "updateConfirmScreen";

    /** reportScreen */
    public static final String REPORT_SCREEN = "reportScreen";

    /** deleteScreen */
    public static final String DELETE_SCREEN = "deleteScreen";

    /** csvScreen */
    public static final String CSV_SCREEN = "csvScreen";

    /** detailScreen */
    public static final String DETAIL_SCREEN = "detailScreen";

    /** searchForm */
    public static final String SEARCH_FORM = "searchForm";


    /** searchFormInDetailScreen */
    public static final String SEARCH_FORM_IN_DETAIL_SCREEN = "searchFormInDetailScreen";

    /** searchFormInDetailScreen */
    public static final String SEARCH_DTO_IN_DETAIL_SCREEN = "searchDtoInDetailScreen";

    /** UPDATE_FORM */
    public static final String UPDATE_FORM = "updateForm";

    /** APP_FORM */
    public static final String APP_FORM = "appForm";

    /** VO */
    public static final String VO = "VO";

    /** OUTPUT */
    public static final String OUTPUT = "OUTPUT";

    /** VO_CLAZZ */
    public static final String VO_CLAZZ = "VO_CLAZZ";

    /** SEARCH_CLAZZ */
    public static final String SEARCH_CLAZZ = "SEARCH_CLAZZ";

    /** UPDATE_FOM_CLAZZ */
    public static final String UPDATE_FOM_CLAZZ = "UPDATE_FOM_CLAZZ";

    /** OUTPUT_CLAZZ */
    public static final String OUTPUT_CLAZZ = "OUTPUT_CLAZZ";

    /** RESULT_CD */
    public static final String RESULT_CD = "LIST_GRADE_CD";

    /** DTO_SEARCH_CLAZZ */
    public static final String DTO_SEARCH_CLAZZ = "DTO_SEARCH_CLAZZ";

    /** DTO_SEARCH_CLAZZ_IN_DETAIL_SCREEN */
    public static final String DTO_SEARCH_CLAZZ_IN_DETAIL_SCREEN = "DTO_SEARCH_CLAZZ_IN_DETAIL_SCREEN";

    /** DTO_CLAZZ */
    public static final String DTO_CLAZZ = "DTO_CLAZZ";

    /** DTO_NAME */
    public static final String DTO_NAME = "DTO_NAME";

    /** CSV_SESSION_NAME */
    public static final String CSV_SESSION_NAME = "CSV_SESSION_NAME";

    /** DELETE_FORM */
    public static final String DELETE_FORM = "DELETE_FORM";

    /** DELETE_FORM_CLASS */
    public static final String DELETE_FORM_CLASS = "DELETE_FORM_CLASS";

    /** COPY_SCREEN */
    public static final String COPY_SCREEN = "copyScreen";

    /** IMAGE_SCREEN*/
    public static final String UPLOAD_IMAGE_SCREEN = "uploadImageScreen";

    /** UPLOAD_IMAGE_DTO_CLAZZ*/
    public static final String UPLOAD_IMAGE_DTO_CLAZZ = "uploadImageDtoClazz";

    /** COPY_FROM */
    public static final String COPY_FROM = "copyForm";

    /** COPY_FROM CLASS*/
    public static final String COPY_FROM_CLAZZ = "COPY_FROM_CLAZZ";

    /** VIEW_TYPE_KEY */
    public static final String VIEW_TYPE_KEY = "VIEW_TYPE_KEY";

    /** MASTER_VIEW_TYPE */
    public static String MASTER_VIEW_TYPE = "MASTER_VIEW_TYPE";

    /** PRODUCT_VIEW_TYPE */
    public static String PRODUCT_VIEW_TYPE = "PRODUCT_VIEW_TYPE";

    /** HANBAI_VIEW_TYPE */
    public static String HANBAI_VIEW_TYPE = "HANBAI_VIEW_TYPE";

    /** CHUMON_VIEW_TYPE */
    public static String CHUMON_VIEW_TYPE = "CHUMON_VIEW_TYPE";

    /**Shohin page view*/
    public static String SHOHIN_VIEW_TYPE = "SHOHIN_VIEW_TYPE";
    // HoaiNT - MNG03050X
    /** INVOICE_VIEW_TYPE */
    public static final String INVOICE_VIEW_TYPE = "INVOICE_VIEW_TYPE";
    public static final String USE_CONFIG = "USE_CONFIG";

    /**THREE_NUMBER */
    public static final String DOWNLOAD = "/downloadCSV";

    /** CUR_DISP_SET_FLG */
    public static final String CUR_DISP_SET_FLG = "1";

    /**ELEMENT_TYPE_LINK*/
    public static final String ELEMENT_TYPE_LINK = "link";

    /**ELEMENT_TYPE_HIDDEN*/
    public static final String ELEMENT_TYPE_HIDDEN = "hidden";

    public static final String ELEMENT_TYPE_MESSAGE_RESOURCE = "messageResource";
    public static final String ELEMENT_TYPE_DATE = "date";
    public static final String ELEMENT_TYPE_YES_NO_FLG = "yesNoFlg";

    /** JP以外の頭文字で始まる場合   */
    public static final String JAPANESE_SHORT_NM = "JP";

    /**
     * money format 
     */
    public static final String MONEY_FORMAT = "moneyFormat";

    /** Start year*/
    public static final int START_YEAR = 1970;

    /** DATE_TIME_PATTERN */
    public static final String DATE_TIME_PATTERN = "yyyy/MM/dd HH:mm:ss";

    /** FORMAT_DATE_TIME_PATTERN */
    public static final String FORMAT_DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    public static final String APP_TRANSFER_DTO = "APP_TRANSFER_DTO";

    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_ERROR = -1;
    public static final int STATUS_SYSTEM_ERROR = -1;


    /** Upload csv form*/
    public static final String UPLOAD_CSV_FORM = "uploadCSVForm";

    /** SCREEN_MESSAGE */
    public static final String SCREEN_MESSAGE = "screenMessage";

    /** STATUS 0 */
    public static final String STATUS_0 = "0";

    /** STATUS 1*/
    public static final String STATUS_1 = "1";

    /** STATUS 2*/
    public static final String STATUS_2 = "2";

    /** STATUS 2*/
    public static final String STATUS_3 = "3";

    /** STATUS 9*/
    public static final String STATUS_9 = "9";

    /** Flag 0*/
    public static final String FLG_0 = "0";

    /** Flag 1*/
    public static final String FLG_1 = "1";



    /** DATE_PATTERN */
    public static final String DATE_PATTERN = "yyyy/MM/dd";

    public static final String TRADING_TERM_TYPE_TWO = "2";
    public static final String TRADING_TERM_TYPE_THREE = "3";
    public static final String TRADING_TERM_TYPE_FOUR = "4";
    public static final String TRADING_TERM_TYPE_FIVE = "5";
    public static final String TRADING_TERM_TYPE_SIX = "6";



    /** SCREEN_ACTION */
    public static final String SCREEN_ACTION = "screenAction";

    /** SEPARATORCHAR */
    public static final String SEPARATORCHAR = "/";









    /** LIST_IMAGE_EXTENSION */
    public static final List<String> LIST_IMAGE_EXTENSION = Arrays.asList("PNG", "JPG", "GIF", "BMP");

    /** MULTI_FILE_PULOAD */
    public static final String MULTI_FILE_UPLOAD = "/multiFileUpload";

    /** MAX_SIZE_FILE_UPLOAD_5MB */
    public static final long MAX_SIZE_FILE_UPLOAD_5MB = 5 * 1024 * 1024;


}

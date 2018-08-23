/**
 * ファイル名 : AppController.java
 * 作成者 : duc.bv
 * 作成日時 : 2018/5/31
 * Copyright ? 2017-2018 TAU Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.controller;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.ApplicationContext;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

//import com.google.gson.Gson;

import vn.avg.zizi.common.CommonConstants;
import vn.avg.zizi.common.CommonConstants.MessageType;
import vn.avg.zizi.dto.BaseDTO;
import vn.avg.zizi.service.CommonService;
import vn.avg.zizi.utils.CheckUtil;

/**
 * <p>ファイル名 : AppController</p>
 * <p>説明 : BaseController controller class</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
public abstract class AppController {

    /** logger*/
    protected final Logger logger = LogManager.getLogger(this.getClass());

    /** Screen error */
    protected static final String SCREEN_ERROR = "Screen error!";

    /** SCREEN_MESSAGE */
    protected static final String SCREEN_MESSAGE = "screenMessage";

    /** BINDING_RESULT */
    protected static final String BINDING_RESULT = "bindingResult";

//    /** AbstractValidatorのインスタンス */
//    private AppValidator validator;

    /** ユーザーのログイン情報" */
    private static final String USER_LOGIN_INFOR = "userLoginInfor";

    /** ApplicationContextのインスタンス */
    @Autowired
    private ApplicationContext context;

    /** HttpSession */
    @Autowired
    protected HttpSession session;

    /** modelMapper*/
    protected ModelMapper modelMapper;

    @Autowired
    protected CommonService commonService;

    /** BindingResult */
//    @Autowired
//    protected BindingResult bindingResult;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true) {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                super.setAsText(StringUtils.replaceChars(text, CommonConstants.COMMA, StringUtils.EMPTY));
            }
        });

        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true) {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                super.setAsText(StringUtils.replaceChars(text, CommonConstants.COMMA, StringUtils.EMPTY));
            }
        });

    }


    /** constructor method */
    public AppController() {
    }

    /**
     * <p>説明 : init variable</p>
     * @author bp.truong.pq
     * @since 2017/11/25
     */
    @PostConstruct
    private void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
            public boolean applies(MappingContext<Object, Object> pContext) {
                if (null == pContext.getSource() || null == pContext.getDestinationType()) {
                    return false;
                }
                /*
                 * if (pContext.getSourceType().equals(String.class)) { if
                 * (CommonConstants.BLANK.equals(pContext.getSource().toString())) { return false; } }
                 */
                if (pContext.getSourceType().equals(String.class)
                        && (Integer.class.equals(pContext.getDestinationType()) || int.class.equals(pContext.getDestinationType()))) {
                    if (CheckUtil.isSignNumber(pContext.getSource().toString())) {
                        return true;
                    }
                    return false;
                }
                return true;
            }
        });
        // convert String -> LocalDate
        Converter<String, LocalDate> localDateConverter = new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> context) {
                String source = context.getSource();
                if (CheckUtil.isEmpty(source) || !CheckUtil.isDateFormat(source, "yyyy/MM/dd"))
                    return null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                return LocalDate.parse(source, formatter);
            }
        };
        modelMapper.addConverter(localDateConverter);

        // convert String -> LocalDateTime
        Converter<String, LocalDateTime> localDateTimeConverter = new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> context) {
                String source = context.getSource();
                if (CheckUtil.isEmpty(source) || !CheckUtil.isDateFormat(source, "yyyy/MM/dd HH:mm"))
                    return null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                return LocalDateTime.parse(source, formatter);
            }

        };
        modelMapper.addConverter(localDateTimeConverter);

        // convert LocalDate -> String
        Converter<LocalDate, String> stringConverter = new Converter<LocalDate, String>() {
            @Override
            public String convert(MappingContext<LocalDate, String> context) {
                LocalDate source = context.getSource();
                if (CheckUtil.isEmpty(source.toString()))
                    return null;
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                Date date = Date.from(source.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                String reportDate = df.format(date);
                return reportDate;
            }
        };
        modelMapper.addConverter(stringConverter);

        // convert LocalDateTime -> String
        Converter<LocalDateTime, String> stringTimeConverter = new Converter<LocalDateTime, String>() {
            @Override
            public String convert(MappingContext<LocalDateTime, String> context) {
                LocalDateTime source = context.getSource();
                if (CheckUtil.isEmpty(source.toString()))
                    return null;
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
                String reportDate = df.format(date);
                return reportDate;
            }
        };

        modelMapper.addConverter(stringTimeConverter);
        
        // convert Long -> String
        Converter<Long, String> longStringConverter = new Converter<Long, String>() {
            @Override
            public String convert(MappingContext<Long, String> context) {
                Long source = context.getSource();
                if (source == null) {
                    return null;
                }
                return String.valueOf(source);
            }
        };
        
        modelMapper.addConverter(longStringConverter);
        
        // convert String -> Long
        Converter<String, Long> stringLongConverter = new Converter<String, Long>() {
            @Override
            public Long convert(MappingContext<String, Long> context) {
                String source = context.getSource();
                if (StringUtils.isEmpty(source)) {
                    return null;
                }
                return Long.parseLong(source);
            }
        };
        
        modelMapper.addConverter(stringLongConverter);
        
        // convert Integer -> String
        Converter<Integer, String> integerStringConverter = new Converter<Integer, String>() {
            @Override
            public String convert(MappingContext<Integer, String> context) {
                Integer source = context.getSource();
                if (source == null) {
                    return null;
                }
                return String.valueOf(source);
            }
        };

        modelMapper.addConverter(integerStringConverter);
        
        // convert String -> Integer
        Converter<String, Integer> stringIntegerConverter = new Converter<String, Integer>() {
            @Override
            public Integer convert(MappingContext<String, Integer> context) {
                String source = context.getSource();
                if (StringUtils.isEmpty(source)) {
                    return null;
                }
                return Integer.parseInt(source);
            }
        };
        
        // convert Boolean -> String
        Converter<Boolean, String> booleanSringConverter = new Converter<Boolean, String>() {
            @Override
            public String convert(MappingContext<Boolean, String> context) {
                Boolean source = context.getSource();
                if (BooleanUtils.isTrue(source)) {
                    return CommonConstants.FLG_1;
                } else {
                    return CommonConstants.FLG_0;
                }
            }
        };

        modelMapper.addConverter(booleanSringConverter);
        
        // convert Boolean -> String
        Converter<String, Boolean> stringBooleanConverter = new Converter<String, Boolean>() {
            @Override
            public Boolean convert(MappingContext<String, Boolean> context) {
                String source = context.getSource();
                if (StringUtils.isBlank(source)) {
                    return Boolean.FALSE;
                } else if (CommonConstants.FLG_0.equals(source)) {
                    return Boolean.FALSE;
                } 
                return Boolean.TRUE;
            }
        };
        
        modelMapper.addConverter(stringBooleanConverter);
    }


    /**
     * <p>説明 : Redirect to view name</p>
     * @author bp.truong.pq
     * @since 2017/11/25
     * @param viewName String
     * @return ModelAndView
     */
    protected ModelAndView redirectTo(String viewName) {
        return new ModelAndView("redirect:" + viewName);
    }

    /**
     * <p>説明 : add Error when has logic error</p>
     * @author bp.truong.pq
     * @since 2017/11/25
     * @param result BindingResult
     * @param message String
     */
    public void addLogicError(org.springframework.validation.BindingResult result, String message) {
        result.rejectValue(SCREEN_MESSAGE, message);
    }

    /**
     * <p>説明 : add Error when has logic error</p>
     * @author bp.truong.pq]
     * @since 2017/11/25
     * @param result BindingResult
     * @param messageId String
     * @param paramMsg Object[]
     */
    public void addLogicError(org.springframework.validation.BindingResult result, String messageId, Object[] paramMsg) {
        result.rejectValue(SCREEN_MESSAGE, messageId, paramMsg, "errorLogic");

    }

    /**
     * 
     * <p>説明 : addObjectToSession</p> 
     * @author : minh.nt
     * @since 2018/01/24
     * @param session HttpSession
     * @param object Object
     * @param name  String
     */
    protected void addObjectToSession(HttpSession session, Object object, String name) {
        session.setAttribute(name, object);
    }

    /**
     * 
     * <p>説明 : addObjectToSession</p> 
     * @author : minh.nt
     * @since 2018/01/24
     * @param object Object
     * @param name  String
     */
    protected void addObjectToSession(String name, Object object) {
        session.setAttribute(name, object);
    }

    /**
     * 
     * <p>説明 : getObjectFromSession</p> 
     * @author : minh.nt
     * @since 2018/01/24
     * @param session HttpSession
     * @param name String
     * @return Object
     */
    protected Object getObjectFromSession(HttpSession session, String name) {
        return session.getAttribute(name);
    }

    /**
     * 
     * <p>説明 : getObjectFromSession</p> 
     * @author : minh.nt
     * @since 2018/01/24
     * @param name String
     * @return Object
     */
    protected Object getObjectFromSession(String name) {
        return session.getAttribute(name);
    }

    /**
     * 
     * <p>説明 : removeObjectFromSession</p> 
     * @author : minh.nt
     * @since 2018/01/24
     * @param session HttpSession
     * @param name String
     */
    protected void removeObjectFromSession(HttpSession session, String name) {
        session.removeAttribute(name);
    }

    /**
     * 
     * <p>説明 : removeObjectFromSession</p> 
     * @author : minh.nt
     * @since 2018/01/24
     * @param name String
     */
    protected void removeObjectFromSession(String name) {
        session.removeAttribute(name);
    }


    /**
     * 
     * <p>説明 : initial model and View with view name</p> 
     * @author : hung.pd
     * @since 2018/05/31
     * @param modelAndView         Template
     * @param strings 
     *                  first element path
     *                  second element view
     * @return
     */
    protected void setViewName(ModelAndView modelAndView, String... strings) {
        String rootPath = "";
        String viewName = "";
        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            String preViewName = modelAndView.getViewName();
            if (preViewName != null) {
                rootPath = preViewName.substring(0, preViewName.lastIndexOf("/") + 1);
            }
        }

        if (strings.length == 1) {
            viewName = rootPath + strings[0];
            logger.debug("Redirect to view Name: {}", viewName);
        }

        if (strings.length == 2) {
            viewName = strings[0] + strings[1];
            logger.debug("View Name: {}", viewName);
        }
        modelAndView.setViewName(viewName);
    }



    /**
     * <p>説明 : POPUPのModelAndViewを作成</p>
     *
     * @author duc.bv
     * @since 2018/5/31
     * @param screenId {@link String}
     * @return ModelAndView {@link ModelAndView}
     */
    protected ModelAndView createViewNamePopup(String screenId) {
        ModelAndView modelAndView = new ModelAndView();
        this.setViewName(modelAndView, CommonConstants.ROOT_DIRECTORY_POPUP, screenId);
        modelAndView.addObject(CommonConstants.TITLE, getTitle());
        return modelAndView;
    }


    /**
     * <p>説明 : FIXME Set title page</p> 
     * @author : hung.pd
     * @since 2018/02/09
     * @return String               Title
     */
    public abstract String getTitle();

    /**
     * <p>説明 : FIXME TabMenu page</p> 
     * @author : hung.pd
     * @since 2018/02/09
     * @return String[]
     */
    protected String[] getTabMenu() {
        return null;
    }

    /**
     * <p>説明 : FIXME getTabMenuLabel page</p> 
     * @author : hung.pd
     * @since 2018/02/09
     * @return String[]
     */
    protected String[] getTabMenuLabel() {
        return null;
    }

}

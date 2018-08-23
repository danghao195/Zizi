/**
 * ファイル名 : CommonService.java
 * 作成者 : linh.ptn
 * 作成日時 : 2018/08/21
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import vn.avg.zizi.common.CommonConstants;
import vn.avg.zizi.dto.SelectItemDTO;
import vn.avg.zizi.utils.CheckUtil;

/**
 * <p>File name : CommonService</p>
 * <p>Description : CommonService</p>
 * @author hao.dv
 * @since 2018/08/21
 */
@Service
public class CommonService extends BaseService {
    /** logger*/
    private static Logger logger = LogManager.getLogger(CommonService.class);

    /**
     * NUMBER_FORMAT_NONE
     */
    public static final String NUMBER_FORMAT_NONE = "none";

    /**
     * NUMBERING_MAX_NO
     */
    private static final Long NUMBERING_MAX_NO = 1000L;

    /**
     * Environment
     */
    @Autowired
    private Environment env;



    /**
     * rollBack
     * 
     * @param errMessage String
     */
    public void rollBack(String errMessage) {
        RollbackException ex = new RollbackException(errMessage);
        throw ex;
    }

    /**
     * 
     * <p>Description : initial value</p> 
     * @author : hao.dv
     * @since : 2018/08/21 
     */
    @PostConstruct
    private void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
            public boolean applies(MappingContext<Object, Object> pContext) {
                if (null == pContext.getSource() || null == pContext.getDestinationType()) {
                    return false;
                }
                // if (pContext.getSourceType().equals(String.class)) {
                // if (CommonConstants.BLANK.equals(pContext.getSource().toString())) {
                // return false;
                // }
                // }
                // Convert String sang Integer, neu khong hop le thi khong map
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                return LocalDateTime.parse(source, formatter);
            }

        };
        modelMapper.addConverter(localDateTimeConverter);
    }


    /**
     * 
     * <p>Description : map data with date conver</p> 
     * @author : hao.dv
     * @since : 2017/12/27
     * @param source Object
     * @param destination  
     */
    public static void map(Object source, Object destination) {
        ModelMapper modelMapper = new ModelMapper();
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

        modelMapper.map(source, destination);
    }

    /**
     * * <p>Description :  Maps {@code source} to an instance of {@code destinationType}. Mapping is performed according
     * to the corresponding TypeMap. If no TypeMap exists for {@code source.getClass()} and
     * {@code destinationType} then one is created.</p> 
     * @author : hao.dv
     * @since : 2017/12/27
     * @param <D> destination type
     * @param source object to map from
     * @param destinationType type to map to
     * @return fully mapped instance of {@code destinationType}
     */
    public <D> D map(Object source, Class<D> destinationType) {
        ModelMapper modelMapper = new ModelMapper();
        // ２つの対象間のデータをマッピング
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
        return modelMapper.map(source, destinationType);
    }

    /**
     * <p>Description : FIXME Mapping entity list</p> 
     * @author : hao.dv
     * @param entityList {@link Collection} 
     * @param outCLass {@link Class} 
     * @return {@link List} 
     */
    public <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
    }

    /**
     * 
     * <p>Description : dowloadFile</p> 
     * @author hao.dv
     * @since 2018/08/21
     * @since 2018/08/21
     * @param response      HttpResponse
     * @param fileName      File name
     * @param file          File binary
     * @throws IOException 
     */
    public void dowloadFile(HttpServletResponse response, String fileName, byte[] file) throws IOException {
        OutputStream servletOutStream = null;
        try {
            servletOutStream = response.getOutputStream();

            if (fileName.indexOf(CommonConstants.CSV_EXTENTION) > 0) {

                // FIXME set response for download csv
                response.setContentType(CommonConstants.CSV_CONTENT_TYPE);
            } else if (fileName.indexOf(CommonConstants.ZIP_EXTENTION) > 0) {

                // FIXME set response for download zip
                response.setContentType(CommonConstants.ZIP_CONTENT_TYPE);
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, CommonConstants.UTF8_CHARSET) + "\"");
            response.setHeader("Content-Encoding", CommonConstants.SHIFT_JIS_CHARSET);

            // FIXME write file
            servletOutStream.write(file);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            if (servletOutStream != null) {
                servletOutStream.flush();
                servletOutStream.close();
                response.isCommitted();
            }
        }
    }


    /**
     * <p>Description : getForeignSalesDivDTOList</p>
     *
     * @author hao.dv
     * @since 2018/08/21
     * @return List<SelectItemDTO> listForeignSalesDivDTO
     */

    public List<SelectItemDTO> getForeignSalesDivDTOList() {
        return new ArrayList<SelectItemDTO>() {
            /**
             * serialVersionUID long
             */
            private static final long serialVersionUID = 1L;

            {
                add(new SelectItemDTO("1", "国内"));
                add(new SelectItemDTO("2", "海外"));
            }
        };
    }

    /**
     * <p>Description : getDelIncDTOList</p>
     *
     * @author hao.dv
     * @since 2018/08/21
     * @return List<SelectItemDTO>
     */

    public List<SelectItemDTO> getDelIncDTOList() {
        return new ArrayList<SelectItemDTO>() {
            /**
             * serialVersionUID long
             */
            private static final long serialVersionUID = 1L;

            {
                add(new SelectItemDTO("1", "削除を含める"));

            }
        };
    }


    /**
     * <p>Description : getCatDispList</p>
     *
     * @return List<SelectItemDTO>
     * @author hao.dv
     * @since 2018/08/21
     */

    public List<SelectItemDTO> getCatDispList() {
        return new ArrayList<SelectItemDTO>() {
            /**
             * serialVersionUID long
             */
            private static final long serialVersionUID = 1L;

            {
                add(new SelectItemDTO("0", "する"));
                add(new SelectItemDTO("1", "しない"));

            }
        };
    }


    /**
     * SELECT JC_YEAR FROM M_JC WHERE JC_STR_DATE <= inputDate ORDER BY JC_STR_DATE LIMIT 1
     * <p>Description : getJcYear</p>
     * @author : hao.dv
     * @since : 2018/03/06
     * @param inputDate inputDate
     * @return JC Year
     */
    public String getJcYear(String inputDate) {
        return "平成13年";
        // List<SelectItemDTO> yearList = getYearList();
        // for (SelectItemDTO selectItemDTO : yearList) {
        // if (selectItemDTO.getCode().equals(inputDate))
        // return selectItemDTO.getName();
        // }
        // return null;
    }


    /**
     * TOTO : Tạo hàm common rỗng để dùng tạm trong MH MNG030602
     * <p>Description : FIXME Mô tả ý nghia method</p> 
     * @author : truong.pq
     * @since : 2018/03/13
     * @param memId String
     * @param mkCd Integer
     * @return FIXME Mô nghĩa param/return
     */
    public String getMemMkNm(String memId, Integer mkCd) {
        return "mkNm";
    }

    /**
     * TOTO : Tạo hàm common rỗng để dùng tạm trong MH MNG030602
     * <p>Description : FIXME Mô tả ý nghia method</p> 
     * @author : truong.pq
     * @since : 2018/03/13
     * @param memId
     * @param mdCd
     * @return FIXME Mô nghĩa param/return
     */
    public String getMemMdNm(String memId, int mdCd) {
        return "mdNm";
    }

    /**
     * TOTO : Tạo hàm common rỗng để dùng tạm trong MH MNG030602
     * <p>Description : FIXME Mô tả ý nghia method</p> 
     * @author : truong.pq
     * @since : 2018/03/13
     * @param stkNo
     * @param memId
     * @return FIXME Mô nghĩa param/return
     */
    public String getPstProdNmByLg(String stkNo, String memId) {
        return "ptsProdNmByLg";
    }




    /**
     * <p>Description : FIXME Gets the email info</p>
     *
     * @author duc.bv
     * @since 2018/08/21
     * @param userId ユーザID
     * @return String {@link String}
     */
    public static String getEmailInfo(String userId) {

        return null;
    }

    /**
     * 
     * <p>Description : getDelvSpBaseList </p> 
     * @author : hung.nq
     * @since : 2018/08/21
     * @return List<SelectItemDTO>
     */
    public List<SelectItemDTO> getStkStatList() {
        List<SelectItemDTO> selectList = Arrays.asList(new SelectItemDTO("0", "未決定"), new SelectItemDTO("1", "決定"), new SelectItemDTO("2", "販売済"),
                new SelectItemDTO("3", "販売中止"));
        return selectList;
    }

    /**
     * 
     * <p>Description : getDelvSpBaseList </p> 
     * @author : hung.nq
     * @since : 2018/08/21
     * @return List<SelectItemDTO>
     */
    public List<SelectItemDTO> getFontHigeFlagList() {
        List<SelectItemDTO> selectList = Arrays.asList(new SelectItemDTO("0", "する"), new SelectItemDTO("1", "しない"));
        return selectList;
    }

    /**
     * 
     * <p>Description : getJcYearYy </p> 
     * @return String
     */
    public String getJcYearYy(String value) {
        if (!StringUtils.isEmpty(value)) {
            return value + "年 /" + getJcYear(value);
        }
        return value;
    }

    /**
     * 
     * <p>Description : getJcYearMm </p> 
     * @return String
     */
    public String getJcYearMm(String value) {
        if (!StringUtils.isEmpty(value)) {
            return (value.length() < 2 ? "0" + value : value) + "月 ";
        }
        return value;
    }

    /**
     * 
      <p>Description : create dump data</p> 
      @author : minh.ls
      @since : 2018/05/31
      @return list jc year
     */
    public List<SelectItemDTO> getJcYearList() {
        return Arrays.asList(new SelectItemDTO("1", "平成13年"), new SelectItemDTO("2", "平成14年"));
    }

    /**
     * 
      <p>Description : get list to check ofr</p> 
      @author : tuan.dd
      @since : 2018/05/31
      @return List<SelectItemDTO>
     */
    public List<SelectItemDTO> getOfrStatusList() {
        return Arrays.asList(new SelectItemDTO("1", "注文決定"), new SelectItemDTO("2", "INVOICE承認待"), new SelectItemDTO("3", "入金待"),
                new SelectItemDTO("4", "注文取消承認待"), new SelectItemDTO("5", "注文取消承認済"), new SelectItemDTO("6", "船積予定（メール未送信）"),
                new SelectItemDTO("7", "船積予定（メール送信済）"), new SelectItemDTO("8", "船積完了"));
    }


    /**
     * 
     * <p>Description : getAmtHideFlg</p> 
     * @author : tuan.dd
     * @since : 2018/05/31
     * @return List<SelectItemDTO>
     *
     */
    public List<SelectItemDTO> getAmtHideFlgList() {
        List<SelectItemDTO> amtHideFlgList = Arrays.asList(new SelectItemDTO("0", "金額表示"), new SelectItemDTO("1", "金額非表示"));
        return amtHideFlgList;
    }
    
    /**
     * <p>Description : 固定設定値取得</p>
     *
     * @author duc.bv
     * @since 2018/08/21
     * @param key {@link String}
     * @return String {@link String}
     */
    public String getPropertyByKey(String key) {
    	return env.getProperty(key);
    }

}

package vn.avg.zizi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.RollbackException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import vn.avg.zizi.utils.CheckUtil;
import vn.avg.zizi.common.CommonConstants;
//import vn.avg.zizi.common.MessageConstants;
import vn.avg.zizi.dto.BaseDTO;
import vn.avg.zizi.entity.BaseEntity;
//import vn.avg.zizi.exception.LogicException;
//import vn.avg.zizi.utils.CheckUtil;

/**
 * <p>ファイル名 : BaseService</p>
 * <p>説明 : BaseService</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
public class BaseService {
    /** logger*/
    protected final Logger logger = LogManager.getLogger(BaseService.class);

    /** modelMapper*/
    protected ModelMapper modelMapper = new ModelMapper();

    {
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
    }

    /**
     * roll back
     * @param errMessage String
     */
    protected void rollBack(String errMessage) {
        logger.info("rollBack {}", errMessage);
        RollbackException ex = new RollbackException(errMessage);
        throw ex;

    }





}
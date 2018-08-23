/**
 * File name : Utilities.java
 * @author : hao.dv
 * @since : 2018/08/23
 * Copyright © 2018-2019 MobiTV Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import vn.avg.zizi.dto.TableHeaderDTO;
import vn.avg.zizi.mappers.common.annotation.HeaderTable;
import vn.avg.zizi.mappers.common.annotation.HeaderTable.AlignType;

/**
 * <p>File name : Utilities.java</p>
 * <p>Description : Utilities.java</p>
 * @author hao.dv
 * @since 2018/08/23
 */
public class Utilities {
    /**
     * 
     * <p>Description : FIXME get system id </p> 
     * @author : hao.dv
     * @since : 2018/08/23
     * @return String
     */
    public static String getSystemId() {
        // TODO implement
        return "1";
    }
    
    /**
     * FIXME get size cua table
     * @param headers
     * @return
     */
    public static int getHeaderSize(List<TableHeaderDTO> headers) {
        if (headers == null) {
            return 0;
        }
        int size = 0;
        for (TableHeaderDTO tableHeader : headers) {
            if (tableHeader.isVisible()) {
                size += tableHeader.getSize();
            }
        }
        return size;

    }

    // TODO sort
    /**
     * Search result table header
     * @param clazz
     * @return
     */
    public static List<TableHeaderDTO> getHeader(Object clazz) {
        List<TableHeaderDTO> headers = new ArrayList<TableHeaderDTO>();
        for (Field field : clazz.getClass().getDeclaredFields()) {
            HeaderTable annotationHT = field.getAnnotation(HeaderTable.class);
            if (annotationHT != null) {
                int showIndex = annotationHT.showIndex();
                if (showIndex != -1) {
                    TableHeaderDTO header = new TableHeaderDTO();
                    header.setShowIndex(String.valueOf(showIndex));
                    String headerVal = annotationHT.value();
                    header.setFieldNm(field.getName());
                    header.setHeader(headerVal);
                    String headerType = annotationHT.typeElement();
                    if (headerType != null && !header.equals("N/A")) {
                        header.setTypeElement(headerType);
                    }

                    AlignType align = annotationHT.align();
                    header.setAlign(align.toString());

                    int sortIndex = annotationHT.sortIndex();
                    if (sortIndex != -1) {
                        String index = String.valueOf(annotationHT.sortIndex());
                        header.setSortIndex(index);
                    }

                    int headerSize = annotationHT.size();
                    header.setSize(headerSize);

                    // set visible for column
                    header.setVisible(annotationHT.visible());

                    headers.add(header);

                    String linkElement = annotationHT.linkElement();
                    if (linkElement != null && !linkElement.equals("N/A")) {
                        header.setLinkElement(linkElement);
                    }

                    // Set time format
                    String dateFormat = annotationHT.dateFormat();
                    if (dateFormat != null && !dateFormat.equals("N/A")) {
                        header.setDateFormat(dateFormat);
                    } else {
                        header.setDateFormat("yyyy/MM/dd");
                    }
                }
            }
        }

        // sort by age
        Collections.sort(headers, new Comparator<TableHeaderDTO>() {
            @Override
            public int compare(TableHeaderDTO o1, TableHeaderDTO o2) {
                return Integer.valueOf(o1.getShowIndex()).compareTo(Integer.valueOf(o2.getShowIndex()));
            }
        });
        return headers;
    }
    
    /**
     * <p>Description : FIXME getRootUrl</p> 
     * @return
     */
    public static String getRootUrl() {
        // TODO implement
        return "http://www.tau-trade.com";
    }

    /**
     * <p>Description : FIXME encodeStkNo</p> 
     * @param stkNo
     * @return
     */
    public static String encodeStkNo(String stkNo) {
        return "ENCODED_" + stkNo;
    }

    /**
     * <p>Description : FIXME Decode stk no</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param encodedStkNo {@link String}
     * @return String {@link String}
     */
    public static String decodeStkNo(String encodedStkNo) {
        return encodedStkNo.replace("ENCODED_", "");
    }

    /**
     * 
     * <p>Description : FIXME Mô tả ý nghia method</p> 
     * @author : hao.dv
     * @since : 2018/08/23
     * @param targetObj
     * @return FIXME Mô nghĩa param/return
     */
    public static Object trim(Object targetObj) {
        // get class
        Class<? extends Object> clazz = targetObj.getClass();
        // get list fields of object
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // static field -> not process
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            // check if type string
            if (field.getType().equals(String.class)) {
                try {
                    field.setAccessible(true);
                    String fieldVal = StringUtil.convertNull(field.get(targetObj));
                    fieldVal = fieldVal.trim();
                    field.set(targetObj, fieldVal);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (field.getType().equals(List.class)) {
                // TODO
            }
        }
        return targetObj;
    }

    /**
     * <p>Description : convert map to url param</p> 
     * @author : hao.dv
     * @since : 2018/08/23
     * @param map Map
     * @return String
     */
    public static String convertMapToUrlParam(Map<String, Object> map) {
        if (map == null) {
            return null;
        }

        Iterator<Entry<String, Object>> it = map.entrySet().iterator();
        StringBuilder str = new StringBuilder();
        while (it.hasNext()) {
            Map.Entry<String, Object> element = (Map.Entry<String, Object>) it.next();
            str.append(element.getKey()).append("=").append(element.getValue());
            if (it.hasNext()) {
                str.append("&");
            }
        }
        return str.toString();
    }

    /**
     * <p>Description : map data</p> 
     * @author : hao.dv
     * @since : 2018/08/23
     * @param source Object
     * @param destination Object
     */
    public static void map(Object source, Object destination) {
        if (source != null && destination != null) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            modelMapper.map(source, destination);
        }
    }

    /**
     * * <p>Description :  Maps {@code source} to an instance of {@code destinationType}. Mapping is performed according
     * to the corresponding TypeMap. If no TypeMap exists for {@code source.getClass()} and
     * {@code destinationType} then one is created.</p> 
     * @author : hao.dv
     * @since : 2018/08/23
     * @param <D> destination type
     * @param source object to map from
     * @param destinationType type to map to
     * @return fully mapped instance of {@code destinationType}
     */
    public static <D> D map(Object source, Class<D> destinationType) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(source, destinationType);
    }

    /**
    *
    * <p>Description : getNextElement</p>
    * @author : hao.dv
    * @since 2018/08/23
    * @param mdCd Integer
    * @param list List<Integer>
    * @return Integer
    */
    public static Object getNextElement(Object source, List<? extends Object> destination) {
        Object idNext = null;
        if (destination != null) {
            for (int i = 0; i < destination.size(); i++) {
                if (destination.get(i).equals(source)) {
                    idNext = (i == (destination.size() - 1)) ? -1 : destination.get(i + 1);
                    break;
                }
            }
        }
        return idNext;
    }

    /**
     * <p>Description : FIXME Gets the next element</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param <E> the element type
     * @param source {@link int}
     * @param destination {@link List<E>}
     * @return int {@link int}
     */
    public static <E> int getNextElement(int source, List<E> destination) {
        int idNext = -1;
        if (destination != null) {
            for (Integer i = 0; i < destination.size(); i++) {
                if (destination.get(i).equals(source)) {
                    idNext = (i == (destination.size() - 1)) ? -1 : (Integer) destination.get(i + 1);
                    break;
                }
            }
        }
        return idNext;
    }

    /**
     * <p>Description : FIXME Gets the next element</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param <E> the element type
     * @param source {@link String}
     * @param destination {@link List<E>}
     * @return String {@link String}
     */
    public static <E> String getNextElement(String source, List<E> destination) {
        String idNext = null;
        if (destination != null) {
            for (Integer i = 0; i < destination.size(); i++) {
                if (destination.get(i).equals(source)) {
                    idNext = (i == (destination.size() - 1)) ? "" : (String) destination.get(i + 1);
                    break;
                }
            }
        }
        return idNext;
    }

    /**
    *
    * <p>Description : getPrevElement</p>
    * @author : minh.nt
    * @since  : 2017/12/27
    * @param mdCd Integer
    * @param list List<Integer>
    * @return Integer
    */
    public static Object getPrevElement(Object source, List<? extends Object> destination) {
        Object idPrev = null;
        if (destination != null) {
            for (int i = 0; i < destination.size(); i++) {
                if (destination.get(i).equals(source)) {
                    idPrev = (i == 0) ? -1 : destination.get(i - 1);
                    break;
                }
            }
        }
        return idPrev;
    }

    /**
     * <p>Description : FIXME Gets the prev element</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param <E> the element type
     * @param source {@link String}
     * @param destination {@link List<E>}
     * @return String {@link String}
     */
    public static <E> String getPrevElement(String source, List<E> destination) {
        String idPrev = null;
        if (destination != null) {
            for (Integer i = 0; i < destination.size(); i++) {
                if (destination.get(i).equals(source)) {
                    idPrev = (i == 0) ? "" : (String) destination.get(i - 1);
                    break;
                }
            }
        }
        return idPrev;
    }

    /**
     * <p>Description : FIXME Gets the prev element</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param <E> the element type
     * @param source {@link int}
     * @param destination {@link List<E>}
     * @return int {@link int}
     */
    public static <E> int getPrevElement(int source, List<E> destination) {
        int idPrev = -1;
        if (destination != null) {
            for (Integer i = 0; i < destination.size(); i++) {
                if (destination.get(i).equals(source)) {
                    idPrev = (i == 0) ? -1 : (Integer) destination.get(i - 1);
                    break;
                }
            }
        }
        return idPrev;
    }

    /**
     * 
     * <p>Description : FIXME Kiem tra 2 string co giong nhau hay khong</p> 
     * @author : hao.dv
     * @since : 2018/08/23
     * @param source String
     * @param target String
     * @return boolean result
     */
    public static boolean compareStr(String source, String target) {
        if (source == null) {
            return target == null;
        }
        return source.equals(target);
    }

    /**
     * <p>Description : FIXME Compare int</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param source {@link Integer}
     * @param target {@link Integer}
     * @return true/false {@link boolean}
     */
    public static boolean compareInt(Integer source, Integer target) {
        if (source == null) {
            return target == null;
        } else {
            return source.equals(target);
        }
    }

    /**
     * <p>Description : FIXME Compare long</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param source {@link Long}
     * @param target {@link Long}
     * @return true/false {@link boolean}
     */
    public static boolean compareLong(Long source, Long target) {
        if (source == null) {
            return target == null;
        } else {
            return source.equals(target);
        }
    }



    /**
     * <p>Description : get prev obj detail</p> 
     * @author : hao.dv
     * @since  : 2018/08/23
     * @param item HashMap<String, Object>
     * @param itmList List<HashMap<String, Object>>
     * @return HashMap<String, Object>
     */
    public static HashMap<String, Object> getPrevElementByHashMap(Map<String, Object> item, List<HashMap<String, Object>> itmList) {
        int pos = itmList.indexOf(item);
        if (pos > 0) {
            return itmList.get(pos - 1);
        }
        return null;
    }

    /**
     * <p>Description : get next obj detail</p> 
     * @author : hao.dv
     * @since  : 2018/08/23
     * @param item HashMap<String, Object>
     * @param itmList List<HashMap<String, Object>>
     * @return HashMap<String, Object>
     */
    public static HashMap<String, Object> getNextElementByHashMap(Map<String, Object> item, List<HashMap<String, Object>> itmList) {
        int pos = itmList.indexOf(item);
        if (pos < itmList.size() - 1 && pos != -1) {
            return itmList.get(pos + 1);
        }
        return null;
    }

    /**
     * <p>Description : convert to Next url param from current item and list HashMap ID</p> 
     * @author : hao.dv
     * @since : 2018/08/23
     * @param currentItm current Item
     * @param itmList List HashMap ID
     * @return url param
     */
    public static String getNextParamUrl(Map<String, Object> currentItm, List<HashMap<String, Object>> itmList) {
        HashMap<String, Object> nextItm = getNextElementByHashMap(currentItm, itmList);
        return Utilities.convertMapToUrlParam(nextItm);
    }

    /**
     * <p>Description : convert to Prev url param from current item and list HashMap ID</p> 
     * @author : hao.dv
     * @since : 2018/08/23
     * @param currentItm current Item
     * @param itmList List HashMap ID
     * @return url param
     */
    public static String getPrevParamUrl(Map<String, Object> currentItm, List<HashMap<String, Object>> itmList) {
        HashMap<String, Object> prevItm = getPrevElementByHashMap(currentItm, itmList);
        return Utilities.convertMapToUrlParam(prevItm);
    }


    /**
     * <p>Description : FIXME Convert string to date format string</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param content {@link String}
     * @return String {@link String}
     */
    public static String convertStringToDateFormatString(String content) {
        if (StringUtil.isEmpty(content)) {
            return content;
        }
        String s = content.substring(0, 10).replace("-", "/");
        return s;
    }

    /**
     * <p>Description : Date conversion</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param date {@link String}
     * @return LocalDateTime {@link LocalDateTime}
     */
    public static LocalDateTime toLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate currentDate = LocalDate.parse(date, formatter);
        return currentDate.atStartOfDay();
    }

    /**
     * <p>Description : Password conversion</p>
     *
     * @author hao.dv
     * @since 2018/08/23
     * @param pwd
     * @param encrypt {@link String}
     * @return String {@link String}
     */
    public static String convertPassword(String pwd, String encrypt) {
        return "sshahcsahchsahcsahchsahcsahc";
    }
}
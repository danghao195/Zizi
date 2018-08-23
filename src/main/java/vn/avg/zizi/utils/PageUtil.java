package vn.avg.zizi.utils;

import vn.avg.zizi.common.CommonConstants;
import vn.avg.zizi.dto.search.BaseSearchDTO;

/**
* <p>ファイル名 : PageUtil </p>
* <p>説明        : Tên cụm MH</p>
* @author   : cau.nh
* @since    : 2018/02/25
 */
public class PageUtil {

    /**
     * <p>説明 : FIXME Mô tả ý nghia method</p> 
     * @author : cau.nh
     * @since  : 2018/02/25
     * @param searchDTO FIXME Mô nghĩa param
     * @param sortColumns FIXME Mô nghĩa param
     * @param sortColumn FIXME Mô nghĩa param/return
     */
    public static void initSearchDTO(BaseSearchDTO searchDTO, String[] sortColumns, String sortColumn) {
        // init page
        // TODO advance
        int currentRow = searchDTO.getPage();
        if (currentRow < 1) {
            currentRow = 1;
        }
        int startRow = (currentRow - 1) * CommonConstants.GRID_ROW_COUNT;
        searchDTO.setLimit(CommonConstants.GRID_ROW_COUNT);
        searchDTO.setOffset(startRow);
        // TODO advance
        // check list order by null
        if (sortColumns == null) {
            searchDTO.setOrderBy(sortColumn);
        } else {
            if (searchDTO.getSortIndex() <= sortColumns.length - 1) {
                String sortValue = sortColumns[searchDTO.getSortIndex()];
                if (sortValue != null && !sortValue.equals(CommonConstants.BLANK)) {
                    searchDTO.setOrderBy(sortValue);
                } else {
                    searchDTO.setOrderBy(sortColumn);
                }
            } else {
                searchDTO.setOrderBy(sortColumn);
            }
        }
        // TODO advance
        // set order by
        String sortType = searchDTO.getSortType();
        if (sortType == null || searchDTO.getSortType().equals(CommonConstants.BLANK) || !CommonConstants.SORT_TYPE_LIST.contains(sortType)) {
            searchDTO.setOrderType(CommonConstants.ASC);
        } else {
            searchDTO.setOrderType(searchDTO.getSortType());
        }
    }

    /**
     * <p>説明 : FIXME Mô tả ý nghia method</p> 
     * @author : cau.nh
     * @since 2018/02/25
     * @param page
     * @param totalRowCount
     * @return FIXME Mô nghĩa param/return
     */
    public static int getPage(int page, int totalRowCount) {
        // kiem tra neu chi co 1 page
        // TODO advance ( Kiem tra lai maintance )
        if (totalRowCount <= CommonConstants.GRID_ROW_COUNT) {
            return 1;
        }
        // kiem tra page da bi xoa
        page = page >= 1 ? page - 1 : 0;
        if (page * CommonConstants.GRID_ROW_COUNT >= totalRowCount) {
            int totalPage = totalRowCount / CommonConstants.GRID_ROW_COUNT;
            if (totalRowCount % CommonConstants.GRID_ROW_COUNT > 0) {
                totalPage++;
            }
            // get current page
            return totalPage;
        }
        return page + 1;
    }

    public static void updatePage(int page, BaseSearchDTO searchDTO, BaseSearchDTO targetDTO, String[] headerSort, String defaultSort) {
        searchDTO.setPage(page);
        targetDTO.setPage(page);
        PageUtil.initSearchDTO(targetDTO, headerSort, defaultSort);
    }
}

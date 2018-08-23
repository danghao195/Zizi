/**
 *
 */
package vn.avg.zizi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * SelectItemDTO
 * @author hao.dv
 *
 */
@Getter
@Setter
public class SelectItemDTO implements Comparable<SelectItemDTO> {

    /**
     * code
     */
    private String code;

    /**
     * name
     */
    private String name;
    
    /**
     * selected
     */
    private boolean selected;

    /**
     * SelectItemDTO
     */
    public SelectItemDTO() {
        super();
    }

    /**
     * SelectItemDTO
     * @param code String
     */
    public SelectItemDTO(String code) {
        super();
        this.code = code;
    }

    /**
     * SelectItemDTO
     * @param code String
     * @param name String
     */
    public SelectItemDTO(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
    public int compareTo(SelectItemDTO o) {
        return code.compareTo(o.code);
    }
}

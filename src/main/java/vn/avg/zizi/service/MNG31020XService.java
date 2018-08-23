/**
 * File name : MNG31020XService.java
 * @author : hao.dv
 * @since : 2018/08/21
 * Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
 */
package vn.avg.zizi.service;

import java.util.List;

//import vn.avg.zizi.entity.MMkByLgEntity;
import vn.avg.zizi.entity.MMkEntity;

/**
 * <p> File name : MNG31020XService <p>
 * <p> Description : MNG31020XService</p>
 * @author hao.dv
 * @since 2018/08/21
 */
public interface MNG31020XService extends MasterBaseService {
	 
	
	
	/**
	 * <p>Description : Exclusive confirmation</p>
	 * @author hao.dv
	 * @since 2018/08/21
	 * @param makerEntity {@link MMkEntity}
	 * @return true/false {@link boolean}
	 */
	public boolean checkExclusive(MMkEntity makerEntity);
	
	/**
	 * <p>Description : Display order check</p>
	 * @author hao.dv
	 * @since 2018/08/21
	 * @param entity {@link MMkEntity}
	 * @return int {@link int}
	 */
	public int validatePullDown(MMkEntity entity);
	
	
	
	/**
	 * <p>Description : Product category check</p>
	 *
	 * @author hao.dv
	 * @since 2018/08/21
	 * @param mkCd the generic type
	 * @param itmDivCdList the generic type
	 * @return List {@link List<String>}
	 */
	public List<String> validateItmDivCd(int mkCd, List<String> itmDivCdList);
}

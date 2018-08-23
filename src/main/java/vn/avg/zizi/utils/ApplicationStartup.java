package vn.avg.zizi.utils;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>ファイル名 : ApplicationStartup</p>
 * <p>説明 : ApplicationStartup</p>
 * @author bp.thien.nv
 * @since : [2018/01/04]
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

    }
}

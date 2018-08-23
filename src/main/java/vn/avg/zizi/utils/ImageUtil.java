/**
* ファイル名 : ImageUtil.java
* 作成者 : hung.pd
* 作成日時 : 2018/5/31
* Copyright © 2017-2018 TAU Corporation. All Rights Reserved. 
*/

package vn.avg.zizi.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * <p>クラス名 : ImageUtil</p>
 * <p>説明 : ImageUtil</p>
 * @author hung.pd
 * @since 2018/5/31
 */
public class ImageUtil {
    /**
     * 
     * <p>説明 : Resize</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @param img Image
     * @param height Height
     * @param width Width
     * @return BufferedImage
     */
    public static BufferedImage resize(BufferedImage img, int height, int width) {
        if (img == null) {
            return null;
        } else {
            Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = resized.createGraphics();
            g2d.setComposite(AlphaComposite.Src);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
            return resized;
        }
    }
}

package neuedu.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GameUtil {
    /*通过工具类读取图片*/
    public static Image getImg(String imgPath) {
        URL url = GameUtil.class.getClassLoader().getResource(imgPath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}

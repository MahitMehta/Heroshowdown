package com.utils;

import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageLoader {
    
    /** Static helper method to load graphic 
     * 
     * @param path looks for specified path of graphic by default in src/assets directory
     * @return JLabel object of graphic or null
     */

    public static JLabel load(String path) {
        try {
            File bgFile = new File(System.getProperty("user.dir") + "/src/assets/" + path);
            BufferedImage bgImage = ImageIO.read(bgFile);
            // BufferedImage outputImage = new BufferedImage(1066, 634, bgImage.getType());
            ImageIcon backgroundIcon = new ImageIcon(bgImage);
            return new JLabel(backgroundIcon);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.utils;

import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageLoader {
      /** Static helper method to load FX Image graphic 
     * 
     * @param path looks for specified path of graphic by default in src/assets directory
     * @return FX Image {@value javafx.scene.image.image} object or null
     */

    public static Image loadFXImage(String path) {
        try {
            File bgFile = new File(System.getProperty("user.dir") + "/src/assets/" + path);
            BufferedImage bgImage = ImageIO.read(bgFile);
            Image image = SwingFXUtils.toFXImage(bgImage, null);
            return image; 
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /** Static helper method to load JLabel graphic 
     * 
     * @param path looks for specified path of graphic by default in src/assets directory
     * @return JLabel {@value javax.swing.JLabel} object of graphic or null
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

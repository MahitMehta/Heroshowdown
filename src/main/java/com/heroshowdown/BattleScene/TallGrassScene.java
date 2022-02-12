package com.heroshowdown.BattleScene;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.awt.Rectangle;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import com.utils.ImageLoader;

import org.mapeditor.core.Map;
import org.mapeditor.core.MapLayer;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

public class TallGrassScene  {
    private final AudioManager audio;
    private final Player player; 
    private final GraphicsContext ctx; 
    private Stage rootStage; 
    private Image background; 

    public AudioManager getAudioManager() { return this.audio; }

    public TallGrassScene(GraphicsContext ctx, Player player) {
        this.audio = new AudioManager();
        this.player = player; 
        this.ctx = ctx; 

        try {
            this.background = ImageLoader.loadFXImage("maps/tallgrass.png");
        } catch (Exception e) {
            this.background = null; 
        }
    }

    public boolean playerIntersectingTallGrass() {
        return this.player.getPlayerBoundary().intersects(this.getTallGrassBoundary());
    }

    public Rectangle2D getTallGrassBoundary() {
        Rectangle2D boundary = new Rectangle2D(270, 190, 100, 100);
        return boundary; 
    }
    
    public void init(Stage stage) {
        this.rootStage = stage; 
        this.audio.fieldMusic.play();
        // this.mapTiles();
    }

    public void backgroundCanvas() {
        try {
            this.ctx.drawImage(this.background, 0, 0, 640, 480);
            this.getTallGrassBoundary();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void mapTiles() {
        try {
            Map map = new TMXMapReader().readMap(System.getProperty("user.dir") + "/src/assets/maps/tallgrass.tmx");
            GridPane gridPane = new GridPane();
            List<MapLayer> layers = map.getLayers();
          
            for (MapLayer layer: layers) {
                TileLayer tileLayer = (TileLayer) layer; 
                Rectangle bounds = tileLayer.getBounds();
                for (int i = 0; i < bounds.width; i++) {
                    for (int j = 0; j < bounds.height; j++) {
                        Tile tile = tileLayer.getTileAt(i, j);
                        if (tile == null) continue; 
                        BufferedImage tileGraphic = tile.getImage(); 
                        Image image = SwingFXUtils.toFXImage(tileGraphic, null);
                        ImageView imageView = new ImageView(image);
                        gridPane.add(imageView, i, j);
                    }
                }
            }
            
            Scene scene = new Scene(gridPane);
            this.rootStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void backgroundPanel() {
        try {
            Pane pane = new Pane();
            File bgFile = new File(System.getProperty("user.dir") + "/src/assets/maps/tallgrass.png");
            BufferedImage bgImage = ImageIO.read(bgFile);
            Image image = SwingFXUtils.toFXImage(bgImage, null);
            ImageView bg = new ImageView(image);
            pane.getChildren().add(bg);
            Scene scene = new Scene(pane);
            this.rootStage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void render() {
        this.backgroundCanvas();
        // this.backgroundPanel();
    }
}

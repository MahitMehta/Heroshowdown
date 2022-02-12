package com.heroshowdown.BattleScene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealthBar {
    private final GraphicsContext ctx;
    private int x; 
    private int y; 
    private int width = 200;
    private int height = 10; 
    private int strokeWidth = 2;
    private int percent = 100; 

    public HealthBar(GraphicsContext ctx, int x, int y) {
        this.ctx = ctx;
        this.x = x; 
        this.y = y; 
    }

    public int setPercent(int percent) {
        this.percent = percent; 
        return this.percent; 
    }

    public void render(int powerLevel) {
        this.ctx.setFill(Color.WHITE);
        // this.ctx.setFont(Font.loadFont(System.getProperty("user.dir") + "/src/assets/fonts.ttf", 20));
        this.ctx.fillText("Power lvl " + powerLevel, this.x + 10, this.y - this.height);

        this.ctx.setFill(Color.BLACK);
        this.ctx.fillRoundRect(this.x, this.y, this.width, this.height, this.height, this.height);
        this.ctx.setFill(Color.WHITE);
        this.ctx.fillRoundRect(
            this.x + this.strokeWidth, 
            this.y + this.strokeWidth, 
            this.width - (2 * this.strokeWidth), 
            this.height - (2 * this.strokeWidth),
            this.height - (2 * this.strokeWidth), 
            this.height - (2 * this.strokeWidth)
        );

        this.ctx.setFill(Color.rgb(220, 0, 0));
        this.ctx.fillRoundRect(
            this.x + strokeWidth, 
            this.y + this.strokeWidth,
            (this.width - (2 * this.strokeWidth)) * (percent / (double) 100), 
            this.height - (2 * this.strokeWidth),
            this.height - (2 * this.strokeWidth), 
            this.height - (2 * this.strokeWidth));
    }
}

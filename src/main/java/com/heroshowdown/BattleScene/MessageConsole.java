package com.heroshowdown.BattleScene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MessageConsole {
    private final GraphicsContext ctx;
    private Stage rootStage;  
    private String message;

    private int width; 
    private int height; 
    private int borderWidth; 
    private int borderRadius; 
    private int x; 
    private int y; 
    private int padding; 
    private boolean hasButton; 

    public MessageConsole(GraphicsContext ctx, int x, int y) {
        this(ctx, x, y, false);
    }

    public MessageConsole(GraphicsContext ctx, int x, int y, boolean hasButton) {
        this.ctx = ctx; 
        this.x = x; 
        this.y = y; 
        this.width = 600; 
        this.height = 100; 
        this.borderWidth = 2; 
        this.borderRadius = 5; 
        this.padding = 10; 
        this.hasButton = hasButton; 
    }

    public void setMessage(String message) {
        this.message = message; 
    }

    public void render() {
        this.ctx.setFill(Color.WHITE);
        this.ctx.fillRoundRect(this.x, this.y, this.width, this.height, this.borderRadius, this.borderRadius);

        this.ctx.setFill(Color.rgb(24, 24, 24));
        this.ctx.fillRoundRect(this.x + this.borderWidth, this.y + this.borderWidth, (this.width * ((this.hasButton ? 5 : 6)/(double)6)) - (this.borderWidth * 2), this.height - (this.borderWidth * 2), this.borderRadius, this.borderRadius);

        if (this.hasButton) {
            this.ctx.setFill(Color.rgb(24, 24, 24));
            this.ctx.fillRoundRect(this.x + this.borderWidth + (this.width * (5/(double)6)), this.y + this.borderWidth, (this.width * (1/(double)6) - 5), this.height - (this.borderWidth * 2), this.borderRadius, this.borderRadius);
        }

        this.ctx.setFill(Color.WHITE);
        this.ctx.fillText(this.message, this.x + this.padding, this.y + this.padding + 10);
    }
}

package com.laserinfinite.java;

import java.awt.*;

public class Tier {

    private int x;
    private int y;
    private int dynamicHeight;

    private String tier;
    private Color color;

    public Tier(int x, int y, String tier, Color color) {
        this.x = x;
        this.y = y;
        this.tier = tier;
        this.color = color;
        this.dynamicHeight = 100;
    }

    public void draw(Graphics2D g) {
        g.setPaint(new GradientPaint(x,y-30,(this.color.darker().darker().darker().darker()), x, y+30, this.color));
        g.fillRect(x+100,y,700,dynamicHeight);

        g.setPaint(new GradientPaint(x,y-30,(new Color(16,16,16)), x, y+30, new Color(32,32,32)));
        g.fillRect(x,y,100,dynamicHeight);
        g.setPaint(new GradientPaint(x,0,(new Color(16,16,16)), x, 50, new Color(32,32,32)));
        g.fillRect(0,0,700,50);

        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Bahnschrift",Font.PLAIN, 30));
        g.drawString(tier, x+40, y+50);

    }
}

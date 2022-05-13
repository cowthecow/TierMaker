package com.laserinfinite.java;

import java.awt.*;

public class Node {

    private int x;
    private int y;
    private int width = 0;
    private int height = 0;
    private String name;

    private boolean isSelected;
    private int xDistToMouse;
    private int yDistToMouse;

    public Node(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean selected() {
        return isSelected;
    }

    public void select(int mouseDistX, int mouseDistY) {
        this.xDistToMouse = mouseDistX;
        this.yDistToMouse = mouseDistY;
        this.isSelected = true;
    }

    public void unSelect() {
        this.isSelected = false;
    }

    public void update() {
        if(!Main.addingNode) {
            Point mouseCursor = MouseInfo.getPointerInfo().getLocation();
            mouseCursor.setLocation(mouseCursor.x - Main.window.getX(), mouseCursor.y - Main.window.getY());

            if (isSelected) {
                this.x = mouseCursor.x + xDistToMouse;
                this.y = mouseCursor.y + yDistToMouse;
            }
        }
    }

    public void draw(Graphics2D g) {

        update();

        g.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
        double halfLength = g.getFontMetrics().getStringBounds(name, g).getWidth()/2;

        g.setColor(new Color(255,255,255));
        g.drawString(name, (int)(x-halfLength),y);

        width = (int) g.getFontMetrics().getStringBounds(name, g).getWidth();
        height = (int) g.getFontMetrics().getStringBounds(name, g).getHeight();

        if(isSelected) {
            g.setColor(new Color(0,128,255));
            g.setStroke(new BasicStroke(3));
            g.drawRect((int)(x-width*0.6), y-height,(int)(width*1.2),(int)(height*1.2));
        }
    }
}

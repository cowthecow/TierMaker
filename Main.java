package com.laserinfinite.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel {

    public static JFrame window = new JFrame();
    private static ArrayList<Tier> tiers = new ArrayList<>();
    private static ArrayList<Node> nodes = new ArrayList<>();

    public static boolean addingNode = false;
    public static boolean addingTitle = false;
    private static StringBuilder addedNode = new StringBuilder("");

    public static void main(String[] args) {

        JButton addNode = new JButton("+");
        JButton rename = new JButton("Title");

        addNode.setBounds(550, 0, 50, 50);
        addNode.setBackground(new Color(32, 32, 32));
        addNode.setForeground(new Color(255, 255, 255));
        addNode.setBorder(null);
        addNode.setFocusPainted(false);
        addNode.setOpaque(true);
        addNode.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        addNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addingNode = !addingNode;
                window.repaint();
            }
        });
        window.add(addNode);

        rename.setBounds(600, 0, 95, 50);
        rename.setBackground(new Color(32, 32, 32));
        rename.setForeground(new Color(255, 255, 255));
        rename.setBorder(null);
        rename.setFocusPainted(false);
        rename.setOpaque(true);
        rename.setFont(new Font("Bahnschrift", Font.PLAIN, 20));

        window.add(rename);

        window.getContentPane().add(new Main());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setSize(700, 625);
        window.setBackground(new Color(64, 64, 64));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.requestFocusInWindow();

        window.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Point mouseCursor = MouseInfo.getPointerInfo().getLocation();
                mouseCursor.setLocation(mouseCursor.x - window.getX(), mouseCursor.y - window.getY());

                for (Node node : nodes) {
                    if (Math.abs(node.getX() - mouseCursor.x) < node.getWidth() * 0.75 && Math.abs(node.getY() - mouseCursor.y) < node.getHeight() * 2) {
                        node.select(node.getX() - mouseCursor.x, node.getY() - mouseCursor.y);
                        return;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                for (Node node : nodes) {
                    node.unSelect();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 8) {
                    for (int i = 0; i < nodes.size(); i++) {
                        if (nodes.get(i).selected()) {
                            nodes.remove(i);
                            return;
                        }
                    }
                }

                System.out.println("no");
                if (addingNode) {
                    if(e.getKeyCode() == 8) {
                        addedNode = new StringBuilder(addedNode.substring(0, addedNode.length()-1));
                    }else {
                        addedNode.append(e.getKeyChar());
                    }
                }

            }
        });

        int ypadding = 50;

        Tier S_TierPlus = new Tier(0, 0 + ypadding, "S+", new Color(0, 64, 128));
        Tier S_Tier = new Tier(0, 75 + ypadding, "S", new Color(0, 128, 128));
        Tier A_Tier = new Tier(0, 150 + ypadding, "A", new Color(0, 128, 64));
        Tier B_Tier = new Tier(0, 225 + ypadding, "B", new Color(64, 128, 0));
        Tier C_Tier = new Tier(0, 300 + ypadding, "C", new Color(128, 128, 0));
        Tier D_Tier = new Tier(0, 375 + ypadding, "D", new Color(128, 64, 0));
        Tier F_Tier = new Tier(0, 450 + ypadding, "F", new Color(128, 0, 0));

        tiers.add(S_TierPlus);
        tiers.add(S_Tier);
        tiers.add(A_Tier);
        tiers.add(B_Tier);
        tiers.add(C_Tier);
        tiers.add(D_Tier);
        tiers.add(F_Tier);


        nodes.add(new Node(150, 100, "among us"));
        nodes.add(new Node(150, 250, "minecraft"));
        nodes.add(new Node(225, 250, "robl0x"));
        nodes.add(new Node(150, 550, "fortnite"));


        for (; ; )
            window.repaint();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        for (Tier tier : tiers)
            tier.draw(g2d);

        for (Node node : nodes)
            node.draw(g2d);

        if (addingNode) {
            g.setColor(new Color(0, 0, 0, 128));
            g.fillRect(0, 0, 700, 600);

            g.setColor(new Color(255, 255, 255, 128));
            g.fillRoundRect(175, 325, 425, 50, 30, 30);

            g.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
            g.drawString("Enter to add node", 175, 400);
            g.drawString("Esc to cancel", 375, 400);
            g.setFont(new Font("Bahnschrift", Font.PLAIN, 30));

            g.setColor(new Color(0, 0, 0, 255));
            g.drawString(addedNode.toString(), 175, 345);
        }

    }


}

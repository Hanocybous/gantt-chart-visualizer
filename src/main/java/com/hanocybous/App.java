package com.hanocybous;

import com.hanocybous.app.ui.JFrameLevel00RootFrame;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrameLevel00RootFrame rootFrame = new JFrameLevel00RootFrame();
        rootFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        rootFrame.setSize(1200, 800);
        rootFrame.setVisible(true);
    }
}

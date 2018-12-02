package com.snakevsblocks.util;

public class Image {

    private static javafx.scene.image.Image COIN = new javafx.scene.image.Image("img/coin.png");
    private static javafx.scene.image.Image DESTROYER = new javafx.scene.image.Image("img/destroyer.png");
    private static javafx.scene.image.Image MAGNET = new javafx.scene.image.Image("img/magnet.png");
    private static javafx.scene.image.Image SHIELD = new javafx.scene.image.Image("img/shield.png");
    private static javafx.scene.image.Image STAR = new javafx.scene.image.Image("img/star.png");

    private static javafx.scene.image.Image B_BACK = new javafx.scene.image.Image("img/back-black.png");
    private static javafx.scene.image.Image W_BACK = new javafx.scene.image.Image("img/back-white.png");
    private static javafx.scene.image.Image RESTART = new javafx.scene.image.Image("img/restart.png");

    public static javafx.scene.image.Image getCOIN() {
        return COIN;
    }

    public static javafx.scene.image.Image getDESTROYER() {
        return DESTROYER;
    }

    public static javafx.scene.image.Image getMAGNET() {
        return MAGNET;
    }

    public static javafx.scene.image.Image getSHIELD() {
        return SHIELD;
    }

    public static javafx.scene.image.Image getBBack() {
        return B_BACK;
    }

    public static javafx.scene.image.Image getWBack() {
        return W_BACK;
    }

    public static javafx.scene.image.Image getRESTART() {
        return RESTART;
    }

    public static javafx.scene.image.Image getSTAR() {
        return STAR;
    }
}
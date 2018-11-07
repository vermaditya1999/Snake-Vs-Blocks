package com.snakevsblocks;

public class Font {

    public static final javafx.scene.text.Font GOTHAM_LARGE = javafx.scene.text.Font.loadFont(Main.class.getClassLoader().getResourceAsStream("fonts/Gotham-Light.otf"), 65);
    public static final javafx.scene.text.Font GOTHAM_MEDIUM = javafx.scene.text.Font.loadFont(Main.class.getClassLoader().getResourceAsStream("fonts/Gotham-Light.otf"), 45);
    public static final javafx.scene.text.Font GOTHAM_SMALL = javafx.scene.text.Font.loadFont(Main.class.getClassLoader().getResourceAsStream("fonts/Gotham-Light.otf"), 25);

    public static final javafx.scene.text.Font CONSOLAS_LARGE = new javafx.scene.text.Font("Consolas", 50);
    public static final javafx.scene.text.Font CONSOLAS_MEDIUM = new javafx.scene.text.Font("Consolas", 30);
    public static final javafx.scene.text.Font CONSOLAS_SMALL = new javafx.scene.text.Font("Consolas", 20);

    public static final javafx.scene.text.Font MUSEO = javafx.scene.text.Font.loadFont(Main.class.getClassLoader().getResourceAsStream("fonts/Museo-100.otf"), 60);
}

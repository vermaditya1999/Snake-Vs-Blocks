package com.snakevsblocks.util;

/**
 * This class contains the static Font objects used to avoid the creation of font objects repeatedly across the application.
 */
public class Font {

    public static final javafx.scene.text.Font GOTHAM_LARGE = javafx.scene.text.Font.loadFont(Font.class.getClassLoader().getResourceAsStream("fonts/Gotham-Light.otf"), 65);
    public static final javafx.scene.text.Font GOTHAM_MEDIUM = javafx.scene.text.Font.loadFont(Font.class.getClassLoader().getResourceAsStream("fonts/Gotham-Light.otf"), 45);
    public static final javafx.scene.text.Font GOTHAM_SMALL = javafx.scene.text.Font.loadFont(Font.class.getClassLoader().getResourceAsStream("fonts/Gotham-Light.otf"), 25);

    public static final javafx.scene.text.Font CONSOLAS_LARGE = javafx.scene.text.Font.loadFont(Font.class.getClassLoader().getResourceAsStream("fonts/Consolas.ttf"), 50);
    public static final javafx.scene.text.Font CONSOLAS_MEDIUM = javafx.scene.text.Font.loadFont(Font.class.getClassLoader().getResourceAsStream("fonts/Consolas.ttf"), 30);
    public static final javafx.scene.text.Font CONSOLAS_SMALL = javafx.scene.text.Font.loadFont(Font.class.getClassLoader().getResourceAsStream("fonts/Consolas.ttf"), 20);
    public static final javafx.scene.text.Font CONSOLAS_XSMALL = javafx.scene.text.Font.loadFont(Font.class.getClassLoader().getResourceAsStream("fonts/Consolas.ttf"), 15);

    public static final javafx.scene.text.Font MUSEO = javafx.scene.text.Font.loadFont(Font.class.getClassLoader().getResourceAsStream("fonts/Museo-100.otf"), 60);
}

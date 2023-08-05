package com.client.utils;

import java.awt.*;

/**
 * The DimensionUtils class provides a set of predefined dimensions that can be used for sizing components in the application's user interface.
 * This class contains static final Dimension objects representing various dimensions for different UI elements.
 * Dimensions are specified as width and height in pixels.
 */
public final class DimensionUtils {

    /**
     * A private constructor to prevent instantiation of the utility class.
     */
    private DimensionUtils() {}

    /**
     * A Dimension object representing the dimensions for a table component.
     */
    public static final Dimension TABLE_DIMENSION = new Dimension(375, 550);

    /**
     * A Dimension object representing the dimensions for the top panel component.
     */
    public static final Dimension TOP_PANEL_DIMENSION = new Dimension(205, 34);

    /**
     * A Dimension object representing the dimensions for the log out button component.
     */
    public static final Dimension LOG_OUT_BUTTON_DIMENSION = new Dimension(74, 34);

    /**
     * A Dimension object representing the dimensions for an option button component.
     */
    public static final Dimension OPTION_BUTTON_DIMENSION = new Dimension(75, 46);

    /**
     * A Dimension object representing the dimensions for the admin name label component.
     */
    public static final Dimension ADMIN_NAME_LABEL_DIMENSION = new Dimension(73, 25);

    /**
     * A Dimension object representing the dimensions for the wrong item label component.
     */
    public static final Dimension WRONG_ITEM_LABEL_DIMENSION = new Dimension(222, 16);

    /**
     * A Dimension object representing the minimum dimensions for the options panel component.
     */
    public static final Dimension OPTIONS_PANEL_MIN_SIZE_DIMENSION = new Dimension(180, 200);

    /**
     * A Dimension object representing the preferred dimensions for the options panel component.
     */
    public static final Dimension OPTIONS_PANEL_PREF_SIZE_DIMENSION = new Dimension(180, 230);

    /**
     * A Dimension object representing the dimensions for the number panel component.
     */
    public static final Dimension NUMBER_PANEL_DIMENSION = new Dimension(280, 130);

    /**
     * A Dimension object representing the dimensions for the number label component.
     */
    public static final Dimension NUMBER_LABEL_DIMENSION = new Dimension(178, 57);

    /**
     * A Dimension object representing the dimensions for an icon label component.
     */
    public static final Dimension ICON_LABEL_DIMENSION = new Dimension(70, 70);

    /**
     * A Dimension object representing the dimensions for the registration panel component.
     */
    public static final Dimension REG_PANEL_DIMENSION = new Dimension(450, 620);

    /**
     * A Dimension object representing the dimensions for the submit and cancel button components.
     */
    public static final Dimension SUBMIT_CANCEL_BUTTON_DIMENSION = new Dimension(116, 27);

    /**
     * A Dimension object representing the dimensions for the application window.
     */
    public static final Dimension WINDOW_DIMENSION = new Dimension(1024, 768);

    /**
     * A Dimension object representing the dimensions for the menu panel component.
     */
    public static final Dimension MENU_PANEL_DIMENSION = new Dimension(180, 806);
}

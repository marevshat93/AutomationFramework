package util;

import org.openqa.selenium.Dimension;
public class Util {
    public static Dimension convertStringToDimension(String sizeString) {
        // Split the string by "x" to extract width and height
        String[] sizeParts = sizeString.split("x");

        // Parse the width and height from the string
        int width = Integer.parseInt(sizeParts[0]);
        int height = Integer.parseInt(sizeParts[1]);

        // Return the Dimension object
        return new Dimension(width, height);
    }
}

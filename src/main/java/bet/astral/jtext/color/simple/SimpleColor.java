package bet.astral.jtext.color.simple;

import org.jetbrains.annotations.NotNull;

public interface SimpleColor {
    /**
     * Converts this to an RGB color
     * @return rgb color instance
     */
    @NotNull
    Color toRGBColor();

    /**
     * Converts this to HSL color
     * @return hsl color instance
     */
    @NotNull
    HSLColor toHSLColor();

    /**
     * Converts this to HSV color
     * @return hsv color instance
     */
    @NotNull
    HSVColor toHSVColor();
}

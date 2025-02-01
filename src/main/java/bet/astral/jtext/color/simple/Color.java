package bet.astral.jtext.color.simple;

import bet.astral.jtext.color.ColorLike;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * Single color representation of {@link ColorLike}
 */
public class Color implements ColorLike, SimpleColor {
    public static final String HEX_SHORT = "[0-9a-fA-F]{3}";
    public static final String HEX_SHORT_ALPHA = "[0-9a-fA-F]{4}";
    public static final String HEX = "#[0-9a-fA-F]{6}";
    public static final String HEX_ALPHA = "#[0-9a-fA-F]{8}";
    @Range(from = 0, to = 255)
    private int red;
    @Range(from = 0, to = 255)
    private int green;
    @Range(from = 0, to = 255)
    private int blue;
    @Range(from = 0, to = 1)
    private float alpha;

    public Color() {
    }

    public Color(@Range(from = 0, to = 255) int red, @Range(from = 0, to = 255) int green, @Range(from = 0, to = 255) int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = 1f;
    }

    public Color(@Range(from = 0, to = 255) int red, @Range(from = 0, to = 255) int green, @Range(from = 0, to = 255) int blue, @Range(from = 0, to = 1) float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }


    /**
     * Returns a new RGBA implementation with red, blue, green and alpha. Given method with default alpha value: {@link #of(int, int, int)}
     * @param red red (0-255)
     * @param green green (0-255)
     * @param blue blue (0-255)
     * @param alpha alpha (0-1)
     * @return new color
     * @see #of(int, int, int, int)
     * @see #of(int, int, int)
     */
    public static Color of(int red, int green, int blue, float alpha){
        return new Color(red, green, blue, alpha);
    }
    /**
     * Returns a new RGBA implementation with red, blue, green and alpha. Given method with default alpha value: {@link #of(int, int, int)}
     * @param red red (0-255)
     * @param green green (0-255)
     * @param blue blue (0-255)
     * @param alpha alpha (0-1)
     * @return new color
     * @see #of(int, int, int, int)
     * @see #of(int, int, int)
     */
    public static Color of(int red, int green, int blue, int alpha){
        return new Color(red, green, blue, alpha*255);
    }

    /**
     * Creates a new RGB implementation with red, blue, green. Given method with alpha value: {@link #of(int, int, int, float)}
     * @param red red (0-255)
     * @param green green (0-255)
     * @param blue blue (0-255)
     * @return new color
     * @see #of(int, int, int, int)
     * @see #of(int, int, int, float)
     */
    public static Color of(@Range(from = 0, to = 255) int red, @Range(from = 0, to = 255) int green, @Range(from = 0, to = 255) int blue){
        return new Color(red, green, blue);
    }

    /**
     * Converts integer RGB value to color implementation. Int RGBA method: {@link #ofRGBA(int)}
     * @param rgb rgb
     * @return new color
     */
    public static Color ofRGB(int rgb){
        return new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
    }
    /**
     * Converts integer RGBA value to color implementation. Int RGB method: {@link #ofRGB(int)}
     * @param rgba rgba
     * @return new color
     */
    static Color ofRGBA(int rgba){
        return new Color((rgba >> 24) & 0xFF, (rgba >> 16) & 0xFF, (rgba >> 8) & 0xFF, (float) (((rgba) & 0xFF) / 255));
    }

    /**
     * Converts HEX RGB or RGBA RGB: (#RRGGBB or #RBG) RGBA: (#RRGGBBAA or #RGBA)
     * @param hex hex
     * @return color
     * @throws IllegalArgumentException if the hex pattern is invalid or null
     */
    @SuppressWarnings("t")
    public static Color ofHex(String hex) throws IllegalArgumentException {
        if (hex == null) {
            throw new IllegalArgumentException("Hex string cannot be null!");
        }

        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }

        // Parse short hex
        if (hex.length() == 3 || hex.length() == 4) {
            if (!hex.matches(HEX_SHORT) && !hex.matches(HEX_SHORT_ALPHA)){
                throw new IllegalArgumentException("Invalid hex format! Must be #RGB, or #RGBA.");
            }

            // Duplicate entries because RGB (255) can't be done without 2 characters
            int red = Integer.parseInt(String.valueOf(hex.charAt(0)) + hex.charAt(0), 16);
            int green = Integer.parseInt(String.valueOf(hex.charAt(1)) + hex.charAt(1), 16);
            int blue = Integer.parseInt(String.valueOf(hex.charAt(2)) + hex.charAt(2), 16);

            // Alpha
            if (hex.length() == 4) {
                float alpha = Integer.parseInt(String.valueOf(hex.charAt(3)) + hex.charAt(3), 16) / 255.0f;
                return new Color(red, green, blue, alpha);
            }
            return new Color(red, green, blue);
        }

        // Parse longer hex
        if (hex.length() == 6 || hex.length() == 8) {
            if (!hex.matches(HEX) && !hex.matches(HEX_ALPHA)){
                throw new IllegalArgumentException("Invalid hex format! Must be #RRGGBB, or #RRGGBBAA.");
            }
            int red = Integer.parseInt(hex.substring(0, 2), 16);
            int green = Integer.parseInt(hex.substring(2, 4), 16);
            int blue = Integer.parseInt(hex.substring(4, 6), 16);

            // Alpha
            if (hex.length() == 8) {
                float alpha = Integer.parseInt(hex.substring(6, 8), 16) / 255.0f;
                return new Color(red, green, blue, alpha);
            }
            return new Color(red, green, blue);
        }

        throw new IllegalArgumentException("Invalid hex format! Must be #RRGGBB, #RGB, #RRGGBBAA, or #RGBA.");
    }

    /**
     * Returns an empty color implementation used for deserialization
     * @return new EMPTY color
     */
    @ApiStatus.Internal
    public static Color empty() {
        return new Color();
    }

    /**
     * Returns the red of the color
     * @return red
     * @see #getGreen()
     * @see #getBlue()
     * @see #getAlpha()
     * @see #getAlpha255()
     */
    @Range(from = 0, to = 255)
    public int getRed() {
        return red;
    }

    /**
     * Returns the green of the color
     * @return green
     * @see #getRed()
     * @see #getBlue()
     * @see #getAlpha()
     * @see #getAlpha255()
     */
    @Range(from = 0, to = 255)
    public int getGreen() {
        return green;
    }

    /**
     * Returns the blue of the color
     * @return blue
     * @see #getRed()
     * @see #getGreen()
     * @see #getAlpha()
     * @see #getAlpha255()
     */
    @Range(from = 0, to = 255)
    public int getBlue() {
        return blue;
    }

    /**
     * Returns the alpha of the color.
     * @return alpha (0-1)
     * @see #getRed()
     * @see #getGreen()
     * @see #getBlue()
     * @see #getAlpha255()
     */
    @Range(from = 0, to = 1)
    public float getAlpha() {
        return alpha;
    }

    /**
     * Returns the alpha (255) of the color
     * @return alpha (0-255)
     * @see #getRed()
     * @see #getGreen()
     * @see #getBlue()
     * @see #getAlpha()
     */
    @Range(from = 0, to = 255)
    public int getAlpha255(){
        return (int) (getAlpha()*255);
    }

    /**
     * Converts the colors to RGB and returns the value
     * @return rgb
     */
    public int getRGB() {
        return (red << 16) | (green << 8) | blue;
    }

    /**
     * Converts the color to RGBA and returns the value
     * @return rgba
     */
    public int getRGBA() {
        return (red << 24) | (green << 16) | (blue << 8) | getAlpha255();
    }

    @Override
    public @NotNull Color toRGBColor() {
        return this;
    }

    @Override
    public @NotNull HSLColor toHSLColor() {
        return HSLColor.rgb(red ,green, blue);
    }

    @Override
    public @NotNull HSVColor toHSVColor() {
        return HSVColor.rgb(red ,green, blue);
    }
}

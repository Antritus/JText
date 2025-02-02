package bet.astral.jtext.color.simple;

import bet.astral.jtext.color.ColorLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * HSV color instance. Hue, Saturation, Value implementation of color. Use {@link Color} for normal RGB(A) color
 */
public class HSVColor implements ColorLike, SimpleColor {
    /**
     * Converts RGB to HSV format. Alpha is not used in HSV format
     * @param red red
     * @param green blue
     * @param blue green
     * @return HSV
     */
    public static @NotNull HSVColor rgb(
            @Range(from = 0, to = 255) int red,
            @Range(from = 0, to = 255) int green,
            @Range(from = 0, to = 255) int blue) {

        float r = red / 255.0f;
        float g = green / 255.0f;
        float b = blue / 255.0f;

        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));
        float delta = max - min;

        float value = max;

        float saturation = (max == 0) ? 0 : (delta / max);

        float hue = 0;
        if (delta != 0) {
            if (max == r) {
                hue = (g - b) / delta;
            } else if (max == g) {
                hue = 2 + (b - r) / delta;
            } else {
                hue = 4 + (r - g) / delta;
            }
        }

        hue = hue * 60;
        if (hue < 0) {
            hue += 360;
        }

        return new HSVColor(hue, saturation, value);
    }

    private final float hue;
    private final float saturation;
    private final float value;

    /**
     * Creates a new instance of hue, saturation, value color
     * @param hue hue
     * @param saturation saturation
     * @param value value
     */
    public HSVColor(float hue, float saturation, float value) {
        this.value = value;
        this.saturation = saturation;
        this.hue = hue;
    }

    /**
     * Returns the hue of this color
     * @return hue
     */
    public float getHue() {
        return hue;
    }

    /**
     * Returns the saturation of this color
     * @return saturation
     */
    public float getSaturation() {
        return saturation;
    }

    /**
     * Returns the value of this color
     * @return value
     */
    public float getValue() {
        return value;
    }

    /**
     * Converts this RGB(A) color instance
     * @return new RGB(A) instance
     */
    @Override
    public @NotNull Color toRGBColor() {
        float hue = this.hue % 360;
        if (hue < 0) hue += 360;

        float chroma = value * saturation;
        float x = chroma * (1 - Math.abs((hue / 60) % 2 - 1));
        float m = value - chroma;

        float r1 = 0, g1 = 0, b1 = 0;

        if (hue >= 0 && hue < 60) {
            r1 = chroma;
            g1 = x;
            b1 = 0;
        } else if (hue >= 60 && hue < 120) {
            r1 = x;
            g1 = chroma;
            b1 = 0;
        } else if (hue >= 120 && hue < 180) {
            r1 = 0;
            g1 = chroma;
            b1 = x;
        } else if (hue >= 180 && hue < 240) {
            r1 = 0;
            g1 = x;
            b1 = chroma;
        } else if (hue >= 240 && hue < 300) {
            r1 = x;
            g1 = 0;
            b1 = chroma;
        } else if (hue >= 300 && hue < 360) {
            r1 = chroma;
            g1 = 0;
            b1 = x;
        }

        int red = (int) ((r1 + m) * 255);
        int green = (int) ((g1 + m) * 255);
        int blue = (int) ((b1 + m) * 255);

        return new Color(red, green, blue);
    }

    /**
     * Converts this HSV color to HSL color instance
     * @return new HSV instance
     */
    @Override
    public @NotNull HSLColor toHSLColor() {
        float hue = this.hue % 360;
        if (hue < 0) hue += 360;

        // Convert HSV to RGB
        float c = value * saturation;
        float x = c * (1 - Math.abs((hue / 60) % 2 - 1));
        float m = value - c;

        float r1 = 0, g1 = 0, b1 = 0;
        if (hue >= 0 && hue < 60) {
            r1 = c;
            g1 = x;
            b1 = 0;
        } else if (hue >= 60 && hue < 120) {
            r1 = x;
            g1 = c;
            b1 = 0;
        } else if (hue >= 120 && hue < 180) {
            r1 = 0;
            g1 = c;
            b1 = x;
        } else if (hue >= 180 && hue < 240) {
            r1 = 0;
            g1 = x;
            b1 = c;
        } else if (hue >= 240 && hue < 300) {
            r1 = x;
            g1 = 0;
            b1 = c;
        } else if (hue >= 300 && hue < 360) {
            r1 = c;
            g1 = 0;
            b1 = x;
        }

        float r = r1 + m;
        float g = g1 + m;
        float b = b1 + m;

        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));

        float lightness = (max + min) / 2;

        float saturationHSL = 0;
        if (max != min) {
            saturationHSL = (max - min) / (1 - Math.abs(2 * lightness - 1));
        }

        return new HSLColor(hue, saturationHSL, lightness);
    }

    /**
     * Returns this
     * @return this
     */
    @Override
    public @NotNull HSVColor toHSVColor() {
        return this;
    }
}
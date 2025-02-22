package bet.astral.jtext.color.simple;

import bet.astral.jtext.color.ColorLike;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * HSL color format. Class made for shader style calculations. Hue, Saturation, Lightness. Use {@link Color} for normmal RGB(A) format.
 */
public class HSLColor implements ColorLike, SimpleColor {
    /**
     * Creates an HSL color instance while calculating red, green and blue to hue saturation light
     * @param red red
     * @param green green
     * @param blue blue
     * @return new hsl instance
     */
    public static @NotNull HSLColor rgb(
            @Range(from = 0, to = 255) int red,
            @Range(from = 0, to = 255) int green,
            @Range(from = 0, to = 255) int blue
    ) {
        float r = red / 255.0f;
        float g = green / 255.0f;
        float b = blue / 255.0f;

        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));

        float lightness = (max + min) / 2.0f;
        float hue;
        float saturation;

        if (max == min) {
            // Cannot calculate -> hue is undefined
            hue = 0.0f;
            saturation = 0.0f;
        } else {
            float delta = max - min;
            saturation = lightness > 0.5f ? delta / (2.0f - max - min) : delta / (max + min);

            if (max == r) {
                hue = (g - b) / delta + (g < b ? 6.0f : 0.0f);
            } else if (max == g) {
                hue = (b - r) / delta + 2.0f;
            } else {
                hue = (r - g) / delta + 4.0f;
            }

            hue /= 6.0f;
        }

        return new HSLColor(hue, saturation, lightness);
    }

    private float hue;
    private float saturation;
    private float lightness;

    /**
     * Creates a new HSL color instance
     * @param hue hue
     * @param saturation saturation
     * @param lightness lightness
     */
    public HSLColor(float hue, float saturation, float lightness) {
        this.hue = hue;
        this.saturation = saturation;
        this.lightness = lightness;
    }

    /**
     * Internal usage for deserialization. Creates a new HSL color instance
     */
    @ApiStatus.Internal
    public HSLColor() {
    }

    /**
     * Returns the hue
     * @return hue
     */
    public float getHue() {
        return hue;
    }

    /**
     * Returns the saturation
     * @return saturation
     */
    public float getSaturation() {
        return saturation;
    }

    /**
     * Returns the lightness
     * @return lightness
     */
    public float getLightness() {
        return lightness;
    }

    /**
     * Converts this HSL to RGB(A) color instance
     * @return rgb(a) instance
     */
    @Override
    public @NotNull Color toRGBColor() {
        hue = (hue % 360 + 360) % 360;

        float c = (1 - Math.abs(2 * lightness - 1)) * saturation;
        float x = c * (1 - Math.abs((hue / 60) % 2 - 1));
        float m = lightness - c / 2;

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

        int red = (int) ((r1 + m) * 255);
        int green = (int) ((g1 + m) * 255);
        int blue = (int) ((b1 + m) * 255);

        return new Color(red, green, blue);
    }

    /**
     * Returns this
     * @return this
     */
    @Override
    public @NotNull HSLColor toHSLColor() {
        return this;
    }


    /**
     * Converts this HSL color to HSV color instance
     * @return new HSV color instance
     */
    @Override
    public @NotNull HSVColor toHSVColor() {
        float value = lightness + saturation * Math.min(lightness, 1 - lightness);
        float saturationHSV = (value == 0) ? 0 : 2 * (1 - lightness / value);

        return new HSVColor(hue, saturationHSV, value);
    }
}

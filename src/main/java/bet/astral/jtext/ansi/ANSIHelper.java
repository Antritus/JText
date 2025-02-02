package bet.astral.jtext.ansi;

import bet.astral.jtext.color.ColorLike;
import bet.astral.jtext.color.GradientColor;
import bet.astral.jtext.color.simple.Color;
import bet.astral.jtext.color.simple.SimpleColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * ANSI codes and helper methods to convert JText objects to ANSI format.
 */
public class ANSIHelper {
    /**
     * The ANSI begin code
     */
    public static final String ESC = "\033";
    /**
     * The ANSI begin code with bracket [ built into the field
     */
    public static final String ESC_B = "\033[";
    /**
     * Resets all the previous formatting codes and colors
     */
    public static final String RESET_FORMAT = ESC_B+"0m";
    /**
     * Double underline, not supported by all terminals.
     */
    public static final String DOUBLE_UNDERLINE = ESC_B+"21m";
    /**
     * Resets the previous double underline and the previous single underline
     */
    public static final String DOUBLE_UNDERLINE_RESET = ESC_B+"24m";
    /**
     * Makes the text bold
     */
    public static final String BOLD = ESC_B+"1m";
    /**
     * Removes bold formatting
     */
    public static final String BOLD_RESET = ESC_B+"22m";
    /**
     * Dims down the colors of the next text
     */
    public static final String DIM = ESC_B+"2m";
    /**
     * Removes the dim formatting
     */
    public static final String DIM_RESET = ESC_B+"22m";
    /**
     * Makes text italic
     */
    public static final String ITALIC = ESC_B+"3m";
    /**
     * Removes the italic formatting
     */
    public static final String ITALIC_RESET = ESC_B+"23m";
    /**
     * Makes the text underlined
     */
    public static final String UNDERLINE = ESC_B+"4m";
    /**
     * Removes the underline formatting
     */
    public static final String UNDERLINE_RESET = ESC_B+"24m";
    /**
     * Makes the text blink (150 times a second)
     */
    public static final String BLINK = ESC_B+"5m";
    /**
     * Makes the text blink (over 150 times a second)
     */
    public static final String RAPID_BLINK = ESC_B+"6m";
    /**
     * Removes the blink formatting
     */
    public static final String BLINK_RESET = ESC_B+"25m";
    /**
     * Reverses the colors from background and text (swaps the places)
     */
    public static final String REVERSE = ESC_B+"7m";
    /**
     * Removes the color reverse formatting
     */
    public static final String REVERSE_RESET = ESC_B+"27m";
    /**
     * Makes the text hidden/invisible
     */
    public static final String INVISIBLE = ESC_B+"8m";
    /**
     * Removes the invisible/hide formatting
     */
    public static final String INVISIBLE_RESET = ESC_B+"28m";
    /**
     * Makes the text have a strikethrough line in the middle
     */
    public static final String STRIKETHROUGH = ESC_B+"9m";
    /**
     * Removes the strikethrough formatting
     */
    public static final String STRIKETHROUGH_RESET = ESC_B+"29m";
    /**
     * Makes the text have overline
     */
    public static final String OVERLINE = ESC_B+"51m";
    /**
     * Removes the overline formatting
     */
    public static final String OVERLINE_RESET = ESC_B+"55m";

    /**
     * Converts {@link SimpleColor}, {@link GradientColor} to an ansi background color
     * @param colorLike color
     * @return ansi color, else ""
     */
    public static @NotNull String convertColorToANSIBackground(ColorLike colorLike){
        if (colorLike instanceof SimpleColor simpleColor){
            return convertSimpleColorToANSIBackground(simpleColor);
        } else if (colorLike instanceof GradientColor gradientColor){
            return convertSimpleColorToANSIBackground(gradientColor.getNextColor());
        }
        return "";
    }

    /**
     * Formats simple color to ANSI color
     * @param simpleColor simple color
     * @return simple color
     */
    private static @NotNull String convertSimpleColorToANSIBackground(@NotNull SimpleColor simpleColor){
        Color color = simpleColor.toRGBColor();
        return ESC_B+"48;2;" + color.getRed() + ";" + color.getGreen() + ";" + color.getBlue() + "m";
    }

    /**
     * Converts {@link SimpleColor}, {@link GradientColor} to an ansi foreground (text) color
     * @param colorLike color
     * @return ansi color, else ""
     */
    public static @NotNull String convertColorToANSIForeground(ColorLike colorLike){
        if (colorLike instanceof SimpleColor simpleColor){
            return convertSimpleColorToANSIForeground(simpleColor);
        } else if (colorLike instanceof GradientColor gradientColor){
            return convertSimpleColorToANSIForeground(gradientColor.getNextColor());
        }
        return "";
    }

    /**
     * Formats simple color to ANSI color
     * @param simpleColor simple color
     * @return simple color
     */
    private static @NotNull String convertSimpleColorToANSIForeground(@NotNull SimpleColor simpleColor){
        Color color = simpleColor.toRGBColor();
        return ESC_B+"38;2;"+color.getRed()+";"+color.getGreen()+";"+color.getBlue()+"m";
    }

    /**
     * Returns true if the string is a foreground color with 24-bit colors
     * @param color color string
     * @return true if matches, else false
     */
    @Contract(pure = true)
    public static boolean isForegroundColor(@NotNull String color){
        return color.matches(ESC+"\\[38;2;([0-9]{1,3});([0-9]{1,3});([0-9]{1,3})m");
    }

    /**
     * Returns true if the string is a background color with 24-bit colors
     * @param color color string
     * @return true if matches, else false
     */
    @Contract(pure = true)
    public static boolean isBackgroundColor(@NotNull String color){
        return color.matches(ESC+"\\[42;2;([0-9]{1,3});([0-9]{1,3});([0-9]{1,3})m");
    }
}

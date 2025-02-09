package bet.astral.jtext.component.text;

import bet.astral.jtext.color.ColorLike;
import bet.astral.jtext.component.Component;
import bet.astral.jtext.component.StyleComponent;
import bet.astral.jtext.style.Style;

import java.util.ArrayList;
import java.util.List;

/**
 * Normal text component instance
 */
public class TextComponent extends StyleComponent {
    /**
     * Creates a new text component instance
     * @param value text
     * @param color color
     * @param backgroundColor background color
     * @param shadowColor shadow color
     * @param style style
     * @param children children
     * @param placeholders placeholders
     */
    public TextComponent(String value, ColorLike color, ColorLike backgroundColor, ColorLike shadowColor, Style style, ArrayList<Component> children, List<Object> placeholders) {
        super(value, color, backgroundColor, shadowColor, style, children, placeholders);
    }

    /**
     * Creates a new text component instance
     * @param value value
     * @param color color
     * @param style style
     */
    public TextComponent(String value, ColorLike color, Style style) {
        super(value, color, style);
    }

    /**
     * Creates a new component instance
     */
    public TextComponent() {
    }
}

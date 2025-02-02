package bet.astral.jtext.component.lang;

import bet.astral.jtext.color.ColorLike;
import bet.astral.jtext.component.Component;
import bet.astral.jtext.component.StyleComponent;
import bet.astral.jtext.style.Style;

import java.util.ArrayList;
import java.util.List;

/**
 * Language component which contains a translation key and placeholders. Converts to a proper translation in the client side
 */
public class LangComponent extends StyleComponent implements Translatable {
    public static final String DEFAULT = "_D_";
    private String language = null;

    /**
     * Creates a new lang component instance
     */
    public LangComponent() {
        super();
    }

    /**
     * Creates a new lang component instance
     * @param value translation key
     * @param color component color
     * @param backgroundColor background color
     * @param shadowColor shadow color
     * @param style component style
     * @param children component children
     * @param placeholders placeholders
     */
    public LangComponent(String value, ColorLike color, ColorLike backgroundColor, ColorLike shadowColor, Style style, ArrayList<Component> children, List<Object> placeholders) {
        super(value, color, backgroundColor, shadowColor, style, children, placeholders);
    }

    /**
     * Creates a new lang component instance
     * @param value translation key
     * @param color component color
     * @param style component style
     * @param children component children
     */
    public LangComponent(String value, ColorLike color, Style style, ArrayList<Component> children) {
        super(value, color, null, null, style, children, null);
    }

    /**
     * Creates a new lang component instance
     * @param value translation key
     * @param color component color
     * @param style component style
     */
    public LangComponent(String value, ColorLike color, Style style) {
        super(value, color, style);
    }

    /**
     * Returns the preferred language for the translation
     * @return preferred language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Modifies the used language of this component
     * @param language language
     * @return this
     */
    public LangComponent language(String language){
        this.language = language;
        if (language == null){
            this.language = DEFAULT;
        }
        return this;
    }

    /**
     * Returns the translation key
     * @return translation key
     */
    @Override
    public String getTranslationKey() {
        return getValue();
    }

    /**
     * Returns this
     * @return this
     */
    @Override
    public LangComponent asLangComponent() {
        return this;
    }

    /**
     * Returns this
     * @param color component color
     * @return this
     */
    @Override
    public LangComponent asLangComponent(ColorLike color) {
        return (LangComponent) color(color);
    }

    /**
     * Returns this
     * @param color component color
     * @param style component style
     * @return this
     */
    @Override
    public LangComponent asLangComponent(ColorLike color, Style style) {
        return (LangComponent) color(color).style(style);
    }
}

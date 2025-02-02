package bet.astral.jtext.component.lang;

import bet.astral.jtext.color.ColorLike;
import bet.astral.jtext.component.Component;
import bet.astral.jtext.style.Style;

/**
 * Helper class for classes which contain a translation key
 * @see LangComponent
 */
public interface Translatable {
    /**
     * Returns the translation key for this object
     * @return translation key
     */
    String getTranslationKey();

    /**
     * Converts this instance to a new lang component
     * @return new component instance
     * @param color component color
     * @param style component style
     * @see LangComponent
     * @see Component#lang(Translatable, ColorLike)
     */
    default LangComponent asLangComponent(ColorLike color, Style style) {
        return (LangComponent) Component.lang(this, color).style(style);
    }
    /**
     * Converts this instance to a new lang component
     * @return new component instance
     * @param color component color
     * @see LangComponent
     * @see Component#lang(Translatable, ColorLike)
     */
    default LangComponent asLangComponent(ColorLike color) {
        return Component.lang(this, color);
    }
    /**
     * Converts this instance to a new lang component
     * @return new component instance
     * @see LangComponent
     * @see Component#lang(Translatable)
     */
    default LangComponent asLangComponent() {
        return Component.lang(this);
    }
}

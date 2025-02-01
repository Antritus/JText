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
     * @see Component#lang(Translatable, ColorLike, Style)
     */
    default LangComponent getTranslationComponent(ColorLike color, Style style) {
        return Component.lang(this, color, style);
    }
    /**
     * Converts this instance to a new lang component
     * @return new component instance
     * @param color component color
     * @see LangComponent
     * @see Component#lang(Translatable, ColorLike)
     */
    default LangComponent getTranslationComponent(ColorLike color) {
        return Component.lang(this, color);
    }
    /**
     * Converts this instance to a new lang component
     * @return new component instance
     * @see LangComponent
     * @see Component#lang(Translatable)
     */
    default LangComponent getTranslationComponent() {
        return Component.lang(this);
    }
}

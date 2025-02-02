package bet.astral.jtext.component;

import bet.astral.jtext.color.ColorLike;
import bet.astral.jtext.component.lang.LangComponent;
import bet.astral.jtext.component.lang.Translatable;
import bet.astral.jtext.component.text.TextComponent;
import bet.astral.jtext.list.ImmutableList;
import bet.astral.jtext.style.Style;
import bet.astral.jtext.utils.FutureProof;

import java.util.Arrays;
import java.util.List;

/**
 * Component used for text and translations. Basic interface see {@link StyleComponent}, {@link TextComponent}, {@link LangComponent}.
 */
public abstract class Component implements ComponentLike, Iterable<Component> {
    /**
     * Returns new {@link LangComponent} with given values
     * @param text text
     * @param color color of the text
     * @param placeholders placeholders
     * @return component
     */
    public static TextComponent text(String text, ColorLike color, Object... placeholders) {
        if (placeholders == null || placeholders.length == 0) {
            return new TextComponent(text, color, null, null, null, null, null);
        }
        return new TextComponent(text, color, null, null, null, null, List.of(placeholders));
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param text text
     * @param color color of the text
     * @param placeholders placeholders
     * @return component
     */
    public static TextComponent text(String text, ColorLike color, List<Object> placeholders) {
        return new TextComponent(text, color, null, null, null, null, placeholders);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param text text
     * @param color color of the text
     * @return component
     */
    public static TextComponent text(String text, ColorLike color) {
        return new TextComponent(text, color, null);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param text text
     * @param placeholders placeholders
     * @return component
     */
    public static TextComponent text(String text, Object... placeholders) {
        return text(text, null, placeholders);
    }
    /**
     * Returns new {@link LangComponent} with given values
     * @param text text
     * @param placeholders placeholders
     * @return component
     */
    public static TextComponent text(String text, List<Object> placeholders) {
        return text(text, null, placeholders);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param text text
     * @return component
     */
    public static TextComponent text(String text) {
        return new TextComponent(text, null, null);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translationKey translation key
     * @param color color of the text
     * @param placeholders placeholders
     * @return component
     */
    public static LangComponent lang(String translationKey, ColorLike color, Object... placeholders) {
        return new LangComponent(translationKey, color, null, null, null, null, List.of(placeholders));
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translationKey translation key
     * @param color color of the text
     * @param placeholders placeholders
     * @return component
     */
    public static LangComponent lang(String translationKey, ColorLike color, List<Object> placeholders) {
        return new LangComponent(translationKey, color, null, null, null, null, placeholders);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translationKey translation key
     * @param color color of the text
     * @return component
     */
    public static LangComponent lang(String translationKey, ColorLike color) {
        return new LangComponent(translationKey, color, null);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translationKey translation key
     * @param placeholders placeholders
     * @return component
     */
    public static LangComponent lang(String translationKey, Object... placeholders) {
        return lang(translationKey, null, placeholders);
    }
    /**
     * Returns new {@link LangComponent} with given values
     * @param translationKey translation key
     * @param placeholders placeholders
     * @return component
     */
    public static LangComponent lang(String translationKey, List<Object> placeholders) {
        return lang(translationKey, null, placeholders);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translationKey translation key
     * @return component
     */
    public static LangComponent lang(String translationKey) {
        return new LangComponent(translationKey, null, null);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translatable translatable
     * @param color color of the text
     * @param placeholders placeholders
     * @return component
     */
    public static LangComponent lang(Translatable translatable, ColorLike color, Object... placeholders) {
        return new LangComponent(translatable.getTranslationKey(), color, null, null, null, null, List.of(placeholders));
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translatable translatable
     * @param color color of the text
     * @param placeholders placeholders
     * @return component
     */
    public static LangComponent lang(Translatable translatable, ColorLike color, List<Object> placeholders) {
        return new LangComponent(translatable.getTranslationKey(), color, null, null, null, null, placeholders);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translatable translatable
     * @param color color of the text
     * @return component
     */
    public static LangComponent lang(Translatable translatable, ColorLike color) {
        return new LangComponent(translatable.getTranslationKey(), color, null);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translatable translatable
     * @param placeholders placeholders
     * @return component
     */
    public static LangComponent lang(Translatable translatable, Object... placeholders) {
        return lang(translatable.getTranslationKey(), null, placeholders);
    }
    /**
     * Returns new {@link LangComponent} with given values
     * @param translatable translatable
     * @param placeholders placeholders
     * @return component
     */
    public static LangComponent lang(Translatable translatable, List<Object> placeholders) {
        return lang(translatable.getTranslationKey(), null, placeholders);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translatable translatable
     * @return component
     */
    public static LangComponent lang(Translatable translatable) {
        return new LangComponent(translatable.getTranslationKey(), null, null);
    }

    /**
     * Combines a list of ComponentLike objects into a single Component. Inserts the "Comma" after each component
     * @param components List of components
     * @param comma "comma" to be inserted after each component
     * @return A new single component combining all input components
     * @throws IllegalArgumentException if the list is empty
     */
    public static Component combine(ComponentLike comma, List<? extends ComponentLike> components) {
        if (components == null || components.isEmpty()) {
            throw new IllegalArgumentException("The components list cannot be null or empty.");
        }
        Component newComponent = components.get(0).toComponent();
        if (components.size() == 1) {
            return newComponent;
        }
        List<Component> newComponents = components.subList(1, components.size()).stream()
                .map(ComponentLike::toComponent)
                .toList();
        for (Component component : newComponents) {
            if (comma != null){
                newComponent.append(comma.toComponent());
            }

            newComponent.append(component);
        }

        return newComponent;
    }
    /**
     * Combines a list of ComponentLike objects into a single Component.
     * @param components List of components
     * @return A new single component combining all input components
     * @throws IllegalArgumentException if the list is empty
     */
    public static Component combine(List<? extends ComponentLike> components) {
        return combine(null, components);
    }

    /**
     * Combines a list of ComponentLike objects into a single Component. Inserts the "Comma" after each component
     * @param components components to combine
     * @param comma "comma" to be inserted after each component
     * @return A new single component combining all input components
     * @throws IllegalArgumentException if the list is empty
     */
    public static Component combine(ComponentLike comma, ComponentLike... components) {
        return combine(comma, Arrays.asList(components));
    }

    /**
     * Combines an array of ComponentLike objects into a single Component.
     * @param components Array of components
     * @return A new single component combining all input components
     * @throws IllegalArgumentException if the array is empty
     */
    public static Component combine(ComponentLike... components) {
        return combine(null, components);
    }

    /**
     * Returns new {@link ImmutableComponent} with the given component as the base
     * @param component component
     * @return immutable version
     */
    public static ImmutableComponent immutable(Component component){
        return new ImmutableComponent(component);
    }

    /**
     * Returns this component parsed using {@link #getMessageParsed()}} and includes children of this component parsed with {@link #getMessageParsed()}
     * @return parsed string
     */
    public abstract String getTotalParsedMessage();

    /**
     * Returns the value for this component
     * @return value
     */
    public abstract String getValue();

    /**
     * Parses the value of this component
     * @return parsed value
     */
    public abstract String getMessageParsed();

    /**
     * Returns the color of this component
     * @return component color
     */
    public abstract ColorLike getColor();

    /**
     * Sets the color of the text in the component
     * @param textColor text color
     * @return this
     */
    public abstract Component color(ColorLike textColor);

    /**
     * Returns the background color of this component
     * @return background component color
     */
    public abstract ColorLike getBackgroundColor();

    /**
     * Sets the color of the background in the component
     * @param backgroundColor background color
     * @return this
     */
    public abstract Component backgroundColor(ColorLike backgroundColor);

    /**
     * Returns the shadow color of this component
     * @return shadow color
     */
    public abstract ColorLike getShadowColor();

    /**
     * Sets the color of the text shadow in the component
     * @param shadowColor text shadow color
     * @return this
     */
    public abstract Component shadowColor(ColorLike shadowColor);

    /**
     * Returns the style of this component
     * @return component style
     */
    public abstract Style getStyle();

    /**
     * Sets the style of the component
     * @param style style
     * @return this
     */
    public abstract Component style(Style style);

    /**
     * Returns the placeholders used to format this component
     * @return placeholders
     */
    public abstract List<Object> getPlaceholders();

    /**
     * Adds a space to the component
     * @return this
     */
    public abstract Component appendSpace();
    /**
     * Adds all the listed children to this component
     * @param componentLikes children to add
     * @return this
     */
    public Component append(List<? extends ComponentLike> componentLikes) {
        for (ComponentLike componentLike : componentLikes){
            append(componentLike);
        }
        return this;
    }
    /**
     * Adds all the listed children to this component
     * @param componentLikes children to add
     * @return this
     */
    public Component append(ComponentLike... componentLikes) {
        for (ComponentLike componentLike : componentLikes){
            append(componentLike);
        }
        return this;
    }

    /**
     * Adds the given component as a child of this list
     * @param component component
     * @return this
     */
    public abstract Component append(ComponentLike component);

    /**
     * Creates a new translatable component with given values
     * @param translatable translatable (converts to key)
     * @param color color
     * @param style style
     * @return this
     */
    public Component appendLanguage(Translatable translatable, ColorLike color, Style style) {
        return append(lang(translatable, color).style(style));
    }
    /**
     * Creates a new translatable component with given values
     * @param translatable translatable (converts to key)
     * @param color color
     * @return this
     */
    public Component appendLanguage(Translatable translatable, ColorLike color) {
        return appendLanguage(translatable, color, null);
    }
    /**
     * Creates a new translatable component with given values
     * @param translatable translatable (converts to key)
     * @return this
     */
    public Component appendLanguage(Translatable translatable) {
        return appendLanguage(translatable, null, null);
    }

    /**
     * Creates a new translatable component with given values
     * @param key translation key
     * @param color color
     * @param style style
     * @return this
     */
    public Component appendLanguage(String key, ColorLike color, Style style) {
        return append(lang(key, color).style(style));
    }
    /**
     * Creates a new translatable component with given values
     * @param key translation key
     * @param color color
     * @return this
     */
    public Component appendLanguage(String key, ColorLike color) {
        return appendLanguage(key, color, null);
    }
    /**
     * Creates a new translatable component with given values
     * @param lang translation key
     * @return this
     */
    public Component appendLanguage(String lang) {
        return appendLanguage(lang, null, null);
    }

    /**
     * Creates a new text component with given values
     * @param text literal text
     * @param color color
     * @param style style
     * @return this
     */
    public Component appendText(String text, ColorLike color, Style style) {
        return append(text(text, color).style(style));
    }
    /**
     * Creates a new text component with given values
     * @param text literal text
     * @param color color
     * @return this
     */
    public Component appendText(String text, ColorLike color) {
        return appendText(text, color, null);
    }
    /**
     * Creates a new text component with given values
     * @param text literal text
     * @return this
     */
    public Component appendText(String text) {
        return appendText(text, null, null);
    }

    @Override
    public Component toComponent() {
        return this;
    }

    /**
     * Returns the children of this component
     * @return children
     */
    public abstract ImmutableList<Component> getChildren();

    /**
     * Converts this component to a list with its children
     * @return this as a list
     */
    public abstract ImmutableList<Component> toList();

    /**
     * Serialization helper
     */
    public enum Type implements FutureProof {
        /**
         * Unknown value converts to {@link UnknownComponent}
         */
        UNKNOWN(-1),
        /**
         * Translation/Lang component {@link LangComponent}
         */
        TRANSLATION(1),
        /**
         * Literal text component {@link TextComponent}
         */
        TEXT(2)
        ;
        private final int futureProof;

        Type(int futureProof) {
            this.futureProof = futureProof;
        }

        @Override
        public int getFutureProof() {
            return futureProof;
        }
    }
}

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
     * Returns new {@link TextComponent} with given values
     * @param text text (literal)
     * @param color color of the text
     * @param backgroundColor color of the text background
     * @param shadowColor color of the text shadow
     * @param style style of the text
     * @return component
     */
    public static Component text(String text, ColorLike color, ColorLike backgroundColor, ColorLike shadowColor, Style style, List<Object> placeholders){
        return new TextComponent(text, color, backgroundColor, shadowColor, style, null, placeholders);
    }

    /**
     * Returns new {@link TextComponent} with given values
     * @param text text (literal)
     * @param color color of the text
     * @param style style of the text
     * @return component
     */
    public static Component text(String text, ColorLike color, Style style){
        return new TextComponent(text, color, style);
    }

    /**
     * Returns new {@link TextComponent} with given values
     * @param text text (literal)
     * @param color color of the text
     * @return component
     */
    public static Component text(String text, ColorLike color){
        return text(text, color, null);
    }

    /**
     * Returns new {@link TextComponent} with the given values
     * @param text text
     * @param placeholders placeholders
     * @return component
     */
    public static Component text(String text, List<Object> placeholders){
        return new TextComponent(text, null, null, null, null, null, placeholders);
    }

    /**
     * Returns new {@link TextComponent} with given values
     * @param text text (literal)
     * @return component
     */
    public static Component text(String text){
        return text(text, null, null);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translationKey translation key
     * @param color color of the text
     * @param backgroundColor color of the background of the text
     * @param shadowColor color of the shadow of the text
     * @param style style of the text
     * @return component
     */
    public static LangComponent lang(String translationKey, ColorLike color, ColorLike backgroundColor, ColorLike shadowColor, Style style) {
        return new LangComponent(translationKey, color, backgroundColor, shadowColor, style, null, null);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translationKey translation key
     * @param color color of the text
     * @param style style of the text
     * @return component
     */
    public static LangComponent lang(String translationKey, ColorLike color, Style style) {
        return new LangComponent(translationKey, color, style);
    }
    /**
     * Returns new {@link LangComponent} with given values
     * @param translationKey translation key
     * @param color color of the text
     * @return component
     */
    public static LangComponent lang(String translationKey, ColorLike color) {
        return lang(translationKey, color, null);
    }
    /**
     * Returns new {@link LangComponent} with given values
     * @param translationKey translation key
     * @return component
     */
    public static LangComponent lang(String translationKey) {
        return lang(translationKey, null, null);
    }

    /**
     * Returns new {@link LangComponent} with given values
     * @param translatable translatable which to convert
     * @param color color of the text
     * @param style style of the text
     * @return component
     */
    public static LangComponent lang(Translatable translatable, ColorLike color, Style style) {
        return lang(translatable.getTranslationKey(), color, style);
    }
    /**
     * Returns new {@link LangComponent} with given values
     * @param translatable translatable which to convert
     * @param color color of the text
     * @return component
     */
    public static LangComponent lang(Translatable translatable, ColorLike color) {
        return lang(translatable.getTranslationKey(), color, null);
    }
    /**
     * Returns new {@link LangComponent} with given values
     * @param translatable translatable which to convert
     * @return component
     */
    public static LangComponent lang(Translatable translatable) {
        return lang(translatable.getTranslationKey(), null, null);
    }

    /**
     * Converts a list of ComponentLike objects into a single Component.
     * @param components List of components
     * @return A new single component combining all input components
     * @throws IllegalArgumentException if the list is empty
     */
    public static Component of(List<? extends ComponentLike> components) {
        if (components == null || components.isEmpty()) {
            throw new IllegalArgumentException("The components list cannot be null or empty.");
        }
        Component newComponent = components.get(0).toComponent();
        if (components.size() == 1) {
            return newComponent;
        }
        return newComponent.addChildren(components.subList(1, components.size()).stream()
                .map(ComponentLike::toComponent)
                .toList());
    }

    /**
     * Converts an array of ComponentLike objects into a single Component.
     * @param components Array of components
     * @return A new single component combining all input components
     * @throws IllegalArgumentException if the array is empty
     */
    public static Component of(ComponentLike... components) {
        if (components == null || components.length == 0) {
            throw new IllegalArgumentException("The components array cannot be null or empty.");
        }
        Component newComponent = components[0].toComponent();
        if (components.length == 1) {
            return newComponent;
        }
        return newComponent.addChildren(
                Arrays.stream(components, 1, components.length)
                        .map(ComponentLike::toComponent)
                        .toList()
        );
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
     * Returns the background color of this component
     * @return background component color
     */
    public abstract ColorLike getBackgroundColor();

    /**
     * Returns the shadow color of this component
     * @return shadow color
     */
    public abstract ColorLike getShadowColor();

    /**
     * Returns the style of this component
     * @return component style
     */
    public abstract Style getStyle();

    /**
     * Returns the placeholders used to format this component
     * @return placeholders
     */
    public abstract List<Object> getPlaceholders();

    /**
     * Adds a space to the component
     * @return this
     */
    public abstract Component addSpace();
    /**
     * Adds all the listed children to this component
     * @param componentLikes children to add
     * @return this
     */
    public Component addChildren(List<? extends ComponentLike> componentLikes) {
        for (ComponentLike componentLike : componentLikes){
            addChild(componentLike);
        }
        return this;
    }
    /**
     * Adds all the listed children to this component
     * @param componentLikes children to add
     * @return this
     */
    public Component addChildren(ComponentLike... componentLikes) {
        for (ComponentLike componentLike : componentLikes){
            addChild(componentLike);
        }
        return this;
    }

    /**
     * Adds the given component as a child of this list
     * @param component component
     * @return this
     */
    public abstract Component addChild(ComponentLike component);

    /**
     * Creates a new translatable component with given values
     * @param translatable translatable (converts to key)
     * @param color color
     * @param style style
     * @return this
     */
    public Component addChildLang(Translatable translatable, ColorLike color, Style style) {
        return addChild(lang(translatable, color, style));
    }
    /**
     * Creates a new translatable component with given values
     * @param translatable translatable (converts to key)
     * @param color color
     * @return this
     */
    public Component addChildLang(Translatable translatable, ColorLike color) {
        return addChildLang(translatable, color, null);
    }
    /**
     * Creates a new translatable component with given values
     * @param translatable translatable (converts to key)
     * @return this
     */
    public Component addChildLang(Translatable translatable) {
        return addChildLang(translatable, null, null);
    }

    /**
     * Creates a new translatable component with given values
     * @param key translation key
     * @param color color
     * @param style style
     * @return this
     */
    public Component addChildLang(String key, ColorLike color, Style style) {
        return addChild(lang(key, color, style));
    }
    /**
     * Creates a new translatable component with given values
     * @param key translation key
     * @param color color
     * @return this
     */
    public Component addChildLang(String key, ColorLike color) {
        return addChildLang(key, color, null);
    }
    /**
     * Creates a new translatable component with given values
     * @param lang translation key
     * @return this
     */
    public Component addChildLang(String lang) {
        return addChildLang(lang, null, null);
    }

    /**
     * Creates a new text component with given values
     * @param text literal text
     * @param color color
     * @param style style
     * @return this
     */
    public Component addChildText(String text, ColorLike color, Style style) {
        return addChild(text(text, color, style));
    }
    /**
     * Creates a new text component with given values
     * @param text literal text
     * @param color color
     * @return this
     */
    public Component addChildText(String text, ColorLike color) {
        return addChildText(text, color, null);
    }
    /**
     * Creates a new text component with given values
     * @param text literal text
     * @return this
     */
    public Component addChildText(String text) {
        return addChildText(text, null, null);
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

package bet.astral.jtext.component;

import bet.astral.jtext.color.ColorLike;
import bet.astral.jtext.component.lang.LangComponent;
import bet.astral.jtext.list.ImmutableList;
import bet.astral.jtext.list.ImmutableListImpl;
import bet.astral.jtext.style.Style;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract implementation of {@link Component}
 */
public abstract class StyleComponent extends Component {
    private String value;
    private ColorLike color;
    private ColorLike background;
    private ColorLike shadow;
    private Style style;
    private ArrayList<Component> children;
    private ArrayList<Object> placeholders;

    /**
     * Creates a new instance of the style component
     *
     * @param value    value
     * @param color    color
     * @param style    style
     * @param children children
     */
    public StyleComponent(String value, ColorLike color, ColorLike background, ColorLike shadow, Style style, ArrayList<Component> children, List<Object> placeholders) {
        this.value = value;
        this.color = color;
        this.background = background;
        this.shadow = shadow;
        this.style = style;

        if (children == null) {
            this.children = new ArrayList<>();
        } else {
            this.children = new ArrayList<>(children);
        }

        if (placeholders == null) {
            this.placeholders = new ArrayList<>();
        } else {
            this.placeholders = new ArrayList<>(placeholders);
        }
    }

    /**
     * Creates a new instance of the style component
     *
     * @param value value
     * @param color color
     * @param style style
     */
    public StyleComponent(String value, ColorLike color, Style style) {
        this(value, color, null, null, style, null, null);
    }

    /**
     * Creates a new instance of the style component.
     */
    public StyleComponent() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTotalParsedMessage() {
        if (children == null || children.isEmpty()) {
            return getMessageParsed();
        }

        StringBuilder builder = new StringBuilder(getMessageParsed());
        for (Component child : getChildren()) {
            builder.append(child.getTotalParsedMessage());
        }
        return builder.toString();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessageParsed() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorLike getColor() {
        return color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component color(ColorLike textColor) {
        color = textColor;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorLike getBackgroundColor() {
        return background;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component backgroundColor(ColorLike backgroundColor) {
        background = backgroundColor;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorLike getShadowColor() {
        return shadow;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component shadowColor(ColorLike shadowColor) {
        shadow = shadowColor;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Style getStyle() {
        return style;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component style(Style style) {
        this.style = style;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component appendSpace() {
        appendText(" ");
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component appendNewLine() {
        appendText("\n");
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component append(ComponentLike component) {
        this.children.add(component.toComponent());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableList<Component> getChildren() {
        if (children == null) {
            return new ImmutableListImpl<>(Collections.emptyList());
        }
        return new ImmutableListImpl<>(children, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableList<Component> toList() {
        if (children == null || children.isEmpty()) {
            return new ImmutableListImpl<>(List.of(this));
        }

        ArrayList<Component> list = new ArrayList<>();
        list.add(this);
        list.addAll(children);
        return new ImmutableListImpl<>(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> getPlaceholders() {
        return placeholders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull Iterator<Component> iterator() {
        return toList().iterator();
    }

}

package bet.astral.jtext.component;

import bet.astral.jtext.color.ColorLike;
import bet.astral.jtext.list.ImmutableList;
import bet.astral.jtext.list.ImmutableListImpl;
import bet.astral.jtext.style.Style;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Immutable component implementation. The component cannot be modified
 */
public class ImmutableComponent extends Component{
    protected final Component component;
    private final ImmutableList<Component> totalList;
    private final ImmutableList<Component> convertedChildren;

    /**
     * Creates a new immutable component using the given component as the base
     * @param component component
     */
    public ImmutableComponent(Component component) {
        this.component = component;
        if (component.getChildren().isEmpty()){
            convertedChildren = new ImmutableListImpl<>(Collections.emptyList());
        } else {
            convertedChildren = new ImmutableListImpl<>(new ArrayList<>(component.getChildren().stream().map(ImmutableComponent::new).toList()));
        }

        List<Component> total = new ArrayList<>();
        total.add(this);
        total.addAll(convertedChildren);
        totalList = new ImmutableListImpl<>(total);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTotalParsedMessage() {
        return component.getTotalParsedMessage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return component.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessageParsed() {
        return component.getMessageParsed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorLike getColor() {
        return component.getColor();
    }

    /**
     * Calling this method calls {@link UnsupportedOperationException}
     * @param textColor color
     * @throws UnsupportedOperationException if used
     */
    @Override
    public Component color(ColorLike textColor) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Cannot modify the color of an immutable component!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorLike getBackgroundColor() {
        return component.getBackgroundColor();
    }

    /**
     * Calling this method calls {@link UnsupportedOperationException}
     * @param backgroundColor color
     * @throws UnsupportedOperationException if used
     */
    @Override
    public Component backgroundColor(ColorLike backgroundColor) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify the background color of an immutable component!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorLike getShadowColor() {
        return component.getShadowColor();
    }

    /**
     * Calling this method calls {@link UnsupportedOperationException}
     * @param shadowColor color
     * @throws UnsupportedOperationException if used
     */
    @Override
    public Component shadowColor(ColorLike shadowColor) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify the shadow color of an immutable component!");
    }

    /**
     * Returns immutable version of this component's style
     * @return style
     */
    @Override
    public Style getStyle() {
        return new Style(component.getStyle(), component.getStyle());
    }

    /**
     * Calling this method calls {@link UnsupportedOperationException}
     * @param style style
     * @throws UnsupportedOperationException if used
     */
    @Override
    public Component style(Style style) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify the style of an immutable component!");
    }

    /**
     * Converts component's placeholders to immutable list and returns it.
     * @return placeholders
     */
    @Override
    public List<Object> getPlaceholders() {
        if (component.getPlaceholders() != null){
            return new ImmutableListImpl<>(component.getPlaceholders());
        }
        return Collections.emptyList();
    }

    /**
     * Calling this method calls {@link UnsupportedOperationException}
     * @throws UnsupportedOperationException if used
     */
    @Override
    public Component appendSpace() {
        throw new UnsupportedOperationException("Cannot add children to an immutable component!");
    }

    /**
     * Calling this method calls {@link UnsupportedOperationException}
     * @param component component
     * @throws UnsupportedOperationException if used
     */
    @Override
    public Component append(ComponentLike component) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Cannot add children to an immutable component!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableList<Component> getChildren() {
        return convertedChildren;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableList<Component> toList() {
        return totalList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull Iterator<Component> iterator() {
        return totalList.iterator();
    }
}

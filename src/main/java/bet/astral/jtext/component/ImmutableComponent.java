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

    @Override
    public String getTotalParsedMessage() {
        return component.getTotalParsedMessage();
    }

    @Override
    public String getValue() {
        return component.getValue();
    }

    @Override
    public String getMessageParsed() {
        return component.getMessageParsed();
    }

    @Override
    public ColorLike getColor() {
        return component.getColor();
    }

    @Override
    public ColorLike getBackgroundColor() {
        return component.getBackgroundColor();
    }

    @Override
    public ColorLike getShadowColor() {
        return component.getShadowColor();
    }

    @Override
    public Style getStyle() {
        return component.getStyle();
    }

    @Override
    public List<Object> getPlaceholders() {
        if (component.getPlaceholders() != null){
            return new ImmutableListImpl<>(component.getPlaceholders());
        }
        return Collections.emptyList();
    }

    @Override
    public Component addSpace() {
        throw new UnsupportedOperationException("Cannot add children to an immutable component!");
    }

    @Override
    public Component addChild(ComponentLike component) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Cannot add children to an immutable component!");
    }

    @Override
    public ImmutableList<Component> getChildren() {
        return convertedChildren;
    }

    @Override
    public ImmutableList<Component> toList() {
        return totalList;
    }

    @Override
    public @NotNull Iterator<Component> iterator() {
        return totalList.iterator();
    }
}

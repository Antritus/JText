package bet.astral.jtext.terminal;

import bet.astral.jtext.component.Component;
import bet.astral.jtext.serializer.AnsiSerializer;

public interface AnsiTerminal {
    AnsiSerializer getSerializer();
    void print(String text);
    default void print(Component component) {
        print(getSerializer().serialize(component));
    }
}

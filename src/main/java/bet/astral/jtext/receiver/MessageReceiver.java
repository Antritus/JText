package bet.astral.jtext.receiver;

import bet.astral.jtext.color.simple.Color;
import bet.astral.jtext.component.Component;
import bet.astral.jtext.component.lang.Translatable;
import bet.astral.jtext.style.Style;

public interface MessageReceiver {
    // Translatable

    default void sendLangComponent(Translatable translatable, Color color, Style style) {
        sendComponent(Component.lang(translatable, color, style));
    }
    default void sendLangComponent(Translatable translatable, Color color) {
        sendComponent(Component.lang(translatable, color, null));
    }

    // Translation keys

    default void sendLangComponent(String lang, Color color, Style style) {
        sendComponent(Component.lang(lang, color, style));
    }
    default void sendLangComponent(String lang, Color color) {
        sendComponent(Component.lang(lang, color, null));
    }

    // Default text

    default void sendTextComponent(String message, Color color, Style style) {
        sendComponent(Component.text(message, color, style));
    }
    default void sendTextComponent(String message, Color color) {
        sendComponent(Component.text(message, color, null));
    }

    // Components

    void sendComponent(Component component);
}

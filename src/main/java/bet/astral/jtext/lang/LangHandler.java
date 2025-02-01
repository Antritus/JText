package bet.astral.jtext.lang;

import bet.astral.jtext.component.Component;

import java.util.List;

public interface LangHandler {
    String DEFAULT_LANG = "_D_";

    Component translate(String locale, String text, List<Object> placeholders);
    Component translate(String locale, String text);
    Component translate(String text, List<Object> placeholders);
    Component translate(String text);

    class LangHandlerImpl implements LangHandler {
        @Override
        public Component translate(String locale, String text, List<Object> placeholders) {
            return translate(text, placeholders);
        }

        @Override
        public Component translate(String locale, String text) {
            return translate(text);
        }

        @Override
        public Component translate(String text, List<Object> placeholder) {
            return Component.text(text, placeholder);
        }

        @Override
        public Component translate(String text) {
            return Component.text(text);
        }
    }
}

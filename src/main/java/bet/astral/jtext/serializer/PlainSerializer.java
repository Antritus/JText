package bet.astral.jtext.serializer;

import bet.astral.jtext.component.Component;
import bet.astral.jtext.component.lang.LangComponent;
import bet.astral.jtext.lang.LangHandler;

/**
 * Converts components to string and converts strings to components
 */
public class PlainSerializer implements Serializer<String, Component> {
    private LangHandler langHandler;

    public PlainSerializer() {
        this.langHandler = new LangHandler.LangHandlerImpl();
    }

    public PlainSerializer(LangHandler langHandler) {
        this.langHandler = langHandler;
    }

    @Override
    public String serialize(Component component) {
        return component instanceof LangComponent langComponent ?
                langHandler.translate(langComponent.getLanguage(), langComponent.getTranslationKey(), langComponent.getPlaceholders()).getTotalParsedMessage()
                : component.getTotalParsedMessage();
    }

    @Override
    public Component deserialize(String s) {
        return Component.text(s);
    }

    @Override
    public LangHandler getLangHandler() {
        return langHandler;
    }
}

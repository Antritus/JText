package bet.astral.jtext.serializer;

import bet.astral.jtext.component.Component;
import bet.astral.jtext.lang.LangHandler;

public interface Serializer<SERIALIZED, DESERIALIZED extends Component> {
    SERIALIZED serialize(DESERIALIZED component);
    DESERIALIZED deserialize(SERIALIZED serialized);

    LangHandler getLangHandler();
}

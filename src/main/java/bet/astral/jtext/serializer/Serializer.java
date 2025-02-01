package bet.astral.jtext.serializer;

import bet.astral.jtext.lang.LangHandler;

public interface Serializer<SERIALIZED, DESERIALIZED> {
    SERIALIZED serialize(DESERIALIZED component);
    DESERIALIZED deserialize(SERIALIZED serialized);

    LangHandler getLangHandler();
}

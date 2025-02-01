package bet.astral.jtext.utils;

/*
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import bet.astral.text.utils.TriState;
import org.jetbrains.annotations.NotNull;

public class CRBinHelper {
    public static void writeTriState(@NotNull CRBinSerializer serializer, String name, @NotNull TriState enumeration){
        writeEnum(serializer, name, enumeration);
    }
    public static TriState readTriState(@NotNull CRBinDeserializer serializer, String name) throws IndexOutOfBoundsException{
        return readEnum(serializer, name, TriState.NOT_SET, TriState.class);
    }


    public static <T extends Enum<T>> void writeEnum(@NotNull CRBinSerializer serializer, String name, @NotNull T enumeration){
        serializer.writeInt(name, enumeration.ordinal());
    }
    public static <T extends Enum<T>> T readEnum(@NotNull CRBinDeserializer serializer, String name, @NotNull T enumerationDefault, @NotNull Class<T> enumeration) throws IndexOutOfBoundsException{
        int value = serializer.readInt(name, enumerationDefault.ordinal());
        return enumeration.getEnumConstants()[value];
    }

    public static <T extends Enum<T> & FutureProof> void writeFutureProofEnum(@NotNull CRBinSerializer serializer, String name, @NotNull T enumeration){
        serializer.writeInt(name, enumeration.getFutureProof());
    }
    public static <T extends Enum<T> & FutureProof> @NotNull T readFutureProofEnum(@NotNull CRBinDeserializer serializer, String name, @NotNull T enumerationDefault, @NotNull Class<T> enumeration) throws IndexOutOfBoundsException{
        int value = serializer.readInt(name, enumerationDefault.getFutureProof());
        for (T constant : enumeration.getEnumConstants()){
            if (constant.getFutureProof() == value){
                return constant;
            }
        }
        return enumerationDefault;
    }

    public static String readStringOrElse(@NotNull CRBinDeserializer deserializer, String name, String otherwise){
        String read = deserializer.readString(name);
        if (read == null){
            return otherwise;
        }
        return read;
    }
}


 */
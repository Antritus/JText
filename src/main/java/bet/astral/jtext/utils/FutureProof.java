package bet.astral.jtext.utils;

public interface FutureProof {
    static <T extends Enum<T> & FutureProof> T unknown(T value, T defaultValue){
        return value.getFutureProof() == -1 ? defaultValue : value;
    }

    int getFutureProof();
}

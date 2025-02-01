package bet.astral.jtext.utils;

import org.jetbrains.annotations.Nullable;

/**
 * Default tri state with true, false, not set
 */
public enum TriState {
    NOT_SET(null),
    TRUE(true),
    FALSE(false),
    ;

    private final Boolean value;

    TriState(Boolean value) {
        this.value = value;
    }

    /**
     * Returns the real value
     * @return real value
      */
    @Nullable
    public Boolean getBool(){
        return value;
    }

    /**
     * Returns the real value, if it's not {@link #NOT_SET}, else returns {@link #value}
     * @param value if null, return this
     * @return value
     */
    public boolean getBoolOrElse(boolean value){
        return this.value != null ? this.value : value;
    }
    /**
     * Returns the real value, if it's not {@link #NOT_SET}, else returns {@link #value}
     * @param value if null, return this
     * @return value
     */
    public boolean getBoolOrElse(TriState value){
        if (value == NOT_SET){
            throw new UnsupportedOperationException("Cannot use NOT_SET tri state value in getBooleanOrElse(TriState)");
        }
        return this.value != null ? this.value : value.value;
    }


}

package bet.astral.jtext.style;

import bet.astral.jtext.utils.TriState;

/**
 * Allows additional styling of the components
 */
public class Style {
    public static final Style DEFAULT = new Builder().build();
    public static final TriState DEFAULT_BOLD = TriState.FALSE;
    public static final TriState DEFAULT_OBFUSCATION = TriState.FALSE;
    public static final TriState DEFAULT_UNDERLINED = TriState.FALSE;
    public static final TriState DEFAULT_STRIKETHROUGH = TriState.FALSE;
    public static final TriState DEFAULT_OVERLINE = TriState.FALSE;
    private TriState bold = DEFAULT_BOLD;
    private TriState obfuscation = DEFAULT_OBFUSCATION;
    private TriState underlined = DEFAULT_UNDERLINED;
    private TriState strikethrough = DEFAULT_STRIKETHROUGH;
    private TriState overline = DEFAULT_OVERLINE;

    /**
     * Creates a new instance of component style
     *
     * @param isBold       is bold
     * @param obfuscation  is obfuscated
     * @param isOverline   is overlined
     * @param isStrike     is strikethrough
     * @param isUnderlined is underlined
     */
    public Style(TriState isBold, TriState obfuscation, TriState isOverline, TriState isStrike, TriState isUnderlined){
        this.bold = isBold;
        this.obfuscation = obfuscation;
        this.underlined = isUnderlined;
        this.strikethrough = isStrike;
        this.overline = isOverline;
    }

    /**
     * Creates a new instance of component style
     */
    public Style(){
    }

    public Style(Style style, Style backupStyle) {
        if (style == null && backupStyle == null){
            return;
        } else if (style == null){
            bold = backupStyle.bold;
            obfuscation = backupStyle.obfuscation;
            underlined = backupStyle.underlined;
            strikethrough = backupStyle.strikethrough;
            overline = backupStyle.overline;
            return;
        } else if (backupStyle == null){
            bold = style.bold;
            obfuscation = style.obfuscation;
            underlined = style.underlined;
            strikethrough = style.strikethrough;
            overline = style.overline;
            return;
        }
        bold = orElse(style.bold, backupStyle.bold);
        obfuscation = orElse(style.obfuscation, style.obfuscation);
        underlined = orElse(style.underlined, style.underlined);
        overline = orElse(style.overline, style.overline);
        strikethrough = orElse(style.strikethrough, style.strikethrough);
    }

    private TriState orElse(TriState state, TriState backup) {
        if (state == TriState.NOT_SET) {
            return backup;
        }
        return state;
    }

    /**
     * true if bold, else false
     * @return bold
     */
    public boolean isBold(){
        return bold.getBoolOrElse(DEFAULT_BOLD);
    }

    /**
     * true if overline, else false
     * @return overline
     */
    public boolean isOverline(){
        return overline.getBoolOrElse(DEFAULT_OVERLINE);
    }

    /**
     * true if strikethrough, else false
     * @return strikethrough
     */
    public boolean isStrikethrough(){
        return strikethrough.getBoolOrElse(DEFAULT_STRIKETHROUGH);
    }

    /**
     * true if underlined, else false
     * @return underlined
     */
    public boolean isUnderlined(){
        return underlined.getBoolOrElse(DEFAULT_UNDERLINED);
    }


    /**
     * True if the style is obfuscated
     * @return obfuscated
     */
    public boolean isObfuscated() {
        return obfuscation.getBoolOrElse(DEFAULT_OBFUSCATION);
    }

    /**
     * Returns true if the serializer should write the given value status
     * @return true if should, else false
     */
    private boolean shouldWrite(TriState current, TriState defaultValue){
        return current != TriState.NOT_SET && !current.equals(defaultValue);
    }

    /**
     * Style builder to help with more readable code
     */
    public static class Builder {
        private TriState isBold = DEFAULT_BOLD;
        private TriState isObfuscated = DEFAULT_OBFUSCATION;
        private TriState isOverline = DEFAULT_OVERLINE;
        private TriState isStrike = DEFAULT_STRIKETHROUGH;
        private TriState isUnderlined = DEFAULT_UNDERLINED;

        /**
         * Changes the bold status of the style
         * @param bold bold status
         * @return this
         */
        public Builder setBold(TriState bold) {
            isBold = bold;
            return this;
        }
        /**
         * Changes the strikethrough status of the style
         * @param strikethrough strikethrough status
         * @return this
         */
        public Builder setStrikethrough(TriState strikethrough) {
            isStrike = strikethrough;
            return this;
        }
        /**
         * Changes the underline status of the style
         * @param underlined underline status
         * @return this
         */
        public Builder setUnderlined(TriState underlined) {
            isUnderlined = underlined;
            return this;
        }
        /**
         * Changes the overline status of the style
         * @param overline overline status
         * @return this
         */
        public Builder setOverline(TriState overline) {
            isOverline = overline;
            return this;
        }

        /**
         * Sets the style of the message to be obfuscated
         * @param isObfuscated obfuscated
         * @return this
         */
        public Builder setObfuscated(TriState isObfuscated) {
            this.isObfuscated = isObfuscated;
            return this;
        }

        /**
         * Creates a new style instance
         * @return style
         */
        public Style build(){
            return new Style(isBold, isObfuscated, isOverline, isStrike, isUnderlined);
        }
    }
}

package bet.astral.jtext.serializer;

import bet.astral.jtext.color.ColorLike;
import bet.astral.jtext.color.GradientColor;
import bet.astral.jtext.color.InheritedColor;
import bet.astral.jtext.color.simple.Color;
import bet.astral.jtext.color.simple.HSLColor;
import bet.astral.jtext.color.simple.HSVColor;
import bet.astral.jtext.component.Component;
import bet.astral.jtext.component.lang.LangComponent;
import bet.astral.jtext.lang.LangHandler;
import bet.astral.jtext.style.Style;
import bet.astral.jtext.utils.TriState;
import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import java.util.LinkedList;
import java.util.List;

/**
 * Converts the components to ANSI colored/formatted string. Does not parse ansi colors back to component.
 */
public class AnsiSerializer implements Serializer<String, Component> {
    private LangHandler langHandler;
    public AnsiSerializer() {
        this.langHandler = new LangHandler.LangHandlerImpl();
    }
    public AnsiSerializer(LangHandler langHandler) {
        this.langHandler = langHandler;
    }

    @Override
    public String serialize(Component component) {
        return serialize(component, finalColor(component.getColor()), finalColor(component.getBackgroundColor()), component.getStyle());
    }


    public boolean orElseTri(TriState value, TriState defaultValue) {
        if (value == TriState.NOT_SET) {
            return defaultValue.getBoolOrElse(false);
        }
        return value.getBoolOrElse(false);
    }

    public <V, T extends V> V orElse(V value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public Color finalColor(ColorLike colorLike) {
        if (colorLike instanceof Color) {
            return (Color) colorLike;
        } else if (colorLike instanceof InheritedColor) {
            return null;
        } else if (colorLike instanceof HSVColor hsvColor) {
            return hsvColor.toRGBColor();
        } else if (colorLike instanceof HSLColor hslColor) {
            return hslColor.toRGBColor();
        } else if (colorLike instanceof GradientColor gradientColor) {
            return gradientColor.getNextColor();
        }
        return null;
    }

    public String serialize(Component component, Color backupColor, Color backupBackgroundColor, Style backupStyle) {
        Color color = finalColor(orElse(component.getColor(), backupColor));
        Color backgroundColor = finalColor(orElse(component.getBackgroundColor(), backupBackgroundColor));
        Style style = component.getStyle();
        Style jointStyle = new Style(style, backupStyle);

        boolean bold = jointStyle.isBold();
        boolean overline = jointStyle.isOverline();
        boolean strike = jointStyle.isStrikethrough();
        boolean underlined = jointStyle.isUnderlined();

        List<Attribute> attributes = new LinkedList<>();
        if (color != null) {
            attributes.add(convertColorText(color));
        }
        if (backgroundColor != null) {
            attributes.add(convertColorBack(backgroundColor));
        }
        if (bold) {
            attributes.add(bold());
        }
        if (overline) {
            attributes.add(overline());
        }
        if (strike) {
            attributes.add(strike());
        }
        if (underlined) {
            attributes.add(underline());
        }

        String returnValue;
        if (component instanceof LangComponent langComponent) {
            if (langHandler == null) {
                returnValue = langComponent.getValue();
            } else {
                returnValue = serialize(langHandler.translate(langComponent.getLanguage(), langComponent.getTranslationKey(), langComponent.getPlaceholders()),
                        color,
                        backgroundColor,
                        style);

            }
        } else{
            returnValue = component.getValue();
        }

        if (returnValue == null){
            return "";
        }
        returnValue = Ansi.makeItFabulous(returnValue, attributes.toArray(new Attribute[0]));
        return returnValue + serializeChildren(component.getChildren(), color, backgroundColor, jointStyle);
    }
    private String serializeChildren(List<Component> children, Color color, Color backgroundColor, Style style) {
        StringBuilder returnValue = new StringBuilder();
        for (Component child : children){
            returnValue.append(serialize(child, color, backgroundColor, style));
        }
        return returnValue.toString();
    }

    public Attribute convertColorBack(Color color) {
        return Attribute.BACK_COLOR(color.getRed(), color.getGreen(), color.getBlue());
    }

    public Attribute convertColorText(Color color) {
        return Attribute.TEXT_COLOR(color.getRed(), color.getGreen(), color.getBlue());
    }

    public Attribute bold() {
        return Attribute.BOLD();
    }

    public Attribute italic() {
        return Attribute.ITALIC();
    }

    public Attribute overline() {
        return Attribute.OVERLINED();
    }

    public Attribute underline() {
        return Attribute.UNDERLINE();
    }

    public Attribute strike() {
        return Attribute.STRIKETHROUGH();
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

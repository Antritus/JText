package bet.astral.jtext;

import bet.astral.jtext.ansi.ANSIHelper;
import bet.astral.jtext.ansi.ANSISerializer;
import bet.astral.jtext.color.Colors;
import bet.astral.jtext.component.Component;

public class Test {
    public static void main(String[] args) {
        Component component = Component.text("Hello World,").color(Colors.BLUE).backgroundColor(Colors.AQUA)
                .shadowColor(Colors.RED)
                .appendSpace()
                .append(Component.text("components are easy to use!", Colors.BLUE).color(Colors.INDIGO)
                        .appendSpace()
                        .appendText("Red", Colors.RED)
                        .appendSpace()
                        .appendText("Blue", Colors.BLUE)
                        .appendSpace()
                        .append(Component.text("Green Background").backgroundColor(Colors.GREEN))
                )
                .appendSpace()
                .append(Component.text(" !", Colors.BLACK).backgroundColor(Colors.WHITE).shadowColor(Colors.RED))
                ;

        ANSISerializer ts = new ANSISerializer();
        System.out.println(ts.serialize(component));
        System.out.println(ts.serialize(Component.text("Welcome to ", Colors.RED).appendText("JText", Colors.BLUE)));
        System.out.println(ANSIHelper.UNDERLINE+ ANSIHelper.OVERLINE+ ANSIHelper.STRIKETHROUGH+ ANSIHelper.convertColorToANSIBackground(Colors.BLUE)+"HELLLO!"+ ANSIHelper.RESET_FORMAT);
    }
}

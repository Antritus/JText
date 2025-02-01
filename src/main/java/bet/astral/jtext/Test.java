package bet.astral.jtext;

import bet.astral.jtext.color.Colors;
import bet.astral.jtext.component.Component;
import bet.astral.jtext.serializer.AnsiSerializer;
import bet.astral.jtext.style.Style;

public class Test {
    public static void main(String[] args) {
        AnsiSerializer ts = new AnsiSerializer();
        Component component = Component.text("HELLO", Colors.RED).addSpace()
                .addChild(Component.text("WORLD", Colors.BLUE, Colors.WHITE, null, new Style(), null).addChildText("NEW CHILD", Colors.RED)).addChildText(" FROM CHILD");

        System.out.println(ts.serialize(component));

    }
}

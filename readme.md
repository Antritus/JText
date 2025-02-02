[![](https://jitpack.io/v/antritus/Jtext.svg)](https://jitpack.io/#antritus/Jtext)
# JText
Java text component library which has built in serializer to serialize java components to ANSI text.

## Add it
Add the repository to gradle
```groovy
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.antritus:Jtext:-SNAPSHOT")
}
```

## How to use it
Creates a new component and sends it to the terminal. Parses the component to ANSI text.

```java
import bet.astral.jtext.color.Colors;
import bet.astral.jtext.component.Component;
import bet.astral.jtext.serializer.AnsiSerializer;

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
                .append(Component.text(" ", Colors.BLACK).backgroundColor(Colors.WHITE).shadowColor(Colors.RED))
                ;

        AnsiSerializer ts = new AnsiSerializer();
        System.out.println(ts.serialize(component));
    }
}
```
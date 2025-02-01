package bet.astral.jtext.color;

import bet.astral.jtext.color.simple.Color;
import bet.astral.jtext.utils.FutureProof;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Gradient color to animate texts with gradients
 */
public class GradientColor implements ColorLike {
    private Color[] colors;
    private GradientAnimation animation;
    private GradientType type;
    private int lastColorIndex = 0;

    /**
     * Creates a new instance of a gradient
     * @param animation Animation to use when rendering
     * @param type Gradient type
     * @param colors colors
     */
    public GradientColor(GradientAnimation animation, GradientType type, @NotNull Color... colors) {
        this.animation = animation;
        this.type = type;
        this.colors = colors;
    }

    /**
     * Creates a new instance of a gradient
     * @param animation Animation to use when rendering
     * @param type Gradient type
     * @param colors colors
     */
    public GradientColor(GradientAnimation animation, GradientType type, List<Color> colors) {
        this.animation = animation;
        this.colors = colors.toArray(Color[]::new);
        this.type = type;
    }

    /**
     * Creates a new instance of a gradient. Uses an animation {@link GradientAnimation#NONE}
     * @param type Gradient type
     * @param colors colors
     */
    public GradientColor(GradientType type, @NotNull Color... colors) {
        this(GradientAnimation.NONE, type, colors);
    }

    /**
     * Creates a new instance of a gradient. Uses an animation {@link GradientAnimation#NONE}
     * @param type Gradient type
     * @param colors colors
     */
    public GradientColor(GradientType type, List<Color> colors) {
        this(GradientAnimation.NONE, type, colors);
    }
    /**
     * Creates a new instance of a gradient. Uses an animation {@link GradientAnimation#NONE} and gradient type {@link GradientType#USE_GIVEN_COLORS}
     * @param colors colors
     */
    public GradientColor(@NotNull Color... colors) {
        this(GradientAnimation.NONE, GradientType.USE_GIVEN_COLORS, colors);
    }

    /**
     * Creates a new instance of a gradient. Uses an animation {@link GradientAnimation#NONE} and gradient type {@link GradientType#USE_GIVEN_COLORS}
     * @param colors colors
     */
    public GradientColor(List<Color> colors) {
        this(GradientAnimation.NONE, GradientType.USE_GIVEN_COLORS, colors);
    }

    /**
     * Returns the colors used in the gradient
     * @return colors
     */
    public Color[] getColors() {
        return colors;
    }

    /**
     * Returns the animation used in the gradient
     * @return colors
     */
    public GradientAnimation getAnimation() {
        return animation;
    }

    /**
     * Returns the gradient type used in the gradient
     * @return type
     */
    public GradientType getType() {
        return type;
    }

    public Color getNextColor() {
        if (lastColorIndex >= colors.length-1) {
            lastColorIndex = 0;
        }
        return colors[++lastColorIndex];
    }

    /**
     * Gradient type. Default value is {@link #USE_GIVEN_COLORS}
     */
    public enum GradientType implements FutureProof {
        /**
         * Unknown value defaults to NONE
         */
        UNKNOWN(-1),

        /**
         * Uses all the given colors as the gradient. Does not liquidate the colors and try to create water paint like gradient
         */
        USE_GIVEN_COLORS(1),

        /**
         * Uses all the given colors as the gradient. When creating gradient creates a new gradient plan and creates water paint like gradient.
         */
        MATCH_NEXT_COLOR(2),

        ;
        private final int futureProof;

        GradientType(int futureProof) {
            this.futureProof = futureProof;
        }

        @Override
        public int getFutureProof() {
            return futureProof;
        }
    }

    /**
     * Gradient rendering style. Default is {@link #NONE}
     */
    public enum GradientAnimation implements FutureProof {
        /**
         * Unknown value defaults to NONE
         */
        UNKNOWN(-1),

        // NONE -> STATIC
        NONE(0),
        /**
         * Animates the gradient to go from top (of the text) to the bottom (of the text)
         */
        UP_DOWN(1),

        /**
         * Animates the gradient to go from bottom (of the text) to the top (of the text)
         */

        DOWN_UP(2),
        /**
         * Animates the gradient to go from left side to the right side
         */
        LEFT_RIGHT(3),
        /**
         * Animates the gradient to go from right side to the left side
         */
        RIGHT_LEFT(4),

        /**
         * Animates the gradient to go from left top to the right bottom
         */
        LEFT_TOP_RIGHT_BOTTOM(10),
        /**
         * Animates the gradient to go from left bottom to the right top
         */
        LEFT_BOTTOM_RIGHT_TOP(11),
        /**
         * Animates the gradient to go from right top to the left bottom
         */
        RIGHT_TOP_LEFT_BOTTOM(12),
        /**
         * Animates the gradient to go from right bottom to the left top
         */
        RIGHT_BOTTOM_LEFT_TOP(13),

        /**
         * Creates a circling effect when the gradient is animated. Circles the text from top to the left
         */
        CIRCLE_LEFT(18),
        /**
         * Creates a circling effect when the gradient is animated. Circles the text from top to the right
         */
        CIRCLE_RIGHT(19)
        ;

        private final int futureProof;

        GradientAnimation(int futureProof) {
            this.futureProof = futureProof;
        }

        @Override
        public int getFutureProof() {
            return futureProof;
        }
    }
}

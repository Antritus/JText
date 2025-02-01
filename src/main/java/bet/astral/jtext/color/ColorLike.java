package bet.astral.jtext.color;

import bet.astral.jtext.color.simple.Color;
import bet.astral.jtext.utils.FutureProof;

/**
 * Color representation. Use {@link Color}, {@link GradientColor}
 */
public interface ColorLike {
    /**
     * Helper enum for serialization and deserialization
     */
    enum Type implements FutureProof {
        /**
         * Unknown type defaults to {@link Colors#WHITE}
         */
        UNKNOWN(-1),

        /**
         * Single color {@link Color}
         */
        SINGLE(1),
        /**
         * Gradient color {@link GradientColor}
         */
        GRADIENT(2);

        private final int futureProof;

        Type(int futureProof) {
            this.futureProof = futureProof;
        }

        @Override
        public int getFutureProof() {
            return futureProof;
        }
    }
}

package bet.astral.jtext.list;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Immutable list. No value can be removed or added
 * @param <T> type
 */
public interface ImmutableList<T> extends List<T> {
    @Override
    default boolean addAll(@NotNull Collection<? extends T> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }

    @Override
    default boolean addAll(int index, @NotNull Collection<? extends T> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }

    @Override
    default boolean retainAll(@NotNull Collection<?> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }

    @Override
    default boolean removeAll(@NotNull Collection<?> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }

    @Override
    default void replaceAll(UnaryOperator<T> operator) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }

    @Override
    default boolean remove(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }

    @Override
    default boolean add(T t) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }

    @Override
    default void sort(Comparator<? super T> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }

    @Override
    default T set(int index, T element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }

    @Override
    default void add(int index, T element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }

    @Override
    default T remove(int index) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }

    @Override
    default boolean removeIf(Predicate<? super T> filter) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot modify immutable list!");
    }
}

package bet.astral.jtext.list;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ImmutableListImpl<T> implements ImmutableList<T>{
    private final List<T> list;
    private final boolean fastenContains;
    /**
     * {@inheritDoc}
     */
    public ImmutableListImpl(List<T> list) {
        this(list, true);
    }

    /**
     * Creates a new instance of an immutable list.
     * @param list list to make immutable
     * @param fastenContains true if uses set as the contains check, else false; uses the given lists contains check
     */
    public ImmutableListImpl(List<T> list, boolean fastenContains) {
        this.list = list;
        this.fastenContains = fastenContains;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull Iterator<T> iterator() {
        return list.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull Object[] toArray() {
        return list.toArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull <T1> T1[] toArray(@NotNull T1[] a) {
        return list.toArray(a);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        if (fastenContains) {
            return new HashSet<>(list).containsAll(c);
        } else {
            //noinspection SlowListContainsAll
            return list.containsAll(c);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int index) {
        return list.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull ListIterator<T> listIterator() {
        return list.listIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull List<T> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }


}

package bet.astral.jtext.list;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ImmutableListImpl<T> implements ImmutableList<T>{
    private final List<T> list;
    private final boolean fastenContains;
    public ImmutableListImpl(List<T> list) {
        this(list, true);
    }

    public ImmutableListImpl(List<T> list, boolean fastenContains) {
        this.list = list;
        this.fastenContains = fastenContains;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public @NotNull Object[] toArray() {
        return list.toArray();
    }

    @Override
    public @NotNull <T1> T1[] toArray(@NotNull T1[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        if (fastenContains) {
            return new HashSet<>(list).containsAll(c);
        } else {
            //noinspection SlowListContainsAll
            return list.containsAll(c);
        }
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public @NotNull ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @Override
    public @NotNull ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public @NotNull List<T> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }


}

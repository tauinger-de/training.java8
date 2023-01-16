package utils;

import java.util.function.Function;

class MapNode<P, T> extends Node<P, T> {
    final Function<? super P, ? extends T> mapper;

    public MapNode(Node<?, P> previous, Function<? super P, ? extends T> mapper) {
        super(previous);
        this.mapper = mapper;
    }

    @Override
    protected T get() {
        P elem = this.previous.get();
        return elem == null ? null : this.mapper.apply(elem);
    }
}

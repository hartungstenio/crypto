package org.bitbucket.votonovo.crypto.encoding;

import java.util.Iterator;

/**
 * Iterate transforming values into another.
 * 
 * <p>This should someday get into the main package</p>
 * 
 * @author Christian Hartung <hartung@live.com>
 *
 * @param <S> Source type
 * @param <T> Target type
 */
public class Transformed<S,T> implements Iterable<T> {
    
    private final Mapping<S,T> mapping;
    private final Iterable<S> source;
    
    /**
     * Maps a value to another value
     * @author Christian Hartung <hartung@live.com>
     *
     * @param <S> Source type
     * @param <T> Target type
     */
    public static interface Mapping<S,T> {
        T map(S input);
    }
    
    /**
     * Ctor.
     * 
     * @param mapping How to map the values
     * @param source The source sample
     */
    public Transformed(Mapping<S, T> mapping, Iterable<S> source) {
        this.mapping = mapping;
        this.source = source;
    }

    public Iterator<T> iterator() {
        return new TransformingIterator<S, T>(this.mapping, this.source.iterator());
    }
    
    private static final class TransformingIterator<S,T> implements Iterator<T> {
        
        private final Mapping<S,T> mapping;
        private final Iterator<S> internal;
        
        public TransformingIterator(final Mapping<S,T> mapping, final Iterator<S> iterator) {
            this.mapping = mapping;
            this.internal = iterator;
        }

        public boolean hasNext() {
            return this.internal.hasNext();
        }

        public T next() {
            return mapping.map(this.internal.next());
        }
        
    }
}

package fr.progz.plumlib.container;
/**
 * 
 * @param <T> 
 */
public class Pair<T> {

    private T _x;
    private T _y;
    public Pair(T x, T y) { _x=x;_y=y; }
    /** True : [0]; False : [1] */
    public T get(boolean i) {return (i) ? _x : _y;}
}
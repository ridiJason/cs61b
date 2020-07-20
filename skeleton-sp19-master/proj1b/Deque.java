public interface Deque<T> {
    int getSize();
    void addFirst(T item);
    void addLast(T item);
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
    default boolean isEmpty(){
        return getSize() == 0;
    }
}

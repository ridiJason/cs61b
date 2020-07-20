import java.util.Objects;

public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private T items[];
    private int nextfirst;
    private int nextlast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextfirst = 0;
        nextlast = 1;
    }

    //when the pointer move right
    private int plusOne(int index){
        return (index + 1) % items.length;
    }

    //when the pointer move left
    //+item.length to avoid index = -1
    private int minusOne(int index){
        return (index - 1 + items.length) % items.length;
    }


    public boolean isEmpty(){
        return size == 0;
    }

    private boolean isFull(){
        return size == items.length;
    }

    private boolean isSparese(){
        return items.length >= 16 && size < (items.length / 4);
    }

    public int getSize(){
        return size;
    }

    private void resize(int capacity){
        T[] newItems = (T[]) new Object[capacity];
        int oldIndex = plusOne(nextfirst); //move to the box which has the first value
        for(int temp = 0; temp <size; temp++){
            newItems[temp] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = newItems;
        nextfirst = capacity - 1;
        nextlast = size;
    }

    public void addFirst(T item){
        if(isFull()){
            resize(2 * size);
        }
        items[nextfirst] = item;
        nextfirst = minusOne(nextfirst);
        size += 1;
    }

    public void addLast(T item){
        if(isFull()){
            resize(2 * size);
        }
        items[nextlast] = item;
        nextlast = plusOne(nextlast);
        size += 1;
    }


    public T removeFirst(){
        if(isSparese()){
            resize(size / 2);

        }
        nextfirst = plusOne(nextfirst);
        T removeOne = items[nextfirst];
        items[nextfirst] = null;
        if(!isEmpty()){
            size -= 1;
        }
        return removeOne;
    }

    public T removeLast(){
        if(isSparese()){
            resize(size / 2);
        }
        nextlast = minusOne(nextlast);
        T removeOne = items[nextlast];
        items[nextlast] = null;
        if(!isEmpty()){
            size -= 1;
        }
        return removeOne;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }
        return items[(index + plusOne(nextfirst)) % items.length];
    }

    public void printDeque(){
        int temp = plusOne(nextfirst);
        while(temp != nextlast){
            System.out.print(items[temp] + " ");
            temp = plusOne(temp);
        }
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[])new Object[other.items.length];
        this.nextfirst = other.nextfirst;
        this.nextlast = other.nextlast;
        size = other.size;
        System.arraycopy(other.items,0,this.items,0,other.size);
    }




}
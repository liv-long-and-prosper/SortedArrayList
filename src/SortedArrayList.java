import java.util.Iterator;

/**
 * SortedArrayList class to maintain an ArrayList data structure with data sorted
 *
 * @param <E> the type parameter
 */
public class SortedArrayList<E extends Comparable<E>> implements SortedArrayListInterface<E>, Comparable<E>, Iterable<E>{
    private final ArrayList<E> data;

    /**
     * Instantiates a new Sorted array list with a default capacity of 100
     */
    public SortedArrayList(){
        data = new ArrayList<>();
    }

    /**
     * Instantiates a new Sorted array list with a desired capacity
     *
     * @param capacity the desired capacity
     */
    public SortedArrayList(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException("capacity must not be negative");
        }
        data = new ArrayList<>(capacity);
    }

    /**
     * Retrieves the number of elements being maintained by the list
     * @return      the number of elements being maintained
     */
    @Override
    public int size() {
        return data.size();
    }

    /**
     * Retrieves whether the list is empty
     *
     * @return true, if there are no elements in the list; false, if there are elements
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Clears the list; no elements will remain after the call, and size will be 0
     */
    @Override
    public void clear() {
      data.clear();
    }

    /**
     * Retrieves whether the specified element is in the list
     *
     * @param value the value to search for
     * @return true, if the element is in the list; false, if not
     */
    @Override
    public boolean contains(E value) {
        if(value == null){
            throw new IllegalArgumentException("value must not be null");
        }
       return data.contains(value);
    }

    /**
     * Uses a binary search to find the index of the first occurrence of the specified value,
     * or, if not found, the place that value should be
     *
     * @param value the value to search for
     * @return if found, the index of the value in the list (range 0 to size - 1);
     * if not found, an index representing where the value would go, if added, returned
     * as Math.abs(-(position+1)), e.g., 1 means it goes at index 0, 5 means it goes at index 4
     */
    @Override
    public int indexOf(E value) {
        if(value == null){
            throw new IllegalArgumentException("value must not be null");
        }

        int min = 0;
        int max = data.size() - 1;

        while (min <= max) {
           int mid = (max + min) / 2;
            if (data.get(mid).equals(value)) {
                while(data.get(--mid).equals(value)){
                    mid--;
                }
                return mid;     // found it!
            } else if (data.get(mid).compareTo(value) < 0) {
                min = mid + 1;  // too small
            } else {   // data.get(mid).compareTo(value) > 0
                max = mid - 1;  // too large
            }
        }
        return Math.abs(-(min - 1));   // not found
    }

    /**
     * Retrieves the element at the specified position in the list
     *
     * @param index the index (position) in the list; must be 0 to size-1
     * @return the element at the specified position
     */
    @Override
    public E get(int index) {
        if(index < 0 || index >= data.size()){
            throw new IndexOutOfBoundsException("index must not be negative or greater than ");
        }
        return data.get(index);
    }

    /**
     * Retrieves an array of elements that are compare themselves equally to the specified value (via compareTo),
     * with results being stored in the array specified.
     *
     * @param value    the element being sought; will be used to compareTo() other elements
     * @param template a template array used to create results; pass in a 0-sized array
     * @return a new array that is right-sized and contains element references, if any
     */
    @Override
    public E[] get(E value, E[] template) {
        if (value == null){
            throw new IllegalArgumentException("value must not be null");
        }
        if(template.length > 0){
            throw new IllegalArgumentException("template must be 0-sized");
        }
        ArrayList<E> newArrayList = new ArrayList<>();
        for (E element : data) {
            if (element.equals(value)) {
                newArrayList.add(element);
            }
        }
        return newArrayList.toArray(template);
    }

    /**
     * Adds a new element to the list, maintaining sorting via natural order (via compareTo)
     *
     * @param value the value to add to the list
     */
    @Override
    public void add(E value) {
        if (value == null){
            throw new IllegalArgumentException("value must not be null");
        }
       if(data.isEmpty()){
           data.add(value);
       } else {
           data.add(indexOf(value)+1,value);
       }
    }

    /**
     * Removes from the list the element at the specified index
     *
     * @param index the index in the list; must be in range  0 to size-1
     */
    @Override
    public void remove(int index) {
        data.remove(index);
    }

    /**
     * Retrieves an iterator over list elements; for/each loops are also supported
     *
     * @return a strongly typed iterator over list elements
     */
    @Override
    public Iterator<E> iterator() {
        return new SortedArrayListIterator();
    }

    private class SortedArrayListIterator implements Iterator<E>{

        /**
         * Instantiates a new Sorted array list iterator.
         */
        public SortedArrayListIterator(){
            data.iterator();
        }

        @Override
        public boolean hasNext(){
           return data.iterator().hasNext();
        }

        @Override
        public E next() {
            return data.iterator().next();
        }
    }
    /**
     * Retrieves an array representing the contents of the list
     *
     * @param array an array of the exact type to be returned, e.g., if E is String,
     *              the caller can pass in as an argument: new String[0]
     * @return an array of the elements in the sorted array list.  Follows
     * the return requirements of Java's ArrayList's toArray method;
     * see Java's API reference for those details
     */
    @Override
    public E[] toArray(E[] array) {
        return data.toArray(array);
    }

    @Override
    public int compareTo(E target) {
        return 0;
    }

    @Override
    public String toString(){
        return data.toString();
    }

}

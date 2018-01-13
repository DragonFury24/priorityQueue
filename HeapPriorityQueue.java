//Rev Dr. D. R. Oberle  Feb 2015


import java.util.*;

public class HeapPriorityQueue implements PriorityQueue {
    private static final int DFLT_CAPACITY = 1024;
    private Comparable[] items;        //index 0 will go unused
    private int numItems;

    public HeapPriorityQueue(int initialCapacity) {
        items = new Comparable[initialCapacity + 1];
        numItems = 0;
    }

    public HeapPriorityQueue() {
        this(DFLT_CAPACITY);
    }

    public boolean isEmpty() {
        return (numItems == 0);
    }

    public Comparable peek() {
        if (numItems == 0) {
            throw new NoSuchElementException();
        }

        return items[1];
    }

    private void reheapDown() {
        int i = 1;
        while (i * 2 <= numItems) {
            if (i * 2 + 1 <= numItems && itemExists(items[i * 2 + 1])) {
                if (!lowerPriority(items[i * 2 + 1], items[i * 2])) {
                    if (lowerPriority(items[i], items[i * 2 + 1])) {
                        swap(items, i, i * 2 + 1);
                        i = i * 2 + 1;
                    }
                } else if (lowerPriority(items[i], items[i * 2])) {
                    swap(items, i, i * 2);
                    i = i * 2;
                }
            } else if (lowerPriority(items[i], items[i * 2])) {
                swap(items, i, i * 2);
                i = i * 2;
            } else break;
        }
        //        for (int i = 1; i < numItems; ) {
        //            if (has2Child(items, i) && i < items.length && i * 2 <= numItems && i * 2 + 1 <= numItems) {
        //                if (!lowerPriority(items[i * 2 + 1], items[i * 2])) {
        //                    if (lowerPriority(items[i], items[i * 2 + 1])) {
        //                        swap(items, i, i * 2 + 1);
        //                        i = i * 2 + 1;
        //                    }
        //                } else if (!lowerPriority(items[i * 2], items[i * 2 + 1])) {
        //                    if (lowerPriority(items[i], items[i * 2])) {
        //                        swap(items, i, i * 2);
        //                        i = i * 2;
        //                    }
        //                }
        //            } else if (hasChild(items[i * 2]) && i < items.length && i * 2 <= numItems) {
        //                if (lowerPriority(items[i], items[i * 2])) {
        //                    swap(items, i, i * 2);
        //                    i = i * 2;
        //                } else
        //                    break;
        //            } else
        //                break;
        //        }
        //OMG - YOU HAVE TO WRITE THIS!
    }

    public Comparable remove() {
        if (numItems == 0) {
            throw new NoSuchElementException();
        }

        Comparable min = items[1];
        items[1] = items[numItems];
        numItems--;
        reheapDown();
        return min;
    }

    private static void swap(Comparable[] list, int a, int b) {
        Comparable temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }

    private void reheapUp() {
        for (int i = numItems; i > 1; i /= 2) {
            if (lowerPriority(items[i / 2], items[i])) {
                swap(items, i / 2, i);
            }
        }
        //HOLY CRAP - YOU HAVE TO WRITE THIS ONE TOO!

    }

    public boolean add(Comparable obj) {
        numItems++;
        if (numItems >= items.length)
            doubleCapacity();
        items[numItems] = obj;
        reheapUp();
        return true;
    }

    private static boolean lowerPriority(Comparable obj1, Comparable obj2) {    //we will consider that low value == high priority
        return (obj1.compareTo(obj2) > 0);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 1; i <= numItems; i++) {
            stringBuilder.append(items[i]);
            if (i <= numItems - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void doubleCapacity() {
        Comparable tempItems[] = new Comparable[2 * items.length - 1];
        for (int i = 0; i <= numItems; i++)
            tempItems[i] = items[i];
        items = tempItems;
    }

    private boolean hasChild(Comparable obj) {
        return obj != null && !obj.equals("0");
    }

    private boolean has2Child(Comparable[] obj, int index) {
        return hasChild(obj[index * 2]) && hasChild(obj[index * 2 + 1]);
    }

    private boolean itemExists(Comparable obj) {
        return obj != null && !obj.equals(0);
    }
}


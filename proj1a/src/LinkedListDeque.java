import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    private IntNode sentinel;
    private int size;

    public class IntNode {
        public IntNode prev;
        public T   item;
        public IntNode next;
    }


    public LinkedListDeque() {
        IntNode first = new IntNode();
        first.next = first;
        first.prev = first;
        size =0;
        sentinel = first;
    }

    @Override
    public void addFirst(T x) {
        //create a new node
        IntNode node = new IntNode();
        node.item = x;
        // update the new node: prev and next
        IntNode leader = sentinel;
        IntNode first = leader.next;
        // update the node
        node.prev = leader;
        node.next = first;
        // update the previous first node
        first.prev = node;
        // update leader node
        leader.next = node;

        size++;
    }

    public void addFirstOld(T x) {
        //create a new node
        IntNode node = new IntNode();
        node.item = x;
        // update the new node: prev and next
        IntNode first = sentinel;
        node.prev = first;
        node.next = first.next;
        // update the previous first node
        first.next.prev = node;
        // update first node
        first.next = node;
        // check if it is the first element to be added
        if (first.prev == first)
            first.prev = node;
        size++;
    }

    @Override
    public void addLast(T x) {
        //create a new node
        IntNode node = new IntNode();
        node.item = x;
        // update the new node: prev and next
        IntNode leader = sentinel;
        IntNode last = leader.prev;

        node.prev = last;
        node.next = leader;
        // update the previous last node
        last.next = node;
        // update first node
        leader.prev = node;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        IntNode leader = sentinel;
        IntNode first = sentinel.next;
        if (isEmpty())
            return null;
        for (IntNode current=first; current != leader; current = current.next) {
            T item = current.item;
            returnList.addLast(item);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        /* check if the queue is empty, out of bound or negative. if yes, return null */
        if (isEmpty() || (index >= size) || (index < 0))
            return null;
        else {
            IntNode curNode = sentinel.next;
            for (int i=0; i < index; i++)
                curNode = curNode.next;
            return curNode.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(3);
        lld.addFirst(1);
        lld.addLast(5);
        lld.addLast(7);
        lld.toList();
    }

}

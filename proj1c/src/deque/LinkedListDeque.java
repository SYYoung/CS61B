package deque;//import deque.Deque;
import deque.Deque;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    private IntNode sentinel;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return null;
    }

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
        if (isEmpty())
            return null;
        IntNode leader, first, newFirst;
        leader = sentinel;
        first = leader.next;
        newFirst = first.next;
        // update newFirst and leader
        newFirst.prev = leader;
        leader.next = newFirst;
        // detach first from queue
        first.next = null;
        first.prev = null;

        size--;

        return first.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty())
            return null;
        IntNode leader, last, newLast;
        leader = sentinel;
        last = leader.prev;
        newLast = last.prev;
        // update newLast and leader
        newLast.next = leader;
        leader.prev = newLast;
        // detach first from queue
        last.next = null;
        last.prev = null;

        size--;

        return last.item;
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

    public T getRecursive(IntNode curNode, int index) {
        /* base case : index = 0 */
        if (index == 0)
            return curNode.item;
        else
            return getRecursive(curNode.next, index - 1);
    }

    @Override
    public T getRecursive(int index) {
        /* check if the queue is empty, out of bound or negative. if yes, return null */
        if (isEmpty() || (index >= size) || (index < 0))
            return null;
        else {
            IntNode curNode = sentinel.next;
            return getRecursive(curNode, index);
        }

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

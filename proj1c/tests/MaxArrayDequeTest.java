import deque.MaxArrayDeque;
import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;
import items.Dog;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {
    @Test
    public void buildMaxArrayDequeTest() {
        Comparator<Dog> dogCompare = Dog.getSizeComparator();
        MaxArrayDeque<Dog> dq= new MaxArrayDeque<>(dogCompare);

        Dog d1 = new Dog("Elyse", 9);
        Dog d2 = new Dog("Sture", 3);
        Dog d3 = new Dog("Artemesios", 15);
        dq.addLast(d1);
        dq.addLast(d2);
        dq.addLast(d3);
    }

    @Test
    public void maxWithDefaultTest() {
        Comparator<Dog> dogCompare = Dog.getSizeComparator();
        MaxArrayDeque<Dog> dq= new MaxArrayDeque<>(dogCompare);

        Dog d1 = new Dog("Elyse", 9);
        Dog d2 = new Dog("Sture", 3);
        Dog d3 = new Dog("Artemesios", 15);
        dq.addLast(d1);
        dq.addLast(d2);
        dq.addLast(d3);

        Dog whichDog = dq.max();
        System.out.println(whichDog);
    }

    @Test
    public void maxWithComparatorTest() {
        Comparator<Dog> dogCompare = Dog.getNameComparator();
        MaxArrayDeque<Dog> dq= new MaxArrayDeque<>(dogCompare);

        Dog d1 = new Dog("Elyse", 9);
        Dog d2 = new Dog("Sture", 3);
        Dog d3 = new Dog("Artemesios", 15);
        dq.addLast(d1);
        dq.addLast(d2);
        dq.addLast(d3);

        Dog whichDog = dq.max();
        System.out.println(whichDog);
    }

}

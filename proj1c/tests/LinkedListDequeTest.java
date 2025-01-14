import deque.Deque;
import deque.LinkedListDeque;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
/*
     @Test
     @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
     void noNonTrivialFields() {
         Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
         List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
     }
*/
     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

     //Below, you'll write your own tests for LinkedListDeque.
    @Test
    /* This test performs isEmpty. */
    public void isEmptyTestTrue() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

        /* 1. test when there is no element in the queue, isEmpty should return true. otherwise, return false */
        assertWithMessage("expected to return true").that(lld1.isEmpty()).isTrue();

        /* when there is one element in the queue, isEmpty() should return false */
        lld1.addLast(5);
        assertWithMessage("Expected to return false").that(lld1.isEmpty()).isFalse();
    }

    @Test
    /* This test performs isEmpty. */
    public void testSizeAndIsEmpty() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

        /* 1. test when there is no element in the queue, isEmpty should return true.
        and size should be 0.*/
        assertWithMessage("expected to return true").that(lld1.isEmpty()).isTrue();
        assertWithMessage("Expected size is 0").that(lld1.size()).isEqualTo(0);

        /* 2. when there is one element in the queue, isEmpty() should return false */
        lld1.addLast(5);
        assertWithMessage("Expected to return false").that(lld1.isEmpty()).isFalse();
        assertWithMessage("Expected size is 1").that(lld1.size()).isEqualTo(1);

        lld1.addFirst(3);
        assertWithMessage("Expected to return false").that(lld1.isEmpty()).isFalse();
        assertWithMessage("Expected size is 2").that(lld1.size()).isEqualTo(2);
    }

    @Test
    /* This test performs isEmpty. */
    public void getTest() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

        /* 1. test if the queue is empty. it should return null for any index */
        assertWithMessage("expected to return null since the queue is empty").that(lld1.get(1)).isNull();
        assertWithMessage("expected to return null since the queue is empty").that(lld1.get(0)).isNull();

        /* when the queue is not empty but the index is out of bound. it should return null */
        lld1.addLast(5);
        assertWithMessage("expected to return null since the index is out of bound").that(lld1.get(1)).isNull();
        /* the index is correct. it should return 5 */
        assertWithMessage("expected to return element 5").that(lld1.get(0)).isEqualTo(5);
        /* the queue is not empty. however, the index is negative. should return null */
        assertWithMessage("expected to return null since the index is negative").that(lld1.get(-1)).isNull();
        /* add more elements, test out of bound and normal case */
        lld1.addFirst(1);
        lld1.addLast(7);
        /* test if the index 0 = 1, index 1 = 5, index 2 = 7 */
        assertWithMessage("expected: index 0 = 1").that(lld1.get(0)).isEqualTo(1);
        assertWithMessage("expected: index 1 = 5").that(lld1.get(1)).isEqualTo(5);
        assertWithMessage("expected: index 2 = 7").that(lld1.get(2)).isEqualTo(7);
    }

    @Test
    /* This test performs isEmpty. */
    public void getRecursiveTest() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

        /* 1. test if the queue is empty. it should return null for any index */
        assertWithMessage("expected to return null since the queue is empty").that(lld1.getRecursive(1)).isNull();
        assertWithMessage("expected to return null since the queue is empty").that(lld1.getRecursive(0)).isNull();

        /* when the queue is not empty but the index is out of bound. it should return null */
        lld1.addLast(5);
        assertWithMessage("expected to return null since the index is out of bound").that(lld1.getRecursive(1)).isNull();
        /* the index is correct. it should return 5 */
        assertWithMessage("expected to return element 5").that(lld1.getRecursive(0)).isEqualTo(5);
        /* the queue is not empty. however, the index is negative. should return null */
        assertWithMessage("expected to return null since the index is negative").that(lld1.getRecursive(-1)).isNull();
        /* add more elements, test out of bound and normal case */
        lld1.addFirst(1);
        lld1.addLast(7);
        /* test if the index 0 = 1, index 1 = 5, index 2 = 7 */
        assertWithMessage("expected: index 0 = 1").that(lld1.getRecursive(0)).isEqualTo(1);
        assertWithMessage("expected: index 1 = 5").that(lld1.getRecursive(1)).isEqualTo(5);
        assertWithMessage("expected: index 2 = 7").that(lld1.getRecursive(2)).isEqualTo(7);
    }

    @Test
    /* This test performs isEmpty. */
    public void removeFirstTest() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

        /* 1. test if the queue is empty. it should return null for any index */
        assertWithMessage("expected to return null since the queue is empty").that(lld1.removeFirst()).isNull();

        /* when the queue has only one element. it will return the first. if remove first again, it should
        return null */
        lld1.addLast(5);
        assertWithMessage("expected to return element: 5").that(lld1.removeFirst()).isEqualTo(5);
        assertWithMessage("the queue is empty now").that(lld1.removeFirst()).isNull();

        /* add more elements, test out of bound and normal case */
        lld1.addFirst(1);
        lld1.addLast(5);
        lld1.addLast(7);
        /* test if the index 0 = 1, index 1 = 5, index 2 = 7 */
        assertWithMessage("expected: remove first element = 1").that(lld1.removeFirst()).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly(5, 7).inOrder();
        assertWithMessage("expected: remove again first element = 5").that(lld1.removeFirst()).isEqualTo(5);
        assertThat(lld1.toList()).containsExactly(7).inOrder();
        assertWithMessage("expected: remove again first element = 7").that(lld1.removeFirst()).isEqualTo(7);
        /* now the queue is empty. if removeFirst again, it should return null */
        assertWithMessage("the queue is empty now").that(lld1.removeFirst()).isNull();
    }

    @Test
    /* This test performs isEmpty. */
    public void removeLastTest() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

        /* 1. test if the queue is empty. it should return null for any index */
        assertWithMessage("expected to return null since the queue is empty").that(lld1.removeLast()).isNull();

        /* when the queue has only one element. it will return the first. if remove first again, it should
        return null */
        lld1.addLast(5);
        assertWithMessage("expected to return element: 5").that(lld1.removeLast()).isEqualTo(5);
        assertWithMessage("the queue is empty now").that(lld1.removeFirst()).isNull();

        /* add more elements, test out of bound and normal case */
        lld1.addFirst(1);
        lld1.addLast(5);
        lld1.addLast(7);
        /* test if the index 0 = 1, index 1 = 5, index 2 = 7 */
        assertWithMessage("expected: remove last element = 7").that(lld1.removeLast()).isEqualTo(7);
        assertThat(lld1.toList()).containsExactly(1, 5).inOrder();
        assertWithMessage("expected: remove again last element = 5").that(lld1.removeLast()).isEqualTo(5);
        assertThat(lld1.toList()).containsExactly(1).inOrder();
        assertWithMessage("expected: remove again last element = 1").that(lld1.removeLast()).isEqualTo(1);
        /* now the queue is empty. if removeFirst again, it should return null */
        assertWithMessage("the queue is empty now").that(lld1.removeFirst()).isNull();
    }

    /* test the iterator */
    @Test
    public void iteratorTest() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        for (String s : lld1) {
            System.out.println(s);
        }

        /* test containExactly */
        assertThat(lld1).containsExactly("front","middle","back");
    }
}
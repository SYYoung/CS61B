import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    public void addFirstTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addLast("a");
        ad.addLast("b");
        ad.addFirst("c");
        ad.addLast("d");
        ad.addLast("e");
        ad.addFirst("f");
        ad.addLast("g");
        ad.addLast("h");
        /* this will cause resize */
        ad.addLast("Z");
        ad.addLast("w");
        ad.addLast("x");
        ad.addLast("y");

        ad.addFirst("m");
        ad.addFirst("n");
        ad.addFirst("o");
        ad.addFirst("p");

        /* should resize again */
        ad.addFirst("r");
        assertWithMessage("expected: size = 17").that(ad.size()).isEqualTo(17);
    }

    @Test
    public void toListTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addLast("a");
        ad.addLast("b");
        ad.addFirst("c");
        ad.addLast("d");
        /* test the list */
        assertThat(ad.toList()).containsExactly("c","a","b","d").inOrder();

        ad.addLast("e");
        ad.addFirst("f");
        ad.addLast("g");
        ad.addLast("h");
        /* test the list */
        assertThat(ad.toList()).containsExactly("f","c","a","b","d","e","g","h").inOrder();

        /* this will cause resize */
        ad.addLast("Z");
        assertThat(ad.toList()).containsExactly("f","c","a","b","d","e","g","h","Z").inOrder();

        ad.addLast("w");
        ad.addLast("x");
        ad.addLast("y");

        ad.addFirst("m");
        ad.addFirst("n");
        ad.addFirst("o");
        ad.addFirst("p");
        /* test again */
        assertThat(ad.toList()).containsExactly("p","o","n","m","f","c","a","b","d","e","g","h","Z","w","x","y").inOrder();

        /* should resize again */
        ad.addFirst("r");
        assertThat(ad.toList()).containsExactly("r","p","o","n","m","f","c","a","b","d","e","g","h","Z","w","x","y").inOrder();
    }

    @Test
    public void isEmptyAndSizeTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        /* test the queue is empty */
        assertWithMessage("expected: empty list").that(ad.isEmpty()).isTrue();
        assertWithMessage("expected: size is 0").that(ad.size()).isEqualTo(0);

        ad.addLast("a");
        ad.addLast("b");
        ad.addFirst("c");
        ad.addLast("d");
        ad.addLast("e");
        ad.addFirst("f");
        ad.addLast("g");
        ad.addLast("h");
        /* test the list */
        assertWithMessage("expected: not empty list").that(ad.isEmpty()).isFalse();
        assertWithMessage("expected: size is 8").that(ad.size()).isEqualTo(8);

        /* this will cause resize */
        ad.addLast("Z");
        assertWithMessage("expected: not empty list").that(ad.isEmpty()).isFalse();
        assertWithMessage("expected: size is 9").that(ad.size()).isEqualTo(9);

        ad.addLast("w");
        ad.addLast("x");
        ad.addLast("y");
        ad.addFirst("m");
        ad.addFirst("n");
        ad.addFirst("o");
        ad.addFirst("p");
        /* test again */
        assertWithMessage("expected: not empty list").that(ad.isEmpty()).isFalse();
        assertWithMessage("expected: size is 16").that(ad.size()).isEqualTo(16);

        /* should resize again */
        ad.addFirst("r");
        assertWithMessage("expected:not empty list").that(ad.isEmpty()).isFalse();
        assertWithMessage("expected: size is 17").that(ad.size()).isEqualTo(17);
    }

    @Test
    public void sizeTest() {

    }
}

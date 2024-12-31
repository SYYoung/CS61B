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
}

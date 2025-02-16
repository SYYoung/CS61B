import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ColoredNumber {
    private int num;
    private int color;

    public ColoredNumber(int number, int col) {
        num = number;
        color = col;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof  ColoredNumber otherCn) {
            return this.num == otherCn.num;
        }
        return false;
    }

    public static void main(String[] args) {
        int N = 20;
        HashSet<ColoredNumber> hs = new HashSet<>();
        for (int i = 0; i < N; i+= 1) {
            hs.add(new ColoredNumber(i, i+1));
        }

        ColoredNumber twelve = new ColoredNumber(12, 13);
        boolean isThere = hs.contains(twelve);

        List<Integer> items = new ArrayList<>();
        items.add(0);
        items.add(1);
        System.out.println(items.hashCode());
        HashSet<List<Integer>> hs1 = new HashSet<>();
        hs1.add(items);
        hs1.add(List.of(2,3));
        items.add(7);
        isThere = hs1.contains(items);
    }
}

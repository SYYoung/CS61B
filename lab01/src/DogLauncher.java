import java.util.Comparator;

public class DogLauncher {

    public static void main(String[] args) {
        Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);
        Dog d3 = new Dog("Artemesios", 15);
        Dog[] dogs = new Dog[]{d1, d2, d3};
        System.out.println(Maximizer.max(dogs));
        Dog d = (Dog) Maximizer.max(dogs);
        d.bark();

        Comparator<Dog> nc = Dog.getNameComparator();
        if (nc.compare(d1, d3) > 0)
            d1.bark();
        else
            d3.bark();

        Comparator<Dog> sc = Dog.getSizeComparator();
        if (sc.compare(d1, d2) > 0)
            d1.bark();
        else
            d2.bark();
    }
}

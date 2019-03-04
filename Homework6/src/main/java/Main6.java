public class Main6 {

    public static void main(String[] args) {
        Tree<Integer> tree = new TreeImpl<>();
        tree.add(60);
        tree.add(25);
        tree.add(66);
        tree.add(15);
        tree.add(5);
        tree.add(20);
        tree.add(45);
        tree.add(30);
        tree.add(32);
        tree.add(55);

        tree.display();

        System.out.println("Find 60: " + tree.find(60));
        System.out.println("Find 45: " + tree.find(45));
        System.out.println("Find 32: " + tree.find(32));
        System.out.println("Find 666: " + tree.find(666));

        tree.remove(15);
        tree.display();

    }
}
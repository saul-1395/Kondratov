import java.util.ArrayList;
import java.util.Random;

public class Main6 {

    public static void main(String[] args) {
        Tree<Integer> tree = new TreeImpl<>();


        ArrayList<TreeImpl<Integer>> treeList = new ArrayList<>();

        addTreeCycle(treeList, 20, 15);


      /*  tree.add(60);
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
*/

    }

    protected static  void addTreeCycle (ArrayList<TreeImpl<Integer>> list, int count, int treeElements){
        Random random = new Random();
        random.nextInt(200);

        for (int i = 0; i < count ; i++) {
            list.add(new TreeImpl<Integer>());
            for (int j = 0; j <  treeElements; j++) {
                list.get(i).add(random.nextInt(200)-100);
            }
            list.get(i).display();
        }
    }
}
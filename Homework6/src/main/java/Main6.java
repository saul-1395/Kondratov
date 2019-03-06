import java.util.ArrayList;
import java.util.Random;

public class Main6 {

    private static Tree.TraverseMode IN_ORDER ;

    public static void main(String[] args) {
        Tree<Integer> tree = new TreeImpl<>();


        ArrayList<TreeImpl<Integer>> treeList = new ArrayList<>();

       addTreeCycle(treeList, 20, 30);


       //вот тут взял из листа одно дерево, и ничего не произошло
        for (int i = 0; i < treeList.size(); i++) {
            System.out.println(TreeImpl.isBalanced(( treeList.get(1)).getRootNode()) );
        }





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


        TreeImpl.isBalanced(((TreeImpl<Integer>) tree).getRootNode());


    }

    protected static  void addTreeCycle (ArrayList<TreeImpl<Integer>> list, int count, int treeElements){
        Random random = new Random();


        for (int i = 0; i < count ; i++) {
            list.add(new TreeImpl<Integer>());
            for (int j = 0; j <  treeElements; j++) {
                list.get(i).add(random.nextInt(40)-20);
            }
            list.get(i).display();
        }
    }

   /* public static float statistic (ArrayList<TreeImpl<Integer>> treeList){
        int countBalance = 0;
        int countUnbalance = 0;
        for (int i = 0; i < treeList.size() ; i++) {
            if (treeList.get(i).isBalanced()){
                //System.out.println("balanced");
                countBalance++;
            }
                countUnbalance++;
        }
        System.out.println(countBalance);
        System.out.println(countUnbalance);
        return countBalance/(countBalance+countUnbalance);
    }*/
}
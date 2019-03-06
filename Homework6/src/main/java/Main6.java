import java.util.ArrayList;
import java.util.Random;

public class Main6 {



    public static void main(String[] args) {
        Tree<Integer> tree = new TreeImpl<>();


        ArrayList<TreeImpl<Integer>> treeList = new ArrayList<>();

       addTreeCycle(treeList, 20, 30);

        System.out.println(statistic(treeList));
       // System.out.println(averageStatistics() + " средняя статистика за 100 итераций");


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

    public static float statistic (ArrayList<TreeImpl<Integer>> treeList){
        float countBalance = 0;
        float countUnbalance = 0;
        for (int i = 0; i < treeList.size(); i++) {
            if(TreeImpl.isBalanced(( treeList.get(i)).getRootNode())){
                countBalance++;
            } else {
            countUnbalance++;
            }
        }

        System.out.println(countBalance);
        System.out.println(countUnbalance);
        float result = countBalance/(countBalance+countUnbalance);
        System.out.println(result);
        return result;
    }
/*
    public static float averageStatistics () {
        int count = 100;
        float average =0f;
        ArrayList<TreeImpl<Integer>> treeList = new ArrayList<>();
        for (int i = 0; i <= count; i++) {
            addTreeCycle(treeList, 20, 15);
            float temp = statistic(treeList) ;
            average += temp;
            System.out.println("статистика " + i + " итерации " +  temp);
        }
        float result = average/100;
        return result;

    }*/
}
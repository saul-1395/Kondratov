//оговорюсь сразу, когда скачал исходники не сразу понял, что засвечено решение дз, шло тяжело, так что всё таки подглядел.
//сначала хотел решить через dfs, запись прохода в лист и сравнение длины, получилось, НО для нахождения самого длинного пути.

import java.util.Stack;


public class Solution {

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Тамбов");
        graph.addVertex("Липецк");
        graph.addVertex("Орел");
        graph.addVertex("Саратов");
        graph.addVertex("Курск");
        graph.addVertex("Воронеж");

        graph.addEdge("Москва","Тула");
        graph.addEdge("Москва","Калуга");
        graph.addEdge("Москва","Рязань");
        graph.addEdge("Тула","Липецк");
        graph.addEdge("Рязань","Тамбов");
        graph.addEdge("Калуга","Орел");
        graph.addEdge("Липецк","Воронеж");
        graph.addEdge("Тамбов","Саратов");
        graph.addEdge("Орел","Курск");
        graph.addEdge("Саратов","Воронеж");
        graph.addEdge("Курск","Воронеж");

        Stack<String> travel = graph.findShortPathViaBfs("Москва", "Воронеж");


        showShortPath(travel);
    }

    private static void showShortPath(Stack<String> stack) {
        StringBuilder sb = new StringBuilder();


        while ( !stack.isEmpty() ) {

            sb.append(stack.pop() + " ");
        }

        System.out.println(sb);
    }

}

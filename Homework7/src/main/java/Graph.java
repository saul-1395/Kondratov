import java.util.*;
import java.util.stream.Stream;

public class Graph {

    private final List<Vertex> vertices = new ArrayList<>();
    private boolean[][] adjMat;

    private int size;

    public Graph(int maxVertexCount) {
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
    }

    public void addVertex(String label) {
        vertices.add(new Vertex(label));
        size++;
    }

    public void addEdge(String start, String finish) {
        if (!find(start) || !find(finish)) {
            throw new IllegalArgumentException(String.format(
                    "Invalid label for vertex: [%s, %s]", start, finish));
        }

        int startIndex = indexOf(start);
        int finishIndex = indexOf(finish);
        adjMat[startIndex][finishIndex] = true;
        adjMat[finishIndex][startIndex] = true;
    }

    public void addEdges(String start, String... others) {
        if (!areAllExist(start, others)) {
            throw new IllegalArgumentException("Invalid params");
        }

        int startIndex = indexOf(start);
        for (int i = 0; i < others.length; i++) {
            int finishIndex = indexOf(others[i]);
            adjMat[startIndex][finishIndex] = true;
            adjMat[finishIndex][startIndex] = true;
        }
    }

    private int indexOf(String start) {
        for (int i = 0; i < size; i++) {
            if (vertices.get(i).getLabel().equals(start)) {
                return i;
            }
        }

        return -1;
    }

    private boolean areAllExist(String... labels) {
//        boolean match = true;
//        for (String label : labels) {
//            match &= find(label);
//        }
//        return match;

//        return Stream.of(labels).allMatch(label -> find(label));
        return Stream.of(labels).allMatch(this::find);
    }

    private boolean areAllExist(String start, String... labels) {
        String[] allLabels = Arrays.copyOf(labels, labels.length + 1);
        allLabels[allLabels.length - 1] = start;
        return areAllExist(allLabels);
    }

    private boolean find(String start) {
        return indexOf(start) != -1;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(vertices.get(i));
            for (int j = 0; j < size; j++) {
                if (adjMat[i][j]) {
                    System.out.print(" -> " + vertices.get(j));
                }
            }
            System.out.println();
        }
    }

    /**
     * англ. Depth-first search, DFS
     * @param startLabel
     */
    public void dfs(String startLabel) {
        if ( !find(startLabel) ) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }

        Stack<Vertex> stack = new Stack<>();

        Vertex vertex = vertices.get(indexOf(startLabel));
        visitVertex(stack, vertex);

        while ( !stack.isEmpty() ) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex == null) {
                stack.pop();
                continue;
            }

            visitVertex(stack, vertex);
        }
        resetVertexState();
    }

    private void resetVertexState() {
        vertices.forEach(Vertex::resetState);
    }

    /**
     * англ. breadth-first search, BFS
     * @param startLabel
     */
    public void bfs(String startLabel) {
        if ( !find(startLabel) ) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }

        Queue<Vertex> queue = new LinkedList<>();

        Vertex vertex = vertices.get(indexOf(startLabel));
        visitVertex(queue, vertex);

        while ( !queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex == null) {
                queue.remove();
                continue;
            }

            visitVertex(queue, vertex);
        }
        resetVertexState();
    }

    private Vertex getNearUnvisitedVertex(Vertex peek) {
        int peekIndex = vertices.indexOf(peek);
        for (int i = 0; i < size; i++) {
            if (adjMat[peekIndex][i] && !vertices.get(i).isWasVisited()) {
                return vertices.get(i);
            }
        }
        return null;
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        displayVertex(vertex);
        stack.push(vertex);
        vertex.markAsVisited();
    }
    private void visitVertex(Queue<Vertex> stack, Vertex vertex) {
        displayVertex(vertex);
        stack.add(vertex);
        vertex.markAsVisited();
    }

    private void displayVertex(Vertex vertex) {
        System.out.println(vertex);
    }

    public Stack<String> findShortPathViaBfs(String startLabel, String finishLabel) {
        int startIndex  = indexOf(startLabel);
        int finishIndex = indexOf(finishLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }
        if (finishIndex == -1) {
            throw new IllegalArgumentException("Invalid finishLabel: " + finishLabel);
        }

        Queue<Vertex> queue = new ArrayDeque();

        Vertex vertex = vertices.get(startIndex);
        visitVertex(queue, vertex);

        while ( !queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex == null) {
                queue.remove();
            }
            else {
                visitVertex(queue, vertex);
                vertex.setPreviousVertex(queue.peek());
                if (vertex.getLabel().equals(finishLabel)) {
                    return buildPath(vertex);
                }
            }
        }

        resetVertexState();
        return null;
    }

    private Stack<String> buildPath(Vertex vertex) {
        Stack<String> stack = new Stack();
        Vertex current = vertex;
        while (current != null) {
            stack.push(current.getLabel());
            current = current.getPreviousVertex();
        }

        return stack;
    }
}

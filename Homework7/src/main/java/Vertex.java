import java.util.Objects;

public class Vertex {

    private final String label;
    private boolean wasVisited;

    private Vertex previousVertex;

    public Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(label, vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    public void markAsVisited() {
        this.wasVisited = true;
    }

    public boolean isWasVisited() {
        return wasVisited;
    }

    public void resetState() {
        this.wasVisited = false;
        this.previousVertex = null;
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }
}
import java.util.Objects;

public class Item {

    private final String title;
    private final int id;

    public Item(int id, String title) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Objects.equals(title, item.title);
    }

    @Override
    public int hashCode() {
//        return Objects.hash(id);
        return id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
import java.util.LinkedList;

public class HashTableLinkImpl  implements HashTable {
    public static final int INVALID_INDEX = -1;
    public static final Integer INVALID_COST = null;

    private class Entry {

        private Item key;
        private int value;

        public Entry(Item key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }


    private LinkedList<Entry>[] data;
    private int size;
    private int maxSize;

    public HashTableLinkImpl(int maxSize) {
        this.data = new LinkedList [maxSize * 2];
        this.maxSize = maxSize;
    }

    private int hashFunc(int key) {
        return key % data.length;
    }

    @Override
    public boolean put(Item item, Integer cost) {
        if (size == maxSize) {
            return false;
        }

        int index = hashFunc(item.hashCode());
        if (data[index] == null) {
            data[index] = new LinkedList<>();
            data[index].add(data[index].size(), new Entry(item, cost));
        }else {
            data[index].add(data[index].size(), new Entry(item, cost));
        }
        size++;

        return true;
    }

    @Override
    public Integer get(Item item) {
        int valueT = 0;
        int index = indexOf(item);
        if (index == INVALID_INDEX) {
            return INVALID_COST;
        }

        for (int i = 0; i < data[index].size() ; i++) {
                if(data[index].get(i).key.equals(item.getTitle())){
                    valueT = data[index].get(i).value;
                }
                valueT =  INVALID_COST;

        }

        return valueT; //пришлось содать переменную т.к. ругалась идея на отсутствие ретон
    }

//        return index == INVALID_INDEX ? INVALID_COST : data[index].value;


    private int indexOf(Item item) {
        int index = hashFunc(item.hashCode());

        int count = 0;
        while (data[index] != null) {
            for (int i = 0; i <data[index].size() ; i++) {
                if (data[index].get(i).key.equals(item)){
                    return index;
                }
            }

            if (count > data.length) {
                return -1;
            }

            count++;
        }
        return -1;
    }

    @Override
    public boolean remove(Item item) {
        int index = indexOf(item);
        if (index != INVALID_INDEX) {
            data[index] = null;
            size--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println("-----------");
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {


                    System.out.println(String.format("%d = [%s]", i, data[i].toString()));


            } else {
                System.out.println("-");
            }
        }
        System.out.println("-----------");
    }


}

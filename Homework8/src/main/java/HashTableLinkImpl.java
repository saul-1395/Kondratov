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


    private HashTableLinkImpl.Entry[] data;
    private int size;
    private int maxSize;

    public HashTableLinkImpl(int maxSize) {
        this.data = new HashTableLinkImpl.Entry[maxSize * 2];
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
        while (data[index] != null) {
            index += getStep(item.hashCode());
            index %= data.length;
        }

        data[index] = new HashTableLinkImpl.Entry(item, cost);
        size++;

        return true;
    }

    @Override
    public Integer get(Item item) {
        int index = indexOf(item);
        if (index == INVALID_INDEX) {
            return INVALID_COST;
        } else {
            return data[index].value;
        }

//        return index == INVALID_INDEX ? INVALID_COST : data[index].value;
    }

    private int indexOf(Item item) {
        int index = hashFunc(item.hashCode());

        int count = 0;
        while (data[index] != null) {
            if (data[index].key.equals(item)) {
                return index;
            }

            if (count > data.length) {
                return -1;
            }

            index += getStep(item.hashCode());
            index %= data.length;

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
            System.out.println(String.format("%d = [%s]", i, data[i]));
        }
        System.out.println("-----------");
    }

    protected int getStep(int key) {
        return 1;
    }
}

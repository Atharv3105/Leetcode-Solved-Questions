class MyHashSet {
    // Number of buckets
    private final int SIZE = 1000;
    private Bucket[] buckets;

    public MyHashSet() {
        buckets = new Bucket[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new Bucket();
        }
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void add(int key) {
        int index = hash(key);
        buckets[index].insert(key);
    }

    public void remove(int key) {
        int index = hash(key);
        buckets[index].delete(key);
    }

    public boolean contains(int key) {
        int index = hash(key);
        return buckets[index].exists(key);
    }
}

// Helper class for bucket (linked list)
class Bucket {
    private LinkedList<Integer> list;

    public Bucket() {
        list = new LinkedList<>();
    }

    public void insert(int key) {
        if (!list.contains(key)) {
            list.add(key);
        }
    }

    public void delete(int key) {
        list.remove((Integer) key);
    }

    public boolean exists(int key) {
        return list.contains(key);
    }
}

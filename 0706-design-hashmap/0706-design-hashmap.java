class MyHashMap {
    private final int SIZE = 1000; // number of buckets
    private Bucket[] buckets;

    public MyHashMap() {
        buckets = new Bucket[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new Bucket();
        }
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int index = hash(key);
        buckets[index].update(key, value);
    }

    public int get(int key) {
        int index = hash(key);
        return buckets[index].get(key);
    }

    public void remove(int key) {
        int index = hash(key);
        buckets[index].remove(key);
    }
}

// Helper class for bucket
class Bucket {
    private LinkedList<Pair> list;

    public Bucket() {
        list = new LinkedList<>();
    }

    public void update(int key, int value) {
        for (Pair p : list) {
            if (p.key == key) {
                p.value = value; // update existing
                return;
            }
        }
        list.add(new Pair(key, value)); // insert new
    }

    public int get(int key) {
        for (Pair p : list) {
            if (p.key == key) {
                return p.value;
            }
        }
        return -1; // not found
    }

    public void remove(int key) {
        for (Pair p : list) {
            if (p.key == key) {
                list.remove(p);
                break;
            }
        }
    }
}

// Simple key-value pair class
class Pair {
    int key;
    int value;

    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

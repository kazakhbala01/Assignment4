public class MyHashTable<K,V> {
    private class HashNode<K,V>{
        private K key;
        private V value;
        private HashNode<K, V> next;

        private HashNode(K key, V value){
            this.key=key;
            this.value=value;
        }

        @Override
        public String toString(){
            return "{"+key+" "+value+"}";
        }
    }

    private HashNode<K,V>[] chainArray;
    private int M=11;
    private int size;

    public MyHashTable(){
        chainArray=new HashNode[M];
    }

    public MyHashTable(int M){
        this.M=M;
        chainArray=new HashNode[M];
    }

    // This is a helper method ash value of a given key using the modulo operation
    private int hash(K key){
        return Math.abs(key.hashCode()) % M;
    }

    // This is a method that inserts or updates a key-value pair in the hash table
    public void put(K key, V value){
        // First, we find the index of the chain where the key belongs using the hash function
        int index = hash(key);
        // Then, we traverse the chain to see if the key already exists in the hash table
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            // If the key exists, we update its value and return
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        // If the key does not exist, we create a new node with the key-value pair and insert it at the beginning of the chain
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }
    public void copy(int j){
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
        }
    }

    // This is a method that returns the value associated with a given key in the hash table, or null if the key does not exist
    public V get(K key){
        // First, we find the index of the chain where the key belongs using the hash function
        int index = hash(key);
        // Then, we traverse the chain to see if the key exists in the hash table
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            // If the key exists, we return its value
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        // If the key does not exist, we return null
        return null;
    }

    // This is a method that removes and returns the value associated with a given key in the hash table, or null if the key does not exist
    public V remove(K key){
        // First, we find the index of the chain where the key belongs using the hash function
        int index = hash(key);
        // We also keep track of the previous node in the chain for deletion purposes
        HashNode<K, V> prev = null;
        // Then, we traverse the chain to see if the key exists in the hash table
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            // If the key exists, we remove it from the chain and return its value
            if (node.key.equals(key)) {
                if (prev == null) {
                    // If it is the first node in the chain, we update the head of the chain to point to its next node
                    chainArray[index] = node.next;
                } else {
                    // If it is not the first node in the chain, we update the previous node to point to its next node
                    prev.next = node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        // If the key does not exist, we return null
        return null;
    }

    // This is a method that checks if the hash table contains a given value
    public boolean contains(V value){
        // We iterate over all the chains in the hash table
        for (int i = 0; i < chainArray.length; i++) {
            // We traverse each chain to see if the value exists in any node
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    // If the value exists, we return true
                    return true;
                }
                node = node.next;
            }
        }
        // If the value does not exist, we return false
        return false;
    }

    // This is a method that returns the key associated with a given value in the hash table, or null if the value does not exist
    public K getKey(V value){
        // We iterate over all the chains in the hash table
        for (int i = 0; i < chainArray.length; i++) {
            // We traverse each chain to see if the value exists in any node
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    // If the value exists, we return its key
                    return node.key;
                }
                node = node.next;
            }
        }
        // If the value does not exist, we return null
        return null;
    }

    // This is a method that returns an array of integers representing the size of each chain in the hash table
    public int[] getBucketSize() {
        int[] size = new int[M];
        // We iterate over all the chains in the hash table
        for (int i = 0; i < M; i++) {
            // We traverse each chain and count the number of nodes in it
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                size[i]++;
                node = node.next;
            }
        }
        return size;
    }
}
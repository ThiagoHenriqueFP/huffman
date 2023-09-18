package server.infrastructure.database;

import server.domain.Node;

public class DatabaseHashTableOp implements DatabaseFunctions{
    private final int SIZE = 251;

    private final Node[] nodes = new Node[SIZE];


    private int hash(String key) {
        int value = reduce(key);

        return value % 251;
    }

    public void insert(Node n) {
        // do something

        int index = hash(n.car().getRenavan());
        int tries = 0;
        if (nodes[index] != null) {
            int newIndex = spread(index, ++tries);
            insert(n, newIndex, tries);
        } else {
            nodes[index] = n;
        }
    }

    private void insert(Node n, int index, int tries) {
        if (nodes[index] != null) {
            int newIndex = spread(index, ++tries);
            insert(n, newIndex, tries);
        } else {
            nodes[index] = n;
        }
    }

    public void remove(String key) {
        int index = hash(key);
        int tries = 0;

        while (index < nodes.length) {
            if (nodes[index].key().equals(key)){
                nodes[index] = null;
                return;
            }
            else index = spread(index, ++tries);
        }

        throw new ArrayIndexOutOfBoundsException("This key is not inside the database");
    }

    @Override
    public void getAll() {
        for (int i = 0; i < this.SIZE; i ++){
            if(this.nodes[i] != null)
                System.out.println("index -> " + i +" = "+ this.nodes[i]);

        }
    }

    public Node get(String key) {
        int index = hash(key);
        int tries = 0;

        while (index < nodes.length) {
            if (nodes[index].key().equals(key)) return nodes[index];
            else index = spread(index, ++tries);
        }

        throw new ArrayIndexOutOfBoundsException("This key is not inside the database");
    }

    public Node update(Node n){
        int index = hash(n.key());
        int tries = 0;

         while (index < nodes.length && tries < 10) {
            if (nodes[index].key().equals(n.key())){
                nodes[index] = n;
                return nodes[index];
            }
            else index = spread(index, ++tries);
        }

         throw new NullPointerException("This node isn`t in the database");
    }

    private int spread(int index, int tries) {
        return (int) ((index + Math.pow(tries, 2) * 2) % nodes.length);
    }

    private int reduce(String key) {
        int sum = 0;

        for (int i = 0; i < key.length(); i++) {
            sum += Character.getNumericValue(key.charAt(i));
        }

        return sum;
    }
}

package server.infrastructure.database;

import server.domain.Node;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.LinkedList;

public class DatabaseHashTableExt implements DatabaseFunctions {
    private final int SIZE = 251;
    private final LinkedList<Node>[] nodes = new LinkedList[SIZE];

    private int hash(String key) {
        int value = reduce(key);

        return value % SIZE;
    }

    public void insert(Node n) {
        // do something
        int index = hash(n.car().getRenavan());
        if (nodes[index] == null){
            nodes[index] = new LinkedList<>();
            nodes[index].add(n);
        } else {
            if (nodes[index].stream().anyMatch(it -> it.equals(n.key())))
                throw new KeyAlreadyExistsException();
            nodes[index].add(n);
        }
    }

    public Node get(String key) {
        int index = hash(key);

        return nodes[index].stream().filter( it -> it.key().equals(key)).findFirst().orElseThrow(() ->
                new NullPointerException("Element not found")
        );
    }

    public Node remove(String key){
        int index = hash(key);

        Node retrieve = nodes[index].stream().filter( it -> it.key().equals(key)).findFirst().orElseThrow(() ->
                new NullPointerException("Element not found")
        );

        nodes[index].remove(retrieve);
        return retrieve;
    }

    @Override
    public void getAll() {
        for (int i = 0; i < this.SIZE; i ++){
            if(this.nodes[i] != null)
                System.out.println("index -> " + i +" = "+ this.nodes[i]);
        }
    }

    @Override
    public int count() {
        int count = 0;
        for (int i = 0; i < this.SIZE; i++)
            if (this.nodes[i] != null){
                count += this.nodes[i].size();
            }

        return count;
    }

    public Node update(Node n){
        int index = hash(n.key());

        for(int i = 0; nodes[index].iterator().hasNext(); i++){
            if(nodes[index].get(i).key().equals(n.key())){
                nodes[index].add(i, n);
                return nodes[index].get(i);
            }
        }

        throw new NullPointerException("This node isn`t in the database");
    }

    private int reduce(String key) {
        int sum = 0;

        for (int i = 0; i < key.length(); i++) {
            sum += Character.getNumericValue(key.charAt(i));
        }

        return sum;
    }
}

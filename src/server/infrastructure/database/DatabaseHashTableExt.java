package server.infrastructure.database;

import server.domain.Node;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class DatabaseHashTableExt implements DatabaseFunctions {
    private final Logger logger = Logger.getLogger("hashTableExt");
    private final int SIZE = 251;
    private final LinkedList<Node>[] nodes = new LinkedList[SIZE];

    private int hash(String key) {
        int value = reduce(key);

        return value % SIZE;
    }

    public void insert(Node n) {
        int index = hash(n.getCar().getRenavan());
        if (nodes[index] == null){
            nodes[index] = new LinkedList<>();
            nodes[index].add(n);
        } else {
            if (nodes[index].stream().anyMatch(it -> it.equals(n.getKey())))
                throw new KeyAlreadyExistsException();
            nodes[index].add(n);
        }
    }

    public Node get(String key) {
        int index = hash(key);

        Node query = nodes[index].stream().filter(it ->
                it.getKey().equals(key)).findFirst().orElseThrow(() ->
                new NullPointerException("Element not found")
        );
        // Inserindo aqui a tranformacao da lista a cada acesso
        query.setFrequency(query.getFrequency() + 1);
        order(nodes[index], index);
        return query;
    }

    public Node remove(String key){
        int index = hash(key);

        Node retrieve = nodes[index].stream().filter( it -> it.getKey().equals(key)).findFirst().orElseThrow(() ->
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
        int index = hash(n.getKey());

        for(int i = 0; nodes[index].iterator().hasNext(); i++){
            if(nodes[index].get(i).getKey().equals(n.getKey())){
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

    private void order(List<Node> list, Integer index) {
        logger.info("ordering list: " + index);
        // shell short
        for (int gap = list.size()/2; gap > 0; gap /= 2) {
            for(int i = gap; i < list.size(); i++){
                Node node = list.get(i);
                int j = i;

                while (j >= gap && list.get(j-gap).getFrequency() < node.getFrequency()){
                   list.set(j, list.get(j - gap));
                   j -= gap;
                }

                list.set(j, node);
            }
        }
    }
}

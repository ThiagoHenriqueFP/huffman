package server.infrastructure.database;

import server.domain.Node;

public interface DatabaseFunctions {
    void insert(Node n);
    Node update(Node n);
    Node get(String key);
    void remove(String key);

    void getAll();

}

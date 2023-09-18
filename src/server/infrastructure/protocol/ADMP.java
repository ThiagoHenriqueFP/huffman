package server.infrastructure.protocol;

import server.domain.Car;
import server.domain.Node;
import server.infrastructure.database.DatabaseFunctions;
import server.infrastructure.database.DatabaseHashTableExt;
import server.infrastructure.database.DatabaseHashTableOp;

import javax.xml.crypto.Data;

// Awesome database manager protocol
public class ADMP {

    private boolean type;
    private DatabaseFunctions db;

    public ADMP(boolean type) {
        this.type = type;
        if (type) this.db = new DatabaseHashTableOp();
        else this.db = new DatabaseHashTableExt();
    }

    public void insert(Car c) {
        Node n = new Node(c);
        db.insert(n);
    }

    public Node update(Car c) {
        Node n = new Node(c);
        return db.update(n);
    }


    public Node get(String key) {
        return db.get(key);
    }

    public void remove(String key) {
        db.remove(key);
    }

    public void getAll() {
        db.getAll();
    }
}

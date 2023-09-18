package server.infrastructure.protocol;

import server.domain.Car;
import server.domain.Node;
import server.infrastructure.database.DatabaseFunctions;
import server.infrastructure.database.DatabaseHashTableExt;
import server.infrastructure.database.DatabaseHashTableOp;

import java.util.logging.Logger;

// Awesome database manager protocol
public class ADMP {

    private boolean type;
    private DatabaseFunctions db;

    private final Logger logger = Logger.getLogger("ADMP");

    public ADMP(boolean type) {
        this.type = type;
        if (type) this.db = new DatabaseHashTableOp();
        else this.db = new DatabaseHashTableExt();
    }

    public void insert(Car c) {
        Node n = new Node(c);
        db.insert(n);
        logger.info("insertion: key = " + c.getRenavan());
    }

    public Node update(Car c) {
        Node n = new Node(c);
        logger.info("update: key = " + c.getRenavan());
        return db.update(n);
    }


    public Node get(String key) {
        logger.info("get: key = " + key);
        return db.get(key);
    }

    public Node remove(String key) {
        logger.info("remove: key = " + key);
        return db.remove(key);
    }

    public void getAll() {
        logger.info("get: all keys");
        db.getAll();
    }

    public int count() {
        logger.info("count elements");
        return db.count();
    }
}

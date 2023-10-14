package server.infrastructure.priorityList;

import server.domain.SymbolNode;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PriorityList {
    private  final List<SymbolNode> heap;
    private final Logger logger = Logger.getLogger("priorityList");

    public PriorityList() {
        this.heap = new ArrayList<>();
    }

    public PriorityList(List<SymbolNode> heap) {
        this.heap = heap;
    }

    public Integer size() {return this.heap.size();}

    public void moveUp(int i) {
        int j;
        SymbolNode node;

        j = (i - 1) / 2;

        if (j >= 0 && heap.get(i).getFreq() < heap.get(j).getFreq()) {
            node = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, node);

            moveUp(j);
        }

        logger.info("moving up");
    }

    public void moveDown(Integer i, List<SymbolNode> heap, int size) {
        int j = 2 * i + 1;
        SymbolNode node;

        if (j < size) {
            if (j < size - 1) {
                if (heap.get(j).getFreq() > heap.get(j + 1).getFreq()) {
                    j++;
                }
                if (heap.get(j).getFreq() < heap.get(i).getFreq()) {
                    node = heap.get(i);
                    heap.set(i, heap.get(j));
                    heap.set(j, node);

                    moveDown(j, heap, size);
                }
            }
        }
        logger.info("moving down");
    }

    public void build(List<SymbolNode> heap) {
        for (int i = (heap.size() / 2) - 1; i >= 0; i--)
            moveDown(i, heap, heap.size());
    }

    public SymbolNode add(SymbolNode node) {
        int incomeSize = this.heap.size();
        this.heap.add(node);

        if (incomeSize != 0)
            moveUp(incomeSize - 1);

        return node;
    }

    public SymbolNode remove() {
        if (this.heap.isEmpty())
            return null;

        SymbolNode removed = this.heap.get(0);
        this.heap.set(0, this.heap.get(this.heap.size() - 1));
        this.heap.remove(this.heap.size() - 1);

        moveDown(0, this.heap, this.heap.size() - 1);

        return removed;
    }

    public void heapSort(List<SymbolNode> heap){
        int heapSize = heap.size() - 1;
        int actualSize = heap.size();

        build(heap);

        for(int i = heapSize; i >=0; i--){
            SymbolNode temp = heap.get(i);
            heap.set(i, heap.get(0));
            heap.set(0, temp);
            actualSize--;
            moveDown(0, heap, actualSize);
        }
    }

    public Integer hasChar(Character c) {
        Integer index = -1;
        for (int i = 0; i < this.heap.size(); i++)
            if(this.heap.get(i).getCharacter().equals(c))
                return i;

        return index;
    }

    public void incrementFrequency(Character c){
        Integer index = hasChar(c);

        if (index < 0)
            throw new RuntimeException("This char isn't in this database");

        Integer frequency = this.heap.get(index).getFreq();
        this.heap.get(index).setFreq(++frequency);
    }

    public void incrementFrequency(Integer index){
        Integer frequency = this.heap.get(index).getFreq();
        this.heap.get(index).setFreq(++frequency);
    }

    @Override
    public String toString() {
        return "PriorityList{" +
                "heap=" + heap +
                '}';
    }
}

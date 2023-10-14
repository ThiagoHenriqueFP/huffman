package server.domain;

public class SymbolNode {
    private Integer freq;
    private Character character;
    private SymbolNode left;
    private SymbolNode right;

    public SymbolNode() {
        this.left = null;
        this.right = null;
    }

    public SymbolNode(Character c) {
        this.character = c;
        this.left = null;
        this.right = null;
        this.freq = 1;
    }

    public SymbolNode(Integer freq, Character character) {
        this.freq = freq;
        this.character = character;
        this.left = null;
        this.right = null;

    }

    public SymbolNode(Integer freq, Character character, SymbolNode left, SymbolNode right) {
        this.freq = freq;
        this.character = character;
        this.left = left;
        this.right = right;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public SymbolNode getLeft() {
        return left;
    }

    public void setLeft(SymbolNode left) {
        this.left = left;
    }

    public SymbolNode getRigth() {
        return right;
    }

    public void setRigth(SymbolNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "SymbolNode{" +
                "freq=" + freq +
                ", character=" + character +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

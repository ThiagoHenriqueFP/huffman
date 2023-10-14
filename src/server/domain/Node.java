package server.domain;

// esse tipo de "classe" espcecial, abstrai a criacao de uma classe "normal"
// como essa classe so deve ter alguns getters e setters e um construtor padrao
// posso deixar de assim de forma a simplificar a implementacao
public class Node {
    private Car car;
    private String key;
    private Integer frequency;

    public Node(Car car, String key, Integer frequency) {
        this.car = car;
        this.key = key;
        this.frequency = frequency;
    }

    public Node(Car car) {
        this(car, car.getRenavan(), 1);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Node {\n" +
                "key=" + key +
                "\ncar='" + car + '\'' +
                "\nfrequency="+ frequency +
                "\n}";
    }
}

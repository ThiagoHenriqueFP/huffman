package server.domain;

// esse tipo de "classe" espcecial, abstrai a criacao de uma classe "normal"
// como essa classe so deve ter alguns getters e setters e um construtor padrao
// posso deixar de assim de forma a simplificar a implementacao
public record Node (
        Car car,
        String key
){
    public Node(Car car, String key) {
        this.car = car;
        this.key = key;
    }

    public Node(Car car){
        this(car, car.getRenavan());
    }

    @Override
    public String toString() {
        return "Node {\n" +
                "key=" + key +
                "\ncar='" + car + '\'' +
                "\n}";
    }
}

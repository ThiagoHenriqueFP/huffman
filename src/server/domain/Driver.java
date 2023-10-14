package server.domain;


// esse tipo de "classe" espcecial, abstrai a criacao de uma classe "normal"
// como essa classe so deve ter alguns getters e setters e um construtor padrao
// posso deixar de assim de forma a simplificar a implementacao
public record Driver(
        String name,
        String cpf
) {
    public Driver(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return name +
                "," + cpf;
    }
}

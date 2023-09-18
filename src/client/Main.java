package client;

import server.domain.Node;
import server.infrastructure.protocol.ADMP;
import server.domain.Car;

import java.util.Scanner;

public class Main {
    static String USERNAME = "password";
    static String PASSWORD = "username";
    static ADMP protocol;

    public static void main(String[] args) {

        int option = 0;
        int type = -1;
        boolean authenticated = true;

        Scanner sc = new Scanner(System.in);

        while (type < 0 || type > 1) {
            System.out.println("Qual encadeamento deseja usar?" +
                    "\nAberto: [1]" +
                    "\nExterno: [0]");

            type = sc.nextInt();
        }

        protocol = new ADMP(type == 1);

        do {
            try {
                System.out.println("+-----+---------------------+\n" +
                        "| 1)  | Login               |\n" +
                        "| 2)  | Salvar carro        |\n" +
                        "| 3)  | Buscar carro        |\n" +
                        "| 4)  | Remover carro       |\n" +
                        "| 5)  | Atualizar carro     |\n" +
                        "| 6)  | Listar todos        |\n" +
                        "| 9)  | Popular banco       |\n" +
                        "| 0)  | Sair                |\n" +
                        "+-----+---------------------+");
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        String username = sc.next();
                        String password = sc.next();

                        if (!login(username, password)) {
                            System.out.println("username invalido, tente novamente");
                            break;
                        }
                        authenticated = true;
                        break;
                    case 2:
                        if (!authenticated) {
                            System.out.println("esta funcao precisa de autenticacao");
                            break;
                        }

                        System.out.println("Informe o renavan");
                        String renavan = sc.next();
                        System.out.println("Informe o nome");
                        String name = sc.next();
                        System.out.println("Informe a marca");
                        String brand = sc.next();
                        System.out.println("Informe o ano");
                        Integer year = sc.nextInt();
                        System.out.println("Informe a placa (sem espaços)");
                        String plate = sc.next();
                        System.out.println("Informe o nome do condutor (sem espaco)");
                        String driverName = sc.next();
                        System.out.println("Informe o cpf do condutor (sem pontos e/ou hifen)");
                        String cpf = sc.next();

                        Car car = new Car(
                                plate,
                                renavan,
                                name,
                                brand,
                                year,
                                driverName,
                                cpf
                        );

                        protocol.insert(car);
                        break;

                    case 3:
                        if (!authenticated) {
                            System.out.println("esta funcao precisa de autenticacao");
                            break;
                        }

                        System.out.println("informe o renavan do carro");
                        String key = sc.next();

                        Node result = protocol.get(key);
                        if (result == null) {
                            System.out.println("car not found");
                            break;
                        }
                        System.out.println(result);
                        break;
                    case 4:
                        if (!authenticated) {
                            System.out.println("esta funcao precisa de autenticacao");
                            break;
                        }

                        System.out.println("informe o renavan do carro");
                        String keyToRemove = sc.next();

                        protocol.remove(keyToRemove);
                        break;
                    case 5:
                        if (!authenticated) {
                            System.out.println("esta funcao precisa de autenticacao");
                            break;
                        }

                        System.out.println("Informe o renavan");
                        renavan = sc.next();
                        System.out.println("Informe o nome");
                        name = sc.next();
                        System.out.println("Informe a marca");
                        brand = sc.next();
                        System.out.println("Informe o ano");
                        year = sc.nextInt();
                        System.out.println("Informe a placa (sem espaços)");
                        plate = sc.next();
                        System.out.println("Informe o nome do condutor (sem espaco)");
                        driverName = sc.next();
                        System.out.println("Informe o cpf do condutor (sem pontos e/ou hifen)");
                        cpf = sc.next();


                        System.out.println("Updated car : " + protocol.update(
                                new Car(
                                        plate,
                                        renavan,
                                        name,
                                        brand,
                                        year,
                                        driverName,
                                        cpf
                                )
                        ));

                        break;
                    case 6:
                        if (!authenticated) {
                            System.out.println("esta funcao precisa de autenticacao");
                            break;
                        }

                        protocol.getAll();
                        break;
                    case 9:
                        populate();
                        break;
                    case 0:
                        System.out.println("FIN ACK\nclosing connection!");
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred\n" + e.getMessage());
            }

        } while (option != 0);

    }

    private static boolean login(String username, String password){
        return username.equalsIgnoreCase(USERNAME) && password.equalsIgnoreCase(PASSWORD);
    }

    private static void populate() {
       for(int i = 0; i < 50; i++){
            int random = (int) (Math.random() * i * 100);
            Car c = new Car(
                    "xgv" + i,
                    "generated" + random,
                    "Civic",
                    "Honda",
                    2015,
                    "Thiago",
                    "00011122233"
            );

            protocol.insert(c);
        }
    }
}

package rpg.io;

import java.util.Scanner;

public class ConsoleUI implements InterfaceUsuario {
    private final Scanner scanner;

    public ConsoleUI(Scanner scanner) { this.scanner = scanner; }

    @Override public void mostrar(String mensagem) { System.out.println(mensagem); }

    @Override public String perguntar(String mensagem) {
        System.out.print(mensagem);
        return scanner.hasNextLine() ? scanner.nextLine().trim() : null;
    }
}

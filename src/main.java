import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("===== RPG GAME =====");
    System.out.println("Escolha sua ação:");
    System.out.println("1 - Ataque comum");
    System.out.println("2 - Ataque especial");

    int opcao = scanner.nextInt();

    System.out.println("Você escolheu: " + opcao);
    }
}

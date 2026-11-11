package rpg;

import java.util.Scanner;
import rpg.io.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new Jogo(new ConsoleUI(scanner)).iniciar();
        }
    }
}

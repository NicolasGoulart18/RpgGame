package rpg;

import rpg.combate.Combate;
import rpg.inimigos.Goblin;
import rpg.personagens.Guerreiro;
import rpg.personagens.Personagem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Personagem heroi = new Guerreiro("Arthur");
        Personagem inimigo = new Goblin("Goblin");

        Combate combate = new Combate(heroi, inimigo);

        try (Scanner teclado = new Scanner(System.in)) {
            combate.iniciarCombate(teclado);
        }
    }
}

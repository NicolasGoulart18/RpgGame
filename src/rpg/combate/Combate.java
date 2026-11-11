package rpg.combate;

import rpg.personagens.Personagem;

import java.util.Scanner;

public class Combate {
    private Personagem heroi;
    private Personagem inimigo;
    private int especiaisRestantes = 3;
    public Combate(Personagem heroi, Personagem inimigo) {
        this.heroi = heroi;
        this.inimigo = inimigo;
    }

    public void iniciarCombate(Scanner teclado) {
        while (heroi.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\n=== SEU TURNO ===");
            System.out.println(heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima() + " HP");
            System.out.println(inimigo.getNome() + ": " + inimigo.getVida() + "/" + inimigo.getVidaMaxima() + " HP");
            System.out.println("1 - Ataque comum");
            System.out.println( "2 - Ataque especial (" + especiaisRestantes + " restantes)");
            System.out.println("3 - Defender");
            System.out.print("Sua escolha: ");

            if (!teclado.hasNext()) {
                System.out.println("Entrada encerrada. Combate interrompido.");
                return;
            }
            if (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Opcao invalida! Digite 1 ou 2.");
                continue;
            }

            int opcao = teclado.nextInt();
            executarRodada(opcao);
        }

        if (heroi.estaVivo()) {
            System.out.println("Heroi venceu!");
        } else {
            System.out.println("Heroi foi derrotado!");
        }
    }

    public void executarRodada(int opcao) {
        int dano = 0;
        boolean defendendo=false;

        switch (opcao) {
            case 1:
                dano = heroi.atacar();
                System.out.println(heroi.getNome() + " usou ataque comum!");
                break;
            case 2:
                 if (especiaisRestantes <= 0) {
                System.out.println("Voce nao possui especiais restantes!");
                return;
                
    }
             dano = heroi.ataqueEspecial();
            especiaisRestantes--;

                System.out.println(heroi.getNome() + " usou ataque especial!");
                break;
                
            
            case 3:
                defendendo=true;  
                System.out.println(heroi.getNome()+" adotou uma postura defensiva!");
                break;

            default:
                System.out.println("Opção inválida! Escolha 1 2 ou 3");
                return;
        }


        if (!defendendo) {
            inimigo.receberDano(dano, defendendo);
            System.out.println(
                "Vida do inimigo: "+inimigo.getVida()
            );
        }
        if(inimigo.estaVivo()){
            System.out.println(inimigo.getNome()+" revida!");
            heroi.receberDano(dano, defendendo);
        }

        System.out.println("Vida do heroi: " + heroi.getVida());
    }
}

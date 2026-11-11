package rpg.inimigos;

import rpg.personagens.Personagem;

/** Qualquer inimigo pode participar do mesmo combate (LSP). */
public abstract class Inimigo extends Personagem {
    protected Inimigo(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }
}

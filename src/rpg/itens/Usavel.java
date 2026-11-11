package rpg.itens;

import rpg.personagens.Heroi;

/** Itens consumiveis nao precisam implementar equipar (ISP). */
public interface Usavel {
    String getNome();
    boolean usar(Heroi heroi);
}

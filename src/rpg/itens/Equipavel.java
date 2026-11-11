package rpg.itens;

import rpg.personagens.Heroi;

/** Equipamentos nao precisam implementar usar (ISP). */
public interface Equipavel {
    String getNome();
    void equipar(Heroi heroi);
}

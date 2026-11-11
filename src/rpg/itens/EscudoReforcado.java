package rpg.itens;

import rpg.personagens.Heroi;

public class EscudoReforcado implements Equipavel {
    public static final int BONUS_DEFESA = 3;

    @Override public String getNome() { return "Escudo reforcado (+3 defesa)"; }

    @Override public void equipar(Heroi heroi) {
        heroi.aumentarDefesa(BONUS_DEFESA);
    }
}

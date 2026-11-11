package rpg.itens;

import rpg.personagens.Heroi;

public class PocaoCura implements Usavel {
    public static final int CURA = 30;

    @Override public String getNome() { return "Pocao de cura (+30 HP)"; }

    @Override public boolean usar(Heroi heroi) {
        return heroi.curar(CURA) > 0;
    }
}

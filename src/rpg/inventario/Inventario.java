package rpg.inventario;

import java.util.ArrayList;
import java.util.List;
import rpg.itens.Equipavel;
import rpg.itens.Usavel;
import rpg.personagens.Heroi;

/** Armazena itens por seus contratos, sem depender de tipos concretos. */
public class Inventario {
    private final List<Usavel> consumiveis = new ArrayList<>();
    private final List<Equipavel> equipamentos = new ArrayList<>();

    public void adicionar(Usavel item) { consumiveis.add(item); }
    public void adicionar(Equipavel item) { equipamentos.add(item); }
    public int getQuantidadeConsumiveis() { return consumiveis.size(); }
    public int getQuantidadeEquipamentos() { return equipamentos.size(); }

    /** Uso bem-sucedido consome o primeiro item e o turno correspondente. */
    public boolean usarPrimeiroConsumivel(Heroi heroi) {
        if (consumiveis.isEmpty()) return false;
        if (!consumiveis.get(0).usar(heroi)) return false;
        consumiveis.remove(0);
        return true;
    }

    public boolean equiparPrimeiro(Heroi heroi) {
        if (equipamentos.isEmpty()) return false;
        Equipavel item = equipamentos.remove(0);
        item.equipar(heroi);
        return true;
    }
}

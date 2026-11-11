package rpg.personagens;

public class Arqueiro extends Heroi {
    public Arqueiro(String nome) { super(nome, 90, 35, 10); }

    @Override public int ataqueEspecial() { return atacar() + 15; }
    @Override public String getClasse() { return "Arqueiro"; }
}

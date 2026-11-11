package rpg.personagens;

public class Mago extends Heroi {
    public Mago(String nome) { super(nome, 70, 40, 5); }

    @Override public int ataqueEspecial() { return atacar() + 20; }
    @Override public String getClasse() { return "Mago"; }
}

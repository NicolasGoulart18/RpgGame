package rpg.personagens;

public class Guerreiro extends Heroi {
    public Guerreiro(String nome) { super(nome, 130, 30, 15); }

    @Override public int ataqueEspecial() { return atacar() + 10; }
    @Override public String getClasse() { return "Guerreiro"; }
}

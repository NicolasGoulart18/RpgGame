package rpg.personagens;

public abstract class Heroi extends Personagem implements HabilidadeEspecial {
    protected Heroi(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    public abstract String getClasse();
}

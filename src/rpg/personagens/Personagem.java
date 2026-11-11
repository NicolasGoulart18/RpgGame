package rpg.personagens;

/** Regras de vida, ataque e defesa compartilhadas por todos os combatentes. */
public abstract class Personagem {
    private final String nome;
    private final int vidaMaxima;
    private final int ataque;
    private int vida;
    private int defesa;

    protected Personagem(String nome, int vidaMaxima, int ataque, int defesa) {
        if (nome == null || nome.isBlank() || vidaMaxima <= 0 || ataque < 0 || defesa < 0) {
            throw new IllegalArgumentException("Atributos de personagem invalidos");
        }
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getDefesa() { return defesa; }
    public int atacar() { return ataque; }
    public boolean estaVivo() { return vida > 0; }

    /** Retorna o dano efetivamente sofrido, sem deixar a vida negativa. */
    public int receberDano(int dano, boolean defendendo) {
        int danoReal = Math.max(0, dano - defesa);
        if (defendendo) {
            danoReal = (danoReal + 1) / 2;
        }
        int vidaAnterior = vida;
        vida = Math.max(0, vida - danoReal);
        return vidaAnterior - vida;
    }

    /** Retorna a quantidade de pontos de vida efetivamente recuperada. */
    public int curar(int quantidade) {
        if (quantidade <= 0 || !estaVivo()) return 0;
        int vidaAnterior = vida;
        vida = (int) Math.min((long) vidaMaxima, (long) vida + quantidade);
        return vida - vidaAnterior;
    }

    public void aumentarDefesa(int bonus) {
        if (bonus <= 0) throw new IllegalArgumentException("Bonus deve ser positivo");
        defesa += bonus;
    }
}

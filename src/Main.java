public class Main {
    public static void main(String[] args) {
    Personagem guerreiro = new Guerreiro("Arthur");
    Personagem inimigo = new Goblin("Goblin");

    System.out.println("Inimigo: " + inimigo.getNome());
    System.out.println("Vida: " + inimigo.getVida());

    inimigo.receberDano(guerreiro.atacar());
    System.out.println("Saúde Goblin após ataque: "+inimigo.getVida());
    guerreiro.receberDano(inimigo.atacar());
    System.out.println("Vida do guerreiro após luta: "+guerreiro.getVida());
    }
}

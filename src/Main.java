public class Main {
    public static void main(String[] args) {
    Personagem guerreiro = new Guerreiro("Arthur");
    Personagem mago= new Mago("Pedro");
    Personagem arqueiro= new Arqueiro("Lucas");
    System.out.println("Guerreiro: ataque "+guerreiro.atacar()+" | especial: "+guerreiro.ataqueEspecial());
    System.out.println("Mago: ataque "+mago.atacar()+" | especial: "+mago.ataqueEspecial());
    System.out.println("Arqueiro: ataque "+arqueiro.atacar()+" | especial: "+arqueiro.ataqueEspecial());
    }
}

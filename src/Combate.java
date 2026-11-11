public class Combate {
    private Personagem heroi;
    private Personagem inimigo;
    public Combate(Personagem heroi,Personagem inimigo){
        this.heroi=heroi;
        this.inimigo=inimigo;
    }

   
    public void iniciarCombate(){
        while (heroi.estaVivo()&&inimigo.estaVivo()) {   
            executarRodada();    
        }
        if (heroi.estaVivo()) {
            System.out.println("Heroi venceu!");
        }else{
            System.out.println("Heroi foi derrotado!");
        }
    }

    public void executarRodada(){
        System.out.println("Ataque do heroi "+heroi.getNome());
        inimigo.receberDano(heroi.atacar());
        System.out.println(
            "Vida do inimigo: "+inimigo.getVida()
            
        );
        if (inimigo.estaVivo()) {
            System.out.println(inimigo.getNome()+" revida!");
            heroi.receberDano(inimigo.atacar());   
        }
        System.out.println("Vida do Heroi: "+heroi.getVida());
    }

    
}

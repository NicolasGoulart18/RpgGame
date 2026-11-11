public class Personagem {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private  int ataque;
    private int defesa;

    public Personagem(String nome,int vida,int ataque,int defesa){
        this.nome=nome;
        this.vida=vida;
        this.vidaMaxima=vida;
        this.ataque=ataque;
        this.defesa=defesa;
    }

    public int getVida() {
        return vida;
    }

    public void receberDano(int dano){
        int danoReal=Math.max(0,dano-this.defesa);
        this.vida=Math.max(0,this.vida- danoReal);
    }

    public void curar(int quantidade){
        this.vida=Math.min(
            this.vidaMaxima,
            this.vida+quantidade
             );
    }

    boolean estaVivo(){
        if(this.vida>0){
            return true;
        }else{
            return false;
        }
    }
}

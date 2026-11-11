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

    public int atacar(){
        return this.ataque;
    }

    public int ataqueEspecial(){
        return this.ataque;
    }


    public int getVida() {
        return vida;
    }

    public void receberDano(int dano){
        int danoReal=Math.max(0,dano-this.defesa);
        this.vida=Math.max(0,this.vida- danoReal);
    }

    public void curar(int quantidade){
        if (quantidade<=0) {
            return;
        }
        
        this.vida=Math.min(
            this.vidaMaxima,
            this.vida+quantidade
             );
    }

    public boolean estaVivo(){
        if(this.vida>0){
            return true;
        }else{
            return false;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public int getVidaMaxima() {
        return this.vidaMaxima;
    }
    

    
}

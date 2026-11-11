public class Mago extends Personagem{
    public Mago(String nome){
        super(nome,70,40,5);
    }

    @Override 
    public int atacar(){
        return super.atacar()+20;
    }
}

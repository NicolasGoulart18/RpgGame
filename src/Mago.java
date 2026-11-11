public class Mago extends Personagem{
    public Mago(String nome){
        super(nome,70,40,5);
    }

    @Override 
    public int ataqueEspecial(){
        return super.atacar()+20;
    }
}

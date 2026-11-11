public class Arqueiro extends Personagem {
    public Arqueiro(String nome){
        super(nome,90,35,10);
    }

    @Override
    public int atacar(){
        return super.atacar()+15;
    }
}

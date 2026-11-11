public class Guerreiro extends Personagem {
    public Guerreiro(String nome){
        super(nome, 130,30,15); // super é utilizado para acessar atributos privates da classe pai
    }

    @Override //sobrescrevendo um metodo da classe pai
    public int atacar(){
        return super.atacar()+10;
    }



}

package rpg.personagens;

public class Guerreiro extends Personagem {
    public Guerreiro(String nome){
        super(nome, 130,30,15); //chama o construtor da classe pai, que inicializa esses atributos.
    }

    @Override //sobrescrevendo um metodo da classe pai
    public int ataqueEspecial(){
        return super.atacar()+10;
    }



}

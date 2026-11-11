class Personagem{
    constructor(nome,vida,ataque,defesa){
        this.nome=nome;
        this.vida=vida;
        this.ataque=ataque;
        this.defesa=defesa;
    }

    receberDano(){
    this.vida -= dano;
    this.vida=Math.max(0,this.vida - dano);
}

// JavaScript=Math.max() -> retonar o maior valor entre os numeros informados

}


const heroi= new Personagem("Arthur",100,25,10)
heroi.receberDano(20);
console.log(heroi);
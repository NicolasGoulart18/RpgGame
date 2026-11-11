class Personagem{
    constructor(nome,vida,ataque,defesa){
        this.nome=nome;
        this.vida=vida;
        this.ataque=ataque;
        this.defesa=defesa;
    }

    receberDano(dano){
    this.vida=Math.max(0,this.vida - dano);
}

// JavaScript=Math.max() -> retonar o maior valor entre os numeros informados

}


const heroi= new Personagem("Arthur",100,25,10)
heroi.receberDano(120);
console.log(heroi);

const guerreiro = new Personagem("Shawnzo",130,40,5)
guerreiro.receberDano(30);
guerreiro.receberDano(50);
console.log(guerreiro);
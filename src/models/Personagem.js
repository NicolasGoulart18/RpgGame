class Personagem{

    #vida;
    #vidaMaxima;
    constructor(nome,vida,ataque,defesa){
        this.nome=nome;
        this.#vida=vida;
        this.#vidaMaxima=vida;
        this.ataque=ataque;
        this.defesa=defesa;
    }

    getVida(){
        return this.#vida;
    }

    receberDano(dano){
    const danoReal=Math.max(0,dano - this.defesa)
    this.#vida=Math.max(0, this.#vida - danoReal);
}

    curar(quantidade){
        this.#vida = Math.min(
            this.#vidaMaxima,
            this.#vida + quantidade
        );
    }

    estaVivo(){
        if (this.#vida>0){
            return true;
        }else{
            return false;
        }
    }

// JavaScript=Math.max() -> retonar o maior valor entre os numeros informados

}


const arthur= new Personagem("Arthur",100,25,10)
arthur.receberDano(80);
console.log("Após dano: ",arthur.getVida());
arthur.curar(40);
console.log("Após cura: ",arthur.getVida());


# Diagrama de classes UML

Representa **a versão atual** do código, com as relações de herança e dependência.

```mermaid
classDiagram
    class Main {
        +main(String[] args) void
    }

    class Personagem {
        -String nome
        -int vida
        -int vidaMaxima
        -int ataque
        -int defesa
        +Personagem(String, int, int, int)
        +atacar() int
        +ataqueEspecial() int
        +getVida() int
        +getVidaMaxima() int
        +getNome() String
        +receberDano(int) void
        +curar(int) void
        +estaVivo() boolean
    }

    class Guerreiro {
        +Guerreiro(String)
        +ataqueEspecial() int
    }
    class Mago {
        +Mago(String)
        +ataqueEspecial() int
    }
    class Arqueiro {
        +Arqueiro(String)
        +ataqueEspecial() int
    }
    class Goblin {
        +Goblin(String)
    }
    class Combate {
        -Personagem heroi
        -Personagem inimigo
        +Combate(Personagem, Personagem)
        +iniciarCombate(Scanner) void
        +executarRodada(int) void
    }

    Personagem <|-- Guerreiro
    Personagem <|-- Mago
    Personagem <|-- Arqueiro
    Personagem <|-- Goblin
    Combate --> Personagem : heroi / inimigo
    Main ..> Combate : instancia
    Main ..> Guerreiro : cria
    Main ..> Goblin : cria
```

**Observação:** o desenho será atualizado quando adicionarmos novas classes de inventário, interação no console e outras mecânicas. Não representa requisitos ainda não implementados.

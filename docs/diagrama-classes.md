# Diagrama de classes UML

O diagrama abaixo apresenta a estrutura **implementada** do RPG Game. É renderizado pelo GitHub via Mermaid.

~~~mermaid
classDiagram
  direction TB
  class Personagem {
    <<abstract>>
    -String nome
    -int vida
    -int vidaMaxima
    -int ataque
    -int defesa
    +getNome() String
    +getVida() int
    +getVidaMaxima() int
    +getDefesa() int
    +atacar() int
    +estaVivo() boolean
    +receberDano(int, boolean) int
    +curar(int) int
    +aumentarDefesa(int) void
  }
  class Heroi {
    <<abstract>>
    +getClasse() String
  }
  class Inimigo {
    <<abstract>>
  }
  class HabilidadeEspecial {
    <<interface>>
    +ataqueEspecial() int
  }
  class Guerreiro
  class Mago
  class Arqueiro
  class Goblin
  class Esqueleto
  class Dragao

  Personagem <|-- Heroi
  Personagem <|-- Inimigo
  HabilidadeEspecial <|.. Heroi
  Heroi <|-- Guerreiro
  Heroi <|-- Mago
  Heroi <|-- Arqueiro
  Inimigo <|-- Goblin
  Inimigo <|-- Esqueleto
  Inimigo <|-- Dragao

  class Usavel {
    <<interface>>
    +getNome() String
    +usar(Heroi) boolean
  }
  class Equipavel {
    <<interface>>
    +getNome() String
    +equipar(Heroi) void
  }
  class PocaoCura
  class EscudoReforcado
  Usavel <|.. PocaoCura
  Equipavel <|.. EscudoReforcado

  class Inventario {
    -List~Usavel~ consumiveis
    -List~Equipavel~ equipamentos
    +adicionar(Usavel) void
    +adicionar(Equipavel) void
    +usarPrimeiroConsumivel(Heroi) boolean
    +equiparPrimeiro(Heroi) boolean
  }
  Inventario o-- Usavel : armazena
  Inventario o-- Equipavel : armazena

  class InterfaceUsuario {
    <<interface>>
    +mostrar(String) void
    +perguntar(String) String
  }
  class ConsoleUI
  InterfaceUsuario <|.. ConsoleUI

  class ResultadoCombate {
    <<enumeration>>
    VITORIA
    DERROTA
    DESISTENCIA
  }
  class Combate {
    -Heroi heroi
    -Inimigo inimigo
    -Inventario inventario
    -InterfaceUsuario ui
    -int especiaisRestantes
    +iniciar() ResultadoCombate
    +executarRodada(int) boolean
  }
  Combate --> Heroi : usa
  Combate --> Inimigo : enfrenta
  Combate --> Inventario : consulta/consome
  Combate --> InterfaceUsuario : interage
  Combate ..> ResultadoCombate : retorna

  class Jogo {
    -InterfaceUsuario ui
    +iniciar() void
  }
  class Main {
    +main(String[]) void
  }
  Main ..> Jogo : inicia
  Main ..> ConsoleUI : instancia
  Jogo --> InterfaceUsuario : injeta
  Jogo ..> Combate : cria fases
~~~

**Legenda:** triângulo cheio indica herança; triângulo com linha tracejada indica implementação de interface; losango vazado indica agregação; seta simples indica dependência/associação.

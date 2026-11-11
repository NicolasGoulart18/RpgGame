# Arquitetura do RPG

Este documento descreve **o estado atual**, sem confundir funcionalidades planejadas com implementadas.

## Organização

- `rpg.Main`: ponto de entrada; cria os objetos iniciais e o `Scanner`.
- `rpg.personagens.Personagem`: armazena os atributos comuns e implementa as regras de vida, defesa e cura.
- `rpg.personagens.Guerreiro`, `Mago` e `Arqueiro`: herdam de `Personagem` e sobrescrevem `ataqueEspecial()`.
- `rpg.inimigos.Goblin`: herda de `Personagem`; usa o ataque comum.
- `rpg.combate.Combate`: recebe duas referências de `Personagem`, realiza as rodadas e atualmente também lê/escreve no console.

## Regras implementadas

1. O dano efetivo é `Math.max(0, dano - defesa)`.
2. A vida não fica abaixo de zero depois de sofrer dano.
3. A cura positiva não ultrapassa a vida máxima; valores de cura não positivos são ignorados.
4. O inimigo só revida caso sobreviva ao ataque.
5. A batalha termina quando um dos combatentes é derrotado.
6. Opção 1 aplica `atacar()`; opção 2 aplica `ataqueEspecial()`. Escolhas inválidas não realizam ataques.

## Avaliação dos princípios SOLID

| Princípio | Estado atual | Evidência / trabalho pendente |
| --- | --- | --- |
| **S — SRP** | Parcial | `Personagem` concentra atributos e regras do personagem, e `Main` inicializa o jogo. `Combate` ainda mistura regras com entrada e saída pelo terminal; separar essas responsabilidades. |
| **O — OCP** | Parcial | É possível adicionar subclasses com diferentes `ataqueEspecial()` sem alterar o cálculo base; o sistema ainda precisa ser avaliado ao adicionar novos comportamentos. |
| **L — LSP** | Parcial | O combate opera com `Personagem` e aceita subclasses. É necessário revisar contratos e pré-condições, especialmente o método `ataqueEspecial()` herdado por inimigos. |
| **I — ISP** | Pendente | Ainda não existem interfaces específicas para comportamentos opcionais, como usar itens ou executar habilidades especiais. |
| **D — DIP** | Pendente | A lógica de `Combate` ainda depende diretamente do `Scanner` e de `System.out`; planeja-se abstrair a interação com o console. |

## Decisões tomadas

- **Java em console:** corresponde ao formato do trabalho. O projeto começou em JavaScript, mas migrou para Java.
- **Pacotes pequenos:** separar personagens, inimigos e combate sem introduzir camadas desnecessárias.
- **Sem bibliotecas externas:** facilita compilar e apresentar na faculdade.
- **Documentação honesta:** o diagrama representa o código existente, e não classes futuras.

## Melhorias planejadas

- Separar interação com o terminal e lógica de combate para reforçar SRP e DIP.
- Definir interfaces para comportamentos opcionais (ISP) e revisar contratos (LSP).
- Adicionar inventário, poções, defesa, limitação de especiais, outros inimigos e chefão.
- Criar testes e capturas de tela reais para o README.

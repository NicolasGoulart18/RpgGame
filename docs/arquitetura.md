# Arquitetura e decisões técnicas

## Fluxo de execução

1. **Main** inicia ConsoleUI com Scanner e cria Jogo.
2. **Jogo** seleciona o Heroi, prepara o Inventario e apresenta três fases.
3. **Combate** recebe Heroi, Inimigo, Inventario e InterfaceUsuario; executa rodadas e retorna ResultadoCombate.
4. **Personagem** protege estado, aplica ataque, defesa e cura.
5. **Inventario** guarda itens através das interfaces Usavel e Equipavel.

## Regras verificáveis

- A vida começa no máximo. Dano nunca deixa a vida negativa; cura nunca ultrapassa a vida máxima.
- Dano normal: **max(0, ataque - defesa)**.
- Se defender: **(danoNormal + 1) / 2**, com divisão inteira; logo 5 vira 3.
- Ataques comuns/especiais causam dano; defender, usar poção e equipar escudo não atacam.
- O adversário revida após ação válida se continuar vivo.
- Cada objeto Combate libera até três especiais. Um novo combate reinicia essa quantidade.
- Poção cura até 30 HP e só é consumida se recuperar vida. Escudo adiciona +3 à defesa, uma única vez por exemplar.
- O jogador enfrenta Goblin (fase 1), Esqueleto Guerreiro (fase 2) e Dragão Ancião (fase 3). Vida/itens persistem; uma poção é concedida após cada uma das duas primeiras vitórias.

## SOLID aplicado

### S — SRP

**Personagem** altera atributos e mantém invariantes de vida/dano; **Inventario** controla a coleção de itens; **Combate** contém o algoritmo dos turnos; **Jogo** orquestra fases; **ConsoleUI** gerencia Scanner e saída. Nenhuma classe isolada precisa assumir todas essas responsabilidades.

### O — OCP

O método de Combate recebe um Heroi e um Inimigo por seus tipos-base, de forma que uma nova subclasse de Inimigo pode participar das mesmas rodadas. Inventario é independente da classe concreta dos objetos que implementam Usavel e Equipavel. O menu de seleção precisará de atualização para expor uma nova classe de herói, mas **as regras de combate** não precisam ser reescritas.

### L — LSP

Guerreiro, Mago e Arqueiro mantêm as regras de Personagem e cumprem o contrato HabilidadeEspecial. Goblin, Esqueleto e Dragao mantêm o contrato Inimigo/Personagem. Todos suportam receberDano, curar, atacar e estaVivo sem lançar exceções para métodos não suportados. A suíte faz combinações entre subclasses.

### I — ISP

HabilidadeEspecial é implementada somente por Heroi, não por inimigos. Usavel e Equipavel são contratos independentes: PocaoCura não implementa equipar, EscudoReforcado não implementa usar. Isso evita métodos artificiais ou UnsupportedOperationException.

### D — DIP

Em vez de ler Scanner diretamente, o motor recebe a interface InterfaceUsuario com os métodos mostrar e perguntar. Main fornece ConsoleUI para uso real; TestesRpg injeta uma UIEmMemoria, permitindo testar regras sem terminal nem entrada manual.

## Modelo de domínio

- **Abstração:** Personagem, Heroi e Inimigo abstratos.
- **Herança e polimorfismo:** classes concretas de heróis e inimigos.
- **Encapsulamento:** nome, vida, ataque e defesa controlados por métodos.
- **Composição/agregação:** Combate recebe seus colaboradores; Inventario mantém listas de Usavel e Equipavel.
- **Sem dependências de terceiros:** apenas JDK; código compatível com Java 17+.

Veja [diagrama de classes UML](diagrama-classes.md), [README e execução](../README.md) e [testes de regressão](../tests/rpg/TestesRpg.java).

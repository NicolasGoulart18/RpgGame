# RPG Game — Paradigmas de Programação

Projeto individual de RPG em **Java, executado no console**, para a disciplina de Paradigmas de Programação. O objetivo é praticar Programação Orientada a Objetos (POO) e evoluir o código para demonstrar os cinco princípios SOLID.

> **Em desenvolvimento:** a versão atual permite uma batalha em turnos entre um Guerreiro e um Goblin, com escolha de ataque comum ou especial. Inventário, ações adicionais, chefão e aplicação integral de SOLID ainda serão desenvolvidos.

## Funcionalidades atuais

- Classe base `Personagem`: vida atual e máxima, ataque, defesa, cura e verificação de sobrevivência.
- Herança: `Guerreiro`, `Mago`, `Arqueiro` e `Goblin`.
- Polimorfismo: cada herói sobrescreve `ataqueEspecial()`.
- Batalha em turnos, dano reduzido pela defesa e revide do inimigo.
- Menu textual com escolha entre ataque comum e especial e tratamento de opções inválidas.

## Requisitos

- **JDK 17 ou superior** (compilador `javac` e runtime `java`).
- Terminal (PowerShell, CMD ou shell). Nenhuma biblioteca externa é necessária.

## Compilar e executar

Na **raiz do repositório**, execute:

```bash
javac -encoding UTF-8 -d out -sourcepath src src/rpg/Main.java
java -cp out rpg.Main
```

No VS Code, também é possível abrir `src/rpg/Main.java` e executar o método `main` com a extensão Java.

Durante o combate: **1 = ataque comum**, **2 = ataque especial**. No momento, o jogador usa o Guerreiro Arthur contra um Goblin; a seleção de classe será adicionada depois.

## Estrutura de pastas

```text
src/
  rpg/
    Main.java                 # inicia o programa
    personagens/
      Personagem.java         # regras e dados compartilhados
      Guerreiro.java
      Mago.java
      Arqueiro.java
    inimigos/
      Goblin.java
    combate/
      Combate.java            # turnos, entradas e resultado da luta
docs/
  arquitetura.md              # decisões técnicas e SOLID: status real
  diagrama-classes.md         # diagrama UML (Mermaid)
```

## POO e SOLID

A modelagem de personagens demonstra encapsulamento, herança e polimorfismo. A classe `Combate` usa o tipo `Personagem`, em vez de depender especificamente de `Guerreiro` ou `Goblin`.

A aplicação de **todos os cinco princípios SOLID ainda não está concluída**. Veja o detalhamento em [Arquitetura e SOLID](docs/arquitetura.md), incluindo o que já existe e o que falta implementar. O [diagrama de classes UML](docs/diagrama-classes.md) mostra a estrutura atual.

## Documentação da execução

Capturas reais da execução no console serão adicionadas antes da entrega, após a conclusão das funcionalidades.

## Próximas etapas

- Defender, limitar especiais e utilizar poções / inventário.
- Escolha de classe e progressão de inimigos, com um chefão.
- Separar entrada/saída no console da regra de combate.
- Revisar os cinco princípios SOLID, testes, UML e capturas de tela.

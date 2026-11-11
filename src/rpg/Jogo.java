package rpg;

import rpg.combate.Combate;
import rpg.combate.ResultadoCombate;
import rpg.inimigos.Dragao;
import rpg.inimigos.Esqueleto;
import rpg.inimigos.Goblin;
import rpg.inimigos.Inimigo;
import rpg.inventario.Inventario;
import rpg.io.InterfaceUsuario;
import rpg.itens.EscudoReforcado;
import rpg.itens.PocaoCura;
import rpg.personagens.Arqueiro;
import rpg.personagens.Guerreiro;
import rpg.personagens.Heroi;
import rpg.personagens.Mago;

/** Orquestra a aventura; regras de turno ficam em Combate. */
public class Jogo {
    private final InterfaceUsuario ui;

    public Jogo(InterfaceUsuario ui) {
        if (ui == null) throw new IllegalArgumentException("Interface obrigatoria");
        this.ui = ui;
    }

    public void iniciar() {
        ui.mostrar("=================================");
        ui.mostrar("      RPG GAME - AVENTURA        ");
        ui.mostrar("=================================");
        Heroi heroi = escolherHeroi();
        if (heroi == null) {
            ui.mostrar("Jogo encerrado.");
            return;
        }

        Inventario inventario = new Inventario();
        for (int i = 0; i < 3; i++) inventario.adicionar(new PocaoCura());
        inventario.adicionar(new EscudoReforcado());
        ui.mostrar("\nBem-vindo, " + heroi.getNome() + "! Classe: " + heroi.getClasse());
        ui.mostrar("Mochila inicial: 3 pocoes (+30 HP cada) e 1 escudo (+3 defesa).");
        ui.mostrar("Derrote dois inimigos e vença o chefao para concluir sua jornada!");

        Inimigo[] inimigos = { new Goblin(), new Esqueleto(), new Dragao() };
        for (int i = 0; i < inimigos.length; i++) {
            ui.mostrar("\n======== FASE " + (i + 1) + "/" + inimigos.length + " ========");
            ResultadoCombate resultado = new Combate(heroi, inimigos[i], inventario, ui).iniciar();
            if (resultado != ResultadoCombate.VITORIA) {
                if (resultado == ResultadoCombate.DERROTA) ui.mostrar("FIM DE JOGO. Tente outra estrategia!");
                return;
            }
            if (i < inimigos.length - 1) {
                inventario.adicionar(new PocaoCura());
                ui.mostrar("Recompensa da fase: 1 pocao de cura!");
            }
        }
        ui.mostrar("\n*** PARABENS! VOCE SALVOU O REINO! ***");
        ui.mostrar("Aventura concluida com " + heroi.getVida() + " HP restantes!");
    }

    private Heroi escolherHeroi() {
        ui.mostrar("\nEscolha sua classe:");
        ui.mostrar("1 - Guerreiro (HP 130, Ataque 30, Defesa 15, Especial 40)");
        ui.mostrar("2 - Mago      (HP 70,  Ataque 40, Defesa 5,  Especial 60)");
        ui.mostrar("3 - Arqueiro  (HP 90,  Ataque 35, Defesa 10, Especial 50)");
        ui.mostrar("0 - Sair");
        while (true) {
            String escolha = ui.perguntar("Sua classe: ");
            if (escolha == null || escolha.equals("0")) return null;
            if (!escolha.equals("1") && !escolha.equals("2") && !escolha.equals("3")) {
                ui.mostrar("Escolha 1, 2, 3 ou 0.");
                continue;
            }
            String nome = ui.perguntar("Nome do heroi: ");
            if (nome == null) return null;
            if (nome.isBlank()) nome = "Arthur";
            switch (escolha) {
                case "1": return new Guerreiro(nome);
                case "2": return new Mago(nome);
                default: return new Arqueiro(nome);
            }
        }
    }
}

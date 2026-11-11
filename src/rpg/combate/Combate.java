package rpg.combate;

import rpg.inimigos.Inimigo;
import rpg.inventario.Inventario;
import rpg.io.InterfaceUsuario;
import rpg.personagens.Heroi;

/** Regras do combate em turnos; nao utiliza Scanner nem System.out. */
public class Combate {
    private final Heroi heroi;
    private final Inimigo inimigo;
    private final Inventario inventario;
    private final InterfaceUsuario ui;
    private int especiaisRestantes = 3;

    public Combate(Heroi heroi, Inimigo inimigo, Inventario inventario, InterfaceUsuario ui) {
        if (heroi == null || inimigo == null || inventario == null || ui == null) {
            throw new IllegalArgumentException("Dependencias de combate invalidas");
        }
        this.heroi = heroi;
        this.inimigo = inimigo;
        this.inventario = inventario;
        this.ui = ui;
    }

    public ResultadoCombate iniciar() {
        ui.mostrar("\n===== COMBATE: " + heroi.getNome() + " vs " + inimigo.getNome() + " =====");
        while (heroi.estaVivo() && inimigo.estaVivo()) {
            mostrarMenu();
            String entrada = ui.perguntar("Sua escolha: ");
            if (entrada == null || entrada.equals("0")) {
                ui.mostrar("Voce abandonou a aventura.");
                return ResultadoCombate.DESISTENCIA;
            }
            int opcao;
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                ui.mostrar("Opcao invalida. Digite um numero de 0 a 5.");
                continue;
            }
            executarRodada(opcao);
        }
        ResultadoCombate resultado = heroi.estaVivo() ? ResultadoCombate.VITORIA : ResultadoCombate.DERROTA;
        ui.mostrar(resultado == ResultadoCombate.VITORIA
                ? "VITORIA! " + inimigo.getNome() + " foi derrotado."
                : "DERROTA! " + heroi.getNome() + " caiu em batalha.");
        return resultado;
    }

    private void mostrarMenu() {
        ui.mostrar("\n--- SEU TURNO ---");
        ui.mostrar(heroi.getNome() + " (" + heroi.getClasse() + "): " + heroi.getVida()
                + "/" + heroi.getVidaMaxima() + " HP | Defesa: " + heroi.getDefesa());
        ui.mostrar(inimigo.getNome() + ": " + inimigo.getVida() + "/" + inimigo.getVidaMaxima() + " HP");
        ui.mostrar("1 - Ataque comum");
        ui.mostrar("2 - Ataque especial (" + especiaisRestantes + " restantes)");
        ui.mostrar("3 - Defender (metade do dano restante)");
        ui.mostrar("4 - Usar pocao (" + inventario.getQuantidadeConsumiveis() + " restantes)");
        ui.mostrar("5 - Equipar escudo (" + inventario.getQuantidadeEquipamentos() + " restantes)");
        ui.mostrar("0 - Sair do jogo");
    }

    /** Retorna true somente se a acao consumiu um turno. */
    public boolean executarRodada(int opcao) {
        if (!heroi.estaVivo() || !inimigo.estaVivo()) return false;
        boolean defendendo = false;
        int dano = 0;
        switch (opcao) {
            case 1:
                dano = heroi.atacar();
                ui.mostrar(heroi.getNome() + " usou ataque comum!");
                break;
            case 2:
                if (especiaisRestantes == 0) {
                    ui.mostrar("Nao ha ataques especiais restantes!");
                    return false;
                }
                dano = heroi.ataqueEspecial();
                especiaisRestantes--;
                ui.mostrar(heroi.getNome() + " usou ataque especial!");
                break;
            case 3:
                defendendo = true;
                ui.mostrar(heroi.getNome() + " adotou postura defensiva!");
                break;
            case 4:
                int vidaAnterior = heroi.getVida();
                if (!inventario.usarPrimeiroConsumivel(heroi)) {
                    ui.mostrar("Nao foi possivel usar a pocao (sem itens ou HP cheio).");
                    return false;
                }
                ui.mostrar("Pocao utilizada: +" + (heroi.getVida() - vidaAnterior) + " HP!");
                break;
            case 5:
                if (!inventario.equiparPrimeiro(heroi)) {
                    ui.mostrar("Voce nao possui equipamento disponivel!");
                    return false;
                }
                ui.mostrar("Escudo equipado! Defesa atual: " + heroi.getDefesa());
                break;
            default:
                ui.mostrar("Opcao invalida. Escolha de 0 a 5.");
                return false;
        }

        if (opcao == 1 || opcao == 2) {
            int vidaAnterior = inimigo.getVida();
            inimigo.receberDano(dano, false);
            ui.mostrar(inimigo.getNome() + " recebeu " + (vidaAnterior - inimigo.getVida())
                    + " de dano. HP: " + inimigo.getVida());
        }

        if (inimigo.estaVivo()) {
            int danoSofrido = heroi.receberDano(inimigo.atacar(), defendendo);
            ui.mostrar(inimigo.getNome() + " revidou: " + danoSofrido
                    + " de dano. Seu HP: " + heroi.getVida());
        }
        return true;
    }
}

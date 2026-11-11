package rpg;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import rpg.combate.Combate;
import rpg.combate.ResultadoCombate;
import rpg.inimigos.Dragao;
import rpg.inimigos.Esqueleto;
import rpg.inimigos.Goblin;
import rpg.inventario.Inventario;
import rpg.io.InterfaceUsuario;
import rpg.itens.EscudoReforcado;
import rpg.itens.PocaoCura;
import rpg.personagens.Arqueiro;
import rpg.personagens.Guerreiro;
import rpg.personagens.Heroi;
import rpg.personagens.Mago;

/** Testes sem dependencias externas. Execute: java -cp out rpg.TestesRpg */
public class TestesRpg {
    private static int total;
    private static void check(boolean ok, String mensagem) {
        total++;
        if (!ok) throw new AssertionError("Teste falhou: " + mensagem);
    }
    private static class UIEmMemoria implements InterfaceUsuario {
        Queue<String> entradas = new ArrayDeque<>();
        StringBuilder saida = new StringBuilder();
        UIEmMemoria(String... valores) { entradas.addAll(Arrays.asList(valores)); }
        @Override public void mostrar(String msg) { saida.append(msg).append('\n'); }
        @Override public String perguntar(String msg) {
            saida.append(msg);
            return entradas.poll();
        }
    }

    public static void main(String[] args) {
        Heroi guerreiro = new Guerreiro("Arthur");
        check(guerreiro.getVida() == 130, "vida inicial");
        check(guerreiro.receberDano(20, false) == 5, "dano com defesa");
        check(guerreiro.getVida() == 125, "vida apos golpe");
        check(guerreiro.receberDano(20, true) == 3, "defesa arredonda para cima");
        check(guerreiro.getVida() == 122, "vida apos defender");
        check(guerreiro.curar(500) == 8, "cura respeita teto");
        check(guerreiro.getVida() == 130, "vida maxima");
        check(guerreiro.receberDano(-100, false) == 0, "dano negativo nao cura");
        check(new Guerreiro("G").ataqueEspecial() == 40, "especial guerreiro");
        check(new Mago("M").ataqueEspecial() == 60, "especial mago");
        check(new Arqueiro("A").ataqueEspecial() == 50, "especial arqueiro");

        Inventario inventario = new Inventario();
        inventario.adicionar(new PocaoCura());
        inventario.adicionar(new EscudoReforcado());
        check(!inventario.usarPrimeiroConsumivel(guerreiro), "pocao nao gasta em vida cheia");
        check(inventario.getQuantidadeConsumiveis() == 1, "pocao ainda existe");
        guerreiro.receberDano(50, false);
        check(inventario.usarPrimeiroConsumivel(guerreiro), "pocao recupera HP");
        check(inventario.getQuantidadeConsumiveis() == 0, "pocao consumida");
        check(inventario.equiparPrimeiro(guerreiro), "equipar escudo");
        check(guerreiro.getDefesa() == 18, "bonus de defesa");
        check(!inventario.equiparPrimeiro(guerreiro), "sem escudo duplicado");

        // LSP: quaisquer subclasses de inimigo entram em combate.
        check(new Goblin().getVidaMaxima() == 60, "goblin");
        check(new Esqueleto().getVidaMaxima() == 105, "esqueleto");
        check(new Dragao().getVidaMaxima() == 160, "chefao");
        check(new Goblin("Inimigo personalizado").getNome().equals("Inimigo personalizado"), "nome goblin");

        UIEmMemoria ui = new UIEmMemoria();
        Combate combate = new Combate(new Guerreiro("Arthur"), new Goblin(), new Inventario(), ui);
        check(!combate.executarRodada(99), "escolha invalida nao consome turno");
        check(combate.executarRodada(3), "defender consome turno");
        check(ui.saida.toString().contains("Seu HP: 127"), "defesa reduz dano");
        check(combate.executarRodada(2), "especial 1");
        check(combate.executarRodada(2), "especial 2");
        check(!combate.executarRodada(2), "combate encerrado bloqueia mais turnos");

        UIEmMemoria ioDragao = new UIEmMemoria("2", "2", "2", "2", "0");
        Combate combateDragao = new Combate(new Guerreiro("A"), new Dragao(), new Inventario(), ioDragao);
        check(combateDragao.iniciar() == ResultadoCombate.DESISTENCIA, "saida do combate");
        check(ioDragao.saida.toString().contains("Nao ha ataques especiais restantes!"),
                "limite de tres especiais por combate");

        UIEmMemoria ioPocao = new UIEmMemoria();
        Inventario mochila = new Inventario();
        mochila.adicionar(new PocaoCura());
        Combate combatePocao = new Combate(new Guerreiro("A"), new Goblin(), mochila, ioPocao);
        check(!combatePocao.executarRodada(4), "vida cheia nao consome turno");
        check(mochila.getQuantidadeConsumiveis() == 1, "pocao preservada");
        check(combatePocao.executarRodada(3), "defender para perder vida");
        check(combatePocao.executarRodada(4), "usar pocao consome turno");
        check(mochila.getQuantidadeConsumiveis() == 0, "pocao consumida em combate");
        check(!combatePocao.executarRodada(4), "nao usar pocao inexistente");

        UIEmMemoria campanha = new UIEmMemoria("1", "Arthur",
                "2", "2", "2", "2", "2", "1",
                "2", "2", "2", "1", "1", "1", "1");
        new Jogo(campanha).iniciar();
        check(campanha.saida.toString().contains("PARABENS! VOCE SALVOU O REINO!"),
                "campanha completa com chefao derrotado");
        check(campanha.saida.toString().contains("FASE 3/3"), "progresso entre fases");
        for (Heroi hero : new Heroi[] { new Guerreiro("G"), new Mago("M"), new Arqueiro("A") }) {
            for (rpg.inimigos.Inimigo inim : new rpg.inimigos.Inimigo[] {
                    new Goblin(), new Esqueleto(), new Dragao() }) {
                UIEmMemoria abandonar = new UIEmMemoria("0");
                check(new Combate(hero, inim, new Inventario(), abandonar).iniciar()
                        == ResultadoCombate.DESISTENCIA, "LSP: subclasses no combate");
            }
        }
        UIEmMemoria classeInvalida = new UIEmMemoria("8", "3", "", "0");
        new Jogo(classeInvalida).iniciar();
        check(classeInvalida.saida.toString().contains("Escolha 1, 2, 3 ou 0."),
                "valida selecao de classe");
        check(classeInvalida.saida.toString().contains("Classe: Arqueiro"),
                "escolhe arqueiro e nome padrao");
        UIEmMemoria saiDireto = new UIEmMemoria("0");
        new Jogo(saiDireto).iniciar();
        check(saiDireto.saida.toString().contains("Jogo encerrado"), "saida no menu");
        System.out.println("OK: " + total + " verificacoes passaram.");
    }
}

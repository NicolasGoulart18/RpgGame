package rpg.io;

/** Abstracao para entrada e saida: a logica nao depende de Scanner (DIP). */
public interface InterfaceUsuario {
    void mostrar(String mensagem);
    String perguntar(String mensagem);
}

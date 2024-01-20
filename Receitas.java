import java.time.LocalDate;

public class Receitas extends Valores {

  private int tipo;

  public Receitas(String nome, double valor, LocalDate data, int tipo) {
    super(nome, valor, data);
    this.tipo = tipo;
  }

  public int getTipo() {
    return tipo;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

}

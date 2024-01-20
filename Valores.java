import java.time.LocalDate;

public class Valores {

  private String nome;
  private double valor;
  private LocalDate data;

  public Valores(String nome, double valor, LocalDate data) {
    this.nome = nome;
    this.valor = valor;
    this.data = data;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

}

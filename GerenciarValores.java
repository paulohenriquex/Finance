import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GerenciarValores {

  public void gerenciarValores(Scanner sc, Connection con, int opcao) {

    do {

      if (opcao == 1) {
        System.out.println("Gerenciar receita");
        cadastrarValores(sc, con, opcao);
        break;
      } else if (opcao == 2) {
        System.out.println("Gerenciar despesa");
        cadastrarValores(sc, con, opcao);
        break;
      } else if (opcao == 3) {
        System.out.println("########## Mostrar Valores ##########");
        System.out.println("1 - Receitas");
        System.out.println("2 - Despesas");
        System.out.println("0 - Sair");
        System.out.println("##############################");
        int op = sc.nextInt();
        sc.nextLine();

        if (op == 0) {
          break;
        }
        imprimirValores(sc, con, op);
      } else if (opcao == 0) {
        System.out.println("Sair");
        break;
      } else {
        System.out.println("Opção inválida");
      }
    } while (opcao != 0);
  }

  public void cadastrarValores(Scanner sc, Connection con, int opcao) {

    String nome;
    double valor;
    String datastr;
    LocalDate data;
    String tipo;

    System.out.println("Nome da " + (opcao == 1 ? "receita" : "despesa") + " ou (sair) para sair ");
    nome = sc.nextLine();

    if (!nome.equals("sair")) {

      System.out.println("Valor da " + (opcao == 1 ? "receita" : "despesa") + ": ");
      valor = sc.nextDouble();
      sc.nextLine();

      try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Data da " + (opcao == 1 ? "receita" : "despesa") + ": ");
        datastr = sc.nextLine();
        data = LocalDate.parse(datastr, formatter);

        if (data.isBefore(LocalDate.now())) {
          System.out.println("Data inválida");
          return;
        }

        tipo = (opcao == 1) ? "receita" : "despesa";

        cadastrarNoBanco(con, nome, valor, data, tipo);

      } catch (Exception e) {
        System.out.println("Data inválida");
      }
    }

  }

  private void cadastrarNoBanco(Connection con, String nome, double valor, LocalDate data, String tipo) {

    try {
      String tabela = (tipo.equals("receita")) ? "receita" : "despesa";
      String sql = "INSERT INTO " + tabela + "(nome,valor,data,tipo) VALUES(?,?,?,?)";

      try (PreparedStatement prep = con.prepareStatement(sql)) {
        prep.setString(1, nome);
        prep.setDouble(2, valor);
        Date datasql = Date.valueOf(data);
        prep.setDate(3, datasql);
        prep.setString(4, tipo);
        prep.executeUpdate();
        System.out.println(tipo + " inserida com sucesso.");
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Erro ao inserir dados.");
    }
  }

  public void imprimirValores(Scanner sc, Connection con, int op) {

    double total = 0;
    String tipo = "";

    try {
      tipo = (op == 1) ? "receita" : "despesa";
      String sql = "SELECT * FROM " + tipo;

      try (PreparedStatement prep = con.prepareStatement(sql)) {
        ResultSet rs = prep.executeQuery();

        while (rs.next()) {
          String nome = rs.getString("nome");
          double valor = rs.getDouble("valor");
          Date data = rs.getDate("data");
          total += valor;

          System.out.println("Nome: " + nome);
          System.out.println("Valor: " + valor);
          System.out.println("Data: " + data);
          System.out.println("");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Erro ao buscar dados.");
    }
    System.out.println("Total: " + tipo + " é: " + total);
    System.out.println("");
  }
}
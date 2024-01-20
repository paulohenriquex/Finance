import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;

public class Principal {

  public static void main(String[] args) {

    int opcao = 0;
    Scanner sc = new Scanner(System.in);
    String url = "jdbc:postgresql://localhost:5432/Balanco";
    String usuario = "postgres";
    String senha = "121255qq";

    GerenciarValores gerReceita = new GerenciarValores();

    try {
      Class.forName("org.postgresql.Driver");
      Connection con = DriverManager.getConnection(url, usuario, senha);

      do {
        System.out.println("########## Principal ##########");
        System.out.println("1 - Gerenciar Receita");
        System.out.println("2 - Gerenciar Despesa");
        System.out.println("3 - Imprimir Balanço");
        System.out.println("0 - Sair");
        System.out.println("##############################");
        System.out.println("");
        opcao = sc.nextInt();
        sc.nextLine();

        if (opcao == 1) {
          gerReceita.gerenciarValores(sc, con, opcao);
        } else if (opcao == 2) {
          gerReceita.gerenciarValores(sc, con, opcao);
        } else if (opcao == 3) {
          gerReceita.gerenciarValores(sc, con, opcao);
        } else if (opcao == 0) {
          System.out.println("Saindo");
          break;
        } else {
          System.out.println("Opção inválida");
        }

      } while (opcao != 0);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      sc.close();
    }
  }

}
import java.util.Scanner;

public class app {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    String sair;

    do {

      System.out.println("1 - Receita");
      System.out.println("2 - Sair");
      sair = sc.nextLine();

    } while (!sair.equals("sair"));
  }

}

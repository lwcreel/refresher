import java.util.Scanner;

public class UserInput {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter a Number: ");
    Integer myInt = scanner.nextInt();
    System.out.println("Is Your Number " + myInt.toString() + "?\nY or N");
    String answer = scanner.next();

    if (answer.equalsIgnoreCase("Y")) {
      System.out.println("Hooray!");
    } else {
      System.out.println("LIAR!");
    }

    scanner.close();
  }
}

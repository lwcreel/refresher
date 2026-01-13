import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class READ {
  public static void main(String[] args) {

    try {

      BufferedReader br =
          new BufferedReader(new FileReader("../resources/READ_THIS.txt"));
      Vector<Integer> numbers = new Vector<>();

      String curr = br.readLine();

      while (curr != null) {
        numbers.add(Integer.parseInt(curr));
        curr = br.readLine();
      }

      br.close();

      for (Integer num : numbers) {
        System.out.println(num);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

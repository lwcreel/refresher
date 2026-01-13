import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {

  public static void main(String[] args) {

    try {

      BufferedReader br =
          new BufferedReader(new FileReader("../resources/MOCK_DATA.csv"));

      String in = br.readLine();
      while (in != null) {
        System.out.println(in);
        in = br.readLine();
      }

      br.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

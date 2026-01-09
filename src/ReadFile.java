import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReadFile {

  public static void main(String[] args) throws Exception {
    System.out.println(readFile("../resources/lorem.txt"));
    System.out.println(
        readFile("../resources/MOCK_DATA.csv", Boolean.TRUE).toString());
  }

  public static HashMap readFile(String fileLocation, Boolean isUserData)
      throws Exception {

    HashMap<Integer, User> users = new HashMap<>();
    BufferedReader br = new BufferedReader(new FileReader(fileLocation));

    try {

      // headers
      String line = br.readLine();

      while (line != null) {
        line = br.readLine();
        if (line == null)
          break;
        List<String> elements = Arrays.asList(line.split(","));

        users.put(Integer.parseInt(elements.get(0)),
                  new User(elements.get(1), elements.get(2), elements.get(3),
                           elements.get(4), elements.get(5)));
      }
    } catch (Exception e) {
      System.err.println(e);
    } finally {
      br.close();
    }

    return users;
  }

  public static String readFile(String fileLocation) throws Exception {

    BufferedReader br = new BufferedReader(new FileReader(fileLocation));

    try {

      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      return sb.toString();
    } catch (Exception e) {
      System.err.println(e);
    } finally {
      br.close();
    }

    return "";
  }
}

public record User(String firstName, String lastName, String email,
                   String gender, String ipAddress) {}
;

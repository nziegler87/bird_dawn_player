import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class BookKeeperModelImpl implements IBookKeeperModel {

  @Override
  public void addBook(long... isbn) {

  }

  @Override
  public void retrieveBook(long isbn) {

    // url for request
    String apiURL = "https://www.googleapis.com/books/v1/volumes?q=isbn:"
            + Long.toBinaryString(isbn);

    // create a client
    HttpClient client = HttpClient.newHttpClient();

    // create a request
    HttpRequest request = HttpRequest.newBuilder(URI.create(apiURL)).header("accept", "application/json").build();

    // use the client to send the request
    var response = client.send(request, new JsonBodyHandler<>(APOD.class));
    }
}

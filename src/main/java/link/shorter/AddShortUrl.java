package link.shorter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import link.shorter.db.Url;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;


@RestController
public class AddShortUrl {
    private static String URL_PREFIX = "http://localhost:8080/url/";

    @PostMapping(value="/url/new")
    public static String addShortUrl(@RequestBody Map<String, String> payload) {
        String uniqString = Url.set(payload.get("url"));

        if (uniqString == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Не удалось сгенерировать короткую ссылку"
            );
        }
        return URL_PREFIX + uniqString;
    }
}

package link.shorter;

import link.shorter.db.Url;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RemoveUrl {

    @DeleteMapping(value="/url/delete/{url}")
    public static String deleteUrl(@PathVariable String url) {
        String value = Url.delete(url);

        if (value == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Не удалось удалить ссылку"
            );
        }

        return value;
    }
}

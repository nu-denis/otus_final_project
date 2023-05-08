package link.shorter;

import link.shorter.db.Url;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class GetUrl {

    @GetMapping(value="/url/{shortUrl}")
    public static RedirectView addShortUrl(@PathVariable  String shortUrl) {
        String originalUrl = Url.get(shortUrl);

        if (originalUrl == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Не удалось получить оригинальную ссылку"
            );
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(originalUrl);
        return redirectView;
    }
}

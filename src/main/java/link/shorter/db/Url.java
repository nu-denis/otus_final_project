package link.shorter.db;

import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class Url {
    private static final Logger LOG = LoggerFactory.getLogger(Url.class);
    private static final int MAX_ITERATES = 100;

    private static String generateUniqString() {int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String uniqString = RandomStringUtils.random(length, useLetters, useNumbers);

        int i = 1;
        while (get(uniqString) != null) {
            uniqString = RandomStringUtils.random(length, useLetters, useNumbers);
            if (Objects.equals(i, MAX_ITERATES)) {
                throw new DbException("Не удалось сгенерировать значение");
            }
            i++;
        }

        return uniqString;
    }

    public static String get(String uniqValue) {
        try{
            LOG.info(uniqValue);
            try (Jedis jedis = DbConnection.pool.getResource()) {
                String originalUrl = jedis.get(uniqValue);
                return originalUrl;
            }
        } catch (Exception ex) {
            LOG.error(String.valueOf(new DbException(ex)));
            return null;
        }
    }

    public static String set(String originalUrl) {
        try {
            try (Jedis jedis = DbConnection.pool.getResource()) {
                String uniqString = generateUniqString();
                jedis.set(uniqString, String.valueOf(originalUrl));
                return uniqString;
            }
        } catch (Exception ex) {
            LOG.error(String.valueOf(new DbException(ex)));
            return null;
        }
    }

    public static String delete(String shortUrl) {
        try{
            try (Jedis jedis = DbConnection.pool.getResource()) {
                jedis.del(shortUrl);
                return "Ok";
            }
        } catch (Exception ex) {
            LOG.error(String.valueOf(new DbException(ex)));
            return null;
        }
    }
}

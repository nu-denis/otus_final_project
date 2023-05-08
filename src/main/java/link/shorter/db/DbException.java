package link.shorter.db;

import redis.clients.jedis.exceptions.JedisException;

public class DbException extends JedisException {
    public DbException(Exception ex) {
        super(ex);
    }

    public DbException(String message) {
        super(message);
    }
}

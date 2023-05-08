package link.shorter.db;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class DbConnection {
    private static String CONNECTION_URL = "redis://localhost:6379";

    public static final JedisPool pool = new JedisPool(new JedisPoolConfig(), CONNECTION_URL);
}

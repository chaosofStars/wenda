package com.nowcoder.wenda.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nowcoder.wenda.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

@Service
public class JedisAdapter implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);
    private JedisPool pool;

    public static void print(int index, Object object) {
        System.out.println(String.format("%d, %s", index, object.toString()));
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("redis://localhost:6379/9");
        jedis.flushAll();

//        //get set
//        jedis.set("hello", "world");
//        print(1, jedis.get("hello"));
//        jedis.rename("hello", "newHello");
//        print(1, jedis.get("newHello"));
//        jedis.setex("hello2", 15, "world");
//
//        //string
//        jedis.set("pv", "100");
//        jedis.incr("pv");
//        print(2, jedis.get("pv"));
//        jedis.incrBy("pv", 5);
//        print(3, jedis.get("pv"));
//        jedis.decrBy("pv", 2);
//        print(4, jedis.get("pv"));
//
//        print(5, jedis.keys("*"));
//

        //list
//        String listName = "list";
//        jedis.del(listName);
//        for (int i = 0; i < 10; i++) {
//            jedis.lpush(listName, "a" + i);
//        }
//        print(1, jedis.lrange(listName, 0, 10));
//        print(2, jedis.llen(listName));
//        print(3, jedis.lpop(listName));
//        print(4, jedis.llen(listName));
//        print(5, jedis.lindex(listName, 3));
//        print(6, jedis.lrange(listName, 0, 10));
//        print(7, jedis.linsert(listName, BinaryClient.LIST_POSITION.BEFORE, "a4", "xx"));
//        print(8, jedis.lrange(listName, 0, 10));
//
//        print(9, jedis.linsert(listName, BinaryClient.LIST_POSITION.AFTER, "a4", "bb"));
//        print(8, jedis.lrange(listName, 0, 10));
//

        //hash
//        String userKey = "userxx";
//        jedis.hset(userKey, "name", "jim");
//        jedis.hset(userKey, "age", "12");
//        jedis.hset(userKey, "phone", "1234");
//        print(1, jedis.hget(userKey, "name"));
//        print(2, jedis.hgetAll(userKey));
//        jedis.hdel(userKey, "phone");
//        print(3, jedis.hgetAll(userKey));
//        print(4, jedis.hexists(userKey, "email"));
//        print(5, jedis.hexists(userKey, "age"));
//        print(6, jedis.hkeys(userKey));
//        print(7, jedis.hvals(userKey));
//        print(8, jedis.keys(userKey));
//        jedis.hsetnx(userKey, "school", "zju");
//        jedis.hsetnx(userKey, "name", "yxy");
//        print(19, jedis.hgetAll(userKey));

        //set
//        String likeKey1 = "commentLike1";
//        String likeKey2 = "commentLike2";
//        for (int i = 0; i < 10; i++) {
//            jedis.sadd(likeKey1, String.valueOf(i));
//            jedis.sadd(likeKey2, String.valueOf(i*i));
//        }
//        print(0, jedis.smembers(likeKey1));
//        print(1, jedis.smembers(likeKey2));
//        print(2, jedis.sunion(likeKey1, likeKey2));
//        print(3, jedis.sdiff(likeKey1, likeKey2));
//        print(4, jedis.sinter(likeKey1, likeKey2));
//        print(5,jedis.sismember(likeKey1,"12"));
//        print(6, jedis.sismember(likeKey2, "16"));
//        jedis.srem(likeKey1, "5");
//        print(7,jedis.smembers(likeKey1));
//        jedis.smove(likeKey2, likeKey1, "25");
//        print(8, jedis.smembers(likeKey1));
//        print(9, jedis.scard(likeKey1));

        //
//        String rankKey = "rankKey";
//        jedis.zadd(rankKey, 15, "jim");
//        jedis.zadd(rankKey, 60, "Ben");
//        jedis.zadd(rankKey, 90, "Lee");
//        jedis.zadd(rankKey, 75, "Lucy");
//        jedis.zadd(rankKey, 80, "Mei");
//        print(0, jedis.zcard(rankKey));
//        print(1, jedis.zcount(rankKey, 61, 100));
//        print(2, jedis.zscore(rankKey, "Lucy"));
//        jedis.zincrby(rankKey, 2, "Lucy");
//        print(3, jedis.zscore(rankKey, "Lucy"));
////        jedis.zincrby(rankKey, 2, "Luc");
////        print(4, jedis.zscore(rankKey, "Luc"));
//        print(5, jedis.zrange(rankKey, 0, 100));
//        print(6, jedis.zrange(rankKey, 1, 3));
//        print(7, jedis.zrevrange(rankKey, 1, 3));
//        for (Tuple tuple : jedis.zrangeByScoreWithScores(rankKey, "60", "100")) {
//            print(8, tuple.getElement() + ":" + String.valueOf(tuple.getScore()));
//        }
//
//        print(9, jedis.zrank(rankKey, "Lee"));
//        print(9, jedis.zrevrank(rankKey, "Lee"));

//        String setKey = "zset";
//        jedis.zadd(setKey, 1, "a");
//        jedis.zadd(setKey, 1, "b");
//        jedis.zadd(setKey, 1, "c");
//        jedis.zadd(setKey, 1, "d");
//        jedis.zadd(setKey, 1, "e");
//
//        print(1, jedis.zlexcount(setKey, "[b", "[d"));
//        print(1, jedis.zlexcount(setKey, "(b", "[d"));
//        jedis.zrem(setKey, "b");
//        print(2, jedis.zrange(setKey, 0, 10));
//        jedis.zremrangeByLex(setKey, "(c", "+");
//        print(3, jedis.zrange(setKey, 0, 10));

//        JedisPool pool = new JedisPool();
//        for (int i = 0; i < 100; i++) {
//            Jedis j = pool.getResource();
//            print(45, j.get("pv"));
//            j.close();
//        }

//        User user = new User();
//        user.setName("xx");
//        user.setPassword("ppp");
//        user.setHeadUrl("a.png");
//        user.setSalt("salt");
//        user.setId(1);
//        print(1, JSONObject.toJSONString(user));
//        jedis.set("user1", JSONObject.toJSONString(user));
//
//        String value = jedis.get("user1");
//        User user2 = JSON.parseObject(value, User.class);
//        print(2, user2);
        //

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("redis://localhost:6379/10");

    }

    public long sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public long srem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.srem(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }



    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }
}

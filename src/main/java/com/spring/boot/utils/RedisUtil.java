package com.spring.boot.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

@Component
public class RedisUtil {
    
    @Autowired
    private JedisPool jedisPool;
    
    public boolean distributedLock(String key,Long time) {
        try(Jedis jedis = jedisPool.getResource()){
            String result = jedis.set(key, "test1", "NX", "EX", time);
            System.out.println("result:"+result);
            if("OK".equals(result)) {
                return true;
            }else {
                return false;
            }
        }
    }
    
    
    private boolean lockTask(String redisLockKey) {
        boolean isOK = false;
        try (Jedis jedis = jedisPool.getResource()) {
            int retryTime = 3;
            while(--retryTime >= 0 && !isOK) {
                Transaction tx = jedis.multi();
                tx.get(redisLockKey);
                tx.setex(redisLockKey, 10, "1");
                List<Object> redisReturn = tx.exec();
                if(redisReturn.get(0) != null) {
                    isOK = false;
                    Thread.sleep(1000);
                } else {
                    isOK = true;
                    break;
                }
            }
        } catch(Throwable e) {
            isOK = true;
        }
//        if(isOK == false) {
//            throw new BusinessException(1240, "任务被锁定,请退出重试");
//        }
        return isOK;
    }
    
    
    public void redisTest() {
        
        
        try(Jedis jedis = jedisPool.getResource()){
            jedis.auth("password"); 

            jedis.connect();//连接

            jedis.disconnect();//断开连接


            /*****************redis 通用功能 ***********************/
            Set<String> keys = jedis.keys("*"); //列出所有的key

            Set<String> keys2 = jedis.keys("key"); //查找特定的key

            //移除给定的一个或多个key,如果key不存在,则忽略该命令. 

            jedis.del("key1");

            jedis.del("key1","key2","key3","key4","key5");

            //移除给定key的生存时间(设置这个key永不过期)
            jedis.persist("key1"); 

            //检查给定key是否存在
            jedis.exists("key1"); 

            //将key改名为newkey,当key和newkey相同或者key不存在时,返回一个错误
            jedis.rename("key1", "key2");

            //返回key所储存的值的类型。 
            //none(key不存在),string(字符串),list(列表),set(集合),zset(有序集),hash(哈希表) 
            jedis.type("key1");

            //设置key生存时间，当key过期时，它会被自动删除。 
            jedis.expire("key1", 5);//5秒过期 
             
            //清空所有的key
            jedis.flushAll();

            //返回key的个数 
            jedis.dbSize();

            /*****************redis 字符串功能 ***********************/
            //字符串值value关联到key。 
            jedis.set("key1", "value1"); 
            
            //nxxx NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
            jedis.set("key", "value", "NX");
            
            //nxxx NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
            // @param expx EX|PX, expire time units: EX = seconds; PX = milliseconds
            // 分布式锁的功能，如果不存在设置value ,加过期时间
            jedis.set("key", "value", "NX", "EX", 3);

            //将值value关联到key，并将key的生存时间设为seconds(秒)。 
            jedis.setex("foo", 5, "haha"); 
            
            //如果key 不存在则重新设置成value
            jedis.setnx("key", "value");
            
            //用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
            jedis.setrange("key", 2, "value");
            
            jedis.get("key");
            //将给定 key 的值设为 value ，并返回 key 的旧值(old value)
            jedis.getSet("key", "value");

           
            /*****************redis Hash 功能 ***********************/
            //哈希表key中的域field的值设为value。 
            jedis.hset("key1", "field1", "field1-value"); 
            jedis.hset("key1", "field2", "field2-value"); 

            Map map = new HashMap(); 
            map.put("field1", "field1-value"); 
            map.put("field2", "field2-value"); 
            jedis.hmset("key1", map); 

            //查看哈希表 key 中，指定的字段是否存在
            jedis.hexists("key", "field");

            //返回哈希表key中给定域field的值 
            String hget = jedis.hget("key1", "field1");

            //返回哈希表key中给定域field的值(多个)
            List list = jedis.hmget("key1","field1","field2"); 
            for(int i=0;i<list.size();i++){ 
               System.out.println(list.get(i)); 
            } 

            //返回哈希表key中所有域和值
            Map<String,String> map2 = jedis.hgetAll("key1"); 
            for(Map.Entry entry: map2.entrySet()) { 
               System.out.print(entry.getKey() + ":" + entry.getValue() + "\t"); 
            } 

            //删除哈希表key中的一个或多个指定域
            jedis.hdel("key1", "field1");
            jedis.hdel("key1", "field1","field2");

            //查看哈希表key中，给定域field是否存在。 
            jedis.hexists("key1", "field1");

            //返回哈希表key中的所有域
            Set<String> hkeys = jedis.hkeys("key1");

            //返回哈希表key中的所有值
            List<String> hvals = jedis.hvals("key1");



            /*****************redis list 功能 ***********************/
            //将值value插入到列表key的表头。 
            jedis.lpush("key1", "value1-0"); 
            jedis.lpush("key1", "value1-1"); 
            jedis.lpush("key1", "value1-2"); 

            //返回列表key中指定区间内的元素,区间以偏移量start和stop指定.
            //下标(index)参数start和stop从0开始;
            //负数下标代表从后开始(-1表示列表的最后一个元素,-2表示列表的倒数第二个元素,以此类推)
            List list2 = jedis.lrange("key1", 0, -1);//stop下标也在取值范围内(闭区间)
            for(int i=0;i<list2.size();i++){ 
               System.out.println(list2.get(i)); 
            } 

            //返回列表key的长度。 
            jedis.llen("key1");
            
            //移出并获取列表的第一个元素
            jedis.lpop("key");
            
            //移除列表的最后一个元素，并将该元素添加到另一个列表并返回
            String rpoplpush = jedis.rpoplpush("srckey", "dstkey");

            //将一个值插入到已存在的列表头部
            jedis.lpushx("key", "string");

            /*****************redis set 功能 ***********************/
            //将member元素加入到集合key当中。 
            jedis.sadd("key1", "value0"); 
            jedis.sadd("key1", "value1"); 

            //移除集合中的member元素。 
            jedis.srem("key1", "value1"); 

            //返回集合key中的所有成员。 
            Set set = jedis.smembers("key1"); 

            //判断元素是否是集合key的成员
            jedis.sismember("key1", "value2");

            //返回集合key的元素的数量
            jedis.scard("key1");
             
            //返回一个集合的全部成员，该集合是所有给定集合的交集
            jedis.sinter("key1","key2");
             
            //返回一个集合的全部成员，该集合是所有给定集合的并集
            jedis.sunion("key1","key2");

            //返回一个集合的全部成员，该集合是所有给定集合的差集
            jedis.sdiff("key1","key2");
        }
    }
    
}

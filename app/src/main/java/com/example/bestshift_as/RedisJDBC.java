package com.example.bestshift_as;
import java.util.Date;
import java.util.concurrent.Future;

import org.aredis.cache.AsyncRedisClient;
import org.aredis.cache.AsyncRedisFactory;
import org.aredis.cache.RedisCommand;
import org.aredis.cache.RedisCommandInfo;

/**
 * Created by Hüseyin on 22.02.2016.
 */
public class RedisJDBC {
    public static void main(String args[]) {
        // AsyncRedisFactory.setAuth("localhost", "mypassword");

        // In a Server create only one instance of AsyncRedisFactory
        // by configuring it as a Spring Bean or as a Singleton
        AsyncRedisFactory f = new AsyncRedisFactory(null);
        // The below call returns the same AsyncRedisClient for all calls to the same server
        AsyncRedisClient aredis = f.getClient("192.168.43.19");
        // Use sendCommand instead of submitCommand when you are not interested in the Return value
        aredis.sendCommand(RedisCommand.SETEX, "hello", "300", "world");
        // You can also save Java Objects as values like in the below command. Whereas String values are
        // stored as UTF-8 Java Objects are Serialized and Stored. Both are GZipped if the data length is
        // more than 1 Kb. This is done by the default Data Handler.
        aredis.sendCommand(RedisCommand.SETEX, "java_date", "300", new Date());
        // Now retrieve and check the values we have stored
        Future<RedisCommandInfo> future = aredis.submitCommand(RedisCommand.GET, "hello");
        // In the below commented lines the key "hello" is passed as a byte array which also
        // works. Keys can be passed as byte arrays if they cannot be easily encoded as java Strings
        // byte [] keyBytes = "hello".getBytes();
        // Future<RedisCommandInfo> future = aredis.submitCommand(RedisCommand.GET, keyBytes);
        Future<RedisCommandInfo> future1 = aredis.submitCommand(RedisCommand.GET, "java_date");
        try {
            // Whenever possible fetch the results together after submitting the commands as it
            // is done here. this increases the pipeline size resulting in better performance
            String val = (String) future.get().getResult();
            System.out.println("Got back val = " + val);
            Date currentDate = (Date) future1.get().getResult();
            System.out.println("Got back date = " + currentDate);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}


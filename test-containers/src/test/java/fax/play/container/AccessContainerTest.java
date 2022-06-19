package fax.play.container;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import redis.clients.jedis.Jedis;

@Testcontainers
public class AccessContainerTest {

   public static final int ORIGINAL_PORT = 6379;
   @Container
   public GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
         .withExposedPorts(ORIGINAL_PORT);

   private Jedis jedis;

   @BeforeEach
   public void before() {
      jedis = new Jedis(ipAddress(), redis.getMappedPort(ORIGINAL_PORT));
   }

   @Test
   public void testSimplePutAndGet() {
      jedis.hset("blablabla", "rage", "daoc");
   }

   public String ipAddress() {
      // this works -> return redis.getContainerIpAddress()
      return redis.getContainerInfo().getNetworkSettings().getNetworks().values().iterator().next().getIpAddress();
   }
}

package fax.play.container;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class AccessContainerTest {

   @Container
   public GenericContainer container = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
         .withExposedPorts(6379);

   @Test
   public void testSimplePutAndGet() {
      assertThat(container.getHost()).isNotBlank();
      assertThat(container.getFirstMappedPort()).isPositive();
   }
}

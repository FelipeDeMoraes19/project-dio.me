package dio.me.project_dio.me_2025.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import redis.embedded.RedisServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.IOException;

@Configuration
@Profile("test")
public class RedisTestConfig {
    private static RedisServer redisServer;
    private static int redisPort;

    @PostConstruct
    public void startRedis() throws IOException {
        // Usa porta aleatória
        redisServer = new RedisServer(0);
        redisServer.start();
        redisPort = redisServer.ports().get(0);
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", () -> "localhost");
        registry.add("spring.data.redis.port", () -> redisPort);
    }
}
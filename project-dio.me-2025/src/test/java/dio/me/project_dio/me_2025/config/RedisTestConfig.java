package dio.me.project_dio.me_2025.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import redis.embedded.RedisServer;

@Configuration
@Profile("test")
public class RedisTestConfig {
    private static RedisServer redisServer;

    @Bean
    public RedisServer redisServer() {
        try {
            redisServer = new RedisServer(6379);
            redisServer.start();
            return redisServer;
        } catch (Exception e) {
            throw new RuntimeException("Failed to start Redis server", e);
        }
    }

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", () -> "localhost");
        registry.add("spring.data.redis.port", () -> 6379);
    }
}
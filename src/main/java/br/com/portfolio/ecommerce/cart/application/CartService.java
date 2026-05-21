package br.com.portfolio.ecommerce.cart.application;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration; import java.util.*;

@Service
public class CartService {
    private final RedisTemplate<String, Object> redisTemplate;
    public CartService(RedisTemplate<String, Object> redisTemplate){ this.redisTemplate = redisTemplate; }
    private String key(UUID customerId){ return "cart:" + customerId; }
    public void addItem(UUID customerId, UUID productId, int quantity){
        redisTemplate.opsForHash().increment(key(customerId), productId.toString(), quantity);
        redisTemplate.expire(key(customerId), Duration.ofHours(12));
    }
    public Map<Object,Object> getCart(UUID customerId){ return redisTemplate.opsForHash().entries(key(customerId)); }
    public void clear(UUID customerId){ redisTemplate.delete(key(customerId)); }
}

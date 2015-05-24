import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by iprocoder on 15-5-24.
 */
public class LRUCacheTest {
    @Before
    public void init() {
    }

    @Test
    public void test1() {
        LRUCache lruCache = new LRUCache(1);
        lruCache.set(1, 2);
        int value;
        value = lruCache.get(1);
        Assert.assertEquals(2, value);
        lruCache.set(3, 2);
        value = lruCache.get(2);
        Assert.assertEquals(-1, value);
    }
}

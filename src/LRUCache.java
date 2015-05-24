import java.util.*;

/**
 * 用带头节点和尾节点的双链表 + 哈希表 实现 LRU 缓存
 * Created by iprocoder on 15-5-24.
 */
public class LRUCache {

    /**
     * 缓存节点
     */
    private class CacheNode {

        int key;
        int value;
        CacheNode prev;
        CacheNode next;

        public CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public CacheNode() {
        }
    }

    private int capacity;
    private int num;
    private Map<Integer, CacheNode> map;
    private CacheNode head;
    private CacheNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.num = 0;
        this.map = new HashMap<>(capacity);
        this.head = new CacheNode();
        this.tail = new CacheNode();
        this.head.prev = null;
        this.head.next = tail;
        this.tail.prev = head;
        this.tail.next = null;
    }

    public int get(int key) {
        CacheNode cacheNode = map.get(key);

        if (cacheNode == null)
            return -1;
        detach(cacheNode);
        attach(cacheNode);
        return cacheNode.value;
    }

    public void set(int key, int value) {
        CacheNode cacheNode = map.get(key);
        if (cacheNode != null) {
            detach(cacheNode);
            cacheNode.value = value;
            attach(cacheNode);
        } else {
            if (num == capacity) {
                cacheNode = tail.prev;
                detach(cacheNode);
                map.remove(cacheNode.key);
            } else {
                num++;
                cacheNode = new CacheNode();
            }
            cacheNode.key = key;
            cacheNode.value = value;
            map.put(key, cacheNode);
            attach(cacheNode);
        }
    }

    /**
     * 分离节点
     * @param cacheNode
     */
    private void detach(CacheNode cacheNode) {
        cacheNode.prev.next = cacheNode.next;
        cacheNode.next.prev = cacheNode.prev;
    }

    /**
     * 将节点插入头部
     * @param cacheNode
     */
    private void attach(CacheNode cacheNode) {
        cacheNode.prev = head;
        cacheNode.next = head.next;
        head.next = cacheNode;
        cacheNode.next.prev = cacheNode;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(1);
        lruCache.set(1, 3);
        System.out.println(lruCache.get(1));
        lruCache.set(3, 2);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(1));
    }
}

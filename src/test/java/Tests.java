import org.junit.Assert;
import org.junit.Test;

public class Tests {

    public MyHashMap<String, String> testHashMap = new MyHashMap<>();

    @Test
    public void isEmpty() {
        Assert.assertTrue(testHashMap.isEmpty());
    }

    @Test
    public void put() {
        Assert.assertEquals("value", testHashMap.put("key", "value"));
    }

    @Test
    public void isEmpty2() {
        testHashMap.put("key2", "value2");
        Assert.assertFalse(testHashMap.isEmpty());
    }

    @Test
    public void containsKey() {
        Assert.assertTrue(testHashMap.containsKey("key"));
    }

    @Test
    public void containsValue() {
        testHashMap.put("key2", "value2");
        Assert.assertTrue(testHashMap.containsValue("value2"));
    }

    @Test
    public void get() {
        Assert.assertEquals("value", testHashMap.get("key"));
    }

}
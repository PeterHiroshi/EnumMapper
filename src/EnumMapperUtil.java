import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class EnumMapperUtil {

    private static final int CACHE_MAX_SIZE = 1 << 6;

    private static ConcurrentHashMap<Object, Object> cacheMap = new ConcurrentHashMap<>();

    static Map<String, String> getDescMsgMapByCodeFromEnumClass(Class enumClass, Integer code) {
        if (cacheMap.containsKey(buildKey(enumClass, code))) {
            return (Map<String, String>)cacheMap.get(buildKey(enumClass, code));
        }
        Map<Integer, MapperClass> codeMsgMap = getCodeMsgMapByEnumClass(enumClass);
        if (codeMsgMap != null && !codeMsgMap.isEmpty()) {
            MapperClass mc = codeMsgMap.entrySet().stream().filter(codeMsg->codeMsg.getKey().equals(code)).map(Map.Entry::getValue).findFirst().orElse(null);
            String desc = (mc == null) ? codeMsgMap.values().iterator().next().getDesc() : mc.getDesc();
            String msg = (mc == null) ? "<invalid code>" : mc.getMsg();
            Map<String, String> descMsgMap = new HashMap<>();
            descMsgMap.put(desc, msg);
            addToCache(buildKey(enumClass, code), descMsgMap);
            return descMsgMap;
        }
        return null;
    }

    private EnumMapperUtil() {}

    private static void addToCache(Object key, Object value) {
        if (cacheMap.size() > CACHE_MAX_SIZE-1) {
            cacheMap.clear();
        }
        cacheMap.put(key, value);
    }

    private static String buildKey(Class clazz, Integer code) {
        return String.format("%s::%s", clazz.toString(), code);
    }

    private static Map<Integer, MapperClass> getCodeMsgMapByEnumClass(Class enumClass) {
        if (cacheMap.containsKey(enumClass)) {
            return (Map<Integer, MapperClass>)cacheMap.get(enumClass);
        }
        if (!enumClass.isEnum()) {
            return null;
        }
        Map<Integer, MapperClass> codeMsgMap = new HashMap<>();
        try {
            Method getCode = enumClass.getMethod("getCode");
            Method getMsg = enumClass.getMethod("getMsg");
            Method getDesc = enumClass.getMethod("getDesc");
            Object[] objs = enumClass.getEnumConstants();
            for (Object obj : objs) {
                codeMsgMap.put((Integer)getCode.invoke(obj), new MapperClass((String)getDesc.invoke(obj),(String)getMsg.invoke(obj)));
            }
            cacheMap.put(enumClass, codeMsgMap);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
        return codeMsgMap;
    }


}

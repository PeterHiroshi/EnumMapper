import annotation.EnumTypeMapper;
import enums.mapper.MapperEnum;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumMapperUtil {

    public static final String ENUM_MAPPER_PACKAGE_NAME = "enums.mapper";
    public static final String ENUM_CLASS_PACKAGE_NAME = "enums";

    public static Map<Integer, String> getCodeMsgMapByEnumClass(Class enumClass) throws Exception{
        Map<Integer, String> codeMsgMap = new HashMap<>();
        if (!enumClass.isEnum()) {
            return codeMsgMap;
        }
        Method getCode = enumClass.getMethod("getCode");
        Method getMsg = enumClass.getMethod("getMsg");
        Object[] objs = enumClass.getEnumConstants();
        for (Object obj : objs) {
            codeMsgMap.put((Integer)getCode.invoke(obj), (String)getMsg.invoke(obj));
        }
        return codeMsgMap;
    }

    public static Map<String, String> getEnumClassMap(Object obj, String fieldName) throws Exception {
        Map<String, String> mapperCodeMsgMap = new HashMap<>();
        String enumTypeName = getEnumTypeNameByFieldName(obj.getClass(), fieldName);
        Field field = obj.getClass().getDeclaredField(fieldName);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        Object fieldValue = field.get(obj);
        Class enumClass = getClassByClassName(enumTypeName, ENUM_MAPPER_PACKAGE_NAME);
        if (enumClass.isEnum()) {
            Integer enumTypeCode = Integer.class.cast(fieldValue);
            Object[] enumMapperObjs = enumClass.getEnumConstants();
            List<MapperEnum> enumMappers = new ArrayList<>();
            for (Object o : enumMapperObjs) {
                enumMappers.add(MapperEnum.class.cast(o));
            }
            MapperEnum mapperConstant = enumMappers.stream().
                    filter(mapperEnum -> enumTypeCode.equals(mapperEnum.getCode())).collect(Collectors.toList()).get(0);
            String enumClassName = mapperConstant.toString();
            String enumClassMsg = mapperConstant.getMsg();
            mapperCodeMsgMap.put(enumClassName, enumClassMsg);
            return mapperCodeMsgMap;
        }
        else {
            return mapperCodeMsgMap;
        }
    }

    public static Class getClassByClassName(String className, String packageName) throws Exception {
        Class clazz = Class.forName(packageName + "." + className);
        return clazz;
    }

    private static String getEnumTypeNameByFieldName(Class clazz, String fieldName) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        return getEnumTypeNameByField(field);
    }

    private static boolean isEnumTypePresentField(Field field) {
        return field.isAnnotationPresent(EnumTypeMapper.class);
    }

    private static String getEnumTypeNameByField(Field field) {
        if (isEnumTypePresentField(field)) {
            return field.getAnnotation(EnumTypeMapper.class).type();
        }
        else {
            return null;
        }
    }


}

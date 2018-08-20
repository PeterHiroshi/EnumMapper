import annotation.EnumTypeMapper;
import enums.mapper.MapperEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

    public static Map<Integer, String> getCodeMsgMapByEnumClass(Class enumClass) {
        Map<Integer, String> codeMsgMap = new HashMap<>();
        if (!enumClass.isEnum()) {
            return codeMsgMap;
        }
        try {
            Method getCode = enumClass.getMethod("getCode");
            Method getMsg = enumClass.getMethod("getMsg");
            Object[] objs = enumClass.getEnumConstants();
            for (Object obj : objs) {
                codeMsgMap.put((Integer)getCode.invoke(obj), (String)getMsg.invoke(obj));
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return codeMsgMap;
    }

    public static Map<String, String> getEnumClassMap(Object obj, String fieldName) {
        Map<String, String> mapperCodeMsgMap = new HashMap<>();
        String enumTypeName = getEnumTypeNameByFieldName(obj.getClass(), fieldName);
        Field field = null;
        try {
            field = obj.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return mapperCodeMsgMap;
        }
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        Object fieldValue = null;
        Class enumClass = null;
        try {
            fieldValue = field.get(obj);
            enumClass = getClassByClassName(enumTypeName, ENUM_MAPPER_PACKAGE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return mapperCodeMsgMap;
        }
        if (enumClass!=null && enumClass.isEnum()) {
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

    public static Class getClassByClassName(String className, String packageName){
        Class clazz = null;
        try {
            clazz = Class.forName(packageName + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    private static String getEnumTypeNameByFieldName(Class clazz, String fieldName) {
        String enumTypeName = "";
        Field field;
        try {
            field = clazz.getDeclaredField(fieldName);
            enumTypeName = getEnumTypeNameByField(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return enumTypeName;
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

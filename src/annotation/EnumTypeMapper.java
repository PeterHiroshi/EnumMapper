package annotation;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumTypeMapper {
    String MAPPER = "MapperEnum";

    String type();

}

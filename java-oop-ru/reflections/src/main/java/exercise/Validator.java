package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public static List<String> validate(Object obj) {
        List<String> notValidFields = new ArrayList<>();


        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value == null) {
                        notValidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Object obj) throws IllegalAccessException {
        Map<String, List<String>> errors = new HashMap<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            // Проверка на аннотацию @NotNull
            if (field.isAnnotationPresent(NotNull.class)) {
                Object value = field.get(obj);
                if (value == null) {
                    String fieldName = field.getName();
                    errors.putIfAbsent(fieldName, new ArrayList<>());
                    errors.get(fieldName).add(fieldName + " must not be null");
                }
            }

            // Проверка на аннотацию @MinLength
            if (field.isAnnotationPresent(MinLength.class)) {
                Object value = field.get(obj);
                if (value instanceof String) {
                    int minLength = field.getAnnotation(MinLength.class).minLength();
                    String strValue = (String) value;
                    if (strValue.length() < minLength) {
                        String fieldName = field.getName();
                        errors.putIfAbsent(fieldName, new ArrayList<>());
                        errors.get(fieldName).add(fieldName + " length must be at least " + minLength);
                    }
                }
            }
        }

        return errors;
    }

}

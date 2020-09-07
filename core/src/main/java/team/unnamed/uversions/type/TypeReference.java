package team.unnamed.uversions.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeReference<T> {

    private Class<? super T> rawType;
    private Type type;

    @SuppressWarnings("unchecked")
    protected TypeReference() {
        Type superClass = getClass().getGenericSuperclass();

        if (superClass instanceof Class) {
            return;
        }

        ParameterizedType parameterizedType = (ParameterizedType) superClass;

        type = Types.wrap(parameterizedType.getActualTypeArguments()[0]);
        rawType = (Class<? super T>) Types.getRawType(type);
    }

    @SuppressWarnings("unchecked")
    public TypeReference(Type type) {
        this.type = Types.wrap(type);
        rawType = (Class<? super T>) Types.getRawType(this.type);
    }

    public final Class<? super T> getRawType() {
        return rawType;
    }

    public final Type getType() {
        return type;
    }

    @Override
    public final int hashCode() {
        return type.hashCode();
    }

    @Override
    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TypeReference<?>)) {
            return false;
        }

        TypeReference<?> other = (TypeReference<?>) o;
        return Types.typeEquals(type, other.type);
    }

    @Override
    public final String toString() {
        return Types.asString(type);
    }

    public static <T> TypeReference<T> of(Type type) {
        return new TypeReference<T>(type) {};
    }

    public static TypeReference<?> of(Type rawType, Type... typeArguments) {
        return of(new ParameterizedTypeReference(null, rawType, typeArguments));
    }

}
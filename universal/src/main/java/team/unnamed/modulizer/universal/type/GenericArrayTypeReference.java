package team.unnamed.modulizer.universal.type;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Objects;

class GenericArrayTypeReference implements GenericArrayType {

    private final Type componentType;

    GenericArrayTypeReference(Type componentType) {
        this.componentType = Types.wrap(componentType);
    }

    public Type getGenericComponentType() {
        return componentType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GenericArrayType)) {
            return false;
        }
        GenericArrayType other = (GenericArrayType) o;
        return Types.typeEquals(this, other);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(componentType);
    }

    @Override
    public String toString() {
        return Types.asString(componentType) + "[]";
    }

}
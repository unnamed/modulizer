package team.unnamed.uversions.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterizedTypeReference implements ParameterizedType {

    private final Type ownerType;
    private final Type rawType;
    private final Type[] typeArguments;

    ParameterizedTypeReference(ParameterizedType prototype) {
        this(prototype.getOwnerType(), prototype.getRawType(), prototype.getActualTypeArguments());
    }

    ParameterizedTypeReference(Type ownerType, Type rawType, Type... typeArguments) {
        this.ownerType = ownerType == null ? null : Types.wrap(ownerType);
        this.rawType = Types.wrap(rawType);
        this.typeArguments = typeArguments;

        for (int i = 0, length = this.typeArguments.length; i < length; i++) {
            this.typeArguments[i] = Types.wrap(this.typeArguments[i]);
        }
    }

    @Override
    public Type[] getActualTypeArguments() {
        return typeArguments.clone();
    }

    @Override
    public Type getRawType() {
        return rawType;
    }

    @Override
    public Type getOwnerType() {
        return ownerType;
    }

}
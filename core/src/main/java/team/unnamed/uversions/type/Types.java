package team.unnamed.uversions.type;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Objects;

public final class Types {

    public static final Type[] EMPTY_TYPE_ARRAY = new Type[] {};

    public static Type wrap(Type type) {
        if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;

            if (clazz.isArray()) {
                return new GenericArrayTypeReference(
                        wrap(clazz.getComponentType())
                );
            }

            return clazz;
        }

        if (type instanceof ParameterizedType) {
            ParameterizedType prototype = (ParameterizedType) type;

            return new ParameterizedTypeReference(prototype);
        }

        if (type instanceof GenericArrayType) {
            GenericArrayType prototype = (GenericArrayType) type;

            return new GenericArrayTypeReference(prototype);
        }

        if (type instanceof WildcardType) {
            WildcardType prototype = (WildcardType) type;

            return new WildcardTypeReference(prototype);
        }

        return type;
    }

    public static Class<?> getRawType(Type type) {

        if (type instanceof Class<?>) {
            return (Class<?>) type;
        }

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();

            if (!(rawType instanceof Class)) {
                throw new IllegalArgumentException("Raw type isn't a Class!");
            }

            return (Class<?>) rawType;
        }

        if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType)type).getGenericComponentType();
            return Array.newInstance(getRawType(componentType), 0).getClass();
        }

        if (type instanceof TypeVariable) {
            return Object.class;
        }

        if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        }

        throw new IllegalArgumentException();
    }

    public static boolean typeEquals(Type a, Type b) {

        if (a == b) {
            return true;
        }

        if (a instanceof Class) {
            return a.equals(b);
        }

        if (a instanceof ParameterizedType) {

            if (!(b instanceof ParameterizedType)) {
                return false;
            }

            ParameterizedType pa = (ParameterizedType) a;
            ParameterizedType pb = (ParameterizedType) b;

            Type aOwnerType = pa.getOwnerType();
            Type bOwnerType = pb.getOwnerType();

            return Objects.equals(aOwnerType, bOwnerType)
                    && pa.getRawType().equals(pb.getRawType())
                    && Arrays.equals(pa.getActualTypeArguments(), pb.getActualTypeArguments());

        }

        if (a instanceof GenericArrayType) {
            if (!(b instanceof GenericArrayType)) {
                return false;
            }

            GenericArrayType ga = (GenericArrayType) a;
            GenericArrayType gb = (GenericArrayType) b;
            return typeEquals(ga.getGenericComponentType(), gb.getGenericComponentType());
        }

        if (a instanceof WildcardType) {
            if (!(b instanceof WildcardType)) {
                return false;
            }

            WildcardType wa = (WildcardType) a;
            WildcardType wb = (WildcardType) b;
            return Arrays.equals(wa.getUpperBounds(), wb.getUpperBounds())
                    && Arrays.equals(wa.getLowerBounds(), wb.getLowerBounds());

        }

        if (a instanceof TypeVariable) {

            if (!(b instanceof TypeVariable)) {
                return false;
            }

            TypeVariable<?> va = (TypeVariable<?>) a;
            TypeVariable<?> vb = (TypeVariable<?>) b;

            return va.getGenericDeclaration() == vb.getGenericDeclaration()
                    && va.getName().equals(vb.getName());

        }

        return false;
    }

    public static String asString(Type type) {
        return type instanceof Class ? ((Class<?>) type).getName() : type.toString();
    }

}
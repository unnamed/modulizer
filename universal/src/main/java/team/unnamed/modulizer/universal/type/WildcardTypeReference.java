package team.unnamed.modulizer.universal.type;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

public class WildcardTypeReference implements WildcardType {

  private final Type upperBound;
  private final Type lowerBound;

  WildcardTypeReference(WildcardType prototype) {
    this(prototype.getUpperBounds(), prototype.getLowerBounds());
  }

  WildcardTypeReference(Type[] upperBounds, Type[] lowerBounds) {
    if (lowerBounds.length == 1) {
      this.lowerBound = TypesUtil.wrap(lowerBounds[0]);
      this.upperBound = Object.class;

      return;
    }

    this.lowerBound = null;
    this.upperBound = TypesUtil.wrap(upperBounds[0]);
  }

  @Override
  public Type[] getUpperBounds() {
    return new Type[]{upperBound};
  }

  @Override
  public Type[] getLowerBounds() {
    if (lowerBound == null) {
      return TypesUtil.EMPTY_TYPE_ARRAY;
    }

    return new Type[]{lowerBound};
  }

}
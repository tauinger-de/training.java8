package util;

import java.util.function.BiPredicate;

@SuppressWarnings({"unchecked", "rawtypes"})

public enum Op {
    EQ((v1, v2) -> cmp(v1, v2) == 0),
    NE((v1, v2) -> cmp(v1, v2) != 0),
    GT((v1, v2) -> cmp(v1, v2) > 0),
    LT((v1, v2) -> cmp(v1, v2) < 0),
    GE((v1, v2) -> cmp(v1, v2) >= 0),
    LE((v1, v2) -> cmp(v1, v2) <= 0);

    public final BiPredicate predicate;

    private Op(BiPredicate predicate) {
        this.predicate = predicate;
    }

    private static int cmp(Object v1, Object v2) {
        return ((Comparable) v1).compareTo((Comparable) v2);
    }
}

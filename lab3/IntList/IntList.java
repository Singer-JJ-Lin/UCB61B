import java.util.Formatter;

/**
 * @author 老爷保号
 */
public class IntList {
    private int first;

    private IntList rest;

    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }

    public IntList(int first) {
        this(first, null);
    }

    public static void dSquareList(IntList L) {
        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList ptr = res;
        L = L.rest;
        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }
        return res;
    }

    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }

    public static IntList dcatenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }

        if (B == null) {
            return A;
        }

        IntList ptrA = A;
        while (ptrA.rest != null) {
            ptrA = ptrA.rest;
        }
        ptrA.rest = B;
        return A;
    }

    public static IntList catenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }
        IntList ptrA = A;
        IntList res = new IntList(0, null);
        IntList ptrRes = res;
        while (ptrA != null) {
            ptrRes.rest = new IntList(ptrA.first, null);
            ptrRes = ptrRes.rest;
            ptrA = ptrA.rest;
        }
        ptrRes.rest = B;
        return res.rest;
    }

    public static IntList reverse(IntList L) {
        if (L == null) {
            return null;
        }
        IntList pre = L, p = L.rest;
        pre.rest = null;
        while (p != null) {
            IntList q = p.rest;
            p.rest = pre;
            pre = p;
            p = q;
        }
        return pre;
    }

    @Override
    public int hashCode() {
        return first;
    }

    public static IntList of(Integer... args) {
        IntList result, p;
        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        p = result;
        for (int k = 1; k < args.length; p = p.rest, k++) {
            p.rest = new IntList(args[k], null);
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof IntList)) {
            return false;
        }

        IntList l = (IntList) o;
        for (IntList p = this; p != null && l != null; p = p.rest, l = l.rest) {
            if (p.first != l.first) {
                return false;
            }
        }

        return true;
    }

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;

        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /**
     * Outputs the IntList as a String. You are not expected to read or understand
     * this method.
     */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }

}

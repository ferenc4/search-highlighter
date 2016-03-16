package rippledown.audit;

/**
 * Created by Ferenc Fazekas on 3/16/2016.
 */
public class Range {

    private final int start;//final for immutability
    private final int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        if (start != range.start) return false;
        return end == range.end;

    }

    @Override
    public int hashCode() {
        int result = start;
        result = 31 * result + end;
        return result;
    }

    @Override
    public String toString() {
        return "Range(" + start + "," + end + ")";
    }
}

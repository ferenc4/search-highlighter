package searchbar;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ferenc on 3/16/2016.
 */
public class RangeTest {

    @Test
    public void shouldBeComparable() {
        Range r1 = new Range(0, 0);
        Range r2 = new Range(0, 0);
        Assertions.assertThat(r1).isEqualTo(r2);
    }

    @Test
    public void shouldBeUsedInSets() {
        Range r1 = new Range(0, 0);
        Range r2 = new Range(0, 0);
        Set<Range> set = new HashSet<>();
        set.add(r1);
        set.add(r2);
        Assertions.assertThat(set).hasSize(1);
        set.add(new Range(0,1));
        Assertions.assertThat(set).hasSize(2);
    }
}

package rippledown.audit;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

/**
 * Created by Ferenc Fazekas on 3/16/2016.
 */
public class HighlighterTest {

    @Test
    public void shouldNotIgnoreWhiteSpaceCharacters(){
        String text = "Hello Hell\no Hell o!";
        List<Range> result = Highlighter.getRanges(text, "Hello");
        Assertions.assertThat(result).containsOnly(new Range(0, 4));
    }

    @Test
    public void shouldIgnoreCases(){
        String text = "Hello Helen, here in hell!";
        List<Range> result = Highlighter.getRanges(text, "He");
        Assertions.assertThat(result).containsOnly(new Range(0, 1), new Range(6, 7), new Range(13,14), new Range(21,22));
    }

    @Test
    public void shouldHighlightSingleCharacter() {
        String text = "Hello World!";
        List<Range> result = Highlighter.getRanges(text, "H");
        Assertions.assertThat(result).containsOnly(new Range(0, 0));
    }

    @Test
    public void shouldHighlightRepeatedSinglesOfCharacters() {
        String text = "Hello Hannah!";
        List<Range> result = Highlighter.getRanges(text, "H");
        Assertions.assertThat(result).containsOnly(new Range(0, 0), new Range(6, 6), new Range(11, 11));
    }

    @Test
    public void shouldHighlightRangeOfCharacters(){
        String text = "Hello world. Oh what a wonderful day!";
        List<Range> result = Highlighter.getRanges(text, "Oh what");
        Assertions.assertThat(result).containsOnly(new Range(13, 19));
    }

    @Test
    public void shouldHighlightRepeatedRangesOfCharacters(){
        String text = "Hello Helen, here in hell!";
        List<Range> result = Highlighter.getRanges(text, "He");
        Assertions.assertThat(result).containsOnly(new Range(0, 1), new Range(6, 7), new Range(13,14), new Range(21,22));
    }

    @Test
    public void shouldHighlightLastCharacter(){
        String text = "Hello Helen, here in hell!";
        List<Range> result = Highlighter.getRanges(text, "hell!");
        Assertions.assertThat(result).containsOnly(new Range(21,25));
    }
}

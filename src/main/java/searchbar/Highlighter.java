package searchbar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

/**
 * Created by Ferenc Fazekas on 3/16/2016.
 */
public class Highlighter {

    public static List<Range> getRanges(String text, String keyword) {
        List<Range> ranges = new ArrayList<>();
        if(keyword.length() > 0) {
            Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                Range currentRange = new Range(matcher.start(), matcher.end());
                ranges.add(currentRange);
            }
        }
        return ranges;
    }
}

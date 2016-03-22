package rippledown.audit;

import org.assertj.core.api.Assertions;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Condition;
import org.assertj.swing.timing.Pause;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ferenc Fazekas on 3/16/2016.
 */
public class HighlighterUITest {

    private FrameFixture fixture;

    @Test
    public void editorPaneText() {
        String actualText = fixture.textBox(HighlighterUI.DETAIL_PANE).text();
        Assertions.assertThat(actualText).startsWith("Lorem ipsum dolor sit amet");
    }

    @Test
    public void shouldHighlightWhenSearchContentsChangeText() {
        fixture.textBox(HighlighterUI.SEARCH_BAR).enterText("sit");
        Pause.pause(new Condition("should highlight") {
            @Override
            public boolean test() {
                JEditorPane target = (JEditorPane) fixture.textBox(HighlighterUI.DETAIL_PANE).target();
                javax.swing.text.Highlighter highlighter = target.getHighlighter();
                return highlighter.getHighlights().length == 2;
            }
        }, 1_000);
    }

    @Test
    public void detailPaneShouldNotBeEditable() {
        fixture.textBox(HighlighterUI.DETAIL_PANE).requireNotEditable();
    }

    @Before
    public void setup() {
        JFrame frame = GuiActionRunner.execute(new GuiQuery<JFrame>() {
            @Override
            protected JFrame executeInEDT() throws Throwable {
                JFrame frame = new JFrame("highlighter test");
                frame.getContentPane().add(new HighlighterUI().component());
                frame.setSize(new Dimension(600, 400));
                frame.setVisible(true);
                return frame;
            }
        });
        fixture = new FrameFixture(frame);
    }

    @After
    public void cleanup() {
        fixture.cleanUp();
    }

}

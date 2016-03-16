package rippledown.audit;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Ferenc Fazekas on 3/16/2016.
 */
public class HighlighterUI {

    private final static String TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam venenatis fringilla consequat. Pellentesque ultricies arcu lorem, eget vestibulum risus dignissim at. In ante elit, efficitur in rutrum eget, porta ac magna. Nam varius leo facilisis volutpat suscipit. Donec quis risus sed diam fermentum egestas. Nulla facilisi. Mauris sagittis ipsum sit amet nulla efficitur, ac pretium justo eleifend.";

    public static final String SEARCH_BAR = "SearchBar";
    public static final String DETAIL_PANE = "DetailPane";

    private JPanel component;

    public HighlighterUI() {
        JTextField searchBar = new JTextField();
        searchBar.setName(SEARCH_BAR);
        JEditorPane detailPane = new JEditorPane();
        detailPane.setName(DETAIL_PANE);

        component = new JPanel();
        component.setLayout(new BorderLayout());
        component.add(searchBar, BorderLayout.NORTH);
        component.add(detailPane, BorderLayout.CENTER);
        detailPane.setText(TEXT);

        searchBar.getDocument().addDocumentListener(new SearchbarListener(searchBar, detailPane));

    }

    public JPanel component() {
        return component;
    }

    private class SearchbarListener implements DocumentListener {
        private JTextField searchBar;
        private JEditorPane detailPane;
        private DefaultHighlightPainter painter = new DefaultHighlightPainter(Color.YELLOW);

        public SearchbarListener(JTextField searchBar, JEditorPane detailPane) {
            this.searchBar = searchBar;
            this.detailPane = detailPane;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateHighlights();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateHighlights();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            //doesn't apply for text based events
        }

        private void updateHighlights(){
            //get highlight ranges
            try {
                List<Range> results = Highlighter.getRanges(detailPane.getText(), searchBar.getText());

                //remove previous highlights
                detailPane.getHighlighter().removeAllHighlights();
                if(!results.isEmpty()){
                    for (Range result : results) {
                        System.out.println("result = " + result);
                        int startIndex = result.getStart();
                        int endIndex = result.getEnd();
                        //highlight text
                        detailPane.getHighlighter().addHighlight(startIndex, endIndex, painter);
                    }
                }
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("App");
            frame.getContentPane().add(new HighlighterUI().component());
            frame.setSize(new Dimension(600, 400));
            frame.setVisible(true);
        });
    }
}


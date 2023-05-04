package markup;

import java.util.List;

public class Paragraph {

    protected String textElements;
    protected List<Combiner> listOfLists;
    public Paragraph(List<Combiner> textElements) {
        this.listOfLists = textElements;
    }

    public void toMarkdown(StringBuilder finalText) {
    for (Combiner element : listOfLists) {
        element.toMarkdown(finalText);
    }
    }
    public void toHtml(StringBuilder finalText) {
        for (Combiner element : listOfLists) {
            element.toHtml(finalText);
        }
    }
}
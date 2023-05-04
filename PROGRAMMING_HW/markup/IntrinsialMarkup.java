package markup;

import java.util.List;

public class IntrinsialMarkup implements Combiner {
    protected List<Combiner> intrinsialList;

    public String getMarker() {
        return null;
    }

    public String getTag() {
        return null;
    }

    public IntrinsialMarkup(List<Combiner> intrinsialList) {
        this.intrinsialList = intrinsialList;
        getMarker();
        getTag();
    }

    public void toMarkdown(StringBuilder text) {
        text.append(getMarker());
        for (Combiner element : intrinsialList) {
            element.toMarkdown(text);
        }
        text.append(getMarker());
    }

    public void toHtml(StringBuilder text) {
        text.append('<');
        text.append(getTag());
        text.append('>');
        for (Combiner element : intrinsialList) {
            element.toHtml(text);
        }
        text.append('<');
        text.append('/');
        text.append(getTag());
        text.append('>');
    }

}

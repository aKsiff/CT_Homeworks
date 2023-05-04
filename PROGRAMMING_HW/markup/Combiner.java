package markup;

public interface Combiner {

    String getMarker();
    String getTag();
    void toMarkdown(StringBuilder text);
    void toHtml(StringBuilder text);
}
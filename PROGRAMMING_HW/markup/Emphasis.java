package markup;


import java.util.List;

public class Emphasis extends IntrinsialMarkup {

    public String getMarker() {
        return "*";
    }

    public String getTag() {
        return "em";
    }
    public Emphasis(List<Combiner> list) {
        super(list);
    }
}

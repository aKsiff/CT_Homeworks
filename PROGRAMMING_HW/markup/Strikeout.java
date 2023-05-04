package markup;

import java.util.List;

public class Strikeout extends IntrinsialMarkup {

    public String getMarker() {
        return "~";
    }


    public String getTag() {
        return "s";
    }
    public Strikeout(List<Combiner> list) {
        super(list);
    }

}

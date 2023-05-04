package markup;

import java.util.List;

public class Strong extends IntrinsialMarkup {

    public String getMarker() {
        return "__";
    }


    public String getTag() {
        return "strong";
    }

    public Strong(List<Combiner> list) {
        super(list);
    }

}

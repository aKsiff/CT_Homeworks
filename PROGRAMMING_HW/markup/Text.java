package markup;


class Text implements Combiner {

    @Override
    public String getMarker() {
        return null;
    }

    @Override
    public String getTag() {
        return null;
    }

    private StringBuilder finalText;

    public Text(String text) {
        finalText = new StringBuilder(text);
    }

    @Override

    public void toMarkdown(StringBuilder finalText) {
        finalText.append(this.finalText);
    }

    @Override

    public void toHtml(StringBuilder finalText) {
        finalText.append(this.finalText);
    }
}
public class LaTeX extends Content{

    public LaTeX(String content) {
        super(content);
    }

    @Override
    public String printTypes() {
        return "\"LaTeX content: \"" + content;
    }
}

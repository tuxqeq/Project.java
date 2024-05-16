public class StringType extends Content{
    public StringType(String content) {
        super(content);
    }

    @Override
    public String printTypes() {
        return "StringType:  \"" + content;
    }
}

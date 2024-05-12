public class StringType extends Content{
    public StringType(String content) {
        super(content);
        System.out.println("StringType:  " + content);
    }

    @Override
    public String printTypes() {
        return "";
    }
}

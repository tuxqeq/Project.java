public class ImgFormat extends Content{
    public ImgFormat(String content) {
        super(content);
    }

    @Override
    public String printTypes() {
        return "Imgformat content: \"" + content;
    }
}

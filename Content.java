public class Content {
    protected String content;

    public Content(String content){
        this.content = content;
        analyse(content);
    }

    private void analyse(String content) {}

    @Override
    public String toString() {
        return "Content{" +
                "content='" + content + '\'' +
                '}';
    }
}

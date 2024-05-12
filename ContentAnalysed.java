import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentAnalysed {
    String content;
    ArrayList<Content> types = new ArrayList<>();
    public ContentAnalysed(String content) {
        this.content = content;
        analyse(content);
        //System.out.println(types);
    }

    private void analyse(String content) {
        Pattern pattern = Pattern.compile(
                "(\\$.*?\\$)|" +                // LaTeX
                        "(\\\\href\\{[^}]*?jpg}|png})|" +     // Image
                        "([^$\\\\]+)");           // Text
        //TODO  сделать чтобы ентер работал
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                types.add(new LaTeX(matcher.group(1)));
            } else if (matcher.group(2) != null) {
                types.add(new ImgFormat(matcher.group(2)));
            } else if(matcher.group(3) != null){
                types.add(new StringType(matcher.group(3).trim()));
            }
        }
    }

}

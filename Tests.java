import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Tests extends File{
    String name;
    Content content;
    public Tests() {
        try(Stream<Path> stream = Files.walk(Paths.get("./tests/"))){
            stream.filter(Files::isRegularFile)
                    .forEach(path -> {
                        String fileContent = null;
                        try {
                            fileContent = new String(Files.readAllBytes(path));
                        } catch (IOException e) {}
                        Content content = new Content(fileContent);
                        new Tests(path.getFileName().toString(), content);
                    });
        }catch (IOException e){
            System.out.println("Tests, first constructor, second ex");
        }
    }
    public Tests(String name, Content content) {
        this.name = name;
        this.content = content;
        addToCollection(this);
    }
    @Override
    String getPath() {
        return "./tests/"+name+".txt";
    }

    @Override
    public String toString() {
        return "Tests{" +
                "name='" + name + '\'' +
                ", content=" + content +
                '}';
    }
}

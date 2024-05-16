import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class Tests extends File{
    String name;
    ContentAnalysed content;
    ArrayList<String> linkedPaths = new ArrayList<>();
    public Tests() {
        try(Stream<Path> stream = Files.walk(Paths.get("./tests/"))){
            stream.filter(Files::isRegularFile)
                    .forEach(path -> {
                        String fileContent = null;
                        try {
                            fileContent = new String(Files.readAllBytes(path));
                        } catch (IOException e) {
                            System.out.println("Tests, second consructor");
                        }
                        ContentAnalysed content = new ContentAnalysed(fileContent);
                        new Tests(path.getFileName().toString(), content);
                    });
        }catch (IOException e){
            System.out.println("Tests, first constructor, second ex");
        }
    }
    public Tests(String name, ContentAnalysed content) {
        this.name = name;
        this.content = content;
        new WrkngFiles<Tests>(){
            @Override
            void newName() {}
            @Override
            void ColImp(Tests obj) {
                File.files.add(obj);
            }
        }.ColImp(this);
    }
    public void link(String folderName){
        this.linkedPaths.add(folderName + "/" + this.name);
    }


    @Override
    String getPath() {
        return "./tests/" + name;
    }

    public void setContent(ContentAnalysed content) {
        this.content = content;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return "\"" + name + "\"";
    }

    public Tests getNext(){
        Iterator<File> iter = File.files.iterator();
        while (iter.hasNext()) {
            File file = iter.next();
            if (file.getPath().equals(getPath())) {
                if(iter.hasNext()) {
                    File innerIt = iter.next();
                    if (innerIt instanceof Tests) {
                        return (Tests) innerIt;
                    } else {
                        return null;
                    }
                }
            }
        }

        return null;
    }


}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public interface Command {
    void execute();

    static boolean ifNotAvaliable1(String nameToCheck, String path) {
        Path startPath = Paths.get(path);
        String fileToFind = nameToCheck;

        try (Stream<Path> stream = Files.walk(startPath)) {
            return stream
                    .anyMatch(iter -> iter.getFileName().toString().equals(fileToFind));

        } catch (IOException e) {
            System.out.println("ifNotAvaliable newtest");
        }
        return false;
    }
    static boolean ifNotAvaliable(String nameToCheck, String path) {
        String search = path + nameToCheck;
        return File.files.stream()
                .anyMatch(iter -> iter.getPath().equals(search));
    }
}

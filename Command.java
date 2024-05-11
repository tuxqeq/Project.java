public interface Command {
    void execute();

    static boolean fileIsInDir(String nameToCheck, String path) {
        String search = path + nameToCheck;
        return File.files.stream()
                .anyMatch(iter -> iter.getPath().equals(search));
    }

}

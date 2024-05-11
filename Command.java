public interface Command {
    void execute();

    static boolean ifNotAvaliable(String nameToCheck, String path) {
        String search = path + nameToCheck;
        return File.files.stream()
                .anyMatch(iter -> iter.getPath().equals(search));
    }
}

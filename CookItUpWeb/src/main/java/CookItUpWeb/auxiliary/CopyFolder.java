package CookItUpWeb.auxiliary;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFolder extends SimpleFileVisitor<Path> {

    private static final String SOURCE = "src\\main\\resources\\static\\";
    private static final String TARGET = "target\\classes\\static\\";

    private Path source;
    private final Path target;

    CopyFolder(String source, String target) {
        this.source = Paths.get(source);
        this.target = Paths.get(target);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir,
                                             BasicFileAttributes attrs) throws IOException {

        Path resolve = target.resolve(source.relativize(dir));
        if (Files.notExists(resolve)) {
            Files.createDirectories(resolve);
            System.out.println("Create directories : " + resolve);
        }
        return FileVisitResult.CONTINUE;

    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        Path resolve = target.resolve(source.relativize(file));
        Files.copy(file, resolve, StandardCopyOption.REPLACE_EXISTING);
        System.out.println(
                String.format("Copy File from \t'%s' to \t'%s'", file, resolve)
        );

        return FileVisitResult.CONTINUE;

    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.format("Unable to copy: %s: %s%n", file, exc);
        return FileVisitResult.CONTINUE;
    }

    public static void copyFolder(String source, String target) throws IOException {
        source = SOURCE + source;
        target = TARGET + target;

        CopyFolder copyFolder = new CopyFolder(source, target);
        Files.walkFileTree(Paths.get(source), copyFolder);
    }

    //THE CHANGES ARE DONE IN THE COMPUTER, NOT THE SERVER!

}

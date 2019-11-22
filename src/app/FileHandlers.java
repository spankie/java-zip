package app;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// REad a list of files from the command line.
// Create a filesystem;
// loop through the filenames
// Get the file and add to the file system;

public class FileHandlers {
    public FileSystem createZip(Path zipPath) throws IOException, URISyntaxException {
        Map<String, String> providerProps = new HashMap<String, String>() {{
            put("create", "true");
        }};
        URI zipURI = new URI("jar:file", zipPath.toUri().getPath(), null);
        FileSystem fs = FileSystems.newFileSystem(zipURI, providerProps);
        return fs;
    }

    public static void copyToZip(FileSystem zipFs, String filename) throws IOException {
        Path sourceFile = Paths.get(filename);
        Path destFile = zipFs.getPath("/"+filename);
        Files.copy(sourceFile, destFile, StandardCopyOption.REPLACE_EXISTING);
    }

    public Reader OpenReader() {
        char[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S'};
//        Reader r = new Reader(b);
        Reader r = new StringReader(String.copyValueOf(c));
        return r;
    }
    public void PrintByChar() {
        char[] buff = new char[8];
        int len;
        try (Reader r = this.OpenReader()){
//            r = this.OpenReader("nofile yet");
//            int v;
            while ((len = r.read()) >= 0) {
                System.out.println(len);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
//            passes on and surpresses the rest
        }
    }

    public String ReadFromFile(String fileName) {
        System.out.println(fileName);
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) { // filereader are deprecated
            String v;
            while((v = bf.readLine()) != null) {
                fileContent.append(v);
            }
        } catch(Exception e){
            System.out.println(e);
        };
        try {
            List<String> list = Files.readAllLines(Paths.get("file.txt"));
            list.forEach(l -> {
                fileContent.append(l);
                System.out.println(l);
            });
        } catch(Exception e) {

        }
        return fileContent.toString();
    }
}

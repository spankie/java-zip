package app;

import java.io.ByteArrayInputStream;
import java.nio.file.FileSystem;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class Zipper {
    public static void main(String[] args) {
        // Startup();
        String name = "data.zip";
        FileHandlers fh = new FileHandlers();
        // fh.PrintByChar();
        // String fc = fh.ReadFromFile("file.txt");
        // System.out.println(fc);
        try (FileSystem fs = fh.createZip(Paths.get(name))) {
            for (String f : args) {
                FileHandlers.copyToZip(fs, f);
                System.out.println("added '" + f + "' to the archive!");
            }
            System.out.println("Your archive is ready : " + name);
        } catch(Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    // Some testings
    public static void Startup() {
        int c = 123;
        byte b = (byte) c;
        char a = 'a';
        Map<Integer, String> m = new HashMap<Integer, String>(){{
            put(1, "value");
            put(2, "Value2");
        }};
        // running anonymous threads
        m.forEach((k, v) -> {
            Runnable r = new Runnable() {
                public void run() {
                    System.out.println(k);
                }
            };
            Thread t = new Thread(r);
            t.start();
        });
        byte[] bb =  {1,2,3,4,5};
        ByteArrayInputStream ba = new ByteArrayInputStream(bb);
        int v;
        while((v = ba.read()) >= 0) {
            System.out.println(v*10);
        }
        System.out.println(a);
    }
}

// jar -cvf zipper.jar app/*.class
// java -cp ./src/zipper.jar app.Zipper file.txt
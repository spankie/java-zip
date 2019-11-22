package app;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class Zipper {
    public static void main(String[] args) {
        // Startup();
        // fh.PrintByChar();
        // String fc = fh.ReadFromFile("file.txt");
        // System.out.println(fc);
        
        // INPUT STREAMS
        byte[] b = {6, 7, 8, 9, 10, 11, 12, 13, 14, 127, 'a'};
        // InputStream is = new ByteArrayInputStream( "myString".getBytes() );
        InputStream is = new ByteArrayInputStream( b );
        int v;
        try {
            while ((v = is.read()) >= 0) {
                System.out.print((char)v);
                System.out.print(" : ");
                System.out.print(v);
                System.out.println();
            }
            System.out.println("\n------------------------------");
            is.close();
        } catch(Exception e) {
            System.out.print(e.getMessage());
        }
        char[] cc = {'A', 'B', 'C', '\n'};
        CharArrayReader r = new CharArrayReader(cc);
        int c;
        try {
            while ((c = r.read()) >= 0) {
                System.out.print(c);
                System.out.print(" : ");
                System.out.print((char)c);
                System.out.println();
            }
            System.out.println("\n------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        // InputStream in = Files.newInputStream(Paths.get("file.txt"), Files.)

        String name = "data.zip";
        FileHandlers fh = new FileHandlers();
        try (FileSystem fs = fh.createZip(Paths.get(name))) {
            for (String f : args) {
                FileHandlers.copyToZip(fs, f);
                System.out.println("added '" + f + "' to the archive!");
            }
            System.out.println("Your archive is ready : " + name);
            Path p = Paths.get(fs.toString());
            System.out.println(p.toUri().toString());
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

// from src
// jar -cvf ../zipper.jar app/*.class
// java -cp ./zipper.jar app.Zipper "file.txt" "file - Copy.txt" "file - Copy (3).txt" "file - Copy (2).txt"

// Bug Tracker
// Conference scheduling app
    // Hold different sessions
    // Allow speakers to be assigned
    // basically manage the conference topics and 
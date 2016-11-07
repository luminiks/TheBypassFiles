import java.io.File;

/**
 * Created by Liminiksik on 07.11.2016.
 */
public class TheBypassFiles1 {
    private static int k = 0;

    public static int fileFly(String path) {
        File file = new File(path);
        File[] s = file.listFiles();
        for (int j = 0; j < s.length; j++) {
            if(!s[j].isDirectory())
                k++;
            if (s[j].isDirectory())
                fileFly(s[j].getPath());
        }
        return k;
    }
}

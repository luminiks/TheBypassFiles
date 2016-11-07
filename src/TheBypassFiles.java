import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Liminiksik on 05.11.2016.
 */
public class TheBypassFiles {
        public static int k = 0;

        public static void main(String[] args) {
            // определяем объект для каталога
            File dir = new File("C:\\work\\Мамке");
            String str = ".doc";
            filter(dir, str);
            // если объект представляет каталог
            int c = folder(dir);
            System.out.println("колличество файлов  " + c);

        }


    public static void filter(File dir, String str){
        if(!dir.exists())
            System.out.println(dir + " папки не существует");
        File[] listFiles = dir.listFiles(new MyFileNameFilter(str));
        if(listFiles.length == 0){
            System.out.println(dir + " не содержит файлов с расширением " + str);
        }else{
            for(File f : listFiles)
                System.out.println("Файл: " + dir + File.separator + f.getName());
        }
    }

    public static class MyFileNameFilter implements FilenameFilter {

        private String str;

        public MyFileNameFilter(String ext){
            this.str = ext.toLowerCase();
        }
        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(str);
        }
    }

    public static int folder(File dir){
        if(!dir.exists())
            System.out.println(dir + " папки не существует");
        if(dir.isDirectory()){
            // получаем все вложенные объекты в каталоге
            for(File item : dir.listFiles()){
                if(item.isDirectory()){
                    //System.out.println(item.getName() + "  \tкаталог");
                    folder(item);
                }
                else{
                    k++;
                    //System.out.println(item.getName() + "\tфайл");
                }
            }
        }
        return k;
    }
}

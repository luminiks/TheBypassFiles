import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Liminiksik on 05.11.2016.
 */
public class TheBypassFiles {
        public static int k = 0;
        private static Map<String, Integer> count = new HashMap<String, Integer>();

        public static void main(String[] args) {
            // определяем объект для каталога
            //File dir = new File("D:\\new\\work\\Мамке"); //D:\new\work\Мамке
            File dir = new File("D:\\new\\Новая папка (2)\\Pictures"); //D:\new\Новая папка (2)\Pictures

            String str = ".doc";
            filter(dir, str);
            // если объект представляет каталог
            int c = folder(dir);
            System.out.println("колличество файлов  " + c);

            System.out.println();
            System.out.println("--------------------------------------------------");
            filter1(dir);
            for (String str1: count.keySet()) {
                if (str1.equals(" "))
                    System.out.println("Файлов без расширения - " + count.get(str1) + " штук");
                else System.out.println("Файлов с расширением " + str1 + " - " + count.get(str1) + " штук");
            }

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

    public static void filter1 (File dir){
        if(!dir.exists())
            System.out.println(dir + " папки не существует");
        if(dir.isDirectory()){
            // получаем все вложенные объекты в каталоге
            File[] listFiles;
            for(File item : dir.listFiles()){
                if(item.isDirectory()) {
                    filter1(item);
                } else {
                    String str = getFileExtension(item);
                    if (count.containsKey(str)){
                        Integer n = new Integer(count.get(str));
                        n++;
                        count.put(str, n);
                    }
                    else
                        count.put(str, new Integer(1));

                }
            }
        }
    }

    //метод определения расширения файла
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".")+1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return " ";
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
                } else{
                    k++;
                    //System.out.println(item.getName() + "\tфайл");
                }
            }
        }
        return k;
    }
}

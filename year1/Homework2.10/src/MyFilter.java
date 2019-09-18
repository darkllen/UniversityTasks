import java.io.File;
import java.io.FileFilter;
import java.util.Date;

public class MyFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        Date last = new Date(pathname.lastModified());
        Date date = new Date(0);
        return last.compareTo(date)!=0;
    }
}

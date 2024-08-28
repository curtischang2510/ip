package Storage;

import java.io.File;
import java.io.IOException;

public class Storage {

    protected File storageFile;

    public Storage(File storageFile) {
        this.storageFile = storageFile;
    }

    public static Storage createStorage(String path) {
        File file = new File(path);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdir();
        }

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("error when creating file");
                e.printStackTrace();
            }
        }
        return new Storage(file);
    }

    public File getStorageFile() {
        return storageFile;
    }
}

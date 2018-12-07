package data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveToFile {
    /***
     * Zapisuje do wskazanego pliki wskazana wiadomosc
     *
     * @param path - Scieżka do pliku (miejsca na dysku)
     * @param message - Lista bitów do zapisania
     */
    public void save(String path, String message) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(makeTableFromList(message));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private byte[] makeTableFromList(String list) {
        List<Byte> byteList = new ArrayList<>();


        for (int i = 0; i < list.length(); i += 8) {
            byteList.add(getOneByte(list, i));
        }

        byte[] ret = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            ret[i] = byteList.get(i);
        }
        return ret;
    }

    private byte getOneByte(String list, int offset) {
        byte ret = 0;
        for (int i = offset; (i < list.length() && ((i-offset) < 8)); i++) {
            ret += (list.charAt(offset + (7-i+offset)) == '0') ? 0 : (Math.pow(2,i-offset));
        }
        return ret;
    }
}

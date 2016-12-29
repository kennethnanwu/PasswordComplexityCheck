import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 2016-12-27.
 */
class CommonPasswords {
    private static final String commonPwdFileName = "10kMostCommonPasswords.txt";
    private static List<String> commonPwds;
    private static List<String> consecutionPwds;

    static {
        String commonPwdsFilePathStr = getCommonPwdFilePathStr();
        loadCommonPwdFile(commonPwdsFilePathStr);
        loadConsecutionPwds();
    }

    private static String getCommonPwdFilePathStr() {
        return CommonPasswords.class.getResource(commonPwdFileName).getPath();
    }

    private static void loadCommonPwdFile(String filePathStr) {
        List<String> pwdsInFile = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePathStr));
            String line;
            while ((line = br.readLine()) != null) {
                pwdsInFile.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        commonPwds = pwdsInFile;
    }

    public static List<String> getCommonPwds() {
        if (commonPwds == null || commonPwds.size() == 0) {
            loadCommonPwdFile(getCommonPwdFilePathStr());
        }
        return commonPwds;
    }

    public static List<String> getConsecutionPwds() {
        if (consecutionPwds == null || consecutionPwds.size() == 0) {
            loadConsecutionPwds();
        }
        return consecutionPwds;
    }


    private static void loadConsecutionPwds() {
        List<String> consecution = new ArrayList<>();
        consecution.add("qwertyuiopasdfghjklzxcvbnm");
        consecution.add("abcdefghijklmnopqrstuvwxyz");
        consecutionPwds = consecution;
    }
}

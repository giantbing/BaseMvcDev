package giantbing.zonlinks.com.giantbaselibrary.Bean;

/**
 * Created by P on 2017/7/7.
 */

public class FileBean {
    public final static int ADDTYPE =0;
    public final static int NOMALTYPE =1;
    private String fileName;
    private String flieSize;
    private String fileDate;

    public int getFiletype() {
        return filetype;
    }

    public void setFiletype(int filetype) {
        this.filetype = filetype;
    }

    private int filetype;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFlieSize() {
        return flieSize;
    }

    public void setFlieSize(String flieSize) {
        this.flieSize = flieSize;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }
}

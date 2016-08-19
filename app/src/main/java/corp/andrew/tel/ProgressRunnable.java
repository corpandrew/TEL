package corp.andrew.tel;

import android.widget.ProgressBar;

/**
 * Created by corpa on Aug 19, 2016
 */
public class ProgressRunnable implements Runnable {

    private ProgressBar progressBar;
    private long total;
    private int lengthOfFile;

    public ProgressRunnable(ProgressBar progressBar, long total, int lengthOfFile) {
        this.progressBar = progressBar;
        this.total = total;
        this.lengthOfFile = lengthOfFile;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getLengthOfFile() {
        return lengthOfFile;
    }

    public void setLengthOfFile(int lengthOfFile) {
        this.lengthOfFile = lengthOfFile;
    }

    @Override
    public void run() {
        progressBar.setProgress((int) ((total * 100) / lengthOfFile));
    }
}

package ar.edu.fesf.model;

import java.util.List;

public class Ranking {

    private List<BookInfo> top20;

    private List<BookInfo> recentlyAvailable;

    public List<BookInfo> getTop20() {
        return this.top20;
    }

    public void setTop20(final List<BookInfo> top20) {
        this.top20 = top20;
    }

    public List<BookInfo> getRecentlyAvailable() {
        return this.recentlyAvailable;
    }

    public void setRecentlyAvailable(final List<BookInfo> recentlyAvailable) {
        this.recentlyAvailable = recentlyAvailable;
    }

}

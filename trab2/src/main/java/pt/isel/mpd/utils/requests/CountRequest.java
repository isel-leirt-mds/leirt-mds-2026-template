package pt.isel.mpd.utils.requests;

import java.io.Reader;

import static pt.isel.mpd.utils.resources.BuildUtils.TODO;

public class CountRequest implements Request {

    public CountRequest(Request req) {
        TODO();
    }

    @Override
    public Reader get(String path) {
       TODO();
       return null;
    }

    public int getCount() {
        TODO();
        return 0;
    }
}

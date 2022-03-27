
import org.junit.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;


public class SongCacheTest {

    @Test
    public void cacheIsWorking(){
        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays("ID-1", 3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2", 2);
        cache.recordSongPlays("ID-3", 5);
        assertThat(cache.getPlaysForSong("ID-1"), is(4));
        assertThat(cache.getPlaysForSong("ID-9"), is(-1));
        assertThat(cache.getTopNSongsPlayed(2), contains("ID-3",
                "ID-1"));
        assertThat(cache.getTopNSongsPlayed(0), is(empty()));
    }
}


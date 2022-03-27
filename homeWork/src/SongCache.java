


import java.util.*;

    interface SongCache{
        /**
         * Record number of plays for a song.
         */
        void recordSongPlays(String songId, int numPlays);

        /**
         * Fetch the number of plays for a song.
         *
         * @return the number of plays, or -1 if the
        song ID is unknown.
         */
        int getPlaysForSong(String songId);

        /**
         * Return the top N songs played, in descending
         order of number of plays.
         */
        List<String> getTopNSongsPlayed(int n);
    }

//class MyComparator implements Comparator<String> {
//
//        Map<String, Integer> base;
//
//        public MyComparator(Map<String, Integer> base) {
//                this.base = base;
//        }
//
//        public int compare(String a, String b) {
//                if (base.get(a) >= base.get(b)) {
//                        return -1;
//                } else {
//                        return 1;
//                }
//        }
//}

    class SongCacheImpl implements SongCache {
        //record played songs in map
        private final Map<String, Integer> songs;
//        private Map<String, Integer> sortedMap;

        public SongCacheImpl() {
            songs = new HashMap<>();
//            sortedMap = new TreeMap<>(new MyComparator(songs));
        }

        @Override
        synchronized public void recordSongPlays(String songId, int numPlays) {
            songs.put(songId, songs.getOrDefault(songId, 0) + numPlays);
        }

        @Override
        synchronized public int getPlaysForSong(String songId) {
            return songs.getOrDefault(songId, -1);
        }

        @Override
        synchronized public List<String> getTopNSongsPlayed(int n) {
            PriorityQueue<String> topSongs = new PriorityQueue<>((a,b)-> songs.get(b) - songs.get(a));
//            sortedMap.putAll(songs);
            List<String> topNSongs = new ArrayList<>();
            while (!topSongs.isEmpty() && n > 0){
                topNSongs.add(topSongs.poll());
                n--;
            }
//                for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
//                        if (n > 0){
//                                topNSongs.add(entry.getKey())
//                        }
//                }
//                Iterator<Map.Entry<String, Integer>> itr = sortedMap.entrySet().iterator();
//                while (itr.hasNext() && n != 0) {
//                        Map.Entry<String, Integer> entry = itr.next();
//                        topNSongs.add(entry.getKey());
//                        n--;
//                }
            return topNSongs;
        }
    }



package tvshow;

import java.io.ObjectInputStream;
import java.time.LocalTime;
import java.util.List;
import java.util.OptionalDouble;

public interface EpisodeManager {

    @SuppressWarnings("unchecked")
    default List<Episode> getEpisodes() {
        try {
            var episodes = (List<Episode>) new ObjectInputStream(EpisodeManager.class.getResourceAsStream("dallas.ser"))
                    .readObject();
            return episodes;
        } catch (Exception e) {
            throw new AssertionError("Failed to load objects");
        }
    }

    /**
     * {@return azon epizódok száma, amelyek vasárnap voltak műsoron}
     */
    long getNumberOfEpisodesAiredOnSunday();

    /**
     * Írja a konzolra az adott évad epizódjainak összefoglalóját, hossz szerinti növekvő sorrendben.
     * Tehát a legrövidebb epizód összefoglalót írja ki elsőnek.
     *
     * @param season egy évad szám
     */
    void printSummariesOfSeasonSortedByLength(int season);

    /**
     * {@return azon különböző időpontok növekvő sorrendbe rendezett listája, amikor az epizódok műsoron voltak}
     */
    List<LocalTime> getDifferentAirTimesAscending();

    /**
     * Visszadja az első évad epizódjainak átlagos értékelését.
     *
     * @return egy {@link OptionalDouble} ami tartalmazza az első évad epizódjainak átlagos értékelését,
     * vagy egy üres {@link OptionalDouble} ha nincsenek értékelések
     */
    OptionalDouble getAverageRatingOfFirstSeason();

}
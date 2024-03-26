package tvshow;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class EpisodeManagerImpl implements EpisodeManager{
    /**
     * {@return azon epizódok száma, amelyek vasárnap voltak műsoron}
     */
    @Override
    public long getNumberOfEpisodesAiredOnSunday() {
        return getEpisodes().stream()
                .filter(e -> e.airDate().getDayOfWeek() == DayOfWeek.SUNDAY)
                .count();
    }

    /**
     * Írja a konzolra az adott évad epizódjainak összefoglalóját, hossz szerinti növekvő sorrendben.
     * Tehát a legrövidebb epizód összefoglalót írja ki elsőnek.
     *
     * @param season egy évad szám
     */
    @Override
    public void printSummariesOfSeasonSortedByLength(int season) {
        getEpisodes().stream()
                .map(Episode::season)
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);
    }

    /**
     * {@return azon különböző időpontok növekvő sorrendbe rendezett listája, amikor az epizódok műsoron voltak}
     */
    @Override
    public List<LocalTime> getDifferentAirTimesAscending() {
        return getEpisodes().stream()
                .map(Episode::airTime)
                .sorted(Comparator.naturalOrder())
                .toList();
    }

    /**
     * Visszadja az első évad epizódjainak átlagos értékelését.
     *
     * @return egy {@link OptionalDouble} ami tartalmazza az első évad epizódjainak átlagos értékelését,
     * vagy egy üres {@link OptionalDouble} ha nincsenek értékelések
     */
    @Override
    public OptionalDouble getAverageRatingOfFirstSeason() {
        return getEpisodes().stream()
                .filter(e -> e.season() == 1)
                .map(Episode::rating)
                .get();
    }

    public static void main(String[] args){
        EpisodeManager manager;

    }
}

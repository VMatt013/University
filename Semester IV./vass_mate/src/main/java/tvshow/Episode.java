package tvshow;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public record Episode(int season, int number, String title, LocalDate airDate,
                      LocalTime airTime, int runtime, Double rating,
                      String summary) implements Serializable {}

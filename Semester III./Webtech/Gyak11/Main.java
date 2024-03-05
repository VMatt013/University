package org.example;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        int i = 0;
        ArrayList<Konyv> konyvek = new ArrayList<>();
        // System.out.println("hello");
        try (Scanner sc = new Scanner(new File("konyv.txt"))) {
            // System.out.println("hello2");
            // String sor2 = sc.nextLine();
            // System.out.println(sor2);
            while (sc.hasNextLine()) {
                // System.out.println("hello3");
                String sor = sc.nextLine();
                // System.out.println(sor);
                String[] sorElemek = sor.split(";");
                // for (String e : sorElemek)
                // System.out.println(e);

                if (i != 0) {
                    Konyv k1;
                    try {
                        k1 = new Konyv(sorElemek[0], sorElemek[1], sorElemek[2], sorElemek[3],
                                LocalDate.parse(sorElemek[4], DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                                Integer.parseInt(sorElemek[5]), sorElemek[6], Integer.parseInt(sorElemek[7]));
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        try {
                            k1 = new Konyv(sorElemek[0], sorElemek[1], sorElemek[2], sorElemek[3],
                                    LocalDate.parse(sorElemek[4], DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                                    Integer.parseInt(sorElemek[5]), sorElemek[6], null);
                        } catch (ArrayIndexOutOfBoundsException e2) {
                            k1 = new Konyv(sorElemek[0], sorElemek[1], sorElemek[2], null, null, null, null, null);
                        }

                    }

                    konyvek.add(k1);
                }
                i++;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        i = 0;

        for (Konyv konyv : konyvek) {
            System.out.println(konyv);
        }
        /*--------------------------------*/

        konyvek.stream().forEach(e -> System.out.println(e));
        System.out.println();
        konyvek.stream().filter(e -> e.getCim().contains("i")).forEach(e -> System.out.println(e));
        System.out.println(
                konyvek.stream().filter(e -> e.getAr() != null).mapToInt(Konyv::getAr).average().getAsDouble());
        System.out.println(konyvek.stream().count());

        System.out.println(konyvek.stream().max(new Comparator<Konyv>() {

            @Override
            public int compare(Konyv o1, Konyv o2) {
                return ((o1.getAr() != null) ? o1.getAr() : 0) - ((o2.getAr() != null) ? o2.getAr() : 0);
            }
        }).get());

        System.out.println(konyvek.stream()
                .max((o1, o2) -> ((o1.getAr() != null) ? o1.getAr() : 0) - ((o2.getAr() != null) ? o2.getAr() : 0))
                .get());

        System.out.println(konyvek.stream().map(Konyv::getCim).reduce("", (a, b) -> a + " " + b));
        System.out.println(konyvek.stream().map(Konyv::getCim)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString());

        System.out.println(konyvek.stream().map(Konyv::getCim).collect(new Supplier<StringBuilder>() {

            @Override
            public StringBuilder get() {
                //
                return new StringBuilder();
            }
        }, new BiConsumer<StringBuilder, String>() {

            @Override
            public void accept(StringBuilder t, String u) {
                t.append("; ").append(u);
            }
        }, new BiConsumer<StringBuilder, StringBuilder>() {

            @Override
            public void accept(StringBuilder t, StringBuilder u) {
                t.append(", ").append(u);

            }
        }));

        // https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/util/stream/Collector.html
        System.out.println(
                konyvek.stream().map(Konyv::getCim).collect(new Collector<String, StringBuilder, StringBuilder>() {

                    @Override
                    public Supplier<StringBuilder> supplier() {
                        return new Supplier<StringBuilder>() {

                            @Override
                            public StringBuilder get() {
                                return new StringBuilder();
                            }
                        };
                    }

                    @Override
                    public BiConsumer<StringBuilder, String> accumulator() {
                        return new BiConsumer<StringBuilder, String>() {

                            @Override
                            public void accept(StringBuilder t, String u) {
                                t.append(u).append("; ");
                            }
                        };
                    }

                    @Override
                    public BinaryOperator<StringBuilder> combiner() {
                        return new BinaryOperator<StringBuilder>() {

                            @Override
                            public StringBuilder apply(StringBuilder t, StringBuilder u) {

                                return t.append(", ").append(u);
                            }
                        };
                    }

                    @Override
                    public Function<StringBuilder, StringBuilder> finisher() {
                        return new Function<StringBuilder, StringBuilder>() {

                            @Override
                            public StringBuilder apply(StringBuilder t) {
                                return t.append("...");
                            }
                        };
                    }

                    @Override
                    public Set<Characteristics> characteristics() {
                        return new TreeSet<Characteristics>();
                    }
                }));

        System.out.println(konyvek.stream().filter(e -> e.getTema() != null
                        && (e.getTema().equals("sci-fi") || e.getTema().equals("krimi") || e.getTema().equals("horror")))
                .collect(Collectors.toList()));

        System.out.println(konyvek.stream().filter(e -> e.getTema() != null
                        && (e.getTema().equals("sci-fi") || e.getTema().equals("krimi") || e.getTema().equals("horror")))
                .collect(Collectors.counting()));

        System.out.println(konyvek.stream().filter(e -> e.getTema() != null
                        && (e.getTema().equals("sci-fi") || e.getTema().equals("krimi") || e.getTema().equals("horror")))
                .collect(Collectors.groupingBy(Konyv::getKiado)));

        Map<String, List<Konyv>> mk = konyvek.stream().filter(e -> e.getTema() != null
                        && (e.getTema().equals("sci-fi") || e.getTema().equals("krimi") || e.getTema().equals("horror")))
                .collect(Collectors.groupingBy(Konyv::getKiado));

        System.out.println();
        for (Map.Entry<String, List<Konyv>> entry : mk.entrySet()) {
            String key = entry.getKey();
            List<Konyv> val = entry.getValue();
            System.out.println(key);
            System.out.println(val);

        }


        System.out.println(konyvek.stream().filter(e -> e.getTema() != null
                        && (e.getTema().equals("sci-fi") || e.getTema().equals("krimi") || e.getTema().equals("horror")))
                .collect(Collectors.groupingBy(Konyv::getKiado, Collectors.toList())));

        System.out.println(konyvek.stream().filter(e -> e.getTema() != null
                        && (e.getTema().equals("sci-fi") || e.getTema().equals("krimi") || e.getTema().equals("horror")))
                .collect(Collectors.groupingBy(Konyv::getKiado, Collectors.averagingInt(Konyv::getAr))));
        System.out.println();
        System.out.println(konyvek.stream().filter(e -> e.getTema() != null
                        && (e.getTema().equals("sci-fi") || e.getTema().equals("krimi") || e.getTema().equals("horror")))
                .collect(Collectors.groupingBy(Konyv::getKiado, Collectors.minBy((u,v)->u.getAr()-v.getAr()))));

        System.out.println();
        System.out.println(konyvek.stream().filter(e -> e.getTema() != null
                        && (e.getTema().equals("sci-fi") || e.getTema().equals("krimi") || e.getTema().equals("horror")))
                .collect(Collectors.groupingBy(Konyv::getKiado, Collectors.reducing("", Konyv::getTema, String::concat))));

    }
}
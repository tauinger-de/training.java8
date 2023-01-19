package appl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;
import static util.Util.mlog;

public class Application {

    static final List<String> words = Arrays.asList("eine", "Rose", "ist", "eine", "Rose", "ist", "eine", "Rose");

    public static void main(String[] args) {
        demoOldFashion();
        demoGetOrDefault();
        demoPutIfAbsentAndReplace();
        demoCompute();
        demoMerge();
    }

    /**
     * Zählt die Number gleicher Wörter -- ohne Nutzung von Java 8 Neuerungen
     */
    static void demoOldFashion() {
        mlog();
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            Integer count = counts.get(word);
            if (count == null) {
                count = 1;
                counts.put(word, count);
            } else {
                counts.put(word, count + 1);
            }
        }
        out.println(counts);
        out.println();
    }

    /**
     * Wie oben nur jetzt mit Java 8
     */
    static void demoGetOrDefault() {
        mlog();
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            int count = counts.getOrDefault(word, 0);
            counts.put(word, count + 1);
        }
        out.println(counts);
        out.println();
    }

    /**
     * Alternative Implementierung der Zählung
     */
    static void demoPutIfAbsentAndReplace() {
        mlog();
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.putIfAbsent(word, 0);
            int count = counts.get(word);
            counts.replace(word, count + 1);
        }
        out.println(counts);
        out.println();
    }

    /**
     * Alternative (und eleganteste) Implementierung der Zählung
     */
    static void demoCompute() {
        mlog();
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.compute(word, (k, v) -> v == null ? 1 : v + 1);
        }
        out.println(counts);
        out.println();
    }


    @SuppressWarnings("Convert2MethodRef")
    static void demoMerge() {
        mlog();
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.merge(word, 1, (oldCount, increment) -> oldCount + increment);
        }
        out.println(counts);
        out.println();
    }

}

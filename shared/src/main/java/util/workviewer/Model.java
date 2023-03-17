package util.workviewer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model {

    public static interface Listener {
        public abstract void bandAdded(Band band);

        public abstract void workBegin(Band band);

        public abstract void workEnd(Band band);
    }

    private final List<Listener> listeners = new ArrayList<>();

    public void addModelListener(Listener l) {
        this.listeners.add(l);
    }

    public void removeModelListener(Listener l) {
        this.listeners.remove(l);
    }

    private final List<Band> bands = new ArrayList<>();

    private Band bandFor(Thread thread) {
        for (final Band band : this.bands) {
            if (band.thread == thread)
                return band;
        }
        return null;
    }

    private Band getOrCreateBand(Thread thread) {
        Band band = this.bandFor(thread);
        if (band == null) {
            band = new Band(thread, this.bands.size());
            this.bands.add(band);
            for (Listener l : listeners)
                l.bandAdded(band);
        }
        return band;
    }

    synchronized public void beginWork(Thread thread, String text, Color color) {
        Band band = this.getOrCreateBand(thread);
        band.works.add(new Work(text, color));
        listeners.forEach(l -> l.workBegin(band));
    }

    synchronized public void endWork(Thread thread) {
        final Band band = this.bandFor(thread);
        if (band == null)
            throw new RuntimeException();
        final Work work = band.works.get(band.works.size() - 1);
        work.end = System.currentTimeMillis();
        this.listeners.forEach(l -> l.workEnd(band));
    }

    public static class Band implements Iterable<Work> {
        private final int index;
        private final Thread thread;
        private final List<Work> works = new ArrayList<>();

        private Band(Thread thread, int index) {
            this.thread = thread;
            this.index = index;
        }

        public Thread getThread() {
            return this.thread;
        }

        public int getIndex() {
            return this.index;
        }

        public Iterator<Work> iterator() {
            return this.works.iterator();
        }
    }

    public static class Work {
        private final long begin;
        private long end = Long.MAX_VALUE;
        private final String text;
        private final Color color;

        private Work(String text, Color color) {
            this.text = text;
            this.color = color;
            this.begin = System.currentTimeMillis();
        }

        public long getBegin() {
            return begin;
        }

        public long getEnd() {
            return end;
        }

        public Color getColor() {
            return color;
        }

        public String getText() {
            return text;
        }
    }
}

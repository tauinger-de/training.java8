package appl;

import java.util.ArrayList;
import java.util.List;

public class Thermostat2 {
    private final List<AlarmListener> maxAlarmListeners = new ArrayList<>();
    private final List<AlarmListener> minAlarmListeners = new ArrayList<>();

    public void addMaxAlarmListener(AlarmListener listener) {
        this.maxAlarmListeners.add(listener);
    }

    public void addMinAlarmListener(AlarmListener listener) {
        this.minAlarmListeners.add(listener);
    }

    public void run() {
        // too cold...
        for (AlarmListener listener : this.minAlarmListeners)
            listener.onAlarm();
        // too hot...
        for (AlarmListener listener : this.maxAlarmListeners)
            listener.onAlarm();
    }
}

package appl;

import java.util.ArrayList;
import java.util.List;

public class Thermostat1 {

    private final List<ThermostatListener> listeners = new ArrayList<>();


    public void addThermostatListener(ThermostatListener listener) {
        this.listeners.add(listener);
    }

    public void run() {
        // too cold...
        for (ThermostatListener listener : this.listeners) {
            listener.onMinAlarm();
        }
        // too hot...
        for (ThermostatListener listener : this.listeners) {
            listener.onMaxAlarm();
        }
    }
}

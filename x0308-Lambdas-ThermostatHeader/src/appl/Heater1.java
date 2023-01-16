package appl;

public class Heater1 implements ThermostatListener {

    public void onMinAlarm() {
        System.out.println("Heater on");
    }

    public void onMaxAlarm() {
        System.out.println("Heater off");
    }

}

package appl;

public class Application {

    /**
     * Demo für Thermostat1
     */
    static void demo1() {
        Thermostat1 thermostat = new Thermostat1();
        Heater1 heater = new Heater1();
        thermostat.addThermostatListener(heater);
        thermostat.run();
    }

    /**
     * Hier werden die Alarm-Listener als einzelne Funktionen als Lambdas implementiert, also
     * mithilfe der Java 8 funktionalen Programmierung.
     */
    @SuppressWarnings("Convert2MethodRef")
    static void demo2() {
        Thermostat2 thermostat = new Thermostat2();
        Heater2 heater = new Heater2();
        thermostat.addMaxAlarmListener(() -> heater.onMaxAlarm());
        thermostat.addMinAlarmListener(() -> heater.onMinAlarm());
        thermostat.run();
    }

    /**
     * In demo2() wird eine Compiler-Warnung unterdrückt, wir sollten hier das Konzept einer
     * "Methoden-Referenz" nutzen. Dies wird später noch näher erläutert.
     */
    static void demo3() {
        Thermostat2 thermostat = new Thermostat2();
        Heater2 heater = new Heater2();
        thermostat.addMinAlarmListener(heater::onMinAlarm);
        thermostat.run();
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }

}

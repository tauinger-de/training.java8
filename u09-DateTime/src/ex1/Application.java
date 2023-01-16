package ex1;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

import util.Util;

public class Application {

    public static void main(String[] args) {
        demo1();
        demo2();
    }
    static void demo1() {
    	Util.mlog();
        PropertyEditor e = PropertyEditorManager.findEditor(int.class);
        e.setValue(42);
        String s = e.getAsText();
        System.out.println(s);
    }
    static void demo2() {
    	Util.mlog();
        PropertyEditor e = PropertyEditorManager.findEditor(int.class);
        e.setAsText("42");
        Integer v = (Integer)e.getValue();
        System.out.println(v);
    }
}

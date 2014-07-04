package com.interzonedev.pienburger.driver;

import java.util.LinkedList;
import java.util.List;

/**
 * An enumeration of the browser executables supported by the pienburger functional testing framework.
 * 
 * @author "Mark Markarian" &lt;mark@interzonedev.com&gt;
 */
public enum Browser {
    FIREFOX("firefox"), CHROME("chrome"), SAFARI("safari"), HTMLUNIT("htmlUnit"), IE("internetExplorer");

    private final static List<String> ids = new LinkedList<String>();

    static {
        for (Browser b : values()) {
            ids.add(b.id());
        }
    }

    private final String id;

    private Browser(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public static List<String> allIds() {
        return ids;
    }

    public static Browser getById(String id) {
        for (Browser b : values()) {
            if (b.id().equals(id)) {
                return b;
            }
        }
        return null;
    }
}

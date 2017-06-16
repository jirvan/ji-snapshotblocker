package com.jirvan;

import com.jirvan.dates.Millisecond;

import java.util.Set;
import java.util.TreeSet;

public class SnapshotMarker {

    public static boolean suppressMessages = false;
    public static boolean surroundMessagesWithBlankLines = true;
    public static Set<String> displayedMessages = new TreeSet<>();

    public static boolean sleep_mustBeRemoved(int millis) {
        return sleep_mustBeRemoved(millis, null);
    }

    public static boolean sleep_mustBeRemoved(int millis, String message) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return message != null ? testingOnly_mustBeRemoved(message) : true;
    }

    public static boolean timing_mustBeRemoved(int millis, String message) {
        System.out.printf("%s: %s\n", new Millisecond().toString(), message);
        return true;
    }

    public static boolean testingOnly_mustBeRemoved(String message) {
        return testingOnly_mustBeRemoved(message, true);
    }

    private static boolean testingOnly_mustBeRemoved(String message, boolean displayMessageOnceOnly) {
        if (!suppressMessages) {
            if (!displayMessageOnceOnly || displayedMessages.add(message)) {
                if (surroundMessagesWithBlankLines) {
                    System.out.printf("\n%s\n\n", message);
                } else {
                    System.out.printf("%s\n", message);
                }
            }
        }
        return true;
    }

    public static boolean testingOnly_mustBeRemoved() {
        return true;
    }

}

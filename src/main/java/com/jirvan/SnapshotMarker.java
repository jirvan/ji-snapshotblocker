package com.jirvan;

public class SnapshotMarker {

    public static boolean testingOnly_mustBeRemoved = true;

    public static boolean testingOnly_mustBeRemoved(String message) {
        System.out.printf("\n%s\n\n", message);
        return true;
    }

    public static boolean testingOnly_mustBeRemoved() {
        return true;
    }

}

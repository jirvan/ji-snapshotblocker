package com.jirvan;

public class SnapshotMarker {

    public static boolean suppressMessages = false;
    public static boolean surroundMessagesWithBlankLines = true;

    public static boolean testingOnly_mustBeRemoved(String message) {
        if (!suppressMessages) {
            if (surroundMessagesWithBlankLines) {
                System.out.printf("\n%s\n\n", message);
            } else {
                System.out.printf("%s\n", message);
            }
        }
        return true;
    }

    public static boolean testingOnly_mustBeRemoved() {
        return true;
    }

}

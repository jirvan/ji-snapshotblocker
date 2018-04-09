package com.jirvan.snapshot;

import com.jirvan.util.Timer;

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

    /**
     * Creates a new timer which you can later start with {@link Timer#startPeriod()}.
     * You could also create an immediately started timer if you didn't need
     * multiple "active" periods with {@link #startNewTimer(String)}.
     *
     * Use this instead of {@link Timer#newTimer(String)} if you want a "snapshot blocked" usage that will prevent
     * releases being built with the timer code.
     *
     * @param timerTitle the title for the timer
     * @return the timer
     * @see Timer#newTimer(String)
     */
    public static Timer newTimer(String timerTitle) {
        return Timer.newTimer(timerTitle);
    }

    /**
     * Creates a new timer which you can later start with {@link Timer#startPeriod()}.
     * You could also create an immediately started timer if you didn't need
     * multiple "active" periods with {@link #startNewTimer(String, boolean)}.
     *
     * Use this instead of {@link Timer#newTimer(String, boolean)} if you want a "snapshot blocked" usage that will prevent
     * releases being built with the timer code.
     *
     * @param timerTitle the title for the timer
     * @param printStartAndFinishMessage whether to print a start and finish message
     * @return the timer
     * @see Timer#newTimer(String, boolean)
     */
    public static Timer newTimer(String timerTitle, boolean printStartAndFinishMessage) {
        return Timer.newTimer(timerTitle, printStartAndFinishMessage);
    }

    /**
     * Creates and starts a new timer.  If you need a timer that can handle
     * multiple "active" periods then use {@link #newTimer(String)}.
     *
     * Use this instead of {@link Timer#startNew(String)} if you want a "snapshot blocked" usage that will prevent
     * releases being built with the timer code.
     *
     * @param timerTitle the title for the timer
     * @return the started timer
     * @see Timer#startNew(String)
     */
    public static Timer startNewTimer(String timerTitle) {
        return Timer.startNew(timerTitle);
    }

    /**
     * Creates and starts a new timer.  If you need a timer that can handle
     * multiple "active" periods then use {@link #newTimer(String, boolean)}.
     *
     * Use this instead of {@link Timer#startNew(String, boolean)} if you want a "snapshot blocked" usage that will prevent
     * releases being built with the timer code.
     *
     * @param timerTitle the title for the timer
     * @param printStartAndFinishMessage whether to print a start and finish message
     * @return the started timer
     * @see Timer#startNew(String,boolean)
     */
    public static Timer startNewTimer(String timerTitle, boolean printStartAndFinishMessage) {
        return Timer.startNew(timerTitle, printStartAndFinishMessage);
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

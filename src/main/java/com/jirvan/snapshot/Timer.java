package com.jirvan.snapshot;

import com.jirvan.dates.Millisecond;

import java.io.PrintStream;
import java.util.Date;

public class Timer {

    private String title;
    private long totalElapsedTime;
    private long start;
    private Long periodStart;

    Timer(String title) {
        this.title = title;
        this.totalElapsedTime = 0;
    }

    public String getTitle() {
        return title;
    }

    public void startTimer() {
        Date startDate = new Date();
        this.start = startDate.getTime();
        this.periodStart = this.start;
        System.out.printf("%s: Started %s\n", Millisecond.from(startDate).toString(), this.title);
    }

    public void endTimer() {
        Date endDate = new Date();
        System.out.printf("%s: Finished %s (%s)\n",
                          Millisecond.from(endDate).toString(),
                          title,
                          Millisecond.formatDuration(endDate.getTime() - start));
    }

    public void startPeriod() {
        if (periodStart != null) {
            throw new RuntimeException("Cannot start a period for %s (a period has already been started");
        } else {
            periodStart = new Date().getTime();
        }
    }

    public void endPeriod() {
        if (periodStart == null) {
            throw new RuntimeException("Cannot end period for %s (a period has not been started");
        } else {
            totalElapsedTime += (new Date().getTime() - periodStart);
            periodStart = null;
        }
    }

    public long getTotalElapsedTime() {
        if (periodStart != null) {
            throw new RuntimeException("Cannot get total elapsed time for %s (a period has been started but not finished");
        } else {
            return totalElapsedTime;
        }
    }

    public void printTotalElapsedTimeString(PrintStream printStream) {
        if (periodStart != null) {
            throw new RuntimeException("Cannot get total elapsed time for %s (a period has been started but not finished");
        } else {
            printStream.printf("Total time spent on \"%s\": %s\n",
                               title,
                               Millisecond.formatDuration(totalElapsedTime));
        }
    }

}

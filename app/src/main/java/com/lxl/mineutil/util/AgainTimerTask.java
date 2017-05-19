package com.lxl.mineutil.util;

import java.util.Timer;
import java.util.TimerTask;

public class AgainTimerTask {
    private TimerTask mTimerTask;
    private Timer mTimer;
    private TimeTaskResult timeTaskResult;
    private int timePeriod;

    public AgainTimerTask(int timePeriod) {
        this.mTimer = null;
        this.mTimerTask= null;
        this.timePeriod = timePeriod;
    }

    public void startTimer() {
        stopTimer();
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        if (this.mTimerTask == null) {
            this.mTimerTask= new TimerTask() {
                @Override
                public void run() {
                   timeTaskResult.run();
                }
            };
        }
        if (this.mTimer!= null && this.mTimerTask != null) {
            this.mTimer.schedule(this.mTimerTask, 0, (long) this.timePeriod);
        }
    }

    public void stopTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer = null;
        }
        if (this.mTimerTask != null) {
            this.mTimerTask.cancel();
            this.mTimerTask = null;
        }
    }

    public AgainTimerTask setTimerTask(TimeTaskResult timeTaskResult){
        this.timeTaskResult=timeTaskResult;
        return this;
    }

    public interface TimeTaskResult{
        void run();
    }
}


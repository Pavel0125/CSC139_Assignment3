import java.util.Comparator;

public class Process {

    private int pid, arrivalTime, cpuBurstTime, priority, timeProgressed, timeWaiting;

    public Process(int pid, int arrivalTime, int cpuBurstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.cpuBurstTime = cpuBurstTime;
        this.priority = priority;
        timeProgressed = 0;
        timeWaiting = arrivalTime;
    }

    public int getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getCpuBurstTime() {
        return cpuBurstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimeProgressed() {
        return timeProgressed;
    }

    public void setTimeProgressed(int timeProgressed) {
        if (timeProgressed > cpuBurstTime) {
            this.timeProgressed = this.cpuBurstTime;
        } else {
            this.timeProgressed = timeProgressed;
        }
    }

    public int getTimeWaiting() {
        return timeWaiting;
    }

    public void setTimeWaiting(boolean isActive, int cpuTime) {
        if (isActive) {
            timeWaiting += cpuTime - arrivalTime;
        } else {

        }
    }
}

class ProcessArrivalComparator implements Comparator<Process> {
    @Override
    public int compare(Process o1, Process o2) {
        return o1.getArrivalTime() - o2.getArrivalTime();
    }
}

class ProcessShortestJobComparator implements Comparator<Process> {
    @Override
    public int compare(Process o1, Process o2) {
        return (o1.getCpuBurstTime() - o1.getTimeProgressed()) - (o2.getCpuBurstTime() - o2.getTimeProgressed());
    }
}


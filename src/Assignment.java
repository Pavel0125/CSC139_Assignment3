import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Assignment {

    public static void main(String[] args) {

        try {
            File inputFile = new File("src/input2.txt");
            BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter("output.txt"));
            String algorithm = inputReader.readLine();
            int numOfProcesses = Integer.parseInt(inputReader.readLine());
            LinkedList<Process> processList = new LinkedList();
            for (int i = 0; i < numOfProcesses; i++) {
                processList.add(convertLineToProcess(inputReader.readLine()));
            }
            if (algorithm.contains("RR")) {
                roundRobin(inputReader, outputWriter, processList, Character.getNumericValue(algorithm.charAt(algorithm.length()-1)));
            } else if (algorithm.equalsIgnoreCase("SJF")) {
                shortestJobFirst(inputReader, outputWriter, processList);
            } else if (algorithm.equalsIgnoreCase("PR_noPREMP")) {

            } else if (algorithm.equalsIgnoreCase("PR_withPREMP")) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void roundRobin(BufferedReader inputReader, BufferedWriter outputWriter, LinkedList<Process> processList, int timeQuantum) throws IOException {
        int cpuTime = 0;
        System.out.println("RR " + timeQuantum);
        processList.sort(new ProcessArrivalComparator());

        while (processList.size() > 0) {
            Process currentProcess = processList.getFirst();
            System.out.printf("%d %d\n", cpuTime, currentProcess.getPid());
            if (currentProcess.getTimeProgressed() + timeQuantum > currentProcess.getCpuBurstTime()) {
                cpuTime += currentProcess.getCpuBurstTime() - currentProcess.getTimeProgressed();
            } else {
                cpuTime += timeQuantum;
            }
            currentProcess.setTimeProgressed(currentProcess.getTimeProgressed() + timeQuantum);
            if (currentProcess.getTimeProgressed() < currentProcess.getCpuBurstTime()) {
                processList.removeFirst();
                processList.addLast(currentProcess);
            } else {
                processList.removeFirst();
            }
        }
        calculateWaitTime();
    }

    private static void shortestJobFirst(BufferedReader inputReader, BufferedWriter outputwriter, LinkedList<Process> processList) {
        int cpuTime = 0;
        int processDoneCounter = 0;
        System.out.println("SJF");
        processList.sort(new ProcessArrivalComparator());
        LinkedList<Process> activeProcesses = new LinkedList();

        while (processDoneCounter < processList.size()) {
            if (cpuTime == processList.getFirst().getArrivalTime()) {
                activeProcesses.add(processList.getFirst());
                processList.removeFirst();
                activeProcesses.sort(new ProcessShortestJobComparator());
            } else {
                cpuTime++;
                processList.getFirst();
            }
        }
    }

    private static Process convertLineToProcess(String line) {
        String[] splitLine = line.split(" ");
        return new Process(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]));
    }

    private static void calculateWaitTime() {

    }
}

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Assignment {

    public static void main(String[] args) {

        try {
            File inputFile = new File("src/input1.txt");
            BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter("output.txt"));
            String algorithm = inputReader.readLine();
            int numOfProcesses = Integer.parseInt(inputReader.readLine());
            if (algorithm.contains("RR")) {
                roundRobin(inputReader, outputWriter, numOfProcesses, Character.getNumericValue(algorithm.charAt(algorithm.length()-1)));
            } else if (algorithm.equalsIgnoreCase("SJF")) {
                shortestJobFirst(inputReader, outputWriter, numOfProcesses);
            } else if (algorithm.equalsIgnoreCase("PR_noPREMP")) {

            } else if (algorithm.equalsIgnoreCase("PR_withPREMP")) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void roundRobin(BufferedReader inputReader, BufferedWriter outputWriter, int numOfProcesses, int timeQuantum) throws IOException {
        int cpuTime = 0;
        System.out.println("RR " + timeQuantum);
        LinkedList<Process> processList = new LinkedList();
        for (int i = 0; i < numOfProcesses; i++) {
            processList.add(convertLineToProcess(inputReader.readLine()));
        }
        processList.sort(new ProcessArrivalComparator());

        while (processList.size() > 0) {
            Process currentProcess = processList.getFirst();
            System.out.printf("%d %d\n", cpuTime, currentProcess.getPid());
            cpuTime += timeQuantum;
            currentProcess.setTimeProgressed(cpuTime);
            if (currentProcess.getTimeProgressed() < currentProcess.getCpuBurstTime()) {
                processList.removeFirst();
                processList.addLast(currentProcess);
            } else {
//                cpuTime =
                processList.removeFirst();
            }
        }
    }

    private static void shortestJobFirst(BufferedReader inputReader, BufferedWriter outputwriter, int numOfProcesses) {

    }

    private static Process convertLineToProcess(String line) {
        String[] splitLine = line.split(" ");
        return new Process(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]));
    }

    private static void calculateWaitTime() {

    }
}

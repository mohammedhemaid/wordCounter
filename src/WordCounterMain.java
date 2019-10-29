import java.io.*;

public class WordCounterMain {

    private static final String WORD_PER_MINUTE = "Words Per minute: ";
    private static int bigFileMinutes = 42;
    private static int smallFileMinutes = 22;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReaderBigFile = readFile("bigFile.txt");
        BufferedReader bufferedReaderSmallFile = readFile("smallFile.txt");

        int bigWordsPerMinute = getWordsPerMinute(bufferedReaderBigFile, bigFileMinutes);

        int smallWordsPerMinute = getWordsPerMinute(bufferedReaderSmallFile, smallFileMinutes);


        System.out.println("average " + (smallWordsPerMinute + bigWordsPerMinute) / 2);
    }

    private static int getWordsPerMinute(BufferedReader bufferedReaderBigFile, int minutes) throws IOException {
        int counterBig = getCounter(bufferedReaderBigFile);
        int bigWordPerMinute = counterBig / minutes;
        System.out.println(WORD_PER_MINUTE + bigWordPerMinute);
        return bigWordPerMinute;
    }

    private static BufferedReader readFile(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        return new BufferedReader(new InputStreamReader(fis));
    }

    private static int getCounter(BufferedReader bufferedReader) throws IOException {
        int counter = 0;

        FileOutputStream fos = new FileOutputStream("Out.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));

        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            String withoutSpaces = line.replaceAll("\\s+", " ");
            if (line.length() != 0 && line.length() != 1 && !line.matches(".*\\d.*")) {
                String[] arraysOfString = withoutSpaces.split(" ");
                counter += arraysOfString.length;
                bufferedWriter.write(withoutSpaces + "\n");
            }

        }
        return counter;
    }
}

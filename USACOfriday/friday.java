import java.io.*;
import java.util.*;
/*
ID: wibison1
LANG: JAVA
TASK: friday
*/

class friday {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // declarations
        BufferedReader f = new BufferedReader(new FileReader("friday.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        final int initialYear = 1900;
        int[] fridayTheThirteenths = new int[7]; // 0sat, 1sun, 2mon, 3tue, 4wed, 5thr, 6fri

        // read input file
        int numOfYears = Integer.parseInt(st.nextToken());

        // process
        int current_year = initialYear;
        int elapsedDays = 0;
        for (int i = 0; i < numOfYears; i++) {
            for (int month = 1; month <= 12; month++) {
                int numOfDaysThirteenth = elapsedDays + 13;
                switch (numOfDaysThirteenth % 7) {
                    case 1:
                        fridayTheThirteenths[2] += 1;
                        break;
                    case 2:
                        fridayTheThirteenths[3] += 1;
                        break;
                    case 3:
                        fridayTheThirteenths[4] += 1;
                        break;
                    case 4:
                        fridayTheThirteenths[5] += 1;
                        break;
                    case 5:
                        fridayTheThirteenths[6] += 1;
                        break;
                    case 6:
                        fridayTheThirteenths[0] += 1;
                        break;
                    case 0:
                        fridayTheThirteenths[1] += 1;
                        break;
                }
                elapsedDays += daysInMonth(month, daysInYear(current_year));
            }
            current_year += 1;
        }

        out.println(fridayTheThirteenths[0] + " " + fridayTheThirteenths[1] + " " + fridayTheThirteenths[2] + " "
                + fridayTheThirteenths[3] + " " + fridayTheThirteenths[4] + " " + fridayTheThirteenths[5] + " "
                + fridayTheThirteenths[6]);
        out.close();
        f.close();
    }

    public static int daysInMonth(int month, int numOfDaysInYear) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 2) {
            if (numOfDaysInYear == 366) {
                return 29;
            } else {
                return 28;
            }
        } else {
            return 30;
        }
    }

    public static int daysInYear(int year) {
        if (year % 4 == 0 && year != 1990) {
            if (year % 100 == 0 && year != 2000) {
                return 365;
            } else {
                return 366;
            }
        } else {
            return 365;
        }
    }
}
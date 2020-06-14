import java.io.*;
import java.util.*;
/*
ID: wibison1
LANG: JAVA
TASK: gift1
*/

class gift1 {
    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

        // initialise hashmap
        Map<String, Integer> peoples = new HashMap<String, Integer>();

        // get input from file
        int numOfPeople = Integer.parseInt(f.readLine());
        List<String> nameOrder = new ArrayList<String>();
        for (int i = 0; i < numOfPeople; i++) {
            String name = f.readLine();
            nameOrder.add(name);
            peoples.put(name, 0);
        }

        try {
            while (true) {
                peoples = runOperation(f, peoples);
            }
        } catch (Exception e) {
            for (String name : nameOrder) {
                out.println(name + " " + peoples.get(name));
            }
        }
        out.close();
    }

    public static Map<String, Integer> runOperation(BufferedReader f, Map<String, Integer> peoples) throws IOException {
        String sourcePeople = f.readLine();
        StringTokenizer st = new StringTokenizer(f.readLine());
        int amountOfMoney = Integer.parseInt(st.nextToken());
        int numOfPeople = Integer.parseInt(st.nextToken());
        if (numOfPeople != 0) {
            int susuk = amountOfMoney % numOfPeople;
            int amountOfMoneyPerPeople = amountOfMoney / numOfPeople;

            peoples.put(sourcePeople, peoples.get(sourcePeople) - amountOfMoney + susuk);
            for (int i = 0; i < numOfPeople; i++) {
                String targetPeople = f.readLine();
                peoples.put(targetPeople, peoples.get(targetPeople) + amountOfMoneyPerPeople);
            }
        }
        return peoples;
    }
}
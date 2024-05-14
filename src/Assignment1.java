//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.time.Duration;
//import java.time.Instant;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Assignment1 {
//
//    /**I, Ahmet Aydogan, 000792453 certify that this material is my original work.
//    No other person's work has been used without due acknowledgement. **/
//
//    //This method finds the lowest value and how many times it repeats
//    void findMode(int[][] theArray) {
//        int placeholder = theArray[0][2]; //placeholder is set to the maximum value of the dataset
//        int ticker = 0; //ticker to count how many matches are found
//        for (int row = 1; row < theArray[0][0]; row++) {
//            //double for loop goes through entire dataset and compares
//            //each value to the placeholder sees if it is less than the value of the placeholder
//            //replaces the placeholder value with the one that met the criteria at the index
//            for (int col = 0; col < theArray[0][1]; col++) {
//                if (theArray[row][col] < placeholder) {
//                    placeholder = theArray[row][col];
//                    ticker = 1;
//                } else if (theArray[row][col] == placeholder) {
//                    ticker += 1;
//                }
//            }
//        }
//        System.out.println("The lowest value currently is: " + placeholder + " and repeats " + ticker + " times");
//    }
//
//    void localPeaks(int[][] theArray) {
//        //initialized a peaks array, made it really really large to accommodate for all possible values
//        int[] peaks = new int[theArray.length * theArray.length];
//        int peakCounter = 0;
//        for (int row = 13; row < theArray.length - 13; row++) {
//            //double for loop again goes through entire array,
//            //dataset has a exclusion radius of 13 so, this for loop
//            //starts 13 indexes in and stops 13 indexes before the end of the dataset
//            for (int col = 13; col < theArray.length - 13; col++) {
//                if (theArray[row][col] >= 111300) {
//                    boolean isPeak = true;
//                    //if a peak is found, it sets a boolean value to true, another for loop is executed
//                    //since every peak has its own exclusion radius, when a peak is found it looks in 13 indexes in
//                    //all directions to ensure validity
//                    for (int exRow = row - 13; exRow <= row + 13; exRow++) {
//                        for (int exCol = col - 13; exCol <= col + 13; exCol++) {
//                            if (exCol == col && exRow == row) {
//                                continue;
//                            }
//                            if (theArray[exRow][exCol] >= theArray[row][col]) {
//                                //if another peak is found in the radius, boolean isPeak is set to false
//                                //and the loop is broken
//                                isPeak = false;
//                                break;
//                            }
//                        }
//                        if (!isPeak) {
//                            break;
//                        }
//                    }
//                    if (isPeak) { //if all criteria are met, the index selected gets added to the peaks array, and the counter is added 1.
//                        peaks[peakCounter] = theArray[row][col];
//                        peakCounter += 1;
//                    }
//                }
//            }
//        }
//        //this filters out all the 0 values in the array and shrinks the array size down to a perfect size
//        int[] filteredArray = Arrays.stream(peaks).filter(num -> num != 0).toArray();
//        //System.out.println("The peaks are: " + Arrays.toString(filteredArray));
//        for (int x : filteredArray) {
//            System.out.println("Peak: " + x);
//
//        }
//        System.out.println(peakCounter);
//    }
//
//
//    //The code is the same from the previous method
//    //until the next line of comments is introduced.
//    void findPeakDistance(int[][] theArray) {
//        int ticker = 0;
//        int[] rowsIndex = new int[theArray.length * theArray.length];
//        int[] colsIndex = new int[theArray.length * theArray.length];
//        String[] peakIndexes = new String[theArray.length];
//        int[] peaks = new int[theArray.length * theArray.length];
//        int peakCounter = 0;
//        for (int row = 13; row < theArray.length - 13; row++) {
//            for (int col = 13; col < theArray.length - 13; col++) {
//                if (theArray[row][col] >= 111300) {
//                    boolean isPeak = true;
//                    for (int exRow = row - 13; exRow <= row + 13; exRow++) {
//                        for (int exCol = col - 13; exCol <= col + 13; exCol++) {
//                            if (exCol == col && exRow == row) {
//                                continue;
//                            }
//                            if (theArray[exRow][exCol] >= theArray[row][col]) {
//                                isPeak = false;
//                                break;
//                            }
//                        }
//                        if (!isPeak) {
//                            break;
//                        }
//                    }
//                    if (isPeak) {
//                        peaks[peakCounter] = theArray[row][col];
//                        peakCounter += 1;
//                        rowsIndex[ticker] = row; //the row index of the valid peak is added to the rowsIndex array
//                        colsIndex[ticker] = col; //the col index of the valid peak is added to the colsIndex array
//                        //System.out.println("Row: "+rowsIndex[ticker] + " Col: "+colsIndex[ticker] + " Elevation: " + peaks[ticker]);
//                        ticker += 1;
//                    }
//                }
//            }
//        }
//
//        //Both arrays are are filtered out of their zeroes.
//        int[] filteredArray = Arrays.stream(peaks).filter(num -> num != 0).toArray();
//        int[] filteredRow = Arrays.stream(rowsIndex).filter(num -> num != 0).toArray();
//        int[] filteredCol = Arrays.stream(colsIndex).filter(num -> num != 0).toArray();
//        //Placeholder is equivalent to the distance equation to the first valid index outside of the exclusion radius
//        double placeholder = Math.sqrt(Math.pow((filteredRow[0] - filteredRow[1]), 2) + Math.pow((filteredCol[0] - filteredCol[1]), 2));
//        double[] lowestValue = new double[1];
//        lowestValue[0] = Math.sqrt(Math.pow((filteredRow[0] - filteredRow[1]), 2) + Math.pow((filteredCol[0] - filteredCol[1]), 2));
//        int ticker2 = 0; //these tickers are made to loop through the row of one peak and the row of another peak without the need for multiple loops.
//        int ticker3 = 1;
//        for (int i = 0; i < filteredRow.length; i++) {
//            if (placeholder != 0 && ticker2 <= filteredRow.length - 1 && ticker3 <= filteredRow.length - 1) {
//                lowestValue[0] = Math.sqrt(Math.pow((filteredRow[ticker2] - filteredRow[ticker3]), 2) + Math.pow((filteredCol[ticker2] - filteredCol[ticker3]), 2));
//                if (lowestValue[0] <= placeholder) {
//                    placeholder = lowestValue[0];
//                    System.out.println("The shortest distance between 2 peaks comes from Row: " + filteredRow[ticker2] + " Col: " + filteredCol[ticker2] +
//                            " with elevation of " + theArray[filteredRow[ticker2]][filteredCol[ticker2]] + " and Row : " + filteredRow[ticker3] + " Col: " + filteredCol[ticker3] +
//                            " with elevation of " + theArray[filteredRow[ticker3]][filteredCol[ticker3]]);
//                    ticker2 += 1;
//                    ticker3 += 1;
//
//                } else {
//                    ticker2 += 1;
//                    ticker3 += 1;
//                }
//            }
//        }
//    }
//
//    int findCommonVal(int[] single) {
//        int maxValue = 0;
//        int maxCount = 0;
//
//        for (int j : single) {
//            int count = 0;
//            for (int k : single) {
//                if (j == k) {
//                    count++;
//                }
//            }
//            if (count > maxCount) {
//                maxCount = count;
//                maxValue = j;
//            }
//        }
//        System.out.println(maxValue + " occurs " + maxCount + " times.");
//        return maxValue;
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(new BufferedReader(new FileReader("/Users/ahmetaydogan/IdeaProjects/Assignment1(2023)/src/ELEVATIONS-1.TXT")));
//        //Scanner sc = new Scanner(new BufferedReader(new FileReader("/Users/ahmetaydogan/IdeaProjects/Assignment1(2023)/src/tenByten.TXT")));
//        int[][] myArray = new int[601][1001];
//        //int[][] myArray = new int[11][11];
//        while (sc.hasNextLine()) {
//            for (int i = 0; i < myArray.length; i++) {
//                String[] line = sc.nextLine().trim().split(" ");
//                for (int j = 0; j < line.length; j++) {
//                    myArray[i][j] = Integer.parseInt(line[j]);
//                }
//            }
//        }
//        int singleRowArray = 0;
//        int[] single = new int[myArray[0][0] * myArray[0][1]];
//        //int[] single2 = new int[theArray[0][0] * theArray[0][1]];
//        int ticker = 0;
//        int maxValue = 0;
//        int maxCount = 0;
//        for (int row = 1; row < myArray[0][0] + 1; row++){
//            for(int col = 0; col < myArray[0][1]; col++){
//                singleRowArray = myArray[row][col];
//                single[ticker] = singleRowArray;
//                ticker+=1;
//            }
//        }
//
//        Instant start = Instant.now();
//        Assignment1 a1 = new Assignment1();
//        a1.findMode(myArray);
//        a1.localPeaks(myArray);
//        a1.findPeakDistance(myArray);
//        a1.findCommonVal(single);
//        System.out.println(a1.findCommonVal(single));
//        Instant end = Instant.now();
//        Duration timeElapsed = Duration.between(start,end);
//        System.out.println("total execution time: " + timeElapsed.toNanos());
//
//    }
//}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Scanner;

public class Assignment1 {

    /**I, Ahmet Aydogan, 000792453 certify that this material is my original work.
     No other person's work has been used without due acknowledgement. **/

    //This method finds the lowest value and how many times it repeats
    void findMode(int[][] theArray) {
        int placeholder = theArray[0][2]; //placeholder is set to the maximum value of the dataset
        int ticker = 0; //ticker to count how many matches are found
        for (int row = 1; row < theArray[0][0]; row++) {
            //double for loop goes through entire dataset and compares
            //each value to the placeholder sees if it is less than the value of the placeholder
            //replaces the placeholder value with the one that met the criteria at the index
            for (int col = 0; col < theArray[0][1]; col++) {
                if (theArray[row][col] < placeholder) {
                    placeholder = theArray[row][col];
                    ticker = 1;
                } else if (theArray[row][col] == placeholder) {
                    ticker += 1;
                }
            }
        }
//        System.out.println("The lowest value currently is: " + placeholder + " and repeats " + ticker + " times");
    }

    void localPeaks(int[][] theArray) {
        //initialized a peaks array, made it really really large to accommodate for all possible values
        int[] peaks = new int[theArray.length * theArray.length];
        int peakCounter = 0;
        for (int row = 13; row < theArray.length - 13; row++) {
            //double for loop again goes through entire array,
            //dataset has a exclusion radius of 13 so, this for loop
            //starts 13 indexes in and stops 13 indexes before the end of the dataset
            for (int col = 13; col < theArray.length - 13; col++) {
                if (theArray[row][col] >= 111300) {
                    boolean isPeak = true;
                    //if a peak is found, it sets a boolean value to true, another for loop is executed
                    //since every peak has its own exclusion radius, when a peak is found it looks in 13 indexes in
                    //all directions to ensure validity
                    for (int exRow = row - 13; exRow <= row + 13; exRow++) {
                        for (int exCol = col - 13; exCol <= col + 13; exCol++) {
                            if (exCol == col && exRow == row) {
                                continue;
                            }
                            if (theArray[exRow][exCol] >= theArray[row][col]) {
                                //if another peak is found in the radius, boolean isPeak is set to false
                                //and the loop is broken
                                isPeak = false;
                                break;
                            }
                        }
                        if (!isPeak) {
                            break;
                        }
                    }
                    if (isPeak) { //if all criteria are met, the index selected gets added to the peaks array, and the counter is added 1.
                        peaks[peakCounter] = theArray[row][col];
                        peakCounter += 1;
                    }
                }
            }
        }
        //this filters out all the 0 values in the array and shrinks the array size down to a perfect size
        int[] filteredArray = Arrays.stream(peaks).filter(num -> num != 0).toArray();
        //System.out.println("The peaks are: " + Arrays.toString(filteredArray));
        for (int x : filteredArray) {
//            System.out.println("Peak: " + x);

        }
//        System.out.println(peakCounter);
    }


    //The code is the same from the previous method
    //until the next line of comments is introduced.
    void findPeakDistance(int[][] theArray) {
        int ticker = 0;
        int[] rowsIndex = new int[theArray.length * theArray.length];
        int[] colsIndex = new int[theArray.length * theArray.length];
        String[] peakIndexes = new String[theArray.length];
        int[] peaks = new int[theArray.length * theArray.length];
        int peakCounter = 0;
        for (int row = 13; row < theArray.length - 13; row++) {
            for (int col = 13; col < theArray.length - 13; col++) {
                if (theArray[row][col] >= 111300) {
                    boolean isPeak = true;
                    for (int exRow = row - 13; exRow <= row + 13; exRow++) {
                        for (int exCol = col - 13; exCol <= col + 13; exCol++) {
                            if (exCol == col && exRow == row) {
                                continue;
                            }
                            if (theArray[exRow][exCol] >= theArray[row][col]) {
                                isPeak = false;
                                break;
                            }
                        }
                        if (!isPeak) {
                            break;
                        }
                    }
                    if (isPeak) {
                        peaks[peakCounter] = theArray[row][col];
                        peakCounter += 1;
                        rowsIndex[ticker] = row; //the row index of the valid peak is added to the rowsIndex array
                        colsIndex[ticker] = col; //the col index of the valid peak is added to the colsIndex array
                        //System.out.println("Row: "+rowsIndex[ticker] + " Col: "+colsIndex[ticker] + " Elevation: " + peaks[ticker]);
                        ticker += 1;
                    }
                }
            }
        }

        //Both arrays are are filtered out of their zeroes.
        int[] filteredArray = Arrays.stream(peaks).filter(num -> num != 0).toArray();
        int[] filteredRow = Arrays.stream(rowsIndex).filter(num -> num != 0).toArray();
        int[] filteredCol = Arrays.stream(colsIndex).filter(num -> num != 0).toArray();
        //Placeholder is equivalent to the distance equation to the first valid index outside of the exclusion radius
        double placeholder = Math.sqrt(Math.pow((filteredRow[0] - filteredRow[1]), 2) + Math.pow((filteredCol[0] - filteredCol[1]), 2));
        double[] lowestValue = new double[1];
        lowestValue[0] = Math.sqrt(Math.pow((filteredRow[0] - filteredRow[1]), 2) + Math.pow((filteredCol[0] - filteredCol[1]), 2));
        int ticker2 = 0; //these tickers are made to loop through the row of one peak and the row of another peak without the need for multiple loops.
        int ticker3 = 1;
        for (int i = 0; i < filteredRow.length; i++) {
            if (placeholder != 0 && ticker2 <= filteredRow.length - 1 && ticker3 <= filteredRow.length - 1) {
                lowestValue[0] = Math.sqrt(Math.pow((filteredRow[ticker2] - filteredRow[ticker3]), 2) + Math.pow((filteredCol[ticker2] - filteredCol[ticker3]), 2));
                if (lowestValue[0] <= placeholder) {
                    placeholder = lowestValue[0];
                    System.out.println("The shortest distance between 2 peaks comes from Row: " + filteredRow[ticker2] + " Col: " + filteredCol[ticker2] +
                            " with elevation of " + theArray[filteredRow[ticker2]][filteredCol[ticker2]] + " and Row : " + filteredRow[ticker3] + " Col: " + filteredCol[ticker3] +
                            " with elevation of " + theArray[filteredRow[ticker3]][filteredCol[ticker3]]);
                    ticker2 += 1;
                    ticker3 += 1;

                } else {
                    ticker2 += 1;
                    ticker3 += 1;
                }
            }
        }
    }

    void findCommonVal(int[][] theArray) {
        //couldnt figure it out :(
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("/Users/ahmetaydogan/IdeaProjects/Assignment1(2023)/src/ELEVATIONS-1.TXT")));
        //Scanner sc = new Scanner(new BufferedReader(new FileReader("/Users/ahmetaydogan/IdeaProjects/Assignment1(2023)/src/tenByten.TXT")));
        int[][] myArray = new int[601][1001];
        //int[][] myArray = new int[11][11];
        while (sc.hasNextLine()) {
            for (int i = 0; i < myArray.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    myArray[i][j] = Integer.parseInt(line[j]);
                }
            }
        }

        Instant start = Instant.now();
        Assignment1 a1 = new Assignment1();
        a1.findMode(myArray);
        a1.localPeaks(myArray);
        a1.findPeakDistance(myArray);
        a1.findCommonVal(myArray);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start,end);
        System.out.println("total execution time: " + timeElapsed.toNanos());

    }
}

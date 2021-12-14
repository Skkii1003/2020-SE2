// -*- Java -*-
/*
 * <copyright>
 *
 *  Copyright (c) 2002
 *  Institute for Information Processing and Computer Supported New Media (IICM),
 *  Graz University of Technology, Austria.
 *
 * </copyright>
 *
 * <file>
 *
 *  Name:    KWIC.java
 *
 *  Purpose: Demo system for practice in Software Architecture
 *
 *  Created: 11 Sep 2002
 *
 *  $Id$
 *
 *  Description:
 *    The basic KWIC system is defined as follows. The KWIC system accepts an ordered
 *  set of lines, each line is an ordered set of words, and each word is an ordered set
 *  of characters. Any line may be "circularly shifted" by repeadetly removing the first
 *  word and appending it at the end of the line. The KWIC index system outputs a
 *  listing of all circular shifts of all lines in alphabetical order.
 * </file>
 */

//package kwic.ms;

/*
 * $Log$
 */

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 *  This class is an implementation of the main/subroutine architectural solution 
 *  for the KWIC system. This solution is based on functional
 *  decomposition of the system. Thus, the system is decomposed into a number of 
 *  modules, each module being a function. In this solution all functions share access 
 *  to data, which is stored in the "core storage". The system is decomposed into the 
 *  following modules (functions):
 *  <ul>
 *  <li>Master Control (main). This function controls the sequencing among the
 *  other four functions.
 *  <li>Input. This function reads the data lines from the input medium (file) and
 *  stores them in the core for processing by the remaining modules. The characters are
 *  stored in a character array (char[]). The blank space is used to separate words in 
 *  a particular line. Another integer array (int[]) keeps the starting indices of 
 *  each line in the character array.
 *  <li>Circular Shift. This function is called after the input function has
 *  completed its work. It prepares a two-dimensional integer array (int[][]) to keep
 *  track of all circular shifts. The array is organized as follows: for each circular
 *  shift, both index of its original line, together with the index of the starting word of
 *  that circular shift are stored as one column of the array.
 *  <li>Alphabetizing. This function takes as input the arrays produced by the input
 *  and circular shift functions. It produces an array in the same format (int[][]) 
 *  as that produced by circular shift function. In this case, however, the circular 
 *  shifts are listed in another order (they are sorted alphabetically).
 *  <li>Output. This function uses the arrays produced by input and alphabetizing
 *  function. It produces a nicely formatted output listing of all circular shifts.
 *  </ul>
 *  @author  dhelic
 *  @version $Id$
 */

public class KWIC{

//----------------------------------------------------------------------
/*
 * Fields
 *
 */
//----------------------------------------------------------------------

    /*
     * Core storage for shared data
     *
     */

    /**
     * Input characters
     *
     */

    private char[] chars_;

    /**
     * Array that keeps line indices (line index is the index of the first character of a line)
     *
     */

    private int[] line_index_;

    /**
     * 2D array that keeps circular shift indices (each circular shift index is a column
     * in this 2D array, the first index is the index of the original line from line_index_
     * array, the second index is the index of the starting word from chars_ array of
     * that circular shift)
     *
     */

    private int[][] circular_shifts_;

    /**
     * 2D array that keeps circular shift indices, sorted alphabetically
     *
     */

    private int[][] alphabetized_;

//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/*
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
    /**
     * Input function reads the raw data from the specified file and stores it in the core storage.
     * If some system I/O error occurs the program exits with an error message.
     * The format of raw data is as follows. Lines are separated by the line separator
     * character(s) (on Unix '\n', on Windows '\r\n'). Each line consists of a number of
     * words. Words are delimited by any number and combination of the space character (' ')
     * and the horizontal tabulation character ('\t'). The entered data is parsed in the
     * following way. All line separators are removed from the data, all horizontal tabulation
     * word delimiters are replaced by a single space character, and all multiple word
     * delimiters are replaced by a single space character. Then the parsed data is represented
     * in the core as two arrays: chars_ array and line_index_ array.
     * @param file Name of input file
     */

    public void input(String file) {
        try {
            FileReader fr = new FileReader(file);
            char[] input = new char[10000];
            ArrayList<Character> parse = new ArrayList<Character>();
            ArrayList<Integer> index = new ArrayList<Integer>();
            int pos = fr.read(input);
            while (pos != -1) {
                for (int i = 0; i < pos; i++) {
                    if (input[i] == '\r'||input[i]=='\n')
                        continue;
                    else if (input[i] == '\t') {
                        if (i > 1 && parse.get(parse.size() - 1) == ' ') {
                            continue;
                        } else {
                            parse.add(' ');
                        }
                    }
                    else if(input[i] == ' '){
                        if (i > 1 && parse.get(parse.size() - 1) == ' ') {
                            continue;
                        }
                        else if(parse.get(parse.size()-1)!=' '&&input[i+1]=='\r')
                            continue;
                        else {
                            parse.add(input[i]);
                        }
                    }
                    else {
                        parse.add(input[i]);
                    }
                    if (i == 0) {
                        index.add(0);
                    }
                    if (i > 1 && input[i - 1] == '\n') {
                        index.add(parse.size()-1);
                    }
                }
                pos = fr.read(input);
            }
            chars_ = new char[parse.size()];
            for(int i=0;i<parse.size();i++){
                chars_[i] = parse.get(i);
            }
            line_index_ = new int[index.size()];
            for(int i=0;i<index.size();i++)
                line_index_[i] = index.get(i);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

//----------------------------------------------------------------------
    /**
     * This function processes arrays prepared by the input
     * function and produces circular shifts of the stored lines. A circular
     * shift is a line where the first word is removed from the begin of a line
     * and appended at the end of the line. To obtain all circular shifts of a line
     * we repeat this process until we can't obtain any new lines. Circular shifts
     * are represented as a 2D array that keeps circular shift indices (each circular
     * shift index is a column in this 2D array, the first index is the index of
     * the original line from line_index_ array, the second index is the index of
     * the starting word from chars_ array of that circular shift)
     */

    public void circularShift(){
        int size = line_index_.length;
        int lines = 0;
        for(int i=0;i<size;i++){
            int low = line_index_[i];
            int high;
            int space = 0;
            if(i==size-1)
                high = chars_.length;
            else
                high = line_index_[i+1];

            while(low<high){
                if (chars_[low]==' ')
                    space++;
                low++;
            }
            lines = lines + space + 1;
        }


        circular_shifts_ = new int[lines][100];
        int index = 0;
        for (int i=0;i<size;i++){
            int low = line_index_[i];
            int high;
            if(i==size-1)
                high = chars_.length;
            else
                high = line_index_[i+1];

            //original case
            for (int j=0;j<high-low;j++){
                circular_shifts_[index][j] = low + j;
            }


            index++;
            if(index==lines)
                break;

            int mid = low;

            while(mid<high){

                if(mid==low) {
                    while (mid < high && chars_[mid] != ' ')
                        mid++;
                }
                if(mid>=high)
                    continue;
                else
                    mid++;
                int space = mid -1;
                int s = high - mid;
                int x = 0;
                int p = mid - low - 1;
                for(int j=0;j<s;j++){
                    if (mid>=high||chars_[mid]==' '){
                        break;
                    }
                    circular_shifts_[index][x] = mid;
                    x++;
                    mid++;
                }
                for(int j=0;j<high-mid;j++){
                    circular_shifts_[index][x] = mid + j;
                    x++;
                }
                circular_shifts_[index][x] = space;
                x++;
                for(int j=0;j<p;j++){
                    circular_shifts_[index][x] = low + j;
                    x++;
                }
                index++;
            }
        }
    }

//----------------------------------------------------------------------
    /**
     * This function sorts circular shifts lines alphabetically. The sorted shifts
     * are represented in the same way as the unsorted shifts with the only difference
     * that now they are ordered alphabetically. This function implements binary search
     * to sort the shifts.
     */

    public void alphabetizing(){
        int size = circular_shifts_.length;
        alphabetized_ = new int[size][100];
        alphabetized_[0] = circular_shifts_[0];
        for (int i=1;i<size;i++){
            int low = 0;
            int high = i-1;
            int mid = -1;
            while(low <= high){
                int i2=0;
                mid = low + (high - low) / 2;
                int i1 = 0;
                String tmp = "";
                while(i1<circular_shifts_[i].length &&chars_[circular_shifts_[i][i1]]!=' '){
                    if (circular_shifts_[i][i1]==0&&circular_shifts_[i][i1]==circular_shifts_[i][i1+1])
                        break;
                    tmp = tmp + chars_[circular_shifts_[i][i1]];
                    i1++;
                }
                String c = "";
                while(i2<alphabetized_[mid].length && chars_[alphabetized_[mid][i2]]!=' '){
                    if(alphabetized_[mid][i2]==0&&alphabetized_[mid][i2]==alphabetized_[mid][i2+1])
                        break;
                    c = c + chars_[alphabetized_[mid][i2]];
                    i2++;
                }
                while(true) {
                    if (compare(c,tmp)<0) {
                        low = mid + 1;
                        break;
                    }
                    else if (compare(c,tmp)>0) {
                        high = mid - 1;
                        break;
                    }
                    else{
                        i1++;
                        i2++;
                        tmp = "";
                        c = "";
                        if(i1>=circular_shifts_[i].length || i2>=alphabetized_[mid].length){
                            low = mid + 1;
                            break;
                        }
                        for(i1=i1;i1<circular_shifts_[i].length;i1++){
                            if (chars_[circular_shifts_[i][i1]]==' '||(circular_shifts_[i][i1]==0&&circular_shifts_[i][i1+1]==circular_shifts_[i][i1]))
                                break;
                            else{
                                tmp = tmp + chars_[circular_shifts_[i][i1]];
                            }
                        }
                        for(i2= i2;i2<alphabetized_[mid].length;i2++){
                            if (chars_[alphabetized_[mid][i2]]==' '||(alphabetized_[mid][i2]==0&&alphabetized_[mid][i2+1]==alphabetized_[mid][i2]))
                                break;
                            else
                                c = c + chars_[alphabetized_[mid][i2]];
                        }
                        if(tmp.equals("") && c.equals("")){
                            low = mid + 1;
                            break;
                        }
//                        if(c.equals("")&&!tmp.equals("")){
//                            low = mid + 1;
//                            break;
//                        }
//                        if(!c.equals("")&&tmp.equals("")){
//                            high = mid - 1;
//                            break;
//                        }
                    }
                }
            }


            int[] temp = circular_shifts_[i];

//            for(int j = circular_shifts_.length-1;j>low;j--){
//                alphabetized_[j] = alphabetized_[j-1];
//            }
//            alphabetized_[low] = temp;

            for(int j = i-1 ; j >=low ; j--){
                alphabetized_[j+1] = alphabetized_[j];
            }
            alphabetized_[low] = temp;
        }
    }
    public int compare(String c,String tmp){
        int len1 = c.length();
        int len2 = tmp.length();
        int i=0;
        while(true){
            if(i<len1&&i>=len2){
                return 1;
            }
            else if(i>=len1&&i<len2)
                return -1;
            else{
                if(i==len1&&i==len2)
                    return 0;

                if(c.charAt(i)>tmp.charAt(i))
                    return 1;
                else if(c.charAt(i)<tmp.charAt(i))
                    return -1;
                else{
                    i++;
                }
            }
        }
    }

//----------------------------------------------------------------------
    /**
     * This function prints the sorted shifts at the standard output.
     */

    public void output(){
        int size = alphabetized_.length;
        for(int i = 0;i<size;i++){
            for(int j=0;j<100;j++){
                if(alphabetized_[i][j]==alphabetized_[i][j+1]&&alphabetized_[i][j]==0)
                    break;
                else
                    System.out.print(chars_[alphabetized_[i][j]]);
            }
            System.out.println("");
        }
    }

//----------------------------------------------------------------------
    /**
     * This function controls all other functions in the system. It implements
     * the sequence of calls to other functions to obtain the desired functionality
     * of the system. Before any other function is called, main function checks the
     * command line arguments. The program expects exactly one command line argument
     * specifying the name of the file that contains the data. If the program have
     * not been started with proper command line arguments, main function exits
     * with an error message. Otherwise, the input function is called first to read the
     * data from the file. After that the circularShift and alphabetizing
     * functions are called to produce and sort the shifts respectively. Finally, the output
     * function prints the sorted shifts at the standard output.
     * @param args command line arguments
     */

    public static void main(String[] args){
        KWIC kwic = new KWIC();
        kwic.input("Test_Case.txt");
        kwic.circularShift();
        kwic.alphabetizing();
        kwic.output();
    }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}

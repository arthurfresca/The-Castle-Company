package com.agranee;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CastleCompany {

    public static void main(String[] args) {
        inputData();
    }

    public static void inputData(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter all elements. You must provide numbers separated by comma (ie: 1,3,4)");
        String input = sc.nextLine();
        generateResponse(input);

    }

    public static void generateResponse(String input) {
        try {
            List<Integer> listOfNumber = getIntegerList(input);
            removeDuplicates(listOfNumber);
            System.out.print("You can build "+countCastles(listOfNumber)+" castles on this land");
        } catch (NumberFormatException e) {
            System.out.print("Invalid input. You must provide numbers separated by comma (ie: 1,3,4)");
        }
    }

    private static List<Integer> getIntegerList(String input) {
        if(input == null){
            throw new NumberFormatException("");
        }
        return Arrays.stream(input.split(","))
                        .map(item -> Integer.parseInt(item.trim()))
                        .collect(Collectors.toList());
    }

    private static void removeDuplicates(List<Integer> list){
        int count = 0;
        for (Iterator<Integer> iter = list.listIterator(); iter.hasNext(); ) {
            Integer a = iter.next();
            if(count > 1 && a.equals(list.get(count-1))){
                count--;
                iter.remove();
            }
            count++;
        }
    }

    private static int countCastles(List<Integer> integerList){
        return (int) IntStream.range(2, integerList.size())
                .filter(i -> isPeak(integerList, i) || isValley(integerList, i))
                .count()+1;
    }

    private static boolean isPeak(List<Integer> integerList, int i) {
        return integerList.get(i-1) > integerList.get(i-2)
        && integerList.get(i-1) > integerList.get(i);
    }

    private static boolean isValley(List<Integer> integerList, int i) {
        return integerList.get(i-1) < integerList.get(i-2)
                && integerList.get(i-1) < integerList.get(i);
    }

}

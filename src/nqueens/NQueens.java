/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author DickD
 */
public class NQueens {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NQueens nqueens = new NQueens();
        List<List<Integer>> solutions = nqueens.solve(8).collect(Collectors.toList());
        //     System.out.println("Aantal solutions " + solutions.size());
        solutions.stream().forEach(System.out::println);
        // .forEach(System.out::println);
    }

    public Stream<List<Integer>> solve(int n) {
        return solve(n, n);
    }

    public Stream<List<Integer>> solve(int b, int n) {
        if (b == 0) {
            return Stream.of(new ArrayList<Integer>());
        }
        Stream<List<Integer>> results = solve(b - 1, n);
        return results.flatMap(result -> {
            return addQueen(result, n);
        }).filter(queens -> isValid(queens));
    }

    boolean isValid(List<Integer> queens) {
        return queens.stream().distinct().count() == queens.size()
                && queens.stream().map(row -> {
                    int column = queens.indexOf(row);
                    return row - column;
                }).distinct().count() == queens.size() && queens.stream().map(row -> {
                    int column = queens.indexOf(row);
                    return column + row;
                }).distinct().count() == queens.size();
    }

    Stream<List<Integer>> addQueen(List<Integer> existingQueens, int n) {
        return IntStream.range(0, n).boxed().map(row -> {
            List<Integer> newBoard = existingQueens.stream().collect(Collectors.toList());
            newBoard.add(row);
            return newBoard;
        });
    }
}

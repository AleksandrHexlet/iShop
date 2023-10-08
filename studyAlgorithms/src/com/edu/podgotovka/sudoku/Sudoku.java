package com.edu.podgotovka.sudoku;

import java.util.Arrays;

public class Sudoku {


    //      0   1   2   3   4   5   6   7   8   =indxCol
//indxRow
//  0 {'5','3','.','.','7','.','.','.','.'},
//  1 {'6','.','.','1','9','5','.','.','.'},
//  2 {'.','9','8','.','.','.','.','6','.'},
//  3 {'8','.','.','.','6','.','.','.','3'},
//  4 {'4','.','.','8','.','3','.','.','1'},
//  5 {'7','.','.','.','2','.','.','.','6'},
//  6 {'.','6','.','.','.','.','2','8','.'},
//  7 {'.','.','.','4','1','9','.','.','5'},
//  8 {'.','.','.','.','8','.','.','7','9'}};

//    =================================== variant №1 create in lessons===============================================
    public static boolean checkDigit(char[][] board, int indexRow, int indexCol, char valueHowAdd) {
//        check row, check col, check Subbox 3*3

        for (int i = 0; i < board.length; i++) {
            if (board[indexRow][i] == valueHowAdd) return false;//число должно быть уникальным в строке и столбце
            if (board[i][indexCol] == valueHowAdd) return false;
        }
//        indexRow = 5;      5 / 3 = 1  эту 1 * 3 = 3; 3-это номер ячейки в строке с которой начинаем поиск по квадрату 3*3
        int startRow = indexRow / 3 * 3;
        int endRow = startRow + 2;
//   indexCol = 5;      6 / 3 = 2  эту 2 * 3 = 6; 6-это номер ячейки в столбце с которой начинаем поиск по квадрату 3*3
        int startCol = indexCol / 3 * 3;
        int endCol = startCol + 2;

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (board[i][j] == valueHowAdd) return false;
            }
        }
        return true;
    }


    public static boolean setDigitInSudoku(char[][] board, int indexRow, int indexCol) {
        if (indexCol == 9) {
            indexRow++;
            indexCol = 0;
        }
        if (indexRow == 9) return true;

        if (board[indexRow][indexCol] != '.') {
            indexCol++;
            return setDigitInSudoku(board, indexRow, indexCol);
        } else {
            char number = '0';

            while (number <= '9') {
                if (checkDigit(board, indexRow, indexCol, number) == true) {
                    board[indexRow][indexCol] = number;
                    indexCol++;
                    return setDigitInSudoku(board, indexRow, indexCol);
                } else {
                    number++;
                }
            }
        }
        return false;
    }
    //    ================================================================================================================
    public static void solveSudoku(char[][] board) {
//        setDigitInSudoku(board, 0, 0);
        setNumberInBoard(board, 0, 0);
    }


    //    =================================== variant №2 create by yourself ===============================================
    public static boolean checkNum(char[][] board, int indexRow, int indexColumn, char numberToInsert) {
        for (int i = 0; i < board.length; i++) {
            if (board[indexRow][i] == numberToInsert) return false;
            if (board[i][indexColumn] == numberToInsert) return false;
        }

        int startRow = indexRow / 3 * 3;
        int endRow = startRow + 2;

        int startCol = indexColumn / 3 * 3;
        int endCol = startCol + 2;

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (board[i][j] == numberToInsert) return false;
            }
        }
        return true;
    }


    public static boolean setNumberInBoard(char[][] board, int indexRow, int indexColumn) {
        if (indexColumn == 9) {
            indexRow++;
            indexColumn = 0;
        }
        if (indexRow == 9) return true;
        if (board[indexRow][indexColumn] != '.') {
            indexColumn++;
            return setNumberInBoard(board, indexRow, indexColumn);
        } else {
            char number = '1';
            while (number <= '9') {
                if (checkNum(board, indexRow, indexColumn, number) == true) {
                    board[indexRow][indexColumn] = number;
                    indexColumn++;
                    return setNumberInBoard(board, indexRow, indexColumn);
                } else {
                    number++;
                    if (number >= '9') number = 1;
//                    РАЗНИЦА В ДВУХ ВАРИАНТАХ ТУТ !!!!!! в проверке number на строке 116
                }
            }
        }
        return false;

    }
//    ======================================================================================================

    public static void main(String[] args) {
        char[][] sudokuField = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        solveSudoku(sudokuField);
        for (char[] chars : sudokuField) {
            System.out.println(Arrays.toString(chars));
        }
    }


}

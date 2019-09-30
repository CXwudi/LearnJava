package com.cxwudi.leetcode;

/**
 * Created by CX无敌 on 2017-07-07.
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!word.equals("")){
                    if (word.charAt(0) == board[i][j]){
                        if (word.length() == 1 || recuve(board,word,0,i,j))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean recuve (char[][] board, String word, int i, int x, int y){ //int px, int py){
        if(x < 0 || y < 0 || x >= board.length || y >= board[x].length){
            return false;
        } else if (i == word.length() -1){
            return board[x][y] == word.charAt(word.length() -1);
        } else if (word.charAt(i) != board[x][y]){
            return false;
        } else {
            board[x][y] <<= 8;
            boolean flag = recuve(board,word,i+1,x-1,y) ||
                    recuve(board,word,i+1,x+1,y) ||
                    recuve(board,word,i+1,x,y-1) ||
                    recuve(board,word,i+1,x,y+1);
            board[x][y] >>= 8;
            return flag;
        }

    }

    public static void main(String[] args) {
        char a = 125; String b = "b";
        a <<= 8;
        System.out.println(a );
    }
    
}

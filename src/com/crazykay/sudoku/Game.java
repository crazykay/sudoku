package com.crazykay.sudoku;

import android.util.Log;


public class Game {
    private final String str = "003000400009000080600000000"
            + "000021000000700000000000000" + "008500000000000006100000000";

    private int shuduku[] = new int[9 * 9];
    private int used[][][] = new int[9][9][];// 存储每个单元格不可用的数字
    private int finalUsed[][][] = new int[9][9][1];//存储初始化游戏数据

    public Game() {
        shuduku = fromPuzzleString(str);
        calFinal();
        calAllUsedTiles();
    }

    // 计算初始的数字
    private void calFinal() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                finalUsed[x][y][0] = shuduku[y * 9 + x];
            }
        }
    }

    //判断是否为初始数据
    public boolean ifFinal(int x, int y) {
        if (finalUsed[x][y][0] == 0) {
            return false;
        } else {
            return true;
        }

    }

    //将清除的数据写入
    public boolean setClean(int x, int y) {
        setTile(x, y, 0);
        calAllUsedTiles();
        return true;
    }

    // 计算所有单元格中不可用 的数字
    public void calAllUsedTiles() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                used[x][y] = calUsedTiles(x, y);
            }
        }

    }

    // 取出某一单元格不可用的数据
    public int[] getUsedTilesByCoor(int x, int y) {
        return used[x][y];
    }

    // 计算某一单元格中不可用 的数字
    public int[] calUsedTiles(int x, int y) {

        int c[] = new int[9];

        for (int i = 0; i < 9; i++) {
            if (i == y)
                continue;
            int t = getTitle(x, i);
            if (t != 0)
                c[t - 1] = t;
        }

        for (int i = 0; i < 9; i++) {
            if (i == x)
                continue;
            int t = getTitle(i, y);
            if (t != 0)
                c[t - 1] = t;
        }

        int starX = (x / 3) * 3;
        int starY = (y / 3) * 3;
        for (int i = starX; i < starX + 3; i++) {
            for (int j = starY; j < starY + 3; j++) {
                if (i == x && j == y)
                    continue;
                int t = getTitle(i, j);
                if (t != 0)
                    c[t - 1] = t;
            }
        }

        int nused = 0;
        for (int t : c) {
            if (t != 0) {
                nused++;
            }
        }
        int c1[] = new int[nused];
        nused = 0;
        for (int t : c) {
            if (t != 0)
                c1[nused++] = t;
        }

        return c1;
    }

    protected boolean setTileIfValid(int x, int y, int value) {
        int tiles[] = getUsedTitles(x, y);
        if (value != 0) {
            for (int tile : tiles) {
                if (tile == value)
                    return false;
            }
        }
        setTile(x, y, value);
        calAllUsedTiles();
        return true;
    }

    private int[] getUsedTitles(int x, int y) {
        return used[x][y];
    }

    private void setTile(int x, int y, int value) {

        shuduku[y * 9 + x] = value;
    }

    // 返回九宫格坐标的对应数字
    private int getTitle(int x, int y) {

        return shuduku[y * 9 + x];
    }

    //返回初始化坐标对应的数据
    private int getFinalTitle(int x, int y) {
        return finalUsed[x][y][0];
    }

    //返回初始化数据对应坐标
    public String getFinalTitleString(int x, int y) {
        int v = getFinalTitle(x, y);
        if (v == 0) {
            return "";
        } else {
            return String.valueOf(v);
        }
    }

    // 返回全部数据对应坐标
    public String getTitleString(int x, int y) {
        int v = getTitle(x, y);
        if (v == 0) {
            return "";
        } else {
            return String.valueOf(v);
        }
    }

    // 字符串数据生成整形数组
    private int[] fromPuzzleString(String src) {
        int sudoku[] = new int[src.length()];
        for (int i = 0; i < sudoku.length; i++) {
            sudoku[i] = src.charAt(i) - '0';
        }
        Log.i("sudoku",sudoku.toString());
        return sudoku;
    }

}


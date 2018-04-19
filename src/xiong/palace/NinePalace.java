package xiong.palace;

public class NinePalace {
    //初始化全部为零
    private int[][] ninePalace = new int[9][9];

    private int[][] initNinePalace = new int[9][9];

    private boolean testOver() {
        boolean flag = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                flag = flag & (ninePalace[i][j] != 0);
            }
        }
        return flag;
    }

    public NinePalace(int[][] ninePalace) {
        this.ninePalace = ninePalace;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                initNinePalace[i][j] = ninePalace[i][j];
            }
        }
    }

    public void printNinePalace() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(ninePalace[i][j]);
            }
            System.out.println();
        }
    }

    public void printNinePalace(int[][] ninePalace) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(ninePalace[i][j]);
            }
            System.out.println();
        }
    }

    //获取下一个位置
    private static int[] next(int i, int j) {
        int[] nextPosition;
        if (i >= 0 && i < 9 && j >= 0 && j < 9) {
            nextPosition = new int[2];
            if (j < 8) {
                nextPosition[0] = i;
                nextPosition[1] = j + 1;
            } else if (j == 8) {
                if (i < 8) {
                    nextPosition[0] = i + 1;
                    nextPosition[1] = 0;
                } else if (i == 8) {
                    nextPosition = null;
                }
            }
        } else {
            nextPosition = null;
        }
        return nextPosition;
    }

    //获取这个格子初始的所能够使用的1-9九个数字中的那几个
    private int[] getNumbers(int i, int j) {
        int[] numbers = new int[9];
        for (int m = 0; m < 9; m++) {
            numbers[m] = m + 1;
        }
        //查看一行之中是否有存在，如果一行中有存在的肯定对于那个位置而言就是不可用的数据
        for (int m = 0; m < 9; m++) {
            if (ninePalace[i][m] != 0) {
                numbers[ninePalace[i][m] - 1] = 0;
            }
        }
        //同理一列
        for (int m = 0; m < 9; m++) {
            if (ninePalace[m][j] != 0) {
                numbers[ninePalace[m][j] - 1] = 0;
            }
        }
        //同理，所在九宫
        removeMul33(numbers, i, j);
        int length = 0;
        for (int m = 0; m < 9; m++) {
            if (numbers[m] != 0) {
                length++;
            }
        }
        int[] numbers2 = new int[length];
        int n = 0;
        for (int m = 0; m < 9; m++) {
            if (numbers[m] != 0) {
                numbers2[n] = numbers[m];
                n++;
            }
        }
        return numbers2;
    }

    //获取该位置所在的3*3的九个格子之内的数字
    private void removeMul33(int[] numbers, int i, int j) {
        int starti = i / 3 * 3;
        int startj = j / 3 * 3;

        int endi = (starti / 3 + 1) * 3 - 1;
        int endj = (startj / 3 + 1) * 3 - 1;
        for (int m = starti; m <= endi; m++) {
            for (int n = startj; n <= endj; n++) {
                if (ninePalace[m][n] != 0) {
                    numbers[ninePalace[m][n] - 1] = 0;
                }
            }
        }
    }

    //查询是否无数字可填
    private boolean isNone(int[] numbers) {
        boolean flag = true;
        for (int i = 0; i < 9; i++) {
            flag = flag && (numbers[i] == 0);
        }
        return flag;
    }

    //填充九宫格
    public void fill(int i, int j) {
        if (!testOver()) {
            if (i > 8 || j > 8) {
                return;
            }
            restoreFill(i, j);
            if (ninePalace[i][j] == 0) {
                int[] numbers = getNumbers(i, j);
                if (numbers.length != 0) {
                    for (int m = 0; m < numbers.length; m++) {
                        if (!testOver()) {
                            restoreFill(i, j);
                            ninePalace[i][j] = numbers[m];
                            int[] nextPostion = next(i, j);
                            if (nextPostion != null) {
                                fill(nextPostion[0], nextPostion[1]);
                            }
                        }
                    }
                }
            } else {
                int[] nextPostion = next(i, j);
                if (nextPostion != null) {
                    fill(nextPostion[0], nextPostion[1]);
                }
            }
        }
    }

    //当正在填充该位置时，需要恢复该位置之后一个位置的数据到初始状态
    private void restoreFill(int i, int j) {
        int[] nextPostion = next(i, j);
        if (nextPostion != null) {
            for (int m = j + 1; m < 9; m++) {
                ninePalace[i][m] = initNinePalace[i][m];
            }
            for (int m = i + 1; m < 9; m++) {
                for (int n = 0; n < 9; n++) {
                    ninePalace[m][n] = initNinePalace[m][n];
                }
            }
        }
    }
}

package numberConstruct;

public class Chess {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑子2表示蓝子
        int chessArr1[][]=new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=8;
        chessArr1[3][3]=15;
        System.out.println("原始的二维数组");
        for (int [] row:chessArr1){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组转为稀疏数组
        //1.先遍历二维数组得到非0数据的个数
        int sum=0;
        for (int i = 0; i < chessArr1.length ; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j]!=0) {
                    sum++;
                }
            }
        }
       //2.创建稀疏数组
        int sparseArr[][]=new int[sum +1][3];
        //给稀疏数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //遍历二维数组，非0的值存放在sparseArr中
        int count=0;//用于记录第几个非0数据
        for (int i = 0; i < chessArr1.length ; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j]!=0) {
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }


            }
        }
        System.out.println();
        System.out.println("稀疏数组为---");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

        /*
        将稀疏数组---》恢复成二维数组
        1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的 chessArr2=int[11][11]
        2.在读取稀疏数组后几行的数据,并赋给原始的二维数组即可.

         */

        int chessArr2[][]= new int[sparseArr[0][0]] [sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
                    chessArr2 [sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }

        System.out.println();

        for ( int[]rows:chessArr2){
            for (int  data: rows){
                System.out.printf("%d\t",data);

            }
            System.out.println();
        }
}

}
package cci.matrix90degrees;

/**
 * Created by igorhara on 16/11/17.
 */
public class RotateMatrix {


    public static <T> void rotateMatrix(T[][] matrix) {


        int size = matrix.length;


        if (size != matrix[0].length) {
            return;
        }


        for (int layer = 0; layer < size / 2; layer++) {
            int min = layer;

            int max = size - 1 - layer;

            for (int i = min; i < max; i++) {
                int delta = i-min;
                T temp = matrix[min][i];
                matrix[min][i] = matrix[max-delta][min];
                matrix[max-delta][min] = matrix[max][max-delta];
                matrix[max][max-delta] = matrix[i][max];
                matrix[i][max]=temp;
            }

        }

    }


    public static void main(String[] args){

        Integer[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};

        for(int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[i].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println(" ");
        }

        System.out.println(" ");
        rotateMatrix(matrix);

        for(int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[i].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println(" ");
        }



    }


}

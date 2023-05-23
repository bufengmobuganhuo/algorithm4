package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_5_Applications.exercises;

/**
 * @author zhangyu
 * 2020/4/15 11:20
 * 练习3.5.23：稀疏矩阵的乘法、加法
 */
public class EX_3_5_23_SparseMatrix {
    public static void main(String[] args) throws CloneNotSupportedException {
        Double[][] values1={
                {0.0,1.0,3.0},
                {1.0,3.0,8.0},
                {4.0,7.0,2.0},
        };
        Double[] values2= {2.0,
                           1.0,
                           5.0};
        Double[][] values3={
                {0.0,6.0,1.0},
                {2.0,1.0,3.0},
                {0.0,0.0,0.0},
        };
        EX_3_5_23_SparseMatrix matrix1=new EX_3_5_23_SparseMatrix(values1);
        EX_3_5_23_SparseMatrix matrix2= new EX_3_5_23_SparseMatrix(values3);
        EX_3_5_16_SparseVector vector=new EX_3_5_16_SparseVector(values2);
        EX_3_5_23_SparseMatrix matrix= matrix1.add(matrix2);
        matrix.println();
        EX_3_5_16_SparseVector resVector= matrix1.times(vector);
        resVector.println();
    }
    private EX_3_5_16_SparseVector[] vectors;
    //n*n的矩阵
    private int n;

    public EX_3_5_23_SparseMatrix(int n) {
        this.n=n;
        vectors=new EX_3_5_16_SparseVector[n];
        for (int i=0;i<n;i++){
            vectors[i]=new EX_3_5_16_SparseVector(n);
        }
    }

    public EX_3_5_23_SparseMatrix(Double[][] values){
        n=values.length;
        vectors=new EX_3_5_16_SparseVector[n];
        for (int i=0;i<values.length;i++){
            vectors[i]=new EX_3_5_16_SparseVector(n);
            for (int j=0;j<values.length;j++){
                if (values[i][j]!=0){
                    vectors[i].put(j,values[i][j]);
                }
            }
        }
    }

    public EX_3_5_23_SparseMatrix add(EX_3_5_23_SparseMatrix matrix){
        if (this.n!=matrix.n){
            throw new IllegalArgumentException();
        }
        EX_3_5_23_SparseMatrix result=new EX_3_5_23_SparseMatrix(n);
        for (int i=0;i<n;i++){
            result.vectors[i]=vectors[i].add(matrix.vectors[i]);
        }
        return result;
    }

    public EX_3_5_16_SparseVector times(EX_3_5_16_SparseVector vector){
        if (n!=vector.getN()){
            throw new IllegalArgumentException();
        }
        EX_3_5_16_SparseVector res=new EX_3_5_16_SparseVector(n);
        for (int i=0;i<n;i++){
            double sum=vectors[i].dot(vector);
            if (sum!=0){
                res.put(i,sum);
            }
        }
        return res;
    }

    public void println(){
        for (int i=0;i<vectors.length;i++){
            for (int j=0;j<vectors.length;j++){
                System.out.print(vectors[i].getOrDefault(j,0)+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

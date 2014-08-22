package genesis.util.rendering;

public class Matrix {

    double[][] matrix;
    int width;
    int height;

    public Matrix(int mWidth, int mHeight, double... matVals) {
        matrix = new double[mHeight][];

        for (int y = 0; y < mHeight; y++) {
            double[] row = new double[mWidth];

            for (int x = 0; x < mWidth; x++) {
                double val = 0;

                if (matVals.length == 1)    // Make it the identity if it has only one arg
                {
                    if (x == y) {
                        val = 1;
                    }
                } else {
                    val = matVals[x + y * mWidth];
                }

                row[x] = val;
            }

            matrix[y] = row;
        }

        this.width = mWidth;
        this.height = mHeight;
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        width = matrix[0].length;
        height = matrix.length;
    }

    public double get(int x, int y) {
        return matrix[y][x];
    }

    public Matrix add(Matrix matrix2) {
        double[][] output = new double[matrix.length][];

        for (int y = 0; y < matrix.length; y++) {
            double[] row1 = matrix[y];
            double[] row2 = matrix[y];
            double[] outRow = new double[row1.length];

            for (int x = 0; x < row1.length; x++) {
                outRow[x] = row1[x] + row2[x];
            }

            output[y] = outRow;
        }

        return new Matrix(output);
    }

    public Matrix mult(double val) {
        double[][] output = new double[height][];

        for (int y = 0; y < height; y++) {
            double[] row = matrix[y];
            double[] newRow = new double[width];

            for (int x = 0; x < width; x++) {
                newRow[x] = row[x] * val;
            }

            output[y] = newRow;
        }

        return new Matrix(output);
    }

    public Matrix mult(Matrix mat2) {
        double[][] output = new double[height][];

        for (int y1 = 0; y1 < height; y1++) {
            double[] row = new double[mat2.width];

            for (int x2 = 0; x2 < mat2.width; x2++) {
                double val = 0;

                for (int i = 0; i < Math.min(width, mat2.height); i++) {
                    val += get(i, y1) * mat2.get(x2, i);
                }

                row[x2] = val;
            }

            output[y1] = row;
        }

        return new Matrix(output);
    }

    public Matrix mult(Vec4 vec) {
        double[] aDouble = vec.toArray(width);

        return mult(new Matrix(1, aDouble.length, aDouble));
    }

    public boolean isValid() {
        for (double[] row : matrix) {
            for (double val : row) {
                if (Double.isNaN(val) ||
                        Double.isInfinite(val)) {
                    return false;
                }
            }
        }

        return true;
    }

    public Vec4 toVec() {
        Vec4 vec = new Vec4(1);

        vec.x = get(0, 0);
        vec.y = get(0, 1);
        vec.z = get(0, 2);
        vec.w = get(0, 3);

        return vec;
    }

    public void print() {
        String[] print = new String[height];

        for (int y = 0; y < height; y++) {
            String line = "{";

            for (int x = 0; x < width; x++) {
                line += get(x, y);

                if (x < width - 1) {
                    line += ", ";
                } else {
                    line += "}";
                }
            }

            print[y] = line;
        }

        print[0] = "{" + print[0];
        print[print.length - 1] += "}";

        for (String str : print) {
            System.out.println(str);
        }
    }

    public Matrix copy() {
        Matrix outMatrix = new Matrix(width, height, 1);
        int y = 0;

        for (double[] row : matrix) {
            int x = 0;

            for (double val : row) {
                outMatrix.matrix[y][x] = val;
                x++;
            }

            y++;
        }

        return outMatrix;
    }
}

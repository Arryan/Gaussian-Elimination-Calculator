
/**
 *
 * @author Arryan Zaman
 */
public class EquationSolver {

    private double[][] equations; //coefficient matrix
    private double[] answers; //constants
    private int nEq; //number of equations
    private int nVar; //number of variables

    /**
     * Constructor
     *
     * @param eq coefficient matrix
     * @param ans constants
     * @param nEq number of equations
     * @param nVar number of variables
     */
    public EquationSolver(double[][] eq, double[] ans, int nEq, int nVar) {
        this.equations = eq;
        this.answers = ans;
        this.nEq = nEq;
        this.nVar = nVar;
    }

    /**
     * Transforms matrix to row echelon form
     */
    public void toREF() {
        //put matrix in row echelon form
        for (int i = 0; i < nEq; i++) {
            if (equations[i][i] == 0) {
                for (int r = i + 1; r < nEq; r++) {
                    if (equations[r][i] != 0) {
                        System.out.println("r" + i + " <-> r" + (r + 1));
                        rowSwap(r, i);
                        printMatrix();
                    }
                }
            }

            rowMultiplication((1 / equations[i][i]), i);
            for (int r = i + 1; r < nEq; r++) {
                System.out.println(-(equations[r][i]) + "r" + (i + 1) + "r" + (r + 1) + " -> r" + (r + 1));
                rowAddition(-(equations[r][i]), r, i);
                printMatrix();
            }
        }
    }

    /**
     * Transforms matrix to reduced row echelon form
     */
    public void toRREF() {
        //put matrix in reduced row echelon form
        toREF();
        for (int i = nEq - 1; i > 0; i--) {
            for (int r = i - 1; r >= 0; r--) {
                rowAddition(-equations[r][i], r, i);
                System.out.println(-(equations[r][i]) + "r" + (i + 1) + "r" + (r + 1) + " -> r" + (r + 1));
                printMatrix();
            }
        }
    }

    /**
     * Performs row swap between row r1 and row r2
     *
     * @param r1 index of first row
     * @param r2 index of second row
     */
    private void rowSwap(int r1, int r2) {
        //swap r1 with r2
        for (int j = 0; j < nVar; j++) {
            double temp = equations[r1][j];
            equations[r1][j] = equations[r2][j];
            equations[r2][j] = temp;
        }
        double temp = answers[r1];
        answers[r1] = answers[r2];
        answers[r2] = temp;
    }

    /**
     * Performs multiplication of a row by a scalar
     *
     * @param factor scalar
     * @param r index of row
     */
    private void rowMultiplication(double factor, int r) {
        //store multiplication of r and factor inside r
        for (int j = 0; j < nVar; j++)
            equations[r][j] *= factor;
        answers[r] *= factor;
    }

    /**
     * Performs addition of row r1 and row r2 and stores result in row r1
     *
     * @param factor scalar by which r2 is multiplied
     * @param r1 index of first row
     * @param r2 index of second row
     */
    private void rowAddition(double factor, int r1, int r2) {
        //add r1 and r2 and store in r1
        for (int j = 0; j < nVar; j++)
            equations[r1][j] += factor * equations[r2][j];
        answers[r1] += factor * answers[r2];
    }

    /**
     * prints contents of matrix
     */
    public void printMatrix() {
        for (int i = 0; i < equations.length; i++) {
            System.out.print("[\t");
            for (int j = 0; j < equations[0].length; j++) {
                System.out.print(equations[i][j] + "\t");
            }
            System.out.println("| " + answers[i] + "\t]\n");
        }
    }
}

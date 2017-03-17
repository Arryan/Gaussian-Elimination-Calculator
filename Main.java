
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arryan Zaman
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("How many different variables are there?");
        int nVar = in.nextInt();
        System.out.println("How many equations are there?");
        int nEq = in.nextInt();
        double[][] equations = new double[nVar][nEq];
        double[] answers = new double[nEq];
        for(int i = 0; i < nEq; i++) {
            System.out.println("Entries in row " + (i + 1));
            for(int j = 0; j < nEq; j++) {
                equations[i][j] = in.nextDouble();
            }
            answers[i] = in.nextDouble();
        }
        EquationSolver solver = new EquationSolver(equations, answers, nEq, nVar);
        solver.toRREF();
        System.out.println("REF:");
        solver.printMatrix();
    }
}

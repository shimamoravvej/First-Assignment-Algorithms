public class Exercises {

    /*
        there is an array of positive integers as input of function and another integer for the target value
        all the algorithm should do is to find those two integers in array which their multiplication is the target
        then it should return an array of their indices
        e.g. {1, 2, 3, 4} with target of 8 -> {1, 3}

        note: you should return the indices in ascending order and every array's solution is unique
    */
    public int[] productIndices(int[] values, int target) {

            // Iterate over the array
            for (int i = 0; i < values.length; i++) {
                // For each value at index i, check if there exists a j such that values[i] * values[j] = target
                for (int j = i + 1; j < values.length; j++) {
                    if (values[i] * values[j] == target) {
                        // If found, return the indices in ascending order
                        return new int[]{i, j};
                    }
                }
            }
            // If no such pair exists, return an empty array (or handle according to requirements)
            return new int[]{};
        }
    }

    /*
        given a matrix of random integers, you should do spiral traversal in it
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        so you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        int[] result = new int[rows * cols];
        int index = 0;

        // Define the boundary of the matrix
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;

        while (top <= bottom && left <= right) {
            // Traverse from left to right along the top row
            for (int i = left; i <= right; i++) {
                result[index++] = values[top][i];
            }
            top++;

            // Traverse from top to bottom along the right column
            for (int i = top; i <= bottom; i++) {
                result[index++] = values[i][right];
            }
            right--;

            if (top <= bottom) {
                // Traverse from right to left along the bottom row
                for (int i = right; i >= left; i--) {
                    result[index++] = values[bottom][i];
                }
                bottom--;
            }

            if (left <= right) {
                // Traverse from bottom to top along the left column
                for (int i = bottom; i >= top; i--) {
                    result[index++] = values[i][left];
                }
                left++;
            }
        }

        return result;
    }

    /*
        integer partitioning is a combinatorics problem in discreet maths
        the problem is to generate sum numbers which their summation is the input number

        e.g. 1 -> all partitions of integer 3 are:
        3
        2, 1
        1, 1, 1

        e.g. 2 -> for number 4 goes as:
        4
        3, 1
        2, 2
        2, 1, 1
        1, 1, 1, 1

        note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different
        you should generate all partitions of the input number and

        hint: you can measure the size and order of arrays by finding the pattern of partitions and their number
        trust me, that one's fun and easy :)

        if you're familiar with lists and arraylists, you can also edit method's body to use them instead of array
    */
    public int[][] intPartitions(int n) {
        List<List<Integer>> result = new ArrayList<>();
        partitionHelper(n, new ArrayList<>(), result, n);

        // Convert List<List<Integer>> to int[][]
        int[][] partitions = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            List<Integer> partition = result.get(i);
            partitions[i] = partition.stream().mapToInt(Integer::intValue).toArray();
        }

        return partitions;
    }

private void partitionHelper(int n, List<Integer> current, List<List<Integer>> result, int max) {
    if (n == 0) {
        result.add(new ArrayList<>(current));
        return;
    }

    for (int i = Math.min(max, n); i >= 1; i--) {
        current.add(i);
        partitionHelper(n - i, current, result, i);
        current.remove(current.size() - 1);
    }
}

    public static void main(String[] args) {
        // you can test your code here
    }
}

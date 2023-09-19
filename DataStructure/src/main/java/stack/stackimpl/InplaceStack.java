package stack.stackimpl;

public class InplaceStack {

    /**
     *
     * @param arr [1,5,6,8,8,8,0,1,1,0,6,5]
     * @return [1]
     */
    public static void removeAdjacentDuplicates(int[] arr) {

        int top = -1;
        int j = 0;

        while (j < arr.length) {
            if(top == -1 || arr[top] != arr[j]) {
                arr[++top] = arr[j++];
            } else if (arr[top] == arr[j]) {
                while(j < arr.length && arr[j] == arr[top]) {
                    j++;
                }
                top--;
            }
        }


        if(top == -1)
            System.out.println("");
        else {
            for(int i = 0; i<= top ;i++ )
                System.out.println(arr[i]);
        }

    }
}

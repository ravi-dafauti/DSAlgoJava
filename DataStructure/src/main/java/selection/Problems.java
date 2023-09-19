package selection;

public class Problems {


    // kth smallest
    public static int kthSmallest(int[] arr, int k) throws Exception {
        return kthSmallestPivotMethod(arr, 0, arr.length - 1, k);
    }

    private static int kthSmallestPivotMethod(int[] arr, int s, int l, int k) throws Exception {

        if(s > l)
            throw new Exception("value of K is not appropriate");
        int pivot = partition(arr, s, l);
        if(pivot + 1 == k)
            return arr[pivot];
        else if (pivot + 1 < k) {
            return kthSmallestPivotMethod(arr, pivot + 1, l, k);
        } else {
            return kthSmallestPivotMethod(arr, s, pivot - 1, k);
        }
    }

    private static int partition(int[] arr, int s, int l) {
        int pivot = s;
        for(int i = s; i < l; i++) {
            if(arr[i] < arr[l]) {
                swap(arr, pivot, i);
                pivot++;
            }
        }
        swap(arr, pivot, l);
        return pivot;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    // median of two sorted array of different lengths in O(log(min(m,n))

    public static int median(int[] a, int[] b) {

        if(a.length > b.length)
            return median(b , a);

        int l = 0;
        int h = a.length;

        while (l <= h) {

            int cut1 = l + (h - l)/2;
            int cut2 = (a.length + b.length + 1)/2 - cut1;
            int l1 = cut1 == 0 ? Integer.MIN_VALUE : a[cut1 - 1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : b[cut2 - 1];
            int r1 = cut1 == a.length ? Integer.MAX_VALUE : a[cut1];
            int r2 = cut2 == b.length ? Integer.MAX_VALUE : b[cut2];

            if(l1 <= r2 && l2 <= r1) {
                if((a.length + b.length) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2;
                } else {
                    return Math.max(l1, l2);
                }
            } else if(l1 > r2) {
                h = cut1 - 1;
            } else if(l2 > r1) {
                l = cut1 + 1;
            }

        }
        return -1;
    }


    // Kth element in two sorted array of different Length in O(log(min(n, m))

    public static long kthElement( int arr1[], int arr2[], int n, int m, int k) {
        if(n > m)
            return kthElement(arr2 , arr1, m, n, k);

        if(k == 0 || k > m + n)
            return -1;

        int l = Math.max(k - m, 0);
        int h = Math.min(k, n);

        while (l <= h) {

            int cut1 = l + (h - l)/2;
            int cut2 = k - cut1;
            int l1 = cut1 == 0 ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int r1 = cut1 == n ? Integer.MAX_VALUE : arr1[cut1];
            int r2 = cut2 == m ? Integer.MAX_VALUE : arr2[cut2];

            if(l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if(l1 > r2) {
                h = cut1 - 1;
            } else {
                l = cut1 + 1;
            }
        }
        return 1;
    }



    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums2.length < nums1.length)
            return findMedianSortedArrays(nums2, nums1);
        int l = 0;
        int h = nums1.length;

        int part1 = (nums1.length + nums2.length + 1) / 2;

        while (l <= h) {

            int cut1 = l + (h - l)/2;
            int cut2 = part1 - cut1;

            int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int r1 = cut1 == nums1.length ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = cut2 == nums2.length ? Integer.MAX_VALUE : nums2[cut2];

            if(l1 <= r2 && l2 <= r1) {
                if((nums1.length + nums2.length) %2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            } else if(l1 > r2) {
                h = cut1 - 1;
            } else if(l2 > r1) {
                l = cut1 + 1;
            }
        }

        return 1;
    }
}

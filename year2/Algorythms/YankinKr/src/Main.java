import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[5];
        arr[0] = 3;
        arr[1] = 4;
        arr[2] = 18;
        arr[3] = 22;
        arr[4] = 25;

        int[] arr2 = new int[6];
        arr2[0] = 5;
        arr2[1] = 9;
        arr2[2] = 11;
        arr2[3] = 14;
        arr2[4] = 16;
        arr2[5] = 17;

        //task1
       int[] res = merge2InOne(arr,arr2);


       LinkedList<Integer> list = new LinkedList<>();
       list.add(5);
       list.add(1);
       list.add(5);
       list.add(7);
       list.add(4);

       //task2
       sortBydescend(list);


       //task3
       Binary3Links<Integer, Integer> binary3Links = new Binary3Links<>();
       binary3Links.put(5, 3);
       binary3Links.put(2, 3);
       binary3Links.put(6, 8);
       binary3Links.put(1, 2);
       binary3Links.put(9, 9);

       binary3Links.delete(1);
       binary3Links.delete(2);
       binary3Links.delete(6);
       binary3Links.delete(5);

       int[] arrray= new int[5];
       arrray[0] = 3;
       arrray[1] = 3;
       arrray[2] = 6;
       arrray[3] = 4;
       arrray[4] = 6;
       System.out.println(additionalTask(arrray));

    }

    //task1
    public static int[] merge2InOne(int[] a, int[]b){
        int[] res = new int[a.length+b.length];
        int aLow = 0;
        int bLow = 0;

        int curr = 0;
        while (aLow<a.length && bLow<b.length){
            if(a[aLow]<b[bLow]){
                res[curr] = a[aLow++];
            }else {
                res[curr] = b[bLow++];
            }
            curr++;
        }
        while (aLow<a.length){
            res[curr] = a[aLow++];
            curr++;
        }
        while (bLow<b.length){
            res[curr] = b[bLow++];
            curr++;
        }
        return res;
    }


    //task2
    public static void sortBydescend(LinkedList<Integer> arr){
        int n = arr.size();
        for (int i=0;i<n;i++){
            for (int j = i;j>0;j--)
                if (arr.get(j)>arr.get(j-1)){
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j-1));
                    arr.set(j-1, temp);
                }
                else break;
        }
    }

    //time = n^2
    public static int additionalTask(int[] arr){
        boolean y = false;
        for(int i = 0; i<arr.length; i++){
            for(int j = 0; j<arr.length; j++){
                if(arr[i]==arr[j] && i!=j){
                    y=true;
                    break;
                }
            }
            if(!y){
                return arr[i];
            }
            y=false;
        }
        return 0;
    }
}

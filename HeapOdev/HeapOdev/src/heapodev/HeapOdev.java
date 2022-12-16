package heapodev;

import java.util.Arrays;
import java.util.Scanner;

public class HeapOdev {

    private int[] Heap;
    private int index;
    private int size;

    public HeapOdev(int size) {
        this.size = size;
        this.index = 0;
        Heap = new int[size];
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return (i * 2) + 1;
    }

    private int rightChild(int i) {
        return (i * 2) + 2;
    }

    private boolean isLeaf(int i) {
        if (rightChild(i) >= size || leftChild(i) >= size) {
            return true;
        }
        return false;
    }

    public void ekle(int element) {
        if (index >= size) {
            return;
        }
        Heap[index] = element;
        int gecerli = index;

        while (Heap[gecerli] < Heap[parent(gecerli)]) {
            swap(gecerli, parent(gecerli));
            gecerli = parent(gecerli);
        }
        index++;
    }

    public int enkucuk() {

        int popped = Heap[0];
        Heap[0] = Heap[--index];
        minHeapify(0);
        return popped;
    }

    private void minHeapify(int i) {
        if (!isLeaf(i)) {
            if (Heap[i] > Heap[leftChild(i)]
                    || Heap[i] > Heap[rightChild(i)]) {
                if (Heap[leftChild(i)] < Heap[rightChild(i)]) {
                    swap(i, leftChild(i));
                    minHeapify(leftChild(i));
                } else {
                    swap(i, rightChild(i));
                    minHeapify(rightChild(i));
                }
            }
        }
    }

    public void minHeap() {
        for (int i = (index - 1 / 2); i >= 1; i--) {
            minHeapify(i);
        }
    }

    public void YazdirHeap() {
        for (int i = 0; i < (index / 2); i++) {
            System.out.print("Ebeveny : " + Heap[i]);
            if (leftChild(i) < index) {
                System.out.print(" sol : " + Heap[leftChild(i)]);
            }
            if (rightChild(i) < index) {
                System.out.print(" sağ :" + Heap[rightChild(i)]);
            }
            System.out.println();
        }
    }

    private void swap(int x, int y) {
        int tmp;
        tmp = Heap[x];
        Heap[x] = Heap[y];
        Heap[y] = tmp;
    }

    public static void main(String[] args) {
        HeapOdev minHeap = new HeapOdev(3);
        Scanner s = new Scanner(System.in);
        System.out.println("1. Değeri Giriniz");
        minHeap.ekle(s.nextInt());
        System.out.println("2. Değeri Giriniz");
        minHeap.ekle(s.nextInt());
        System.out.println("3. Değerinizi Giriniz");
        minHeap.ekle(s.nextInt());
        minHeap.minHeap();

        if (minHeap.isLeaf(1)) {
            minHeap.YazdirHeap();
            System.out.println("\n MinHeap En Küçük Değeri= " + minHeap.enkucuk());
            System.out.println("\n Min Heap =" + Arrays.toString(minHeap.Heap));
            minHeap.YazdirHeap();
        } else {
            System.out.println("Min Heap Değildir");
        }

    }

}

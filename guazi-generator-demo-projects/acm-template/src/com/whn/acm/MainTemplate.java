package com.whn.acm;

import java.util.Scanner;
/**
 * ACM输入模版
 */
public class MainTemplate{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            // 读取输入元素个数
            int n = scanner.nextInt();

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            // 处理问题逻辑，根据需要进行输出
            int sum = 0;
            for (int num :
                    arr) {
                sum += num;
            }

            System.out.println("Sum: " + sum);
        }
        scanner.close();
    }
}
package com.example.xiaoqiang.myapplication.algorithm;

/**
 * @Author: [xiaoqiang]
 * @Description: [Palindrome]
 * @CreateDate: [2018/5/13]
 * @UpdateDate: [2018/5/13]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class Palindrome {
    public static void main(String[] args) {
        String str = "babad";
        System.out.println(palindrome(str));
         str = "cbbd";
        System.out.println(palindrome(str));
         str = "bbb";
        System.out.println(palindrome(str));

        str = "c";
        System.out.println(palindrome(str));

         str = "babaddtattarrattatddetartrateedredividerb";
        System.out.println(palindrome(str));
         str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(palindrome(str));



        str = "babad";
        System.out.println(palindrome2(str));
        str = "cbbd";
        System.out.println(palindrome2(str));
        str = "bbb";
        System.out.println(palindrome2(str));

        str = "c";
        System.out.println(palindrome2(str));

        str = "babaddtattarrattatddetartrateedredividerb";
        System.out.println(palindrome2(str));
        str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(palindrome2(str));
    }

    private static String palindrome2(String str){
        int size = str.length();
        boolean[][] f = new boolean[size][size];
        int max = 0;
        String s = null;

        for(int i=0;i<size;i++){
            f[i][i] = true;
            for(int j =0;j<i;j++){
                f[j][i] = (str.charAt(j) == str.charAt(i)) &&(i-j<2 || f[j+1][i-1]);
                if(f[j][i] && max < i-j+1){
                    max = i-j+1;
                    s = str.substring(j,i+1);
                }
            }
        }
        return s;
    }

    private static String palindrome(String str) {
//        String reve = new StringBuffer(str).reverse().toString();
//        String s = null;
//        int con = 0;
//        for (int i = 0; i < str.length(); i++) {
//            char cha = str.charAt(i);
//            int end = str.lastIndexOf(cha);
//            String zi;
//            while (end > i) {
//                zi = str.substring(i, end+1);
//                if(zi.length() <= con) break;
//                if (reve.contains(zi)) {
//                    con = zi.length();
//                    s = zi;
//                    break;
//                }else{
//                    end = str.substring(i,end).lastIndexOf(cha) + i;
//                }
//            }
//            if(end == 0 && con ==0){
//                s = str.substring(0,1);
//                con = 1;
//            }
//        }
//        return s;
        int max = 0;
        String s = null;
        for(int i=0;i<str.length();i++){
            for(int j = i;j<str.length();j++){
                boolean isH = false;
                for(int k =i;k<=j;k++){
                    if(!(isH = str.charAt(k)==str.charAt(i+j-k))){
                        break;
                    }
                }
                if(isH && j-i+1>max){
                    s = str.substring(i,j+1);
                    max = j-i + 1;
                }
            }
        }
        return s;
    }



    private static boolean palindrome(String str, String max) {
        return str.contains(max);
    }


}

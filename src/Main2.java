import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.*;



public class Main2{
    static  Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);

    static int mod = (int)1e9+7;
    static int m,d,a,b;
    static char[] s1,s2;
    public static void main(String[] args) throws IOException {
        m = sc.nextInt();
        d = sc.nextInt();
        String s = sc.next();
        s1 = s.toCharArray();
        a = Integer.parseInt(s);
        s = sc.next();
        s2 = s.toCharArray();
        b = Integer.parseInt(s);

        dp(0,s2,0,0);
        long ans1 = ans;
        ans=0;
        dp(0,s1,0,0);
//        pw.println(ans1+" "+ans);
        pw.println(ans1-ans);
        pw.flush();
    }
    static long ans = 0;
    static void dp(int i,char[] s,int can,long rem) {
        if (i == s.length){
            if(rem%m == 0) {
                ans++;
                ans%=mod;
            }
            return;
        }
        int ub = can==0?s[i]-'0':9;
        for(int j = 0;j<=ub;j++){
            int newCan = (can == 0 && j==ub)?0:1;
            if(i%2 == 0 && j != d){
//                System.out.println(j+" "+i);
                dp(i+1,s,newCan,Long.parseLong(rem+""+j));
            }else if(i%2 != 0 && j == d){
//                System.out.println(j+" "+i);
                dp(i+1,s,newCan,Long.parseLong(rem+""+j));
            }

        }

    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader r) {
            br = new BufferedReader(r);
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public long[] nextlongArray(int n) throws IOException {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public Long[] nextLongArray(int n) throws IOException {
            Long[] a = new Long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public int[] nextIntArray(int n) throws IOException {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public Integer[] nextIntegerArray(int n) throws IOException {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }

}
package com.lxy;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:LiuXinYu
 * @Date:2020/6/6
 * @Content:
 */
public class MySQLClient {
    /**
     * 以下配置可以通过 parseArguments 进行替换
     */
    private static String host = "127.0.0.1";
    private static int port = 3306;
    private static String user = null;
    private static String password = " ";
    private static String defaultDatabaseName = " ";
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        if(args.length == 0){
            printUsageAndExit();
        }

        parseArgument(args);
        System.out.println(host);
        System.out.println(port);
        System.out.println(user);
        System.out.println(password);
        System.out.println(defaultDatabaseName);
    }

    private static void parseArgument(String[] args) {
        defaultDatabaseName = args[args.length - 1];
        args = Arrays.copyOfRange(args,0,args.length-1);
        for(int i = 0; i<args.length;i++){
            String arg = args[i];
            switch(arg){
                case"--help":
                    printUsageAndExit();
                case"-h":
                case"--host":
                    host = args[++i];
                    break;
                case"-P":
                case"--port":
                    port = Integer.parseInt(args[++i]);
                    break;
                case"-u":
                case"--user":
                    user = args[++i];
                    break;
                case"-p":
                case"--password":
                    password = args[++i];
                    break;
                default:
                    printUsageAndExit("不认识的选项 "+ arg);
            }
        }
    }

    private static void printUsageAndExit(String... massages) {
        System.out.println("使用帮助: mysql [选项] 默认数据库");
        System.out.println(" --help  打印帮助信息");
        System.out.println(" -h , --host 连接主机，默认是 127.0.0.1");
        System.out.println(" -P , --port 连接端口，默认是3306");
        System.out.println(" -u , --user mysql用户名，必须设置");
        System.out.println(" -p , --password mysql密码");

        //1.stream的操作
        //2.方法引用
        Arrays.stream(massages).peek(System.out::println);
        //和下面的方法一样 是简单版本
//        for (String s:massages) {
//            System.out.println(s);
//        }

        System.exit(1);
    }
}

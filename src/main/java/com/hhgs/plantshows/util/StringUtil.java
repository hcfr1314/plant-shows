package com.hhgs.plantshows.util;

public class StringUtil {

    /**
     * 验证字符串是否为null，空格，"null"
     * @param str 需要验证的字符
     * @return 为空返回true   否则返回false
     */
    public static boolean testStrIsNUll(String str){
        if(str==null||str.trim().length()==0||str.equalsIgnoreCase("null")){
            return true;
        }
        return false;
    }

    /**
     * 验证字符串不为null，空格，"null"
     * @param str 需要验证的字符
     * @return 不为空返回true   否则返回false
     */
    public static boolean testStrIsNotNUll(String str){
        return !testStrIsNUll(str);
    }

    /**
     * 字符串不为null，空格，"null" 则返回null  否则返回真实的字符串
     * @param str 字符
     * @return
     */
    public static String getString(String str){
        if(testStrIsNUll(str)){
            return null;
        }else{
            return str;
        }
    }

    /**
     * 验证字符只能并且同时包含字母和下划线
     * @param str
     * @return
     */
    public static boolean wordAndUnderLine(String str){
        int underLine=0;
        int word=0;
        for (int i = 0; i <str.trim().length() ; i++) {
            int ch=str.charAt(i);
            if(ch>=65&&ch<=90){
                underLine=1;
                continue;
            }
            if(ch>=97&&ch<=122){
                underLine=1;
                continue;
            }
            if(ch==95){
                word=1;
                continue;
            }
            underLine=0;
            word=0;
            break;
        }
        return (underLine+word)==2;
    }

    public static boolean checkLength(int max,int min,String str){
        if(str.trim().length()>max||str.trim().length()<min){
            return false;
        }
        return true;
    }

    /**
     * 如果指定字符开头、结尾为指定字符，则删除首尾的指定字符
     * @param str 字符
     * @param code 代删除字符
     * @return 新的字符
     */
    public static String subStartEnd(String str,String code){
        str=str.trim();
        if(str.startsWith(code)){
            str=str.substring(1,str.length());
        }
        if(str.endsWith(code)){
            str=str.substring(0,str.length()-1);
        }
        return str;
    }


    public static boolean stringIsNumber(String str){
        boolean result=true;
        str=str.trim();
        for (int i = 0; i <str.trim().length() ; i++) {
            int ch=str.charAt(i);
            if(ch<48||ch>57){
                result=false;
                break;
            }
        }
        return result;
    }

    /**
     * 比较两个字符串相等的个数
     * @param source  源字符串
     * @param tartget  目标字符串
     * @param isPre     true 从前到后    false从后到前
     * @return 返回个数
     */
    public static int compare(String source,String tartget,boolean isPre){
        int num=0;
        if(!isPre){
            source=new StringBuffer(source).reverse().toString();
            tartget=new StringBuffer(tartget).reverse().toString();
        }

        int[] sourceArray=new int[source.length()];
        int[] tartgetArray=new int[tartget.length()];
        for (int i = 0; i <source.trim().length() ; i++) {
            int ch=source.charAt(i);
            sourceArray[i]=ch;
        }
        for (int i = 0; i <tartget.trim().length() ; i++) {
            int ch=tartget.charAt(i);
            tartgetArray[i]=ch;
        }

        int arrayLen=(sourceArray.length>=tartgetArray.length)?tartgetArray.length:sourceArray.length;

        for (int i = 0; i <arrayLen ; i++) {
            if(sourceArray[i]==tartgetArray[i]){
                num=num+1;
            }else{
                break;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        String source="我爱你中国";
        String target="中国";
        System.out.println(StringUtil.compare(source,target,true));
    }
}

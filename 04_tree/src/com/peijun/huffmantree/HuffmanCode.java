package com.peijun.huffmantree;


import java.io.*;
import java.util.*;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/1/24 23:04
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 哈夫曼编码
 */
public class HuffmanCode {
    /**
     * 根结点
     */
    private TreeNode root;

    /**
     * 获取每个字符和其权值(出现次数)的结点列表
     *
     * @param bytes
     */
    private PriorityQueue<TreeNode> getNodeList(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        PriorityQueue<TreeNode> queue = new PriorityQueue<>();
        // 保存字符和出现次数的map
        Map<Byte, Integer> map = new HashMap<>();
        for (byte data : bytes) {
            // 假如不存在此key,存一个此key且值为1
            // 假如存在此key,旧值和新值相加即可
            map.merge(data, 1, Integer::sum); // 添加到Map,JDK8的Map新方法
        }
        // 转换为结点
        map.forEach((key, value) -> {
//            byte[] bytes1 = new byte[1];
//            bytes1[0] = key;
//            System.out.println(new String(bytes1) + "=====" + value);
            queue.offer(new TreeNode(value, key));
        });
//        System.out.println(map.size());
        return queue;
    }

    /**
     * 构建哈夫曼树
     *
     * @param nodeQueue
     */
    private void buildHuffmanTree(PriorityQueue<TreeNode> nodeQueue) {
        while (nodeQueue.size() > 1) {
            TreeNode left = nodeQueue.poll();
            TreeNode right = nodeQueue.poll();
            TreeNode father = new TreeNode(left.weight + right.weight, null);
            father.left = left;
            father.right = right;
            nodeQueue.offer(father);
        }
        root = nodeQueue.poll();
    }

    /* ------------------

    /**
     * 哈夫曼编码表
     */
    private Map<Byte, String> codeTable = new HashMap<>();


    /**
     * 使用哈夫曼编码对 字符串进行压缩
     */
    public byte[] zip(byte[] bytes) {
        // 1.初始化结点
        PriorityQueue<TreeNode> queue = getNodeList(bytes);
        // 2.创建哈夫曼树
        buildHuffmanTree(queue);
        // 3.获得哈夫曼编码
        Map<Byte, String> codeTable = getCodeTable(root);
        // 4.压缩
        return zipToByteArray(bytes, codeTable);
    }

    /**
     * 使用哈夫曼编码 字符串进行  解缩
     */
    public String unZip(byte[] bytes) {
        // 1.将byte[]数组转换为二进制字节码字符串
        String str = byteToBitString2(bytes);
        // 2.按照编码表进行解压
        return decode2(str, codeTable);
    }

    /**
     * @param codeTable    编码表
     * @param huffmanBytes 哈夫曼编码得到的字节数组 也就是压缩后的字节数组
     * @return 返回原始的字节数组
     */
    public byte[] decode(Map<Byte, String> codeTable, byte[] huffmanBytes) {
        StringBuilder sb = new StringBuilder();
        // 把压缩后的byte[]数组转换为压缩后的二进制字节码 字符串
        for (int i = 0; i < huffmanBytes.length - 1; i++) {
            byte abyte = huffmanBytes[i];
            //对于byte中的元素，只要不是倒数第2个和倒数第一个就正常处理
            if(i != huffmanBytes.length -2){
                //正常处理
                sb.append(byteToBitStr(abyte, 8));
            }else{
                sb.append(byteToBitStr(abyte, (int)huffmanBytes[huffmanBytes.length -1]));
            }
//            boolean flag = (i != huffmanBytes.length - 1);
//            sb.append(byteToBitString(flag, abyte));
        }
        // 将压缩后的二进制字节码 按照 编码表反向 获取其对应的key
        // 首先将编码表的key-value转换， 也就是key->value, value->key
        Map<String, Byte> newCodeTable = new HashMap<>();
        codeTable.forEach((k, v) -> newCodeTable.put(v, k));
        // 查表 将压缩后的字节码转换为对应的字符
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < sb.length(); ) {
            int count = 1; // 指针，指向新的字符编码的第一个字节位置
            Byte aByte = null;
            while (true) {
                String key = sb.substring(i, i + count);
                aByte = newCodeTable.get(key);
                if (aByte != null) {
                    break;
                }
                count ++;
            }
            list.add(aByte);
            i += count; // 直接指向新的位置
        }

        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    private String byteToBitStr(byte abyte) {
        int temp = abyte;

        temp |= 256;//与256按位或，就是在前面补0
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length()-8);
    }

    //将输入的byte转化为二进制，不一定是8位，位数取决于b2
    private String byteToBitStr(byte b, int b2){
        int temp = b;
        temp |= 256;//与256按位或，在前面补齐0
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length()-b2);
    }

    //将二进制字符串解码
    // TODO 看看是否有汲取的必要
    public String decode2(String hashCode, Map<Byte,String> codeMap){
        //先将编码表的key value反过来，变成解码表
        Map<String, Byte> decodingMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : codeMap.entrySet()) {
            decodingMap.put(entry.getValue(), entry.getKey());
        }
        //存放每个符号的ASCII码值
        List<Byte> byteList = new ArrayList<>();
        //用来表示开始对比的首位1,或0的下标，循环中的i为subString截取的最后一个0、1，它后面一位的下标。
        int start = 0;
        for (int i = 0; i < hashCode.length(); i++) {
            //遍历传入的二进制hashCode字符串，与解码表对比，得到一个字符，就加入到集合中
            String substring = hashCode.substring(start, i);
            if (decodingMap.get(substring) != null){
                //表示在解码表中有此字符
                byteList.add(decodingMap.get(substring));
                //让start=i，重新开始对比扫描
                start = i;
            } else if (i == hashCode.length()-1 ){
                //因为subString是左闭右开，在最后一段的时候会少截一个字符，所以需要判断一下，i是否到达最后一个字符
                substring = hashCode.substring(start);
                byteList.add(decodingMap.get(substring));
            }
        }
        //将集合中的byte取出，放入数组，方便转换成字符串
        byte[] decodingBytes = new byte[byteList.size()];
        for (int i = 0; i < decodingBytes.length; i++) {
            //遍历集合，放入数组
            decodingBytes[i] = byteList.get(i);
        }
        //将数组中的byte依照ASCII码表转换成字符串
        String decodingStr = new String(decodingBytes);
        return decodingStr;
    }


    /**
     * 将byte字节转换为字节码字符串
     *
     * @return
     */
    public String byteToBitString2(byte[] bytes) {
        String string; // 表示将byte转换成的String字符串
        String substring; // 因为负数转换成二进制补码的时候是32位，系统会自动用1补全前面的位数，所以需要截取字符串
        StringBuilder stringBuilder = new StringBuilder(); // 用来拼接转换后的字符串
        for (int i = 0; i < bytes.length; i++) {
            // 如果是正数，会不足八位，需要用0填充，所以使用 按位或 256运算，256的二进制位100000000，后面是8个0.
            if (bytes[i] >= 0 && i < bytes.length - 1) {
                int temp = bytes[i] | 256;
                string = Integer.toBinaryString(temp);
                // 按位或运算以后，是首位为1的9位二进制数，所以需要截取后八位
                substring = string.substring(string.length() - 8);
            } else if (bytes[i] >= 0 && i == bytes.length - 1) {
                // 表示是最后一串正数二进制，在压缩的时候可能是不足八位的，所以无需补位。
                substring = Integer.toBinaryString(bytes[i]);
            } else {
                // 表示是负数，截取后八位即可
                string = Integer.toBinaryString(bytes[i]);
                substring = string.substring(string.length() - 8);
            }
            // 拼接截取后的字符串
            stringBuilder.append(substring);
        }
        return stringBuilder.toString();
    }


    /**
     * 将byte字节转换为字节码字符串
     *
     * @param flag true表示不是最后一个字节 false表示是最后一个字节
     * @param b
     * @return
     */
    public String byteToBitString(boolean flag, byte b) {
        int temp = b; // byte转为int
        if (flag) {
            // 如果是最后一个字节无需补高位
            // 比如最后一个 字节是 28 -> 11100 此时就不能补高位了 假如补高位 就变成 00011100了

            // 末尾是 4 -> 100 , 但是有可能是 0100 也可能是00100,说不清楚,所以需要判断最后一位是几位二进制
            temp |= 256; // 或操作 1 0000 0000
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            // 假如末位大于8个字节
            return str.substring(str.length() - 8);
        }
        // 末位小于8个字节，直接返回
        return str;
    }

    /**
     * 生成哈夫曼编码
     */
    public Map<Byte, String> getCodeTable(TreeNode root) {
        if (root == null) {
            return null;
        }
        getCodes(root, "", new StringBuilder());
        return codeTable;
    }

    /**
     * 生成哈夫曼编码列表
     *
     * @param node
     * @param code 路径 左子结点是0 右子结点是1
     * @param sb
     */
    private void getCodes(TreeNode node, String code, StringBuilder sb) {
        if (node == null) {
            return;
        }
        // 用一个新的sbb变量，是为了保存父结点的路径
        StringBuilder sbb = new StringBuilder(sb);
        sbb.append(code);
        // 判断是否是叶子结点，假如是叶子结点，需要将这个结点和路径放到Map里
        if (node.getLeft() == null && node.getRight() == null) {
            // 是叶子结点，需要将这个结点和路径放到Map里
            codeTable.put(node.getData(), sbb.toString());
        } else {
            // 不是叶子结点，递归左子树和右子树
            getCodes(node.getLeft(), "0", sbb);
            getCodes(node.getRight(), "1", sbb);
        }
    }

    /**
     * 将字符串生成哈夫曼编码的列表 也就是byte[]数组
     */
    private byte[] zipToByteArray(byte[] bytes, Map<Byte, String> codeTable) {
        if (bytes == null) {
            return null;
        }

        // 遍历原始bytes数组去查询 编码表
        StringBuilder sb = new StringBuilder();
        for (byte data : bytes) {
            sb.append(codeTable.get(data));
        }
        // 将编码后的 字节码序列 转换为byte数组

        // 创建返回byte[]数组
        int len;
        // 下面的if else等价 (sb.length() + 7 / 8 )
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        byte[] newBytes = new byte[len + 1]; // 为什么加1呢? 是为了保存最后一位的字节的 二进制位数
        // 将字节码每8位转为byte
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String singleByte;
            if (i + 8 < sb.length()) {
                singleByte = sb.substring(i, i + 8);
            } else {
                singleByte = sb.substring(i);
            }
            newBytes[index++] = (byte) Integer.parseInt(singleByte, 2);
        }
        newBytes[index] = (byte)(sb.length() % 8) ; // 保存最后一位的字节的 二进制位数
        return newBytes;
    }

    /**
     * 压缩文件
     *
     * @param srcFile 源文件路径
     * @param dstFile 压缩文件存放路径
     */
    public void zipFile(String srcFile, String dstFile) {
        try (
            InputStream is = new FileInputStream(srcFile);
            FileOutputStream os = new FileOutputStream(dstFile); // 创建字节输出流
            ObjectOutputStream oos = new ObjectOutputStream(os)
        ) {
            // 创建一个byte数组，存放待压缩文件的字节
            byte[] bytes = new byte[is.available()];
            System.out.println("原始字节数组长度：" + bytes.length);
            // 读取源文件数据
            is.read(bytes);
            // 对源文件进行哈夫曼编码的压缩
            byte[] zipBytes = zip(bytes);
            System.out.println("压缩字节数组长度：" + bytes.length);
            // 使用对象输出流,将压缩后的字节数组和对应的哈夫曼编码表保存起来
            // 哈夫曼编码表是为了后续解压缩使用的
            oos.writeObject(zipBytes);
            oos.writeObject(codeTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解压文件
     *
     * @param srcFile 压缩源文件路径
     * @param dstFile 解压存放路径
     */
    public void unZipFile(String srcFile, String dstFile) {
        try (
            InputStream is = new FileInputStream(srcFile);
            ObjectInputStream ois = new ObjectInputStream(is);
            FileOutputStream os = new FileOutputStream(dstFile) // 创建字节输出流
        ) {
            // 读取压缩源文件
            byte[] bytes = (byte[]) ois.readObject();
            // 读取压缩文件的哈夫曼编码
            Map<Byte, String> codeTable = (Map<Byte, String>) ois.readObject();
            // 解压缩文件
            byte[] dstBytes = decode(codeTable, bytes);
            // 将解压的字节码存放到新的路径
            os.write(dstBytes);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public Map<Byte, String> getCodeTable() {
        return codeTable;
    }

    public void setCodeTable(Map<Byte, String> codeTable) {
        this.codeTable = codeTable;
    }

    /**
     * 二叉树的结点对象
     */
    class TreeNode implements Comparable<TreeNode> {
        /**
         * 权值
         */
        private int weight;
        /**
         * 左子树指针
         */
        private TreeNode left;
        /**
         * 右子树指针
         */
        private TreeNode right;
        /**
         * 该结点存储的字节
         */
        private Byte data;

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public Byte getData() {
            return data;
        }

        public void setData(Byte data) {
            this.data = data;
        }

        public TreeNode(int weight, Byte data) {
            this.weight = weight;
            this.data = data;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }

        /**
         * 按照权值升序
         */
        @Override
        public int compareTo(TreeNode node) {
            return this.weight - node.weight;
        }
    }
}

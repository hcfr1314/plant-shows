package com.hhgs.plantshows.util;

import com.hhgs.plantshows.model.DO.Bcs;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;


@Component
public class HbaseUtil {

    public static Connection connection = null;
    public static HBaseConfiguration hBaseConfiguration = null;
    public static Admin admin = null;




    static {
        Configuration configuration = new Configuration();
        configuration.set("hbase.zookeeper.quorum", "172.28.8.9,172.28.8.10,172.28.8.12");
        configuration.set("hbase.master", "172.28.8.10:60000");
        configuration.set("hbase.rootdir", "hdfs://nameservice1/hbase");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.setLong("hbase.rpc.timeout", 600000);
        configuration.setLong("hbase.client.operation.timeout", 600000);
        configuration.setLong("hbase.client.scanner.timeout.period", 600000);
        configuration.set("hbase.client.ipc.pool.type", "ThreadLocalPool");
        configuration.setInt("hbase.client.ipc.pool.size", 10);
        hBaseConfiguration = new HBaseConfiguration(configuration);

        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static File scanReportDataByRowkeyword(File file, String tableName, Bcs bcs, long startTime, long endTime) {

          BufferedWriter bw= null;
          StringBuilder stringBuilder = null;
        try  {
            //HTable table = new HTable(hBaseConfiguration, Bytes.toBytes(tableName));
            stringBuilder = new StringBuilder();


            //File file = new File(filePath+lcuId+".csv");
            //File file = FileUtil.createCsv(filePath);
            bw = new BufferedWriter(new FileWriter(file, true));

            stringBuilder.append("ROWKEY," + "TIME," + "CLUMN," + "VALUE" + "\n");

            Scan scan = new Scan();
            scan.setStartRow(Bytes.toBytes(bcs.getLcuId() + "|" + startTime));
            scan.setStopRow(Bytes.toBytes(bcs.getLcuId() + "|" + endTime));
            scan.addColumn(Bytes.toBytes("f"),Bytes.toBytes(bcs.getOrgId()));

            //scan.setRowPrefixFilter(Bytes.toBytes(lcuId));
            Table table = connection.getTable(TableName.valueOf(tableName));

            ResultScanner rs = table.getScanner(scan);
            for (Result r : rs) {
                for (KeyValue keyValue : r.list()) {



                    //hbase库中的rowkey
                    String rowkey = Bytes.toString(keyValue.getRow());
                    System.out.println("rowkey: " + rowkey);
                    stringBuilder.append(rowkey + ",");


                    //hbase库中的时间
                    String dateLong = rowkey.split("\\|")[1];
                    long date = Long.parseLong(dateLong);
                    String sdfTime = DateUtil.getSdfTime(date);
                    System.out.println("Date: " + sdfTime);
                    stringBuilder.append(sdfTime + ",");


                    List<Tag> tags = keyValue.getTags();
                    System.out.println("tags：" + tags);

                    String family = Bytes.toString(keyValue.getFamily());
                    System.out.println("family: " + family);

                    //hbase中的列名称（原始测点id）
                    String qualifier = Bytes.toString(keyValue.getQualifier());
                    System.out.println("qualifier: " + qualifier);
                    stringBuilder.append(qualifier + ",");

                    //实时值
                    String value = Bytes.toString(keyValue.getValue());
                    System.out.println("value: " + value);
                    stringBuilder.append(value + "\n");


                    if (stringBuilder.length() > 100000) {
                        bw.write(stringBuilder.toString(), 0, stringBuilder.length());
                        bw.flush();
                        stringBuilder.delete(0, stringBuilder.length());

                        //判断文件大小
                        /*if (file.length() / 1024f / 1024f > 50) {
                            file = FileUtil.createCsv(filePath);
                            bw.close();
                            bw = new BufferedWriter(new FileWriter(file));
                            stringBuilder.append("ROWKEY," + "TIME," + "CLUMN," + "VALUE" + "\n");
                        }*/

                    }
                }
            }

            return file;

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }finally {

            try {
                bw.write(stringBuilder.toString(),0,stringBuilder.length());
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

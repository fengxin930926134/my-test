package com.fengx.mytest.external.pandoc;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * C:\Users\gzxzl\Desktop\docs
 */
public class MarkdownToPdfConverter {

    public static void main(String[] args) throws Exception {
        batch2();
    }

    private static void batch2() {
        String inputDir = "C:\\Users\\gzxzl\\Desktop\\docs\\";  // Markdown 文件所在目录
        String outputDir = "C:\\Users\\gzxzl\\Desktop\\test\\";  // PDF 文件输出目录
        String fromFormat = "markdown"; // 源文档格式
        String toFormat = "docx"; // 目标文档格式

        // 获取输入目录下所有的markdown文件
        List<File> fileList = new ArrayList<>();
        File dir = new File(inputDir);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".md")) {
                        fileList.add(file);
                    }
                }
            }
        }
        System.out.println("即将转换的文件:" + fileList.size());
        for (File inputFile : fileList) {
            System.out.println(inputFile.getAbsolutePath());
        }

        // 执行Pandoc的命令进行批量转换
        for (File inputFile : fileList) {
            System.out.println("开始转换文件 " + inputFile.getAbsolutePath());
            String outputFilePath = outputDir + File.separator + inputFile.getName().replace(".md", "." + toFormat);

            String pandocCommand = "pandoc " + inputFile.getAbsolutePath() + " --pdf-engine=xelatex -o " + outputFilePath;
            System.out.println(pandocCommand);
//            String pandocCommand = "pandoc " + inputFile.getAbsolutePath() + " -f " + fromFormat + " -t " + toFormat + " -o " + outputFilePath + " --pdf-engine=xelatex";
            try {
                Process process = Runtime.getRuntime().exec(pandocCommand);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
//                reader.close();
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println("文件 " + inputFile.getAbsolutePath() + " 转换成功");
                } else {
                    System.err.println("文件 " + inputFile.getAbsolutePath() + " 转换失败");
                    InputStream errorStream = process.getErrorStream();
                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
                    String line2;
                    while ((line2 = errorReader.readLine()) != null) {
                        System.err.println(line2);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void one() throws Exception {
        // 1. 指定Markdown文件路径
        String inputFilePath = "C:\\Users\\gzxzl\\Desktop\\docs\\.md";

        // 2. 指定输出PDF文件路径
        String outputFilePath = "C:\\Users\\gzxzl\\Desktop\\file.pdf";

//        String pandocCommand = "C:\\Users\\gzxzl\\AppData\\Local\\Pandoc\\pandoc -s " + inputFilePath + " -f markdown -t pdf -o " + outputFilePath + " --pdf-engine=xelatex";
//        Process process = new ProcessBuilder(pandocCommand).start();

        // 3. 调用pandoc命令进行转换
        Process process = new ProcessBuilder("C:\\Users\\gzxzl\\AppData\\Local\\Pandoc\\pandoc", "-s", inputFilePath, "-o", outputFilePath, "--pdf-engine=C:\\Users\\gzxzl\\AppData\\Local\\Programs\\MiKTeX\\miktex\\bin\\x64\\xelatex.exe").start();

        // 4. 等待转换完成
        InputStream errorStream = process.getErrorStream();
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        String line;
        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Markdown转换为PDF成功！");
        } else {
            System.err.println("Markdown转换为PDF失败！");
        }
    }
}

package com.fengx.mytest.base.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesTest {
 
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Path path = Paths.get("/com/fengx/mytest/base/nio/FilesTest.java");
		// 复制文件
		Files.copy(path, new FileOutputStream("/com/fengx/mytest/base/nio/CopyFilesTest.txt"));
		// 判断FilesTest.java是不是隐藏文件
		System.out.println("FilesTest.java是隐藏文件么?" + Files.isHidden(path));
		// 一次性读取FilesTest.java文件的所有行
		List<String> lines = Files.readAllLines(path);
		System.out.println(lines);
		// 判断文件的大小
		System.out.println("FilesTest.java的文件大小为" + Files.size(path));
		List<String> poem = new ArrayList<>(2);
		poem.add("野火烧不尽");
		poem.add("春风吹又生");
		// 直接将多个字符串内容写入到指定文件中
		Files.write(Paths.get("/com/fengx/mytest/base/nio/CopyFilesTest.txt"), poem, StandardCharsets.UTF_8);
	}
 
}
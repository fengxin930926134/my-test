package com.fengx.mytest.external.minio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileClientTest {

    @Autowired
    private FileClient fileClient;

    @Test
    public void test() {
        fileClient.listFileNames("default").forEach(System.out::println);

        System.out.println(fileClient.getFileInfo("1ab2a515-577e-4168-93cd-0a3d11ae6907").toString());
    }
}

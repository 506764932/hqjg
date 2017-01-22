package com.hqjg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by 50676 on 2016/9/11.
 */

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldListAllFiles() throws Exception {
/*        given(this.storageService.loadAll())
                .willReturn(Stream.of(Paths.get("first.txt"), Paths.get("second.txt")));*/

        /*this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("files",
                        Matchers.contains("http://localhost/files/first.txt", "http://localhost/files/second.txt")));*/
    }

    @Test
    public void shouldSaveUploadedFile() throws Exception {
        MockMultipartFile multipartFile =
                new MockMultipartFile("uploadFile", "test.txt", "text/plain", "Spring Framework".getBytes());
        this.mvc.perform(fileUpload("/system/uploadFile").file(multipartFile))
                .andExpect(status().isOk());

        // then(this.storageService).should().store(multipartFile);
    }

    @Test
    public void should404WhenMissingFile() throws Exception {
       /* given(this.storageService.loadAsResource("test.txt"))
                .willThrow(StorageFileNotFoundException.class);*/

        this.mvc.perform(get("/files/test.txt"))
                .andExpect(status().isNotFound());
    }
}

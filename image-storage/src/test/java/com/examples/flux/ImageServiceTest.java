package com.examples.flux;

import com.examples.flux.service.ImageService;
import com.examples.flux.web.dto.Image;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ImageServiceTest {

    private final static int IMAGE_BATCH_SIZE = 100;

    @Autowired
    private ImageService imageService;

    @Test
    public void getAsyncImagesTest(){
        List<Image> images = imageService.getImages(IMAGE_BATCH_SIZE);

        Assert.assertEquals(images.size(), IMAGE_BATCH_SIZE);
    }
}

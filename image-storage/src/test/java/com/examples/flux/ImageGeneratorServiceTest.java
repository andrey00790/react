package com.examples.flux;

import com.examples.flux.configuration.StorageConfiguration;
import com.examples.flux.service.ImageGeneratorService;
import com.examples.flux.web.dto.Image;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImageGeneratorServiceTest.TestConfiguration.class)
@ContextConfiguration(classes = {
        StorageConfiguration.class
})
@Profile("test")
@Slf4j
public class ImageGeneratorServiceTest {

    @Autowired
    private ImageGeneratorService imageGeneratorService;

    @Test
    public void userProfileGeneratorTest() {
        Image image = imageGeneratorService.generate();

        Assert.assertNotNull(image.getValue());
    }

    @Configuration
    @Import(ImageGeneratorService.class)
    static class TestConfiguration {
    }
}


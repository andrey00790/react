package com.examples.flux;

import com.examples.flux.configuration.FakeDataConfiguration;
import com.examples.flux.web.dto.Profile;
import com.examples.flux.service.ProfileGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProfileGeneratorServiceTest.TestConfiguration.class)
@ContextConfiguration(classes = {
        FakeDataConfiguration.class})
@org.springframework.context.annotation.Profile("test")
@Slf4j
public class ProfileGeneratorServiceTest {

    @Autowired
    private ProfileGeneratorService profileGeneratorService;

    @Test
    public void userProfileGeneratorTest() {
        Profile generate = profileGeneratorService.generate();

        Assert.assertNotNull(generate.getFio());
    }

    @Configuration
    @Import(ProfileGeneratorService.class)
    static class TestConfiguration {
    }
}

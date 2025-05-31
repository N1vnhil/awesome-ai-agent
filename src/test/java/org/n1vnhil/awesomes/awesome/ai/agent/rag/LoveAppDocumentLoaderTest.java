package org.n1vnhil.awesomes.awesome.ai.agent.rag;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoveAppDocumentLoaderTest {

    @Autowired
    LoveAppDocumentLoader loveAppDocumentLoader;

    @Test
    public void test() {
        loveAppDocumentLoader.loadMarkDowns();

    }

}
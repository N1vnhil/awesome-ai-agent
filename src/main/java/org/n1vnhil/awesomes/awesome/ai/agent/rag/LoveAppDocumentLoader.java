package org.n1vnhil.awesomes.awesome.ai.agent.rag;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class LoveAppDocumentLoader {

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    public List<Document> loadMarkDowns() {
        List<Document> allDocuments = new ArrayList<>();
        try {
            Resource[] resources = resourcePatternResolver.getResources("classpath:document/*.md");
            for(Resource resource: resources) {
                String filename = resource.getFilename();
                MarkdownDocumentReaderConfig markdownDocumentReaderConfig = MarkdownDocumentReaderConfig.builder()
                        .withIncludeBlockquote(false)
                        .withIncludeCodeBlock(false)
                        .withHorizontalRuleCreateDocument(true)
                        .withAdditionalMetadata("filename", filename)
                        .build();

                MarkdownDocumentReader markdownDocumentReader = new MarkdownDocumentReader(resource, markdownDocumentReaderConfig);
                allDocuments.addAll(markdownDocumentReader.get());
            }
        } catch (IOException e) {
            log.error("Markdown 加载失败：", e);
        }

        return allDocuments;
    }

}

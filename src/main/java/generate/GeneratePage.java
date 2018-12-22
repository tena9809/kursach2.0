package generate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


public class GeneratePage {
    private static final String HTML_DIR = "public_html";

    private static GeneratePage generatePage;
    private final Configuration cfg;

    public static GeneratePage instance() {
        if (generatePage == null)
           generatePage = new GeneratePage();
        return generatePage;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator +filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

    private GeneratePage() {
        cfg = new Configuration();
    }
}

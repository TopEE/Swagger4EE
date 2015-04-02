/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import dk.lnj.swagger4ee.model.SWRoot;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Assert;

/**
 *
 * @author laj
 */
public class AbstractJSonTest {
      public void makeCompare(SWRoot root, String expFile) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
// make deserializer use JAXB annotations (only)
        mapper.setAnnotationIntrospector(introspector);
// make serializer use JAXB annotations (only)

        StringWriter sw = new StringWriter();
        mapper.writeValue(sw, root);
        mapper.writeValue(new FileWriter(new File("lasttest.json")), root);

        String actual = sw.toString();
        String expected = new String(Files.readAllBytes(Paths.get(expFile)));

        String[] exp_split = expected.split("\n");
        String[] act_split = actual.split("\n");

        for (int i = 0; i < exp_split.length; i++) {
            String exp = exp_split[i];
            String act = act_split[i];
           String shortFileName = expFile.substring(expFile.lastIndexOf("/"));
            Assert.assertEquals(shortFileName+":" + (i+1), superTrim(exp), superTrim(act));
        }

    }

    public String superTrim(String s) {
        return s.trim().replaceAll(": ", ":").replaceAll(" :", ":").replaceAll(" \\[", "[").replaceAll(" \\]", "]").replaceAll("\\[ ", "[").replaceAll("\\] ", "]");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SWModelProperty {

    @XmlElement(required = false)
    private String type;
    @XmlElement(required = false)
    private String format;
    @XmlElement(required = false)
    private SWSchemaRef schema;
    @XmlElement(required = false)
    private String description;
    @XmlElement(required = false, name = "enum")
    private List<String> enums;

    public SWModelProperty() {
    }
    
    

    public SWModelProperty(String type, String format, List<String> enums) {
        this.enums = enums;
        this.type = type;
        this.format = format;
    }

    public SWModelProperty(String type, String format) {
        this.type = type;
        this.format = format;
    }

    public SWModelProperty(SWSchemaRef schema) {
        this.schema = schema;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public SWSchemaRef getSchema() {
        return schema;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setSchema(SWSchemaRef schema) {
        this.schema = schema;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getEnums() {
        return enums;
    }

    public void setEnums(List<String> enums) {
        this.enums = enums;
    }

}

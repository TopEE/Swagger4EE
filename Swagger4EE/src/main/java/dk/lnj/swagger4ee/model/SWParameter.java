/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public class SWParameter {

        private String name;
        private String in;
        @XmlElement(required = false)
        private String description;

        private boolean required = true;

        @XmlElement(required = false)
        private String defaultValue;
        @XmlElement(required = false)
        private String type;
        @XmlElement(required = false)
        private String format;
        @XmlElement(required = false)
        private SWSchemaRef schema;

    public SWParameter() {
    }
        
        

        public String getName() {
            return name;
        }

        public String getFormat() {
            return format;
        }

        public SWSchemaRef getSchema() {
            return schema;
        }

        public void setSchema(SWSchemaRef schema) {
            this.schema = schema;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIn() {
            return in;
        }

        public void setIn(String paramType) {
            this.in = paramType;
        }

    }

   

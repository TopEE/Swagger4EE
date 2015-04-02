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
    public class SWSchemaRef {

        @XmlElement(name = "$ref", required = false)
        private String ref;

        @XmlElement(required = false)
        private String type;

        @XmlElement(required = false)
        private SWItem items;

        @XmlElement(required = false)
        private String format;

    public SWSchemaRef() {
    }
        
        

        public SWSchemaRef(String ref) {
            this.ref = "#/definitions/"+ref;
        }

        public SWSchemaRef(SWItem items) {
            this.type = "array";
            this.items = items;
        }

        public SWSchemaRef(String type, String format) {

            this.type = type;
            this.format = format;
        }

        public String getFormat() {
            return format;
        }

        public String getType() {
            return type;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getRef() {
            return ref;
        }

    }
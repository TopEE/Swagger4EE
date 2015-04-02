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
    public class SWResponse {

        private String description;

        @XmlElement(required = false)
        private SWSchemaRef schema;

    public SWResponse() {
    }
        
        

        public SWResponse(SWSchemaRef schemaRef, String description) {
            this.schema = schemaRef;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    public SWSchemaRef getSchema() {
        return schema;
    }

    public void setSchema(SWSchemaRef schema) {
        this.schema = schema;
    }
        
        

    }

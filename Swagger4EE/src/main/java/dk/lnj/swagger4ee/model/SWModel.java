/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee.model;

import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

 @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public class SWModel {

        private String id;
        private Map<String, SWModelProperty> properties = new TreeMap<String, SWModelProperty>();

    public SWModel() {
    }

        
        public SWModel(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Map<String, SWModelProperty> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, SWModelProperty> properties) {
            this.properties = properties;
        }

    }

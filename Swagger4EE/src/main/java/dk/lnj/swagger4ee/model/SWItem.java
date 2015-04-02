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
    public class SWItem {

        @XmlElement(name = "$ref", required = false)
        private String ref;

    public SWItem() {
    }
        
        

        public SWItem(String ref) {
            this.ref = "#/definitions/"+ref;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

    }

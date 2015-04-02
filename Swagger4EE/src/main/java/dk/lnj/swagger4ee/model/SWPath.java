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
    public class SWPath {

        @XmlElement(required = false)
        private SWOperation get;
        @XmlElement(required = false)
        private SWOperation post;
        @XmlElement(required = false)
        private SWOperation delete;
        @XmlElement(required = false)
        private SWOperation put;

        public SWPath() {

        }

        public void setGet(SWOperation get) {
            this.get = get;
        }

        public void setPost(SWOperation post) {
            this.post = post;
        }

        public SWOperation getPost() {
            return post;
        }

        public void setDelete(SWOperation delete) {
            this.delete = delete;
        }

        public void setPut(SWOperation put) {
            this.put = put;
        }

        public SWOperation getDelete() {
            return delete;
        }

        public SWOperation getPut() {
            return put;
        }
       
        public SWOperation getGet() {
            return get;
        }
    }

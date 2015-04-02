/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public class SWOperation {

        private List<String> tags = new ArrayList<String>();
        @XmlElement(required = false)
        private String summary;
        @XmlElement(required = false)
        private String description;
        private String operationId;
        private String[] produces;
        @XmlElement(required = false)
        private Map<String, SWResponse> responses = new HashMap<String, SWResponse>();
        @XmlElement(required = false)
        private List<SWParameter> parameters;

    public SWOperation() {
    }
        
        

        public String getOperationId() {
            return operationId;
        }

        public void setOperationId(String operationId) {
            this.operationId = operationId;
        }

        public String getSummary() {
            return summary;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public void setSummary(String summary) {
            if (summary == null || summary.equals("")) {
                return;
            }
            this.summary = summary;
        }

        public String getDescription() {

            return description;
        }

        public void setDescription(String description) {
            if (description == null || description.equals("")) {
                return;
            }
            this.description = description;
        }

        public Map<String, SWResponse> getResponses() {
            return responses;
        }

        public void setResponses(Map<String, SWResponse> responses) {
            this.responses = responses;
        }

        public String[] getProduces() {
            return produces;
        }

        public void setProduces(String[] produces) {
            if (produces == null || produces.length == 0) {
                return;
            }
            this.produces = produces;
        }

        public List<SWParameter> getParameters() {
            if(parameters==null){
                parameters = new ArrayList<SWParameter>();
            }
            return parameters;
        }

        public void setParameters(List<SWParameter> parameters) {
            this.parameters = parameters;
        }

    }

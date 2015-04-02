package dk.lnj.swagger4ee.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
  @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
public class SWInfo {

    String title;
    String version;

    public SWInfo() {
    }
    
    

    public SWInfo(String title, String version) {
        this.title = title;
        this.version = version;
    }



    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

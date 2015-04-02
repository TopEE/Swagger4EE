package dk.lnj.swagger4ee.model;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SWRoot {

	private String swagger = "2.0";
	private String host;
	private String basePath;
	private SWInfo info;
        
	private Map<String, SWPath> paths = new HashMap<String, SWPath>();
	private Map<String, SWModel> definitions = new TreeMap<String, SWModel>();

    public SWRoot() {
    }
        
        

	public SWRoot(String host, String basePath) {
		this.host = host;
		this.basePath = basePath;
	}

	public Map<String, SWModel> getModels() {
		return definitions;
	}

	public void setModels(Map<String, SWModel> definitions) {
		this.definitions = definitions;
	}

	public void setInfo(SWInfo info) {
		this.info = info;
	}

	public SWInfo getInfo() {
		return info;
	}

	public String getSwagger() {
		return swagger;
	}

	public void setSwagger(String swagger) {
		this.swagger = swagger;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public Map<String, SWPath> getPaths() {
		return paths;
	}

	public void setApis(Map<String, SWPath> paths) {
		this.paths = paths;
	}
}
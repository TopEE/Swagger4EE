package dk.lnj.swagger4ee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

public class SwaggerModel {
	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Parameter {
		String name;
		@XmlElementWrapper(required = false)
		String description;
		@XmlElementWrapper(required = false)
		String defaultValue;
		boolean required = true;
		String type;
		String paramType;
		boolean allowMultiple;
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ResponseClass {
		@XmlElement(name = "$ref")
		String ref;

		public ResponseClass(String ref) {
			this.ref = ref;
		}
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Model {
		String id;
		Map<String, ModelProperty> properties = new TreeMap<String, ModelProperty>();

		public Model(String id) {
			this.id = id;
		}
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ModelProperty {
		@XmlElementWrapper(required = false)
		String type;
		@XmlElementWrapper(required = false, name = "$ref")
		String ref;
		@XmlElementWrapper(required = false)
		String description;
		@XmlElementWrapper(required = false,name="enum")
		List<String> enums;

		public ModelProperty(String type,String ref,List<String> enums) {
			this.ref=ref;
			this.type=type;
			this.enums=enums;
		}
	}
	
	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Root {
		String apiVersion = "1.0.0";
		String swaggerVersion = "1.2";
		List<ApiResume> apis = new ArrayList<ApiResume>();
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ApiResume {
		String path;

		public ApiResume(String path) {
			this.path = path;
		}
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class RESTResource {
		String apiVersion = "1.0.0";
		String swaggerVersion = "1.2";
		String basePath;
		String resourcePath;
		String[] produces;
		List<SApi> apis = new ArrayList<SApi>();
		Map<String, Model> models = new TreeMap<String, Model>();

		public RESTResource(String basePart) {
			this.basePath = basePart;
		}
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class SApi {
		String path;
		List<Operation> operations = new ArrayList<Operation>();

		public SApi(String path) {
			this.path = path;
		}
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Operation {
		String method;
		String summary = "";
		String notes = "";
		String type;
		@XmlElementWrapper(required = false)
		ResponseClass items;
		String nickname;
		String[] produces;
		List<Parameter> parameters = new ArrayList<Parameter>();	
	}
}

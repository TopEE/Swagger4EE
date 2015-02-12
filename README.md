# Swagger4EE
Swagger on Java EE Rest without all the dependencies.

Use it like this. You also need to add JacksonFeature in Your Configclass. 

public class MySwaggerResource extends SwagResource {
	@Override
	public String getSwaggerUrl() {
		String url  = "http://localhost:9080/CSDistribution/rest";
		return url;
	}

	@Override
	public Class<?>[] getResourcesForSwagging() {
		return new Class[]{CockpitResource.class,ArkiverOgRegisterResource.class};
	}
}

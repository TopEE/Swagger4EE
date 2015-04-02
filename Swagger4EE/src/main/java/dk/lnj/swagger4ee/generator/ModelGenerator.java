/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee.generator;


import dk.lnj.swagger4ee.model.SWModel;
import dk.lnj.swagger4ee.model.SWModelProperty;
import dk.lnj.swagger4ee.model.SWSchemaRef;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author laj
 */
public class ModelGenerator {
    public static SWModel createModel(Class<?> c, Map<String, SWModel> models) {

        SWModel model = new SWModel(c.getSimpleName());
        Field[] declaredFields = c.getDeclaredFields();

        for (Field field : declaredFields) {
            if(field.getName().startsWith("_") || Modifier.isStatic(field.getModifiers())){
                continue;
            }
            if (SwaggerUtil.isClass(field.getType())) {
                if (field.getType().isEnum()) {
                    Field[] enums = field.getType().getDeclaredFields();
                    List<String> names = new ArrayList<String>();
                    for (Field field2 : enums) {
                        if (!field2.getName().contains("$")) {
                            names.add(field2.getName());
                        }
                    }
                    model.getProperties().put(field.getName(), new SWModelProperty("string", null,names));
                } else {
                    model.getProperties().put(field.getName(), new SWModelProperty(new SWSchemaRef(field.getType().getSimpleName())));
                    models.put(field.getType().getSimpleName(), createModel(field.getType(), models));
                }

            } else {
                model.getProperties().put(field.getName(), new SWModelProperty(SwaggerUtil.calculateType(field.getType().getSimpleName()), SwaggerUtil.calculateFormat(field.getType().getSimpleName())));
            }
        }
        return model;
    } 
}

package com.foodapi.utils.serialization;


import io.micronaut.context.annotation.Factory;
import io.micronaut.serde.annotation.SerdeImport;


@Factory
@SerdeImport(packageName = "com.foodapi.models.reponses")
public class SerializationConfig {


}

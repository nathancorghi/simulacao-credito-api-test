package br.com.simulacao.credito.utils;

import br.com.simulacao.credito.Constants;
import br.com.simulacao.credito.model.response.ResponseData;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.lang.reflect.Type;
import java.text.MessageFormat;

@Component
public class RequestUtils {

    @Autowired
    private Gson gson;

    private Response response;

    private RequestSpecification requestSpecification;

    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    public <T> ResponseData<T> get(String url, Class<T> clazz, Object... endpointParameters) {

        url = MessageFormat.format(url, endpointParameters);

        ResponseData<T> responseData = new ResponseData<>();

        RestAssured.baseURI = Constants.BASE_URI;
        RestAssured.defaultParser = Parser.JSON;

        requestSpecification = RestAssured.given();

        requestSpecification.contentType(MimeTypeUtils.APPLICATION_JSON_VALUE);

        response = requestSpecification.get(url);

        logger.info("REQUEST -> Executing GET on {}", url);
        logger.info("RESPONSE -> Code {}", response.statusCode());
        logger.info("RESPONSE -> Body {}", gson.newBuilder().setPrettyPrinting().serializeNulls().create().toJson(response.getBody().as(Object.class)));

        responseData.setResponseData(response.then().extract().response().getBody().as((Type) clazz));
        responseData.setStatusCode(response.then().extract().response().getStatusCode());

        return responseData;
    }
}
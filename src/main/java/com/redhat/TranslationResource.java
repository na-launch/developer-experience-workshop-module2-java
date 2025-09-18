package com.redhat;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Map;

@Path("/helloworld-by-language")
public class TranslationResource {

    @ConfigProperty(name = "translation.default-language", defaultValue = "EN")
    String defaultLanguage;

    @ConfigProperty(name = "translation.file", defaultValue = "translations.json")
    String translationFile;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTranslation() {
        try {
            // Use configured default language instead of hardcoded value
            String countryCode = defaultLanguage.toUpperCase();

            // Load translations.json from resources
            InputStream is = getClass().getClassLoader().getResourceAsStream(translationFile);
            if (is == null) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Could not find " + translationFile + " in resources.")
                        .build();
            }

            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            // Parse JSON
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonObject = mapper.readValue(json, Map.class);

            if (jsonObject != null && jsonObject.containsKey("translations")) {
                Map<String, String> translations = (Map<String, String>) jsonObject.get("translations");

                // Lookup with fallback
                String translation = translations.getOrDefault(
                        countryCode,
                        translations.get("EN") // fallback to English if even defaultLanguage not found
                );

                String timestamp = Instant.now().toString();
                return Response.ok(translation + " @ " + timestamp).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Invalid JSON format in " + translationFile)
                        .build();
            }

        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error reading " + translationFile + ": " + e.getMessage())
                    .build();
        }
    }
}
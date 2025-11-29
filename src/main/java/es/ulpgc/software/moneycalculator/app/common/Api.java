package es.ulpgc.software.moneycalculator.app.common;

import io.github.cdimascio.dotenv.DotenvBuilder;

public class Api {
    private final String ApiKey;
    private final String ApiUrl;

    public Api() {
        ApiKey = new DotenvBuilder().load().get("API_KEY");
        ApiUrl = "https://v6.exchangerate-api.com/v6/API-KEY/".replace("API-KEY", this.ApiKey);
    }

    public String url() {
        return ApiUrl;
    }
}

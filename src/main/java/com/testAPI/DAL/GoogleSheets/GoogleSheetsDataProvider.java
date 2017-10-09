package com.testAPI.DAL.GoogleSheets;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.testAPI.Model.Food;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class GoogleSheetsDataProvider {

    private static final String APPLICATION_NAME = "Google Sheets API Java Test Project";

    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-TestProject");

    private static FileDataStoreFactory DATA_STORE_FACTORY;

    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static HttpTransport HTTP_TRANSPORT;


    private static final List<String> SCOPES =
            Arrays.asList(SheetsScopes.SPREADSHEETS);


    private String spreadsheetId;

    private Sheets service;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {

        }
    }

    public void SetSpreadsheetId(String spreadsheetId)
    {
        this.spreadsheetId = spreadsheetId;
    }

    private Credential authorize() throws IOException {
        // Load client secrets.
        String path = System.getProperty("user.dir") +"//client_secret.json";
        InputStream in =  new FileInputStream(path);
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver())
                .authorize("user");

        return credential;
    }

    private Sheets getSheetsService() throws IOException {

        Credential credential = authorize();
        if (service == null) {
            service =  new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        }
        return service;
    }

    public List<List<Object>> Get(String range) {
        try {
            Sheets service = getSheetsService();
            ValueRange response =  service.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            return response.getValues();
        }catch (Exception e)
        {
            return null;
        }
    }

    public void Set(String range, Food model) {
        try {
            Sheets service = getSheetsService();

            ValueRange valueRange = new ValueRange()
                    .setMajorDimension("ROWS").
                            setValues(model.toObjectList());
            service.spreadsheets().values()
                    .append(spreadsheetId, range,valueRange)
                    .setValueInputOption("RAW")
                    .execute();
        }catch (Exception e)
        {

        }
    }

    public void Updata(String range, Food model) {
        try {
            Sheets service = getSheetsService();

            ValueRange valueRange = new ValueRange()
                    .setMajorDimension("ROWS").
                            setValues(model.toObjectList());
            service.spreadsheets().values()
                    .update(spreadsheetId, range,valueRange)
                    .setValueInputOption("RAW")
                    .execute();

        }catch (Exception e)
        {

        }
    }

}

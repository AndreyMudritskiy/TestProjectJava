package com.testAPI.DAL.GoogleSheets.Repository;

import com.fasterxml.jackson.core.JsonFactory;
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


import com.testAPI.DAL.GoogleSheets.GoogleSheetsDataProvider;
import com.testAPI.DAL.IRepository;
import com.testAPI.Model.Food;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class FoodsRepository implements IRepository<Food>
{
    final String spreadsheetId = "1vaJN8sqoYXZtFSYzChlXMiNdUlO9SUgC3KvYh-FPsvY";
    private GoogleSheetsDataProvider googleSheetsDataProvider;

    public FoodsRepository(GoogleSheetsDataProvider googleSheetsDataProvider)
    {
        this.googleSheetsDataProvider = googleSheetsDataProvider;
        this.googleSheetsDataProvider.SetSpreadsheetId(spreadsheetId);
    }

    @Override
    public Food Get(String ID) {

        String range = "A"+ID+":C"+ID;
        List<List<Object>> values =  googleSheetsDataProvider.Get(range);

        if (values == null || values.size() == 0) {
            return  null;
        } else {
            List<Object> row = values.get(0);
            return new Food(ID, row);
        }
    }

    @Override
    public void Set(Food model) {
        String range = "A"+model.ID+":C"+model.ID;
        googleSheetsDataProvider.Set(range, model);
    }

    @Override
    public void Updata(Food model) {
        String range = "A"+model.ID+":C"+model.ID;
        googleSheetsDataProvider.Updata(range, model);
    }
}

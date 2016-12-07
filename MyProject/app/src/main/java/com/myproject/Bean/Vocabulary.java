package com.myproject.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by D1ngZenL1ng on 2016/11/25.
 */
@DatabaseTable(tableName ="tb_name")

public class Vocabulary {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "vocabulary")
    private String vocabulary;
    @DatabaseField(columnName = "explain")
    private String explain;

    public Vocabulary() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Vocabulary(String vocabulary, String explain) {

        this.vocabulary = vocabulary;
        this.explain = explain;
    }
}

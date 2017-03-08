package com.example.hyejin.kb.activity.singleplay;

import android.provider.BaseColumns;

/**
 * Created by hyejin on 2016-12-17.
 */
public class DataBases {

    public static final class CreateDB implements BaseColumns {
        public static final String QUESTION = "question";
        public static final String ANSWER = "answer";
        public static final String _TABLENAME = "GameInfo";
        // id name number time image
        public static final String _CREATE =
                "create table "+_TABLENAME+"("
                        +_ID+" integer primary key autoincrement, "
                        +QUESTION+" varchar(200) not null , "
                        +ANSWER+" varchar(200) not null , )";

    }

}

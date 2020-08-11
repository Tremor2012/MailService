package com.example.service.utils;

import com.example.service.model.QueryLine;
import com.example.service.model.TimeLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class FileParser {
    private List<QueryLine> queryLines;
    private List<TimeLine> timeLines;
    private List<String> result = new ArrayList<>();

    private List<QueryLine> getQueryInstance() {
        if (queryLines != null)
            return queryLines;
        queryLines = new ArrayList<>();
        return queryLines;
    }

    private List<TimeLine> getTimeInstance() {
        if (timeLines != null)
            return timeLines;
        timeLines = new ArrayList<>();
        return timeLines;
    }

    public void parseFile(String file) {
        timeLines = null;
        queryLines = null;

        Scanner scanner = null;
        try {
            File filePath = new File(file);
            scanner = new Scanner(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            assert scanner != null;
            if (!scanner.hasNext()) break;
            String str = scanner.nextLine();

            if (str.startsWith("C"))
                makeTimeLine(str);

            if (str.startsWith("D"))
                makeQueryLine(str);
        }
        scanner.close();

        listsAnalise(getTimeInstance(), getQueryInstance()).forEach(System.out::println);
    }

    private void makeQueryLine(String str) {
        String[] fields = str.split(" ");

        getQueryInstance().add(new QueryLine(fields[1], fields[2], fields[3], fields[4]));

    }

    private void makeTimeLine(String str) {
        String[] fields = str.split(" ");

        getTimeInstance().add(new TimeLine(fields[1], fields[2], fields[3], fields[4], fields[5]));
    }

    private List<String> listsAnalise(List<TimeLine> timeLines, List<QueryLine> queryLines) {

        for (QueryLine ql : queryLines) {
            int min = 0;
            int count = 0;

            for (TimeLine tl : timeLines) {
                if (isAllMatch(ql, tl)) {
                    min += Integer.parseInt(tl.getTime());
                    count += 1;
                }
            }

            if (count == 0) {
                result.add("-");
            } else {
                result.add(valueOf(min / count));
            }
        }
        return result;
    }

    private boolean isAllMatch(QueryLine ql, TimeLine tl){
        return isServiceMatch(ql.getService(), tl.getService())
                && isQuestionMatch(ql.getQuestion(), tl.getQuestion())
                && isTypeMatch(ql.getResponseType(), tl.getResponseType())
                && isDateMatch(ql.getData(), tl.getData());
    }

    private boolean isServiceMatch(String st1, String st2) {
        if (st1.equals("*"))
            return true;
        return st2.startsWith(st1);
    }

    private boolean isQuestionMatch(String st1, String st2) {
        if (st1.equals("*"))
            return true;
        return st2.startsWith(st1);
    }

    private boolean isTypeMatch(String st1, String st2) {
        if (st1.equals("*"))
            return true;
        return st2.equals(st1);
    }

    private boolean isDateMatch(String st1, String st2) {

        if (!st1.contains("-")) {
            return getDateForm(st2).after(getDateForm(st1));
        }

        String[] dates = st1.split("-");

        return getDateForm(dates[0]).before(getDateForm(st2)) && getDateForm(st2).before(getDateForm(dates[1]));
    }

    private Date getDateForm(String date) {

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }


}

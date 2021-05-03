package org.launchcode.soweto.models;

import java.util.ArrayList;

// This is a change made in sandbox.

/**
 * Created by LaunchCode
 */
public class ConcertData {


    /**
     * Returns the results of searching the concerts data by field and search term.
     *
     * For example, searching for venue "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * @param column Concert field that should be searched.
     * @param value Value of the field to search for.
     * @param allConcerts The list of concerts to search.
     * @return List of all concerts matching the criteria.
     */
    public static ArrayList<Concert> findByColumnAndValue(String column, String value, Iterable<Concert> allConcerts) {

        ArrayList<Concert> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Concert>) allConcerts;
        }

        if (column.equals("all")){
            results = findByValue(value, allConcerts);
            return results;
        }
        for (Concert concert : allConcerts) {

            String aValue = getFieldValue(concert, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(concert);
            }
        }

        return results;
    }

    public static String getFieldValue(Concert concert, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = concert.getName();
        } else if (fieldName.equals("venue")){
            theValue = concert.getVenue().toString();
        } else {
            theValue = concert.getArtists().toString();
        }

        return theValue;
    }

    /**
     * Search all Concert fields for the given term.
     *
     * @param value The search term to look for.
     * @param allConcerts The list of concerts to search.
     * @return      List of all concerts with at least one field containing the value.
     */
    public static ArrayList<Concert> findByValue(String value, Iterable<Concert> allConcerts) {


        ArrayList<Concert> results = new ArrayList<>();

        for (Concert concert : allConcerts) {

            if (concert.getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(concert);
            } else if (concert.getVenue().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(concert);
            } else if (concert.getArtists().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(concert);
            }

        }

        return results;
    }


}


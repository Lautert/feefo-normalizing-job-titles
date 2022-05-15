package com.feefo.normalizingjob.service;

import java.util.HashMap;
import java.util.Map;

import com.feefo.normalizingjob.exception.MessageUserException;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

@Service
public class NormalizeService
{
    public String normalize (
        String search
    )
        throws MessageUserException {

        if (search.length() == 0)
        {
            throw new MessageUserException(
                "The search parameter is required",
                400
            );
        }

        // Fixed Data - Map of search term to job title
        // In a real application, this data would be stored in a database
        Map<String, String> map = new HashMap<>();
        //@formatter:off
        map.put("Java engineer", "Software engineer");
        map.put("C# engineer", "Software engineer");
        map.put("Accountant", "Accountant");
        map.put("Chief Accountant", "Accountant");
        //@formatter:on

        float distance = 1;
        String result = "";
        LevenshteinDistance levenshtein = new LevenshteinDistance();

        for (Map.Entry<String, String> entry : map.entrySet())
        {
            // May need to change to lowercase for case-insensitive matching
            // need check the business rules
            float currentDistance = levenshtein
                .apply(
                    entry.getKey(),
                    search
                );

            currentDistance = currentDistance / entry.getKey().length();

            if (currentDistance < distance)
            {
                distance = currentDistance;
                result = entry.getValue();
            }
        }

        return result;
    }
}

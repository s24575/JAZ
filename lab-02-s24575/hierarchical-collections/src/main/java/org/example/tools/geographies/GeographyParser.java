package org.example.tools.geographies;

import org.example.model.Geography;
import org.example.tools.abstractions.IParse;

public class GeographyParser implements IParse<Geography> {
    @Override
    public Geography parse(String s){
        String arr[] = s.split(";");
        Geography geography = new Geography();
        geography.setId(Integer.parseInt(arr[0]));
        geography.setType(arr[1]);
        geography.setName(arr[2]);
        geography.setCode(arr[3]);
        try{
            geography.setParentId(Integer.parseInt(arr[4].trim()));
        }
        catch (NumberFormatException e){
            // System.out.println("Not a num: " + arr[4].trim());
            geography.setParentId(null);
        }
        return geography;
    }
}

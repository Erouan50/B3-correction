package com.supinfo.geekquote.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v);
    }
}

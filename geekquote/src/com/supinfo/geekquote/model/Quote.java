package com.supinfo.geekquote.model;

import java.util.Date;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class Quote {

    private String strQuote;
    private int rating;
    private Date creationDate;

    public String getStrQuote() {
        return strQuote;
    }

    public void setStrQuote(String strQuote) {
        this.strQuote = strQuote;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quote quote = (Quote) o;

        if (rating != quote.rating) return false;
        if (creationDate != null ? !creationDate.equals(quote.creationDate) : quote.creationDate != null) return false;
        if (strQuote != null ? !strQuote.equals(quote.strQuote) : quote.strQuote != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = strQuote != null ? strQuote.hashCode() : 0;
        result = 31 * result + rating;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "strQuote='" + strQuote + '\'' +
                ", rating=" + rating +
                ", creationDate=" + creationDate +
                '}';
    }
}

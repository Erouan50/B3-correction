package com.supinfo.geekquote.model;

import com.supinfo.geekquote.utils.DateAdapter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@Entity
@Table(name = "quotes")
@NamedQueries({
        @NamedQuery(name = "findAllQuotes", query = "SELECT quote FROM Quote AS quote")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String strQuote;
    private Integer rating;
    @Temporal(TemporalType.DATE)
    @XmlJavaTypeAdapter(value = DateAdapter.class, type = String.class)
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrQuote() {
        return strQuote;
    }

    public void setStrQuote(String strQuote) {
        this.strQuote = strQuote;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date date) {
        this.createDate = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quote quote = (Quote) o;

        if (createDate != null ? !createDate.equals(quote.createDate) : quote.createDate != null) return false;
        if (id != null ? !id.equals(quote.id) : quote.id != null) return false;
        if (rating != null ? !rating.equals(quote.rating) : quote.rating != null) return false;
        if (strQuote != null ? !strQuote.equals(quote.strQuote) : quote.strQuote != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (strQuote != null ? strQuote.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}

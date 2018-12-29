/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;


import entities.Bill;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;



public class BILLID implements IdentifierGenerator, Configurable {

    @Override
    public Serializable generate(SessionImplementor si, Object o) throws HibernateException {
        Bill bill = (Bill)o;
        if (bill.getId() != null || !bill.getId().equals("")) {
            return bill.getId();
        }
        String id = "";
        Date dateSell = (Date) bill.getDateSell();
        DateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
        id = format.format(dateSell);
        return id;
    }

    @Override
    public void configure(Type type, Properties prprts, JdbcEnvironment je) throws MappingException {
        
    }

}

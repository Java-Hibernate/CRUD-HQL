/*
 * MIT License
 * 
 * Copyright (c) 2016 kemalsamikaraca
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.karaca.hibernateintro;

import com.karaca.hibernateintro.common.User;
import com.karaca.hibernateintro.persistence.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HibernateApp {
    
    private static Session session;
    
    public static void main(String[] args){
        
        // -1- Create SessionFactory & get Session from it
        session = HibernateUtil.getSessionFactory().openSession();
        
        // -2- Add User to Table
        registerNewUser("kemalsamikaraca" , "java-developer" , "kskaraca@gmail.com");
        
        // -3- HQL example 
        Query query = session.createQuery("from User");
        List list = query.list();
        
        // -4- Print Table
        System.out.println("\n\nJava-Hibernate CRUD-HQL example\n");
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            User user = (User)iterator.next();
            System.out.println(user.getId() + " - " + user.getUsername() + " - " + user.getEmail());
        }
        System.out.println("\nEnd of Java-Hibernate CRUD-HQL example\n");
        
        // -5- Close Connection
        HibernateUtil.shutdown(); 
        
    }
    
    
    
    /**
     * 
     * @param username
     * @param password
     * @param mail 
     */
    public static void registerNewUser(String username, String password, String mail){
        // -- 1 -- Transaction begin
        session.beginTransaction();
        
        // -- 2 -- Set user values
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(mail);
        
        // -- 3 -- Transaction commit 
        session.save(user);
        session.getTransaction().commit(); 
    }
    
}


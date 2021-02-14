package pl.edu.wszib.school.website.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.sql.*;

@Configuration
@ComponentScan("pl.edu.wszib.school.website")

public class AppConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        createDBifExist();
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }

    @Bean
    public ClassLoaderTemplateResolver secondaryTemplateResolver() {
        //dodanie folderu html
        //defaultowo templates
        ClassLoaderTemplateResolver secondaryTemplateResolver = new ClassLoaderTemplateResolver();
        secondaryTemplateResolver.setPrefix("html/");
        secondaryTemplateResolver.setSuffix(".html");
        secondaryTemplateResolver.setTemplateMode(TemplateMode.HTML);
        secondaryTemplateResolver.setCharacterEncoding("UTF-8");
        secondaryTemplateResolver.setOrder(1);
        secondaryTemplateResolver.setCheckExistence(true);

        return secondaryTemplateResolver;
    }



    public void createDBifExist(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", "root", "");
            String DBname = "schoolSite";
            if(!(checkDatabaseExist(DBname,connection))){
                String sql = "CREATE DATABASE IF NOT EXISTS "+DBname+" ;";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    public boolean checkDatabaseExist(String name, Connection connection){
        Statement statement =null;

        try {

            statement = connection.createStatement();

            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getCatalogs();

            while(resultSet.next()){
                String DBName = resultSet.getString(1).toLowerCase();
                if(DBName.equals(name.toLowerCase())){
                    System.out.println(true);
                    return true;
                }
            }

        } catch ( SQLException e) {
            e.printStackTrace();
        }

        System.out.println(false);
        return false;
    }

}

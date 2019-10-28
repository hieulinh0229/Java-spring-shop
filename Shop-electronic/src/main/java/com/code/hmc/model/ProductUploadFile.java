package com.code.hmc.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProductUploadFile  extends Product implements Validator   {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductUploadFile.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Pattern pattern = Pattern.compile("\\d*");
        ProductUploadFile productUploadFile = (ProductUploadFile)o;
        Matcher matcher = pattern.matcher(String.valueOf(productUploadFile.getPrice()));
        if(productUploadFile.getName().length()==0){
            errors.rejectValue("name","name.empty");
        }
        if(productUploadFile.getName().length()>20){
            errors.rejectValue("name","name.length");
        }
        if(productUploadFile.getDescription().length()==0){
            errors.rejectValue("description","description");
        }
        if(productUploadFile.getPrice()<0||productUploadFile.getPrice()>1000){
            errors.rejectValue("price","price");
        }
    }

    public ProductUploadFile() {
    }

    public ProductUploadFile(String name, String description, Double price, String urlPicture, int vote, Date dateUpload, Date date_update, Category category, Producer producer) {
        super(name, description, price, urlPicture, vote, dateUpload, date_update, category, producer);
    }

}

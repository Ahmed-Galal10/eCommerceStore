package com.store.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ROLE_ADMIN")
public class Admin extends  User  {


}

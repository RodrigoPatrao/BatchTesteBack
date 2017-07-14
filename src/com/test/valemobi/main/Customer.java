/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.valemobi.main;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author rodri
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    
    private String cpf_cnpj;
    public String getCpf_cnpj() {return cpf_cnpj;}
    public void setCpf_cnpj(String cpf_cnpj) {this.cpf_cnpj = cpf_cnpj;}
    
    private String nm_customer;
    public String getNm_customer() {return nm_customer;}
    public void setNm_customer(String nm_customer) {this.nm_customer = nm_customer;}
    
    private Boolean is_active;
    public Boolean getIs_active() {return is_active;}
    public void setIs_active(Boolean is_active) {this.is_active = is_active;}
    
    private int vl_total;
    public int getVl_total() {return vl_total;}
    public void setVl_total(int vl_total) {this.vl_total = vl_total;}
 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.valemobi.User[ id=" + id + " ]";
    }

       
}

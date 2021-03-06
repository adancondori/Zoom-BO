package com.vista.dato;
// Generated 10-06-2013 10:34:05 PM by Hibernate Tools 3.2.1.GA



/**
 * DetalleDeVentaId generated by hbm2java
 */
public class DetalleDeVentaId  implements java.io.Serializable {


     private int vent;
     private int prod;

    public DetalleDeVentaId() {
    }

    public DetalleDeVentaId(int vent, int prod) {
       this.vent = vent;
       this.prod = prod;
    }
   
    public int getVent() {
        return this.vent;
    }
    
    public void setVent(int vent) {
        this.vent = vent;
    }
    public int getProd() {
        return this.prod;
    }
    
    public void setProd(int prod) {
        this.prod = prod;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DetalleDeVentaId) ) return false;
		 DetalleDeVentaId castOther = ( DetalleDeVentaId ) other; 
         
		 return (this.getVent()==castOther.getVent())
 && (this.getProd()==castOther.getProd());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getVent();
         result = 37 * result + this.getProd();
         return result;
   }   


}



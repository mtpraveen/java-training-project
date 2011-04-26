//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.04.23 at 06:38:36 PM EEST 
//


package edu.XML.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="candy" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                   &lt;element name="energy" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded"/>
 *                   &lt;element name="type" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="CARAMEL"/>
 *                         &lt;enumeration value="IRIS"/>
 *                         &lt;enumeration value="CHOCOLATE_WHITH_STUFFING"/>
 *                         &lt;enumeration value="CHOCOLATE_WHITHOUT_STUFFING"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ingredients">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="water" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="suger" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="fructose" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="chocolateType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="vanillin" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="value">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="proteins" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="fats" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="carbohydrates" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="production" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "candy"
})
@XmlRootElement(name = "candies")
public class Candies {

    protected List<Candies.Candy> candy;

    /**
     * Gets the value of the candy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the candy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCandy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Candies.Candy }
     * 
     * 
     */
    public List<Candies.Candy> getCandy() {
        if (candy == null) {
            candy = new ArrayList<Candies.Candy>();
        }
        return this.candy;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *         &lt;element name="energy" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded"/>
     *         &lt;element name="type" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="CARAMEL"/>
     *               &lt;enumeration value="IRIS"/>
     *               &lt;enumeration value="CHOCOLATE_WHITH_STUFFING"/>
     *               &lt;enumeration value="CHOCOLATE_WHITHOUT_STUFFING"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ingredients">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="water" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="suger" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="fructose" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="chocolateType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="vanillin" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="value">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="proteins" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="fats" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="carbohydrates" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="production" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name",
        "energy",
        "type",
        "ingredients",
        "value",
        "production"
    })
    public static class Candy {

        @XmlElement(required = true)
        protected List<String> name;
        @XmlElement(type = Integer.class)
        protected List<Integer> energy;
        protected String type;
        @XmlElement(required = true)
        protected Candies.Candy.Ingredients ingredients;
        @XmlElement(required = true)
        protected Candies.Candy.Value value;
        @XmlElement(required = true)
        protected String production;

        /**
         * Gets the value of the name property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the name property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getName() {
            if (name == null) {
                name = new ArrayList<String>();
            }
            return this.name;
        }

        /**
         * Gets the value of the energy property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the energy property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEnergy().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Integer }
         * 
         * 
         */
        public List<Integer> getEnergy() {
            if (energy == null) {
                energy = new ArrayList<Integer>();
            }
            return this.energy;
        }

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the ingredients property.
         * 
         * @return
         *     possible object is
         *     {@link Candies.Candy.Ingredients }
         *     
         */
        public Candies.Candy.Ingredients getIngredients() {
            return ingredients;
        }

        /**
         * Sets the value of the ingredients property.
         * 
         * @param value
         *     allowed object is
         *     {@link Candies.Candy.Ingredients }
         *     
         */
        public void setIngredients(Candies.Candy.Ingredients value) {
            this.ingredients = value;
        }

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link Candies.Candy.Value }
         *     
         */
        public Candies.Candy.Value getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link Candies.Candy.Value }
         *     
         */
        public void setValue(Candies.Candy.Value value) {
            this.value = value;
        }

        /**
         * Gets the value of the production property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProduction() {
            return production;
        }

        /**
         * Sets the value of the production property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProduction(String value) {
            this.production = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="water" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="suger" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="fructose" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="chocolateType" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="vanillin" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "water",
            "suger",
            "fructose",
            "chocolateType",
            "vanillin"
        })
        public static class Ingredients {

            @XmlElement(required = true)
            protected String water;
            protected int suger;
            protected int fructose;
            @XmlElement(required = true)
            protected String chocolateType;
            protected int vanillin;

            /**
             * Gets the value of the water property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getWater() {
                return water;
            }

            /**
             * Sets the value of the water property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setWater(String value) {
                this.water = value;
            }

            /**
             * Gets the value of the suger property.
             * 
             */
            public int getSuger() {
                return suger;
            }

            /**
             * Sets the value of the suger property.
             * 
             */
            public void setSuger(int value) {
                this.suger = value;
            }

            /**
             * Gets the value of the fructose property.
             * 
             */
            public int getFructose() {
                return fructose;
            }

            /**
             * Sets the value of the fructose property.
             * 
             */
            public void setFructose(int value) {
                this.fructose = value;
            }

            /**
             * Gets the value of the chocolateType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getChocolateType() {
                return chocolateType;
            }

            /**
             * Sets the value of the chocolateType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setChocolateType(String value) {
                this.chocolateType = value;
            }

            /**
             * Gets the value of the vanillin property.
             * 
             */
            public int getVanillin() {
                return vanillin;
            }

            /**
             * Sets the value of the vanillin property.
             * 
             */
            public void setVanillin(int value) {
                this.vanillin = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="proteins" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="fats" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="carbohydrates" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "proteins",
            "fats",
            "carbohydrates"
        })
        public static class Value {

            protected int proteins;
            protected int fats;
            protected int carbohydrates;

            /**
             * Gets the value of the proteins property.
             * 
             */
            public int getProteins() {
                return proteins;
            }

            /**
             * Sets the value of the proteins property.
             * 
             */
            public void setProteins(int value) {
                this.proteins = value;
            }

            /**
             * Gets the value of the fats property.
             * 
             */
            public int getFats() {
                return fats;
            }

            /**
             * Sets the value of the fats property.
             * 
             */
            public void setFats(int value) {
                this.fats = value;
            }

            /**
             * Gets the value of the carbohydrates property.
             * 
             */
            public int getCarbohydrates() {
                return carbohydrates;
            }

            /**
             * Sets the value of the carbohydrates property.
             * 
             */
            public void setCarbohydrates(int value) {
                this.carbohydrates = value;
            }

        }

    }

}
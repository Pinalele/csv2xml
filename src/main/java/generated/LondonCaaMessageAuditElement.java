//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.10.30 at 08:08:17 da tarde WET 
//


package generated;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="londonNameElement" type="{urn:x-emc:com.bnpparibas:solar:solar:infoarchive:lama:london:1.13}CaaMessageAuditObject" maxOccurs="unbounded"/>
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
    "londonNameElement"
})
@XmlRootElement(name = "londonCaaMessageAuditElement")
public class LondonCaaMessageAuditElement {

    @XmlElement(required = true)
    protected List<CaaMessageAuditObject> londonNameElement;

    /**
     * Gets the value of the londonNameElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the londonNameElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLondonNameElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CaaMessageAuditObject }
     * 
     * 
     */
    public List<CaaMessageAuditObject> getLondonNameElement() {
        if (londonNameElement == null) {
            londonNameElement = new ArrayList<CaaMessageAuditObject>();
        }
        return this.londonNameElement;
    }

}

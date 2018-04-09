package com.niafikra.inaya.domain.idGenerator;

import com.niafikra.inaya.domain.HasName;
import com.niafikra.inaya.domain.InayaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * A String formatted ID wrapper class
 * Used to generate ID which have been formated to use 0--9 and A -- Z
 * with user preference separators such as -,|/ etc
 * <p/>
 * Author: Boniface Chacha <bonifacechacha@gmail.com>
 */
@Entity
@Table(name = "CR_FormattedID")
@Data
@EqualsAndHashCode(callSuper = true)
public class FormattedID extends InayaEntity implements HasName {

    private String name;
    private String startingID;
    private String lastGeneratedID;
    private String currentGenerateID;

    /**
     * Create a new ID specification to be generated
     *
     * @param name    name of the ID which should always be unique in the usage context
     * @param startID a starting ID which also specify the format to be used
     */
    public FormattedID(String name, String startID) {
        this.name = name;
        startingID = startID;
        lastGeneratedID = startID;
    }

    public FormattedID() {

    }

    /**
     * Generate an ID
     *
     * @return generated ID
     * @throws FormatExhaustedException when the numbers to be generated from the specified format are over
     */
    public String generate() throws FormatExhaustedException {
        currentGenerateID = new String(lastGeneratedID);
        for (int index = startingID.length() - 1; index >= 0; index--) {
            char indexedChar = currentGenerateID.charAt(index);
            if (Character.isLetter(indexedChar)) {
                if (incrementChar(index)) {

                    break;
                    //  return currentGenerateID;
                } else if (index == 0) {
                    throw new FormatExhaustedException("the format which started at " + startingID + " reached last ID which is" + lastGeneratedID);
                }
                // else continue;
            } else if (Character.isDigit(indexedChar)) {
                if (incrementInt(index)) {
                    //   if(index==0) throw new FormatExhaustedException("the format which started at "+startingID+" reached last ID which is"+lastGeneratedID);
                    break;
                } else if (index == 0) {
                    throw new FormatExhaustedException("the format which started at " + startingID + " reached last ID which is" + lastGeneratedID);
                }
                //    else continue;                
            } else {
                //throw  new UnsuportedFormatException("The format for the ID to generated is not supported:"+startingID);
            }
            //if(lastGeneratedID.equalsIgnoreCase(currentGenerateID)){
            // throw new FormatExhaustedException("the format which started at "+startingID+" reached last ID which is"+lastGeneratedID); 
            //}else
            resetStart(index);
        }
        lastGeneratedID = currentGenerateID;
        return currentGenerateID;
    }

    private boolean incrementChar(int position) {
        Character subject = getSubject(position);
        if (isLastLetterChar(subject)) {
            return false;
        }
        setOnID(position, ++subject);
        return true;
    }

    private boolean incrementInt(int position) {
        Character subject = getSubject(position);
        if (isLastIntChar(subject)) {
            return false;
        }
        setOnID(position, ++subject);
        return true;
    }

    private boolean isLastLetterChar(Character ch) {
        return ch.equals('Z');
    }

    private boolean isLastIntChar(Character ch) {
        return ch.equals('9');
    }

    private Character getSubject(int position) {
        return currentGenerateID.charAt(position);
    }

    private void setOnID(int position, Character i) {
        String left = currentGenerateID.substring(0, position);
        String right = currentGenerateID.substring(position + 1);
        currentGenerateID = left + i + right;
    }

    private void resetStart(int index) {
        for (int ind = index; ind < startingID.length(); ind++) {
            resetAt(ind);
        }
    }

    private void resetAt(int ind) {
        if (Character.isLetter(startingID.charAt(ind))) {
            setOnID(ind, 'A');
        } else if (Character.isDigit(startingID.charAt(ind))) {
            setOnID(ind, '0');
        }
    }

    public class FormatExhaustedException extends RuntimeException {

        public FormatExhaustedException(String message) {
            super(message);
        }
    }

    public String getStartingID() {
        return startingID;
    }

    public void setStartingID(String startingID) {
        this.startingID = startingID;
        lastGeneratedID = startingID;
    }
}

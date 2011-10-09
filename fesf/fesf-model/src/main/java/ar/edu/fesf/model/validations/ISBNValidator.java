package ar.edu.fesf.model.validations;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.StringTokenizer;

/*
 * <br><br><center><table border="1" width="80%"><hr> <strong> <a href="http://jkf.sourceforge.net">The JKF Project</a>
 * </strong> <p> Copyright (C) 2003 by Theodor Willax <p> This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version. <p> This library is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details. <p> You should have received a copy
 * of the <a href="http://www.gnu.org/copyleft/lesser.html"> GNU Lesser General Public License</a> along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * <hr></table></center>
 */

/**
 * Checks if a given ISBN is correct. <br>
 * What is the format of a ISBN?<br>
 * Every ISBN consists of ten digits and whenever it is printed it is preceded by the letters ISBN. The ten-digit number
 * is divided into four parts of variable length, each part separated by a hyphen. See <a
 * href="http://www.isbn.org/standards/home/isbn/us/isbnqa.asp#Q3"> www.isbn.org</a> for detailed information about a
 * isbn. <br>
 * The preceding letters "ISBN" will be left an it would be an error if the are contained in the string which should be
 * checked.
 * 
 * @version $$Revision: 1.1 $$
 * @author Theodor Willax
 */
public class ISBNValidator {

    /** Constant for validation error due to incorrect length. */
    public static final String VALIDATION_ERROR_LENGTH = "length of ISBN incorrect";

    /** Constant for validation error due to incorrect hyphen count. */
    public static final String VALIDATION_ERROR_HYPHEN_COUNT = "incorrect hyphen count in ISBN";

    /** Constant for validation error due to incorrect part composition. */
    public static final String VALIDATION_ERROR_PART_COMPOSITION = "incorrect part composition in ISBN";

    /**
     * Validates if the given <code>String</code> is a valid ISBN.
     * 
     */
    public void checkISBN(final String isbn) {
        checkArgument(isbn != null, ISBNValidator.VALIDATION_ERROR_LENGTH);
        checkArgument(this.isLengthOk(isbn), ISBNValidator.VALIDATION_ERROR_LENGTH);
        checkArgument(this.isHyphenCountOk(isbn), ISBNValidator.VALIDATION_ERROR_HYPHEN_COUNT);
        checkArgument(this.isPartCompositionOk(isbn), ISBNValidator.VALIDATION_ERROR_PART_COMPOSITION);
    }

    /**
     * Validates if the length of the given ISBN is ok. It must have 10 digits plus 3 hyphens (= 13).
     * 
     * @return true if length is ok, false otherwise.
     */
    private boolean isLengthOk(final String isbn) {
        return isbn.length() == 13;
    }

    /**
     * ISBN's must have exactly 3 hyphens. Hyphens can not be side by side, there is always at least one digit between.
     * 
     * returns true if number and position of hyphens is ok, false otherwise.
     */
    private boolean isHyphenCountOk(final String isbn) {
        // do we have 3 hyphens
        int idx = 0;
        int idxOld = 0;
        int hyphenCount = 0;
        idxOld = isbn.indexOf('-', idx);
        while (idxOld != -1) {
            // 2 hyphens side by side are not allowed
            if (idxOld + 1 == idx) {
                hyphenCount = 0;
                break;
            }
            idx = idxOld + 1;
            hyphenCount++;
            idxOld = isbn.indexOf('-', idx);
        }
        return hyphenCount == 3;
    }

    /**
     * A ISBN consists of four valid parts, each separated by a hyphen.
     * 
     * @return true if all parts are ok, false otherwise.
     */
    private boolean isPartCompositionOk(final String isbn) {
        int index = 0;
        StringTokenizer tokenizer = new StringTokenizer(isbn, "-");
        while (tokenizer.hasMoreElements()) {
            index++;
            String token = tokenizer.nextToken();
            if (index != 4) {
                try {
                    Integer.parseInt(token);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            if (token.length() != 1) {
                return false;
            }
            // the last token can be one digit or 'X'
            if (token.charAt(0) == 'X') {
                return true;
            }
            try {
                Integer.parseInt(token);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }

        return false;
    }
}
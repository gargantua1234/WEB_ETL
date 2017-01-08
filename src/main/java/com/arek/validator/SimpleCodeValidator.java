package com.arek.validator;

public class SimpleCodeValidator implements CodeValidator{

    private final int CORRECT_LENGTH  = 8;

    @Override
    public boolean validateProductCode(String productCode) {
        return codeLengthIsCorrect(productCode) && codeIsNumber(productCode);
    }

    private boolean codeIsNumber(String productCode) {
        try {
            int code = Integer.parseInt(productCode);
            return true;
        }
        catch (Exception exception){
            return false;
        }
    }

    private boolean codeLengthIsCorrect(String productCode){
        return productCode.length() == CORRECT_LENGTH;
    }
}

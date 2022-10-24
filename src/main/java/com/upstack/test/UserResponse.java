package com.upstack.test;

class UserResponse {

    private boolean result;

    public UserResponse(boolean b) {
        result = b;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}

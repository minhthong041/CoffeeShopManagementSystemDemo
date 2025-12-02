package model;

public class PaymentMethod {
    private int paymentId;
    private String methodName;

    public PaymentMethod() {
    }

    public PaymentMethod(int paymentId, String methodName) {
        this.paymentId = paymentId;
        this.methodName = methodName;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return this.methodName;
    }
    
    
}

package Entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Romanenchuk Oleg
 */

@Embeddable
public class Payment {

    @Column(name = "account_number", length = 30)
    @NotNull
    @Size(min = 1, max = 30)
    private String accountNumber;

    @Column(name = "payment_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    public Payment() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        if (!accountNumber.equals(payment.accountNumber)) return false;
        return true;

    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();

    }

    @Override
    public String toString() {
        return "Payment{" +
                "accountNumber='" + accountNumber + '\'' +
                ", paymentType=" + paymentType +
                '}';
    }
}

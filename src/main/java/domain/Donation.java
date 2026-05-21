package domain;


import java.time.LocalDate;
import java.util.Objects;


public class Donation {


    private int donation_id;

    private double amount;
    private LocalDate donation_date;
    private String donation_type; // e.g. Cash// , Online
    private String donor_name;
    private String recipient_organization;

    private Donation(Builder builder) {
        this.donation_id = builder.donation_id;
        this.amount = builder.amount;
        this.donation_date = builder.donation_date;
        this.donation_type = builder.donation_type;
        this.donor_name = builder.donor_name;
        this.recipient_organization = builder.recipient_organization;
    }

    protected Donation() {
    }

    public int getDonation_id() {
        return donation_id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDonation_date() {
        return donation_date;
    }

    public String getDonation_type() {
        return donation_type;
    }

    public String getDonor_name() {
        return donor_name;
    }

    public String getRecipient_organization() {
        return recipient_organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Donation donation = (Donation) o;

        return donation_id == donation.donation_id &&
                Double.compare(donation.amount, amount) == 0 &&
                Objects.equals(donation_date, donation.donation_date) &&
                Objects.equals(donation_type, donation.donation_type) &&
                Objects.equals(donor_name, donation.donor_name) &&
                Objects.equals(recipient_organization, donation.recipient_organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(donation_id, amount, donation_date, donation_type, donor_name, recipient_organization);
    }

    @Override
    public String toString() {
        return "Donation{" +
                "donation_id=" + donation_id +
                ", amount=" + amount +
                ", donation_date=" + donation_date +
                ", donation_type='" + donation_type + '\'' +
                ", donor_name='" + donor_name + '\'' +
                ", recipient_organization='" + recipient_organization + '\'' +
                '}';
    }

    public static class Builder {

        private int donation_id;
        private double amount;
        private LocalDate donation_date;
        private String donation_type;
        private String donor_name;
        private String recipient_organization;

        public Builder() {
        }

        public Builder setDonation_id(int donation_id) {
            this.donation_id = donation_id;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setDonation_date(LocalDate donation_date) {
            this.donation_date = donation_date;
            return this;
        }

        public Builder setDonation_type(String donation_type) {
            this.donation_type = donation_type;
            return this;
        }

        public Builder setDonor_name(String donor_name) {
            this.donor_name = donor_name;
            return this;
        }

        public Builder setRecipient_organization(String recipient_organization) {
            this.recipient_organization = recipient_organization;
            return this;
        }

        public Donation build() {
            return new Donation(this);
        }
    }
}
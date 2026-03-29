package Factory;

import domain.Donation;

import java.time.LocalDate;

public class DonationFactory {

    public static Donation createDonation(
            int donation_id,
            double amount,
            LocalDate donation_date,
            String donation_type,
            String donor_name,
            String recipient_organization
    ) {

        if (donation_id <= 0 ||
                amount <= 0 ||
                donation_date == null ||
                donation_type == null || donation_type.isEmpty() ||
                donor_name == null || donor_name.isEmpty() ||
                recipient_organization == null || recipient_organization.isEmpty()) {

            return null;
        }

        return new Donation.Builder()
                .setDonation_id(donation_id)
                .setAmount(amount)
                .setDonation_date(donation_date)
                .setDonation_type(donation_type)
                .setDonor_name(donor_name)
                .setRecipient_organization(recipient_organization)
                .build();
    }
}

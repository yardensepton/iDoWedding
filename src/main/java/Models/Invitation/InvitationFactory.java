package Models.Invitation;

public class InvitationFactory {
    public Invitation getInvitation(String sendType) {
        if (sendType == null) {
            return null;
        }
        if (sendType.equalsIgnoreCase("Invitation by Courier")) {
            return new ByCourier();

        } else if (sendType.equalsIgnoreCase("Invitation by Email")) {
            return new ByEmail();

        } else if (sendType.equalsIgnoreCase("Invitation by SMS")) {
            return new BySMS();
        }
        return null;
    }
}
